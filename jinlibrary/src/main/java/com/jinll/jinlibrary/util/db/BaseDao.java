package com.jinll.jinlibrary.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Jin Liang on 2015/12/6.
 */
public class BaseDao {

    private Context context;
    private DBHelper dbHelper;

    protected SQLiteDatabase writableDatabase;
    protected SQLiteDatabase readableDatabase;

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public BaseDao(Context context) {
        this.context = context;

        dbHelper = DBHelper.getInstance(context);

        writableDatabase = dbHelper.getWritableDatabase();
        readableDatabase = dbHelper.getReadableDatabase();
    }


}
