package com.hanbit.kakao.presentation.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;
import com.hanbit.kakao.service.MemberService;
import com.hanbit.kakao.service.MemberServiceImpl;

public class ModifyMemberActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvID;
    EditText etPass, etName, etEmail, etPhone, etPhoto, etAddr;
    Button btUpdate, btCancel;

    MemberBean member;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_member);

        member = new MemberBean();

        service = new MemberServiceImpl(this.getApplicationContext());
        Intent intent = this.getIntent();

        String id = intent.getExtras().getString("id");
        member = service.searchById(id);

        tvID = (TextView) findViewById(R.id.tvID);
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

        tvID.setText(member.getId());
        etPass.setHint(member.getPw());
        etName.setHint(member.getName());
        etEmail.setHint(member.getEmail());
        etPhone.setHint(member.getPhone());
        etPhoto.setHint(member.getPhoto());
        etAddr.setHint(member.getAddr());
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.btUpdate:
                MemberBean tmp = new MemberBean();
                tmp.setId(tvID.getText().toString());
                tmp.setPw((etPass.getText().toString().equals("")) ? member.getPw() : etPass.getText().toString());
                tmp.setName((etName.getText().toString().equals("")) ? member.getName() : etName.getText().toString());
                tmp.setEmail((etEmail.getText().toString().equals("")) ? member.getEmail() : etEmail.getText().toString());
                tmp.setPhone((etPhone.getText().toString().equals("")) ? member.getPhone() : etPhone.getText().toString());
                tmp.setPhoto((etPhoto.getText().toString().equals("")) ? member.getPhoto() : etPhoto.getText().toString());
                tmp.setAddr((etAddr.getText().toString().equals("")) ? member.getAddr() : etAddr.getText().toString());

                service.modify(tmp);
                break;

            case R.id.btCancel:
                break;
        }

        intent = new Intent(ModifyMemberActivity.this, MemberDetailActivity.class);
        intent.putExtra("id", member.getId());
        startActivity(intent);
    }
}
