package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.Scanner;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String ketqualuu;
    private Button lichsu;

    private ArrayList<String> luutruls = new ArrayList<>();
    private static final String STATE_COUNTER = "counter";
    private static final String STATE_LS = "lichsu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnDivide = findViewById(R.id.buttonDivide);
        Button btnMultiply = findViewById(R.id.buttonMultiply);
        Button btnMinus = findViewById(R.id.buttonMinus);
        Button btnEqual = findViewById(R.id.buttonEqual);
        Button btnEXP = findViewById(R.id.buttonExp);
        lichsu = findViewById(R.id.LS);
        Button[] btn = {button0, button1,button2,button3,button4,button5,button6,button7,button8,button9};
        String[] dau = {"/","-","+","*"};
        Button[] nutdau = {btnDivide, btnMinus, btnPlus, btnMultiply};

        if(savedInstanceState != null){
            ketqualuu = savedInstanceState.getString(STATE_COUNTER,"");
            resultTextView.setText(ketqualuu);
            luutruls = savedInstanceState.getStringArrayList(STATE_LS);
        }

        for (int i = 0; i < nutdau.length; i++){
            String dautam = dau[i];
            nutdau[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultTextView.setText(resultTextView.getText() + dautam);
                }
            });
        }
        for(int i = 0; i < btn.length; i++){
             int index = i;
            btn[i].setOnClickListener(new View.OnClickListener(){
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v){
                    resultTextView.setText(resultTextView.getText() + String.valueOf(index));
                }
            });
        }
        btnEXP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resultTextView.setText("");
            }
        });
       btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultTextView.getText() == ""){
                    resultTextView.setText("ERROR");
                }
                else{
                    String lt = "";
                    lt += String.valueOf(resultTextView.getText());
                    String[] postfix = Infix.chuyendoi(String.valueOf(resultTextView.getText()));
                    double ketqua = Infix.tinhtoan(postfix);
                    lt += " = "+ String.valueOf(ketqua);
                    resultTextView.setText(String.valueOf(ketqua));
                    luutruls.add(lt);

                }

            }
        });
       lichsu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), LichSuTinhToan.class);
                intent.putStringArrayListExtra("LS",luutruls);
                //luutruls.clear();
                startActivity(intent);
           }
       });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_COUNTER,String.valueOf(resultTextView.getText()));
        outState.putStringArrayList(STATE_LS,luutruls);
    }
}