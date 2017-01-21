package com.hanbit.kakao.presentation.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;
import com.hanbit.kakao.presentation.message.WriteMessageActivity;
import com.hanbit.kakao.service.MemberService;
import com.hanbit.kakao.service.MemberServiceImpl;
import com.hanbit.kakao.util.Phone;

public class MemberDetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvID, tvPass, tvName, tvEmail, tvPhone, tvPhoto, tvAddr;
    Button btCall, btMap, btMessage, btUpdate, btDelete, btList;
    Phone phone;
    ImageView ivPhoto;

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
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

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


        //이미지 테스트를 해봐야 함(확장자가 있는 경우 에러)
        String tmpImg = member.getPhoto().substring(0, member.getPhoto().indexOf("."));
        Log.d("tmpImg fileName", tmpImg);

        //getPackageName() : com.hanbit.kakao로 지칭
        int img = getResources().getIdentifier(this.getPackageName()+":drawable/" + tmpImg, null, null);
        Log.d("Photo", member.getPhoto());
        ivPhoto.setImageDrawable(getResources().getDrawable(img, getApplicationContext().getTheme()));

        btCall.setOnClickListener(this);
        btMap.setOnClickListener(this);
        btMessage.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.btCall:
                Log.d("Phone Call", member.getPhone());

                //phone.directCall(member.getPhone());
                phone.dial(member.getPhone());
                break;
            case R.id.btMap:
                //임시로 주소정보 설정(신촌역)
                member.setAddr("37.5597680,126.9423080");

                //구글맵 연동
                intent = new Intent(MemberDetailActivity.this, MapsActivity.class);
                intent.putExtra("position", member.getAddr());
                startActivity(intent);

                break;
            case R.id.btMessage:
                //this.startActivity(new Intent(this, WriteMessageActivity.class));

                intent = new Intent(MemberDetailActivity.this, WriteMessageActivity.class);
                intent.putExtra("phone", member.getPhone());
                startActivity(intent);

                break;
            case R.id.btUpdate:
                intent = new Intent(MemberDetailActivity.this, ModifyMemberActivity.class);
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
