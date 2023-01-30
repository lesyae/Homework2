package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private float telescope = 14000;
    private int account = 1000;
    private float st = 2500;
    private int precentFree = 100;
    private float bank = 5;
    private float[] month = new float[120];

    private float telescopeWithContribution(){

        return telescope - account;
    }

    public float mortgageCosts (float amount, int precent){
        return (amount*precent)/100;
}

    private int countMonth(float total, float mortgageCosts, float precentBankYear) {
        float precentBankMonth = precentBankYear/12;
        int count = 0;

        while (total>0) {
            count++;
            total = (total + (total * precentBankMonth) / 100)-mortgageCosts;

            if (total > mortgageCosts) {
                month[count-1] = mortgageCosts;
            }else {
                month[count-1] = total;
            }
        }
        return count;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countOut = findViewById(R.id.countOut);
        TextView manyMonthOut = findViewById(R.id.manyMonthOut);

        int count = countMonth(telescopeWithContribution(), mortgageCosts(st, precentFree),bank);

        countOut.setText("ипотека будет выплачиваться" + countMonth(telescopeWithContribution(), mortgageCosts(st, precentFree),bank ) + "месяцев");

        String s = "";

        for (float list: month) {

            if(list>0) {
                s = s + Float.toString(list) + " монет ";

            } else {
                break;
            }

        }
        manyMonthOut.setText("первоначальный взнос: " + account + " монет, ежемесячные выплаты: " + s);
        }
    }