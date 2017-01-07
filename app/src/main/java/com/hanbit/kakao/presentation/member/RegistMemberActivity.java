package com.hanbit.kakao.presentation.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hanbit.kakao.MainActivity;
import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;
import com.hanbit.kakao.service.MemberService;
import com.hanbit.kakao.service.MemberServiceImpl;

public class RegistMemberActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etID, etPass, etName, etEmail, etPhone, etPhoto, etAddr;
    Button btJoin, btCancel;

    MemberBean member;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_member);

        etID = (EditText) findViewById(R.id.etID);
        etPass = (EditText) findViewById(R.id.etName);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPhoto = (EditText) findViewById(R.id.etPhoto);
        etAddr = (EditText) findViewById(R.id.etAddr);

        btJoin = (Button) findViewById(R.id.btJoin);
        btCancel = (Button) findViewById(R.id.btCancel);

        member = new MemberBean();
        service = new MemberServiceImpl(this.getApplicationContext());

        btJoin.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String id = String.valueOf(etID.getText());
        String pass = String.valueOf(etPass.getText());
        String name = String.valueOf(etName.getText());
        String email = String.valueOf(etEmail.getText());
        String phone = String.valueOf(etPhone.getText());
        String photo = String.valueOf(etPhoto.getText());
        String addr = String.valueOf(etAddr.getText());

        Log.d("MemberBean.toString -> ", member.toString());

        member.setId(id);
        member.setPw(pass);
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);
        member.setPhoto(photo);
        member.setAddr(addr);

        switch (view.getId()){
            case R.id.btJoin:
                service.regist(member);

                Toast.makeText(this, "Regist Success...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));

                break;
            case R.id.btCancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
