package com.hanbit.kakao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.kakao.presentation.member.LoginActivity;
import com.hanbit.kakao.presentation.member.MemberListActivity;
import com.hanbit.kakao.presentation.member.RegistMemberActivity;
import com.hanbit.kakao.util.BackPressCloseHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btRegist, menu_login, menu_help;
    LinearLayout help_center;
    TextView menu_home;
    View mCustomView;
    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu_home = (TextView) findViewById(R.id.menu_home);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        mCustomView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_custombar, null);
        actionBar.setCustomView(mCustomView);

        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        btRegist = (Button) findViewById(R.id.btRegist);
        btRegist = (Button) findViewById(R.id.btRegist);
        menu_login = (Button) mCustomView.findViewById(R.id.menu_login);
        menu_help = (Button) mCustomView.findViewById(R.id.menu_help);

        btRegist.setOnClickListener(this);
        menu_login.setOnClickListener(this);
        menu_help.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btRegist:
                this.startActivity(new Intent(this, RegistMemberActivity.class));
                break;
            case R.id.menu_login:
                this.startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menu_help:
                //this.startActivity(new Intent(this, null));
                //임시로 작성
                this.startActivity(new Intent(this, MemberListActivity.class));
                Toast.makeText(this, "kakao story...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
