package com.jinll.mylife.readlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jinll.mylife.R;
import com.jinll.mylife.entity.ReadLog;

import java.util.List;

/**
 * 阅读记录列表 适配
 * Created by Jin Liang on 2015/12/6.
 */
public class ReadLogListAdapter extends ArrayAdapter<ReadLog> {

    private final LayoutInflater mInflater;
    private int mResource;

    public ReadLogListAdapter(Context context, int resource, List<ReadLog> readLogs) {
        super(context, resource, readLogs);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.bookNameTv = (TextView) view.findViewById(R.id.readlog_list_book_name_tv);
            viewHolder.dateTv = (TextView) view.findViewById(R.id.readlog_list_date_tv);
            viewHolder.minuteTv = (TextView) view.findViewById(R.id.readlog_list_minute_tv);
            viewHolder.pageTv = (TextView) view.findViewById(R.id.readlog_list_page_tv);
            viewHolder.oneCommentTv = (TextView) view.findViewById(R.id.readlog_list_onecomment_tv);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        ReadLog item = getItem(position);
        viewHolder.bookNameTv.setText(item.getBookName());
        viewHolder.dateTv.setText(item.getReadDate());
        viewHolder.minuteTv.setText(item.getMinuteCost()+"分钟");
        viewHolder.pageTv.setText("page: "+item.getStartPage()+"~"+item.getEndPage());
        viewHolder.oneCommentTv.setText(item.getOneComment());
        return view;
    }

    class ViewHolder{
        TextView bookNameTv;
        TextView dateTv;
        TextView minuteTv;
        TextView pageTv;
        TextView oneCommentTv;
    }
}
