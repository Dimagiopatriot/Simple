package com.vogella.android.rxjava.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        int id = v.getId();
        Intent intent;
        if (id == R.id.first){
            intent = new Intent(this, RxJavaSimpleActivity.class);
            startActivity(intent);
        }
        if (id == R.id.second){
            intent = new Intent(this, BooksActivity.class);
            startActivity(intent);
        }
        if (id == R.id.third){
            intent = new Intent(this, ColorsActivity.class);
            startActivity(intent);
        }
    }
}
