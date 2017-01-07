package com.hanbit.kakao.presentation.member;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;

public class ModifyMemberActivity extends AppCompatActivity implements View.OnClickListener{
    TextView etID;
    EditText etPass, etName, etEmail, etPhone, etPhoto, etAddr;
    Button btUpdate, btCancel;

    MemberBean member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_member);

        member = new MemberBean();

        etID = (TextView) findViewById(R.id.etID);

        etPass = (EditText) findViewById(R.id.etPass);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPhoto = (EditText) findViewById(R.id.etPhoto);
        etAddr = (EditText) findViewById(R.id.etAddr);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btCancel = (Button) findViewById(R.id.btCancel);

        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        member.setId(String.valueOf(etID.getText()));
        member.setPw(String.valueOf(etPass.getText()));
        member.setName(String.valueOf(etName.getText()));
        member.setEmail(String.valueOf(etEmail.getText()));
        member.setPhone(String.valueOf(etPhone.getText()));


    }
}
