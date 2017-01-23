package com.jinll.mylife.book;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jinll.jinlibrary.util.ImageUtil;
import com.jinll.mylife.R;
import com.jinll.mylife.entity.Book;

/**
 * Created by Jin Liang on 2016/7/11.
 */
public class ViewBookActivity extends Activity {

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book = (Book)getIntent().getParcelableExtra("book");

        setContentView(R.layout.m_book_view);
        ImageView coverIv = (ImageView) findViewById(R.id.bookview_iv_cover);
        coverIv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        WindowManager wm = this.getWindowManager();
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);

        AsyncTask<String, Void, Bitmap> loadImgTask = new ImageUtil.LoadBigBitmapWorkerTask(coverIv);
        loadImgTask.execute(book.getCoverUrl(), point.x+ "", point.y+"");
    }

}
