package com.example.myintentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.annotation.SuppressLint;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvResult;
    private int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = (Button)findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);
        Button btnMoveWithDataActivity = (Button)findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);
        Button btnMoveWithObject = (Button)findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);
        Button btnDialPhone = (Button)findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);
        Button btnMoveForResult = (Button)findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Teknologi & Aplikasi Mobile");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 1);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_move_activity_object:
                Person mPerson = new Person();
                mPerson.setName("Teknologi & Aplikasi Mobile");
                mPerson.setAge(4);
                mPerson.setEmail("oktatoyibah23@gmail.com");
                mPerson.setCity("Palembang");
                Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithOjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithOjectActivity.EXTRA_PERSON, mPerson);
                startActivity(moveWithObjectIntent);
                break;
            case R.id.btn_dial_number:
                String phoneNumber = "081234567890";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL);
                dialPhoneIntent.setData(Uri.parse("no. NP: "+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent (MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText("Hasil: "+selectedValue);
            }
        }
    }
}