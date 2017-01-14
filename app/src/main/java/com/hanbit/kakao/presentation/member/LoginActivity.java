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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etID, etPass;
    Button btLogin, btCancel;

    MemberService service;
    MemberBean member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etID = (EditText) findViewById(R.id.etID);
        etPass = (EditText) findViewById(R.id.etPass);

        btLogin = (Button) findViewById(R.id.btLogin);
        btCancel = (Button) findViewById(R.id.btCancel);
        btLogin.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        service = new MemberServiceImpl(this.getApplicationContext());
        member = new MemberBean();
    }

    @Override
    public void onClick(View v) {
        String id = etID.getText().toString();
        String pass = etPass.getText().toString();
        Log.d("DB_ID:",id);
        Log.d("DB_Pass:",pass);

        member.setId(id);
        member.setPw(pass);

        switch (v.getId()){
            case R.id.btLogin:
                boolean flag =  service.login(member);

                if(flag){
                    Toast.makeText(this,"LOGIN SUCCESS",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MemberListActivity.class));
                }else{
                    Toast.makeText(this,"LOGIN FAIL",Toast.LENGTH_SHORT).show();
                    etID.setText("");
                    etPass.setText("");
                }

                break;
            case R.id.btCancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
