package com.jinll.mylife.entity;

/**
 * 阅读记录
 * Created by Jin Liang on 2016/7/30.
 */
public class ReadLog {

    private String id;
    private String bookId;
    private String readDate;
    private int minuteCost;   // 花费多少分钟
    private int startPage;
    private int endPage;
    private String oneComment; // 感悟一句话

    private String bookName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public int getMinuteCost() {
        return minuteCost;
    }

    public void setMinuteCost(int minuteCost) {
        this.minuteCost = minuteCost;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getOneComment() {
        return oneComment;
    }

    public void setOneComment(String oneComment) {
        this.oneComment = oneComment;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
