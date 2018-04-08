package com.developer.nguyenngocbaothy.webserviceptit;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by HOME on 08-Apr-18.
 */

public class Rent  {
    private static Context context;

    public Rent(Context c) {
        this.context = c;
    }

    public void getAllRent() {
        Get download =  new Get();
        download.execute("https://cuongmanh2311.000webhostapp.com/rent");

        try {
            String data = download.get();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Toast.makeText(context, jsonObject+"", Toast.LENGTH_LONG).show();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void postRent(int user_id, int pro_id) {
        Post post =  new Post();
        post.execute("https://cuongmanh2311.000webhostapp.com/rent/store?user_id=" + user_id + "&pro_id=" + pro_id);
    }

    // API loi
//    public void updateRent(int id) {
//        Put put =  new Put();
//        put.execute("https://cuongmanh2311.000webhostapp.com/rent/update");
//    }

    // API loi
//    public void deleteRent(int id) {
//        Delete delete =  new Delete();
//        delete.execute("https://cuongmanh2311.000webhostapp.com/destroy/" + id);
//    }

    public void getBookUser(int userId) {
        Get get = new Get();
        get.execute("https://cuongmanh2311.000webhostapp.com/rent/user?user_id=" + userId);

        try {
            String data = get.get();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Toast.makeText(context, jsonObject+"", Toast.LENGTH_LONG).show();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
