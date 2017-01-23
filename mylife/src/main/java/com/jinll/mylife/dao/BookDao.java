package com.jinll.mylife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jinll.jinlibrary.util.db.BaseDao;
import com.jinll.mylife.entity.Book;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jin Liang on 2015/12/6.
 */
public class BookDao extends BaseDao {

    private String tableName = "book";

    public BookDao(Context context) {
        super(context);
    }

    public boolean insert(Book book){
        ContentValues v = new ContentValues();
        v.put("id", book.getId());
        v.put("name", book.getName());
        v.put("chinese_name", book.getChineseName());
        v.put("author", book.getAuthor());
        v.put("publisher", book.getPublisher());
        v.put("isbn", book.getIsbn());
        v.put("cover_url", book.getCoverUrl());
        v.put("create_time", sdf.format(book.getCreateTime()));
        return this.writableDatabase.insert(tableName, null, v) > -1;
    }

    public List<Book> findAll() throws ParseException {
        List<Book> list = new ArrayList<>();
        Cursor c = this.readableDatabase.rawQuery("select * from book", null);
        if(c.getCount()>0){
            while(c.moveToNext()){
                Book b = new Book();
                b.setId(c.getString(0));
                b.setName(c.getString(1));
                b.setChineseName(c.getString(2));
                b.setAuthor(c.getString(3));
                b.setPublisher(c.getString(4));
                b.setIsbn(c.getString(5));
                b.setCoverUrl(c.getString(6));
                b.setCreateTime(sdf.parse(c.getString(7)));
                list.add(b);
            }
        }
        return list;
    }

    public List<Book> findByName(String name)throws ParseException{
        List<Book> list = new ArrayList<>();
        Cursor c = this.readableDatabase.rawQuery("select * from book where chinese_name like ?", new String[]{"%"+name+"%"});
        if(c.getCount()>0){
            while(c.moveToNext()){
                Book b = new Book();
                b.setId(c.getString(0));
                b.setName(c.getString(1));
                b.setChineseName(c.getString(2));
                b.setAuthor(c.getString(3));
                b.setPublisher(c.getString(4));
                b.setIsbn(c.getString(5));
                b.setCoverUrl(c.getString(6));
                b.setCreateTime(sdf.parse(c.getString(7)));
                list.add(b);
            }
        }
        return list;
    }
}
