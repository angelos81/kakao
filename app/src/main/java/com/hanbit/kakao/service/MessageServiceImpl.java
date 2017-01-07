package com.hanbit.kakao.service;

import android.content.Context;

import com.hanbit.kakao.dao.MessageDao;
import com.hanbit.kakao.domain.MessageBean;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2017-01-07.
 */

public class MessageServiceImpl implements MessageService {
    MessageDao dao;

    public MessageServiceImpl(Context applicationContext) {
        dao = new MessageDao(applicationContext);
    }

    @Override
    public void write(MessageBean msg) {
        dao.insert(msg);
    }

    @Override
    public ArrayList<MessageBean> list() {
        return dao.selectAll();
    }

    @Override
    public ArrayList<MessageBean> findByWriter(String id) {
        return dao.selectByWriter(id);
    }

    @Override
    public MessageBean findBySeq(int seq) {
        return dao.selectBySeq(seq);
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public int countByWriter(String id){
        return dao.countByWriter(id);
    }

    @Override
    public void updateMessage(MessageBean msg) {
        dao.update(msg);
    }

    @Override
    public void delegetMessage(int seq) {
        dao.deleget(seq);
    }
}
