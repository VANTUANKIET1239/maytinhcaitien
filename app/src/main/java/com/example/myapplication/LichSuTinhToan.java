package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LichSuTinhToan extends AppCompatActivity {

    private ListView lvtinhtoan;
    private ArrayList<String> lstt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_tinh_toan);
         lvtinhtoan = findViewById(R.id.lichsu);
        Intent intent = getIntent();
        lstt = intent.getStringArrayListExtra("LS");
        String[] lstt2 = lstt.toArray(new String[lstt.size()]);

        ArrayAdapter<String> tinhtoan = new ArrayAdapter<>(this,R.layout.list_item,lstt2);
        lvtinhtoan.setAdapter(tinhtoan);
    }
}