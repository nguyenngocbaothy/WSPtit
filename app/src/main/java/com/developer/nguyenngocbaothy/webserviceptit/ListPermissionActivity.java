package com.developer.nguyenngocbaothy.webserviceptit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPermissionActivity extends AppCompatActivity {

    ListView listPermission;

    JSONArray jsonArrayPermission;
    JSONObject jsonObject = null;
    ArrayList arrPermisson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_permission);
        listPermission = (ListView) findViewById(R.id.listPermission);

        arrPermisson = new ArrayList<>();

        Permission p = new Permission(ListPermissionActivity.this);
        jsonArrayPermission = p.getAllPermission();
        for(int i=0; i<jsonArrayPermission.length(); i++)
        {
            try {
                jsonObject = jsonArrayPermission.getJSONObject(i);
                arrPermisson.add(jsonObject.get("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrPermisson);
        listPermission.setAdapter(adapter);
    }
}
