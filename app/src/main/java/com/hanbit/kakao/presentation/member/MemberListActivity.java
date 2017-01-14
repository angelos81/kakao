package com.hanbit.kakao.presentation.member;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hanbit.kakao.R;
import com.hanbit.kakao.domain.MemberBean;
import com.hanbit.kakao.service.MemberService;
import com.hanbit.kakao.service.MemberServiceImpl;
import com.hanbit.kakao.util.MemberAdapter;

import java.util.ArrayList;

public class MemberListActivity extends AppCompatActivity {
    MemberService service;
    ListView lv_member_list;
    final String[] arr = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        service = new MemberServiceImpl(this.getApplicationContext());
        lv_member_list = (ListView) findViewById(R.id.lv_member_list);

        ArrayList<MemberBean> list = service.list();
        Log.d("Service return Count", String.valueOf(list.size()));

        lv_member_list.setAdapter(new MemberAdapter(list, this));

        //리스트 짧게 누를시 상세 이동
        lv_member_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = lv_member_list.getItemAtPosition(i);
                MemberBean member = (MemberBean) obj;

                Toast.makeText(MemberListActivity.this, "Select Name:"+member.getName(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MemberListActivity.this, MemberDetailActivity.class);
                intent.putExtra("id", member.getId());  //Map이라고 생각하면 됨
                startActivity(intent);
            }
        });


        //리스트 길게 누를시 삭제 처리
        lv_member_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = lv_member_list.getItemAtPosition(i);
                MemberBean member = (MemberBean) obj;
                arr[0] = member.getId();        //서로 다른 화면이기 때문에 변수가 공유 되지 못하므로 static 변수로 처리

                new AlertDialog.Builder(MemberListActivity.this).setTitle("delete ???").setMessage("정말로 삭제?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        service.unregist(arr[0]);


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

                return true;
            }
        });
    }
}
