package com.jinll.mylife.service;

import android.content.Context;

import com.jinll.mylife.dao.ReadLogDao;
import com.jinll.mylife.entity.ReadLog;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Jin Liang on 2016/7/31.
 */
public class ReadLogService {

    private ReadLogDao readLogDao;

    public ReadLogService(Context context) {
        this.readLogDao = new ReadLogDao(context);
    }

    public boolean createReadLog(ReadLog ReadLog){
        return this.readLogDao.insert(ReadLog);
    }

    public List<ReadLog> findAll() throws ParseException {
        return this.readLogDao.findAll();
    }
}
