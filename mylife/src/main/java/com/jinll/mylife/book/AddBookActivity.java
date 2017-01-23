package com.jinll.mylife.book;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinll.jinlibrary.util.UUid;
import com.jinll.mylife.R;
import com.jinll.mylife.entity.Book;
import com.jinll.mylife.service.BookService;

import java.io.File;
import java.io.IOException;
import java.util.Date;



/**
 * 添加图书
 * Created by Jin Liang on 2015/10/24.
 */
public class AddBookActivity extends Activity implements View.OnClickListener{

    private EditText etName;
    private EditText etChineseName;
    private EditText etAuthor;
    private EditText etPublisher;
    private EditText etISBN;
    private Button btnTakePhoto;
    private Button btnChoosePic;
    private Button btnSave;
    private ImageView ivCover;

    private Book book = new Book();
    private File photoFile = null;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    static final int REQUEST_TAKE_PHOTO = 2;
    String mCurrentPhotoPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_book_add);

        initWidgets();
        bindListeners();
    }

    private void initWidgets() {
        etName = (EditText) findViewById(R.id.bookadd_edit_name);
        etChineseName = (EditText) findViewById(R.id.bookadd_edit_chineseName);
        etAuthor = (EditText) findViewById(R.id.bookadd_edit_author);
        etPublisher = (EditText) findViewById(R.id.bookadd_edit_publisher);
        etISBN = (EditText) findViewById(R.id.bookadd_edit_isbn);

        btnTakePhoto = (Button) findViewById(R.id.bookadd_btn_takePhoto);
        btnChoosePic = (Button) findViewById(R.id.bookadd_btn_choosePic);
        btnSave = (Button) findViewById(R.id.bookadd_btn_save);
        ivCover = (ImageView) findViewById(R.id.bookadd_iv_cover);
    }

    private void bindListeners() {
        btnTakePhoto.setOnClickListener(this);
        btnChoosePic.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bookadd_btn_takePhoto:
                dispatchTakePictureIntent();
                break;
            case R.id.bookadd_btn_save:
                Book book = new Book();
                book.setId(UUid.id());
                book.setName(etName.getText().toString());
                book.setChineseName(etChineseName.getText().toString());
                book.setAuthor(etAuthor.getText().toString());
                book.setPublisher(etPublisher.getText().toString());
                book.setIsbn(etISBN.getText().toString());
                book.setCoverUrl(photoFile.getAbsolutePath());
                book.setCreateTime(new Date());

                BookService bookService = new BookService(this);
                if(bookService.createBook(book)) {
                    Toast.makeText(this, "图书保存成功！", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        this.finish();
                    }
                } else {
                    Toast.makeText(this, "图书保存失败！", Toast.LENGTH_SHORT).show();
                }


        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            // Create the File where the photo should go
            try {
                photoFile = com.jinll.jinlibrary.util.ImageUtil.createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            String filePath = photoFile.getAbsolutePath();
            com.jinll.jinlibrary.util.ImageUtil.galleryAddPic(this, filePath);
            com.jinll.jinlibrary.util.ImageUtil.setPic(ivCover, filePath);
        }
    }


}