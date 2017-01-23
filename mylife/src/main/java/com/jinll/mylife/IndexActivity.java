package com.jinll.mylife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.jinll.mylife.book.BookListActivity;
import com.jinll.mylife.readlog.ReadLogListActivity;
import com.jinll.mylife.test.ProgressBarTestActivity;

/**
 * Created by Jin Liang on 2015/10/23.
 */
public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListBook;
    private ActionBar actionbar;
    private Button btnHideActionBar;

    private Button listReadLogBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_index);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        initWidgets();
    }

    private void initWidgets() {
        btnListBook = (Button) findViewById(R.id.btn_list_book);
        btnHideActionBar = (Button)findViewById(R.id.btn_hide_actionbar);

        btnListBook.setOnClickListener(this);
        btnHideActionBar.setOnClickListener(this);

        listReadLogBtn = (Button) findViewById(R.id.btn_list_readlog);
        listReadLogBtn.setOnClickListener(this);

        actionbar = getSupportActionBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list_book:
                Intent i1 = new Intent();
                i1.setClass(this, BookListActivity.class);
                startActivity(i1);
                break;
            case R.id.btn_hide_actionbar:
                actionbar.hide();
                break;
            case R.id.btn_list_readlog:
                Intent toReadLogIntent = new Intent();
                toReadLogIntent.setClass(this, ReadLogListActivity.class);
                startActivity(toReadLogIntent);
                break;
            case R.id.btn_progressbar:
                Intent in = new Intent();
                in.setClass(this, ProgressBarTestActivity.class);
                startActivity(in);
        }
    }
}