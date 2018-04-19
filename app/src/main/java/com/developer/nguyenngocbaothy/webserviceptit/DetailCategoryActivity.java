package com.developer.nguyenngocbaothy.webserviceptit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class DetailCategoryActivity extends AppCompatActivity {

    EditText edtName, edtDes;
    Button btnUpdate, btnSave, btnCancel;
    Spinner spinner;

    String id;

    // spinner
    JSONArray jsonArray;
    ArrayList arrCate;
    ArrayList arrIdCate;
    int chooseId = 0;
    Object cateId;
    int indexCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        spinner = (Spinner) findViewById(R.id.spinner3);

        edtName.setEnabled(false);
        spinner.setEnabled(false);
        edtDes.setEnabled(false);

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("categoryDetail"));
            id = obj.get("id").toString();
            edtName.setText(obj.get("name").toString());
            cateId = obj.get("parent_id").toString();
            edtDes.setText(obj.get("description").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // spinner
        arrCate = new ArrayList<>();
        arrIdCate = new ArrayList<>();
        Categories c = new Categories(DetailCategoryActivity.this);
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

        indexCate = arrIdCate.indexOf(cateId);
//        Toast.makeText(this, cateId+"", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, indexCate+" is index", Toast.LENGTH_LONG).show();
        Collections.swap(arrCate, indexCate, 0);
        Collections.swap(arrIdCate, indexCate, 0);

        ArrayAdapter adapter = new ArrayAdapter(DetailCategoryActivity.this, android.R.layout.simple_spinner_item, arrCate);
        spinner.setAdapter(adapter);
        // click item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                //chooseId = Integer.parseInt((arrIdCate.get(position).toString()));
                chooseId = Integer.parseInt(arrIdCate.get(position).toString());
                Toast.makeText(DetailCategoryActivity.this, String.valueOf(chooseId), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setEnabled(true);
                spinner.setEnabled(true);
                edtDes.setEnabled(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories c = new Categories(DetailCategoryActivity.this);
                c.updateCategories(
                        Integer.parseInt(id),
                        edtName.getText().toString().trim(),
                        chooseId,
                        edtDes.getText().toString().trim()
                );

                edtName.setEnabled(false);
                spinner.setEnabled(false);
                edtDes.setEnabled(false);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailCategoryActivity.this, ListCategoryActivity.class);
                startActivity(i);
            }
        });

    }
}
