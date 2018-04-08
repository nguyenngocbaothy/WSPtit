package com.developer.nguyenngocbaothy.webserviceptit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Categories categories = new Categories(MainActivity.this);
        categories.getAllChild(3);

    }


}
