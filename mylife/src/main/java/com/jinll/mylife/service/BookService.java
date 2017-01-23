package com.jinll.mylife.service;

import android.content.Context;

import com.jinll.mylife.dao.BookDao;
import com.jinll.mylife.entity.Book;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Jin Liang on 2015/12/6.
 */
public class BookService {

    private BookDao bookDao;

    public BookService(Context context) {
        this.bookDao = new BookDao(context);
    }

    public boolean createBook(Book book){
        return this.bookDao.insert(book);
    }

    public List<Book> findAll() throws ParseException {
        return this.bookDao.findAll();
    }

    public List<Book> findByName(String name)throws ParseException{
        return this.bookDao.findByName(name);
    }
}
