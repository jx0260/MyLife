package com.jinll.mylife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jinll.jinlibrary.util.db.BaseDao;
import com.jinll.mylife.entity.ReadLog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jin Liang on 2015/12/6.
 */
public class ReadLogDao extends BaseDao {

    private String tableName = "readlog";

    public ReadLogDao(Context context) {
        super(context);
    }

    public boolean insert(ReadLog readlog){
        ContentValues v = new ContentValues();
        v.put("id", readlog.getId());
        v.put("book_id", readlog.getBookId());
        v.put("read_date", readlog.getReadDate());
        v.put("minute_cost", readlog.getMinuteCost());
        v.put("start_page", readlog.getStartPage());
        v.put("end_page", readlog.getEndPage());
        v.put("one_comment", readlog.getOneComment());
        return this.writableDatabase.insert(tableName, null, v) > -1;
    }

    public List<ReadLog> findAll() throws ParseException {
        List<ReadLog> list = new ArrayList<>();
        Cursor c = this.readableDatabase.rawQuery(
                "select rl.*, b.chinese_name from readlog rl " +
                "left join book b on rl.book_id = b.id "
                , null);
        if(c.getCount()>0){
            while(c.moveToNext()){
                ReadLog rl = new ReadLog();
                rl.setId(c.getString(0));
                rl.setBookId(c.getString(1));
                rl.setReadDate(c.getString(2));
                rl.setMinuteCost(c.getInt(3));
                rl.setStartPage(c.getInt(4));
                rl.setEndPage(c.getInt(5));
                rl.setOneComment(c.getString(6));
                rl.setBookName(c.getString(7));
                list.add(rl);
            }
        }
        return list;
    }
}
