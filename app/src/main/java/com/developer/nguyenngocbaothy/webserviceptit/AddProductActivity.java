package com.developer.nguyenngocbaothy.webserviceptit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {

    EditText edtName, edtCate, edtPrice, edtIntro, edtDes;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        edtName = (EditText) findViewById(R.id.edtName);
        edtCate = (EditText) findViewById(R.id.edtCate);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtIntro = (EditText) findViewById(R.id.edtIntro);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product(AddProductActivity.this);
                p.postProduct(
                        edtName.getText().toString().trim(),
                        Integer.parseInt(edtCate.getText().toString().trim()),
                        Integer.parseInt(edtPrice.getText().toString().trim()),
                        edtIntro.getText().toString().trim(),
                        edtDes.getText().toString().trim()
                );
            }
        });

    }
}
