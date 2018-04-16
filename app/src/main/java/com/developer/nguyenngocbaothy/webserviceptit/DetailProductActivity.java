package com.developer.nguyenngocbaothy.webserviceptit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.developer.nguyenngocbaothy.webserviceptit.R.id.imageView;

public class DetailProductActivity extends AppCompatActivity {
    EditText edtName, edtCategory, edtPrice, edtIntro, edtDes;
    Button btnUpdate, btnSave;
    ImageView img;

    String url = "https://cuongmanh2311.000webhostapp.com";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        edtName = (EditText) findViewById(R.id.edtName);
        edtCategory = (EditText) findViewById(R.id.edtCate);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtIntro = (EditText) findViewById(R.id.edtIntro);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnSave = (Button) findViewById(R.id.btnSave);
        img = (ImageView) findViewById(imageView);

        edtName.setEnabled(false);
        edtCategory.setEnabled(false);
        edtPrice.setEnabled(false);
        edtIntro.setEnabled(false);
        edtDes.setEnabled(false);

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("productDetail"));
            id = obj.get("id").toString();
            edtName.setText(obj.get("name").toString());
            edtCategory.setText(obj.get("cate_id").toString());
            edtPrice.setText(obj.get("price").toString());
            edtIntro.setText(obj.get("intro").toString());
            edtDes.setText(obj.get("description").toString());

            Picasso.with(getBaseContext()).load(obj.get("image").toString()).into(img);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setEnabled(true);
                edtPrice.setEnabled(true);
                edtCategory.setEnabled(true);
                edtIntro.setEnabled(true);
                edtDes.setEnabled(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product(DetailProductActivity.this);
                p.updateProduct(
                        Integer.parseInt(id),
                        edtName.getText().toString(),
                        Integer.parseInt(edtCategory.getText().toString()),
                        Integer.parseInt(edtPrice.getText().toString()),
                        edtIntro.getText().toString(),
                        edtDes.getText().toString()
                );

                edtName.setEnabled(false);
                edtCategory.setEnabled(false);
                edtPrice.setEnabled(false);
                edtIntro.setEnabled(false);
                edtDes.setEnabled(false);
            }
        });
    }
}
