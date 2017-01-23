package com.jinll.mylife.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jinll.mylife.R;
import com.jinll.mylife.entity.Book;
import com.jinll.mylife.service.BookService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 选择图书<br>
 * 返回一个图书entity<br>
 * Created by Jin Liang on 2016/7/31.
 */
public class ChooseBookActivity extends Activity implements AdapterView.OnItemClickListener{

    private SearchView searchView;
    private ListView bookLv;
    private BookListAdapter bookListAdapter;
    private ChooseBookActivity this0;

    public static final int CHOOSE_BOOK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_book_choose);
        this0 = this;

        searchView = (SearchView) findViewById(R.id.book_choose_searchView);
        bookLv = (ListView) findViewById(R.id.book_choose_listView);

        searchView.setIconifiedByDefault(true);
        searchView.onActionViewExpanded();
        searchView.setFocusable(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.clearFocus();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                BookService bs = new BookService(ChooseBookActivity.this);
                try {
                    List<Book> books = bs.findByName(query);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // 图书查询方法 更新lv的结果
                bookListAdapter = new BookListAdapter(this0, R.layout.item_book_list, new ArrayList<Book>());
                bookLv.setAdapter(bookListAdapter);
                bookLv.setOnItemClickListener(this0);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BookService bs = new BookService(ChooseBookActivity.this);
                List<Book> books = null;
                try {
                    books = bs.findByName(query);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // 图书查询方法 更新lv的结果
                bookListAdapter = new BookListAdapter(this0, R.layout.item_book_list, books);
                bookLv.setAdapter(bookListAdapter);
                bookLv.setOnItemClickListener(this0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookListAdapter bookLa = (BookListAdapter)parent.getAdapter();
        Book book = bookLa.getItem(position);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("chosen_book", book);
        setResult(CHOOSE_BOOK, resultIntent);
        finish();
    }
}
