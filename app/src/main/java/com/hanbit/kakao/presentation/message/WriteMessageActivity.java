package com.hanbit.kakao.presentation.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hanbit.kakao.R;

public class WriteMessageActivity extends AppCompatActivity {
    WebView mWebView;
    WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        Intent intent = this.getIntent();
        String phone = intent.getExtras().getString("phone");

        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.addJavascriptInterface(new JavascriptInterface(this), "Hybrid");   //message_write.html안의 스크립트쪽 이름과 같도록 설정 : Hybrid
        mWebView.loadUrl("file:///android_asset/www/message_write.html");


    }
}
