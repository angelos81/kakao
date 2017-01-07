package com.hanbit.kakao.presentation.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hanbit.kakao.R;
import com.hanbit.kakao.service.MessageService;
import com.hanbit.kakao.service.MessageServiceImpl;

public class WriteMessageActivity extends AppCompatActivity {

    MessageService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        service = new MessageServiceImpl(this.getApplicationContext());
    }
}
