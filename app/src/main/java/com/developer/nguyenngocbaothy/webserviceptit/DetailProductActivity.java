package com.developer.nguyenngocbaothy.webserviceptit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.developer.nguyenngocbaothy.webserviceptit.R.id.imageView;

public class DetailProductActivity extends AppCompatActivity {
    EditText edtName, edtCategory, edtPrice, edtIntro, edtDes;
    Button btnUpdate, btnSave;
    ImageView img;
    Spinner spinner;

    String url = "https://cuongmanh2311.000webhostapp.com";
    String id;

    JSONArray jsonArray;
    ArrayList arrCate;
    ArrayList arrIdCate;
    int chooseId = 0;

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
        spinner = (Spinner) findViewById(R.id.spinner2);

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
//            Categories c = new Categories(DetailProductActivity.this);
//            c.getCategoryById(Integer.parseInt(obj.get("cate_id").toString()));
            edtPrice.setText(obj.get("price").toString());
            edtIntro.setText(obj.get("intro").toString());
            edtDes.setText(obj.get("description").toString());

            Picasso.with(getBaseContext()).load(obj.get("image").toString()).into(img);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        arrCate = new ArrayList<>();
        arrIdCate = new ArrayList<>();
        Categories c = new Categories(DetailProductActivity.this);
        jsonArray = c.getCategories();
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                //Toast.makeText(this, jsonObject.get("name")+"", Toast.LENGTH_SHORT).show();
                arrCate.add(jsonObject.get("name"));
                arrIdCate.add(jsonObject.get("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(DetailProductActivity.this, android.R.layout.simple_spinner_item, arrCate);
        spinner.setAdapter(adapter);
        // click item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                //chooseId = Integer.parseInt((arrIdCate.get(position).toString()));
                Toast.makeText(DetailProductActivity.this, arrIdCate.get(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
