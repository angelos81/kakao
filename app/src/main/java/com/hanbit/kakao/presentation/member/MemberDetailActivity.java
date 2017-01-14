package com.hanbit.kakao.presentation.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;
import com.hanbit.kakao.service.MemberService;
import com.hanbit.kakao.service.MemberServiceImpl;
import com.hanbit.kakao.util.Phone;

public class MemberDetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvID, tvPass, tvName, tvEmail, tvPhone, tvPhoto, tvAddr;
    Button btCall, btMap, btMessage, btUpdate, btDelete, btList;
    Phone phone;

    MemberService service;
    MemberBean member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        service = new MemberServiceImpl(this.getApplicationContext());
        Intent intent = this.getIntent();

        String id = intent.getExtras().getString("id");

        member = service.searchById(id);
        phone = new Phone(this, this);

        tvID = (TextView) findViewById(R.id.tvID);
        tvPass = (TextView) findViewById(R.id.tvPass);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPhoto = (TextView) findViewById(R.id.tvPhoto);
        tvAddr = (TextView) findViewById(R.id.tvAddr);

        btCall = (Button) findViewById(R.id.btCall);
        btMap = (Button) findViewById(R.id.btMap);
        btMessage = (Button) findViewById(R.id.btMessage);
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btList = (Button) findViewById(R.id.btList);

        tvID.setText(member.getId());
        tvPass.setText(member.getPw());
        tvName.setText(member.getName());
        tvEmail.setText(member.getEmail());
        tvPhone.setText(member.getPhone());
        tvPhoto.setText(member.getPhoto());
        tvAddr.setText(member.getAddr());


        btCall.setOnClickListener(this);
        btMap.setOnClickListener(this);
        btMessage.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCall:
                Log.d("Phone Call", member.getPhone());

                phone.directCall("tel:010-9075-3070");
                break;
            case R.id.btMap:

                break;
            case R.id.btMessage:

                break;
            case R.id.btUpdate:
                Intent intent = new Intent(MemberDetailActivity.this, ModifyMemberActivity.class);
                intent.putExtra("id", member.getId());
                startActivity(intent);
                break;
            case R.id.btDelete:
                service.unregist(member.getId());
                this.startActivity(new Intent(this, MemberListActivity.class));

                break;
            case R.id.btList:
                this.startActivity(new Intent(this, MemberListActivity.class));

                break;
        }

    }
}
