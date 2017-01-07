package com.hanbit.kakao.service;

import android.content.Context;

import com.hanbit.kakao.dao.MemberDao;
import com.hanbit.kakao.domain.MemberBean;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2017-01-07.
 */

public class MemberServiceImpl implements MemberService {
    MemberDao dao;
    MemberBean session;

    public MemberServiceImpl(Context applicationContext) {
        dao = new MemberDao(applicationContext);
    }


    @Override
    public void regist(MemberBean param) {
        dao.insert(param);
    }

    @Override
    public ArrayList<MemberBean> list() {
        return dao.selectAll();
    }

    @Override
    public ArrayList<MemberBean> searchByName(String name) {
        return dao.selectByName(name);
    }

    @Override
    public MemberBean searchById(String id) {
        return dao.selectById(id);
    }

    @Override
    public boolean login(MemberBean param) {
        boolean flag = false;
        session = dao.login(param);

        if(session.getId().equals("fail")){
            flag = false;
        }else{
            flag = true;
        }

        return flag;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void modify(MemberBean param) {

    }

    @Override
    public void unregist(String id) {

    }
}
