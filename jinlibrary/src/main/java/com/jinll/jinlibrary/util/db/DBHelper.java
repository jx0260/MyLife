package com.jinll.jinlibrary.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jin Liang on 2015/12/6.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;

    private static DBHelper instance;

    private DBHelper(Context context, String dbName) {
        super(context, dbName+".db", null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context){
        if(instance==null){
            instance = new DBHelper(context, "mylife");
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        List<String> createTableSql = new ArrayList<>();

        String createSqlBook = "CREATE TABLE book ( \n" +
                "    id           CHAR( 36 )      PRIMARY KEY,\n" +
                "    name         VARCHAR( 100 ),\n" +
                "    chinese_name VARCHAR( 100 )  NOT NULL,\n" +
                "    author       VARCHAR( 100 )  NOT NULL,\n" +
                "    publisher    VARCHAR( 100 )  NOT NULL,\n" +
                "    isbn         VARCHAR( 25 )   NOT NULL,\n" +
                "    cover_url    VARCHAR( 300 )  NOT NULL,\n" +
                "    create_time  DATETIME        NOT NULL \n" +
                ");";

        createTableSql.add(createSqlBook);

        String createSqlReadLog = "CREATE TABLE readlog( \n" +
                "    id           CHAR( 36 )      PRIMARY KEY,\n" +
                "    book_id      CHAR( 36 )      NOT NULL,\n" +
                "    read_date    VARCHAR(10)     NOT NULL,\n " +
                "    minute_cost  INT             NOT NULL,\n " +
                "    start_page    INT             NOT NULL,\n " +
                "    end_page      INT             NOT NULL,\n " +
                "    one_comment   VARCHAR( 300 )  NOT NULL\n" +
                ");";
        createTableSql.add(createSqlReadLog);

        Iterator<String> iterator = createTableSql.iterator();
        while (iterator.hasNext()){
            db.execSQL(iterator.next());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
