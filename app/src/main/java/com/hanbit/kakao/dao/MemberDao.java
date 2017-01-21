package com.hanbit.kakao.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hanbit.kakao.domain.MemberBean;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2017-01-07.
 */

public class MemberDao extends SQLiteOpenHelper{
    public MemberDao(Context applicationContext) {
        //sqlite가 자동으로 만들어 주는 것은 null로 처리하고 버전 1인 hanbit.db를 만들어라
        super(applicationContext, "hanbit.db", null, 1);
        this.getWritableDatabase();     //생성한 데이터베이스에 read, write 속성을 부여
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Member(\n" +
                "\tid TEXT PRIMARY KEY,\n" +
                "    pw TEXT,\n" +
                "    name TEXT,\n" +
                "    email TEXT,\n" +
                "    phone TEXT,\n" +
                "    photo TEXT,\n" +
                "    addr TEXT\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS Message(\n" +
                "\t_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    sender TEXT,\n" +
                "    receiver TEXT,\n" +
                "    content TEXT,\n" +
                "    writeTime TEXT,\n" +
                "    id TEXT,\n" +
                "    FOREIGN KEY(id) REFERENCES Member(id)\n" +
                ");");

/*        db.execSQL("INSERT INTO Member(id, pw, name, email, phone, photo, addr)\n" +
                "VALUES('hong', '1', '홍길동', 'hong@test.com', '010-1234-5678', 'cupcake.jpg', '서울');");
        db.execSQL("INSERT INTO Member(id, pw, name, email, phone, photo, addr)\n" +
                "VALUES('kim', '1', '김유신', 'kim@test.com', '010-1234-5678', 'donut.jpg', '서울');");
        db.execSQL("INSERT INTO Member(id, pw, name, email, phone, photo, addr)\n" +
                "VALUES('yumi', '1', '유미', 'yumi@test.com', '010-1234-5678', 'eclair.jpg', '서울');");
        db.execSQL("INSERT INTO Member(id, pw, name, email, phone, photo, addr)\n" +
                "VALUES('lee', '1', '이순신', 'lee@test.com', '010-1234-5678', 'froyo.jpg', '서울');");
        db.execSQL("INSERT INTO Member(id, pw, name, email, phone, photo, addr)\n" +
                "VALUES('kang', '1', '강강술래', 'kang@test.com', '010-1234-5678', 'icecream.jpg', '서울');");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void insert(MemberBean param) {                       //createMember
        String sql = "INSERT\n" +
                    "  INTO MEMBER(id, pw, name, email, phone, photo, addr)\n" +
                    "VALUES('"+param.getId()+"', '"+param.getPw()+"', '"+param.getName()+"', '"+param.getEmail()+"', '"+param.getPhone()+"', '"+param.getPhoto()+"', '"+param.getAddr()+"');";
        SQLiteDatabase db = this.getWritableDatabase();     //나에게 있는 hanbit.db를 writable형태로 가져와라
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<MemberBean> selectAll() {                        //realAll
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
        String sql = "SELECT id, pw, name, email, phone, photo, addr FROM Member;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor != null){
            Log.d("Member", "Exist~!!!");

            cursor.moveToFirst();
        }

        //일반적인 while문을 적용시 첫번째 cursor의 값을 무시하므로 do~while문을 사용해야 함
        do{
            MemberBean member = new MemberBean();
            member.setId(cursor.getString(0));
            member.setPw(cursor.getString(1));
            member.setName(cursor.getString(2));
            member.setEmail(cursor.getString(3));
            member.setPhone(cursor.getString(4));
            member.setPhoto(cursor.getString(5));
            member.setAddr(cursor.getString(6));

            list.add(member);
        }while (cursor.moveToNext());

        Log.d("Member Count", String.valueOf(list.size()));

        return list;
    }

    public ArrayList<MemberBean> selectByName(String name) {     //readGroup
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
        String sql = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return list;
    }

    public MemberBean selectById(String id) {                    //readOne
        MemberBean member = new MemberBean();
        String sql = "SELECT id, pw, name, email, phone, photo, addr FROM Member WHERE id = '"+id+"';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToNext()){
            member.setId(cursor.getString(0));
            member.setPw(cursor.getString(1));
            member.setName(cursor.getString(2));
            member.setEmail(cursor.getString(3));
            member.setPhone(cursor.getString(4));
            member.setPhoto(cursor.getString(5));
            member.setAddr(cursor.getString(6));
        }else{
            member.setId("fail");
        }

        return member;
    }

    public MemberBean login(MemberBean param) {                     //login
        MemberBean member = new MemberBean();
        String sql = "SELECT id, pw, name, email, phone, photo, addr FROM Member WHERE id = '"+param.getId()+"' AND pw = '"+param.getPw()+"';";
        Log.d("Login_SQL : ", sql);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToNext()){
            member.setId(cursor.getString(0));
            member.setPw(cursor.getString(1));
            member.setName(cursor.getString(2));
            member.setEmail(cursor.getString(3));
            member.setPhone(cursor.getString(4));
            member.setPhoto(cursor.getString(5));
            member.setAddr(cursor.getString(6));
        }else{
            member.setId("fail");
        }

        Log.d("Login_result : ", member.getId());

        return member;
    }

    public int count() {                                         //readCount
        int count = 0;
        String sql = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return count;
    }

    public void update(MemberBean param) {                       //updateMember
        String sql = "UPDATE MEMBER\n" +
                    "   SET pw = '"+param.getPw()+"',\n" +
                    "       name = '"+param.getName()+"',\n" +
                    "       email = '"+param.getEmail()+"',\n" +
                    "       phone = '"+param.getPhone()+"',\n" +
                    "       photo = '"+param.getPhoto()+"',\n" +
                    "       addr = '"+param.getAddr()+"'\n" +
                    "WHERE id = '"+param.getId()+"';";
        SQLiteDatabase db = this.getWritableDatabase();     //나에게 있는 hanbit.db를 writable형태로 가져와라
        db.execSQL(sql);
        db.close();
    }

    public void delete(String id) {                            //deleteMember
        String sql = "DELETE\n" +
                    "  FROM MEMBER\n" +
                    " WHERE id = '"+id+"';";
        SQLiteDatabase db = this.getWritableDatabase();     //나에게 있는 hanbit.db를 writable형태로 가져와라
        db.execSQL(sql);
        db.close();
    }

}
