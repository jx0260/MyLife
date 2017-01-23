package com.jinll.mylife.readlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.jinll.mylife.R;
import com.jinll.mylife.entity.ReadLog;
import com.jinll.mylife.service.ReadLogService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 阅读记录列表
 * Created by Jin Liang on 2015/12/6.
 */
public class ReadLogListActivity extends AppCompatActivity {

    private ListView lvReadLogList;
    private ReadLogListAdapter readlogListAdapter;

    private ReadLogService readlogService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_readlog_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.readlog_list_toolbar);
        setSupportActionBar(myToolbar);

        readlogService = new ReadLogService(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_readlog_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_new_readlog:
                    Intent i = new Intent();
                    i.setClass(ReadLogListActivity.this, AddReadLogActivity.class);
                    startActivity(i);
                    return true;
                default:
                    return true;
            }
        }
    };

    private void initView() {
        lvReadLogList = (ListView)this.findViewById(R.id.readlog_list_lv);
        readlogListAdapter = new ReadLogListAdapter(this, R.layout.item_readlog_list, new ArrayList<ReadLog>());
        lvReadLogList.setAdapter(readlogListAdapter);

        List<ReadLog> readlogList = null;
        try {
            readlogList = readlogService.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(readlogList!=null && !readlogList.isEmpty()){
            readlogListAdapter.addAll(readlogList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_readlog:
                Intent i = new Intent();
                i.setClass(this, AddReadLogActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}