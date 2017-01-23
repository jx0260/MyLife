package com.jinll.mylife.readlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jinll.jinlibrary.util.UUid;
import com.jinll.mylife.R;
import com.jinll.mylife.book.ChooseBookActivity;
import com.jinll.mylife.entity.Book;
import com.jinll.mylife.entity.ReadLog;
import com.jinll.mylife.service.ReadLogService;

/**
 * 添加 阅读记录
 * Created by Jin Liang on 2016/7/30.
 */
public class AddReadLogActivity extends Activity implements View.OnClickListener {

    private EditText bookIdEt;
    private EditText dateEt;
    private EditText minuteEt;
    private EditText startPageEt;
    private EditText endPageEt;
    private EditText onecommentEt;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.m_readlog_add);

        bookIdEt = (EditText) findViewById(R.id.readlogadd_edit_bookId);
        bookIdEt.setOnClickListener(this);

        dateEt = (EditText) findViewById(R.id.readlogadd_edit_date);
        minuteEt = (EditText) findViewById(R.id.readlogadd_edit_minute);
        startPageEt = (EditText) findViewById(R.id.readlogadd_edit_startpage);
        endPageEt = (EditText) findViewById(R.id.readlogadd_edit_endpage);
        onecommentEt = (EditText) findViewById(R.id.readlogadd_edit_onecomment);
        saveBtn = (Button) findViewById(R.id.bookadd_btn_save);
        saveBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.readlogadd_edit_bookId:
                Intent chooseBookIntent = new Intent();
                chooseBookIntent.setClass(this, ChooseBookActivity.class);
                startActivityForResult(chooseBookIntent, ChooseBookActivity.CHOOSE_BOOK);
                break;
            default:
                ReadLog rl = new ReadLog();
                rl.setId(UUid.id());
                rl.setBookId((String) bookIdEt.getTag());
                rl.setReadDate(dateEt.getText().toString());
                rl.setMinuteCost(Integer.valueOf(minuteEt.getText().toString()));
                rl.setStartPage(Integer.valueOf(startPageEt.getText().toString()));
                rl.setEndPage(Integer.valueOf(endPageEt.getText().toString()));
                rl.setOneComment(onecommentEt.getText().toString());

                ReadLogService rls = new ReadLogService(this);
                if(rls.createReadLog(rl)){
                    Toast.makeText(this, "阅读记录保存成功！", Toast.LENGTH_LONG).show();
                    this.finish();
                } else {
                    Toast.makeText(this, "阅读记录保存失败！", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ChooseBookActivity.CHOOSE_BOOK){
            // 取出选择的图书的信息
            Book book = data.getParcelableExtra("chosen_book");
            bookIdEt.setText(book.getChineseName());
            bookIdEt.setTag(book.getId());
        }
    }
}
