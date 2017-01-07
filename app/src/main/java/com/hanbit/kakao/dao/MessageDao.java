package com.hanbit.kakao.dao;

import android.content.Context;

import com.hanbit.kakao.domain.MessageBean;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2017-01-07.
 */

public class MessageDao {
    public MessageDao(Context applicationContext) {

    }

    public void insert(MessageBean msg){

    }

    public ArrayList<MessageBean> selectAll(){
        ArrayList<MessageBean> list = new ArrayList<MessageBean>();

        return list;
    }

    public ArrayList<MessageBean> selectByWriter(String id){
        ArrayList<MessageBean> list = new ArrayList<MessageBean>();

        return list;
    }

    public MessageBean selectBySeq(int seq){
        MessageBean message = new MessageBean();

        return message;
    }

    public int count(){
        int count = 0;

        return count;
    }

    public int countByWriter(String id){
        int count = 0;

        return count;
    }

    public void update(MessageBean msg){

    }

    public void deleget(int seq){

    }
}
