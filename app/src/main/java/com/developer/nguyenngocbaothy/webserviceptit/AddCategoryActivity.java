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

public class AddCategoryActivity extends AppCompatActivity {

    EditText edtName, edtDes;
    Button btnAdd, btnCancel;
    Spinner spinner;

    // spinner
    JSONArray jsonArray;
    ArrayList arrCate;
    ArrayList arrIdCate;
    int chooseId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        spinner = (Spinner) findViewById(R.id.spinner4);


        // spinner
        arrCate = new ArrayList<>();
        arrIdCate = new ArrayList<>();

        Categories c = new Categories(AddCategoryActivity.this);
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
        ArrayAdapter adapter = new ArrayAdapter(AddCategoryActivity.this, android.R.layout.simple_spinner_item, arrCate);
        spinner.setAdapter(adapter);

        // click item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                chooseId = Integer.parseInt((arrIdCate.get(position).toString()));
                Toast.makeText(AddCategoryActivity.this, String.valueOf(chooseId), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories c = new Categories(AddCategoryActivity.this);
                c.postcategories(
                        edtName.getText().toString().trim(),
                        chooseId,
                        edtDes.getText().toString().trim()
                );
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddCategoryActivity.this, ListCategoryActivity.class);
                startActivity(i);
            }
        });

    }
}
