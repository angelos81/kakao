package com.hanbit.kakao.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2017-01-14.
 */

public class MemberAdapter extends BaseAdapter {
    ArrayList<MemberBean> list;     //DB에서 받는 값
    LayoutInflater inflater;        //DB에서 받는 값을 부풀리는 녀석
    private int[] photos = {
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop
    };

    public MemberAdapter(ArrayList<MemberBean> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup vg) {
        ViewHolder holder;

        if(v == null){
            v = inflater.inflate(R.layout.member_list, null);
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) v.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) v.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }



        return v;
    }

    //Inner class
    //static 을 준 이유 : getter, setter 쓰기 귀찮아서...
    static class ViewHolder{
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPhone;
    }
}
