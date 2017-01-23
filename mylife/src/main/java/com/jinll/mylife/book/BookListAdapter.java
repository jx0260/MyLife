package com.jinll.mylife.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinll.jinlibrary.util.ImageUtil;
import com.jinll.mylife.R;
import com.jinll.mylife.entity.Book;

import java.util.List;

/**
 * 图书列表 适配
 * Created by Jin Liang on 2015/12/6.
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    private final LayoutInflater mInflater;
    private int mResource;

    public BookListAdapter(Context context, int resource, List<Book> bookList) {
        super(context, resource, bookList);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }

        ImageView img = (ImageView)view.findViewById(R.id.item_booklist_img);
        TextView chineseName = (TextView)view.findViewById(R.id.item_booklist_chinese_name);
        TextView author = (TextView)view.findViewById(R.id.item_booklist_author);

        Book item = getItem(position);
        ImageUtil.setPic(img, 80, 120, item.getCoverUrl());
        chineseName.setText(item.getChineseName());
        author.setText(item.getAuthor());
        return view;
    }
}
