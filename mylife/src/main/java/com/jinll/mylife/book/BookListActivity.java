package com.jinll.mylife.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
 * 图书列表
 * Created by Jin Liang on 2015/12/6.
 */
public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvBookList;
    private BookListAdapter bookListAdapter;

    private BookService bookService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_book_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        bookService = new BookService(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_new_book:
                    Intent i = new Intent();
                    i.setClass(BookListActivity.this, AddBookActivity.class);
                    startActivity(i);
                    return true;
                default:
                    return true;
            }
        }
    };

    private void initView() {
        lvBookList = (ListView)this.findViewById(R.id.booklist_lv_main);
        bookListAdapter = new BookListAdapter(this, R.layout.item_book_list, new ArrayList<Book>());
        lvBookList.setAdapter(bookListAdapter);
        lvBookList.setOnItemClickListener(this);

        List<Book> bookList = null;
        try {
            bookList = bookService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(bookList!=null && !bookList.isEmpty()){
            bookListAdapter.addAll(bookList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_book:
                Intent i = new Intent();
                i.setClass(this, AddBookActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookListAdapter bookListAdapter = (BookListAdapter)parent.getAdapter();
        Book book = bookListAdapter.getItem(position);

        Intent viewBookIntent = new Intent();
        viewBookIntent.setClass(BookListActivity.this, ViewBookActivity.class);
        viewBookIntent.putExtra("book", book);
        startActivity(viewBookIntent);
    }
}