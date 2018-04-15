package com.developer.nguyenngocbaothy.webserviceptit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String url = "https://cuongmanh2311.000webhostapp.com";

    Button btnProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProduct = (Button) findViewById(R.id.btnProduct);

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListProduct.class);
                startActivity(i);

            }
        });

//        Users users = new Users(MainActivity.this);
//        users.search("Bin");

    }


}
