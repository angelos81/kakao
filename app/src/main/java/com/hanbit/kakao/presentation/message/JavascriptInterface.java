package com.hanbit.kakao.presentation.message;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by hb2009 on 2017-01-21.
 */

public class JavascriptInterface {
    Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    //Toast
    @android.webkit.JavascriptInterface
    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    //sd 카드 저장
    @android.webkit.JavascriptInterface
    public void savePreferences(String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);  //SD카드에 저장할때 사용
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //진동
    @android.webkit.JavascriptInterface
    public void vibrate(long milliseconds){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milliseconds);
    }

    //SMS 전송
    @android.webkit.JavascriptInterface
    public void sendSMS(String phone, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, message, null, null);

    }
}
