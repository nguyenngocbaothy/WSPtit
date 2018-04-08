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

public class Categories {
    private static Context context;

    public Categories(Context c) {
        this.context = c;
    }

    public void getCategories() {
        Get download =  new Get();
        download.execute("https://cuongmanh2311.000webhostapp.com/categories");

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

    public void getCategoryById(int id) {
        Get download = new Get();
        download.execute("https://cuongmanh2311.000webhostapp.com/categories/show/" + id);

        try {
            String data = download.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    // cuong chua lam xong

//    public void searchCategories(String name) {
//        Get download =  new Get();
//        download.execute("https://cuongmanh2311.000webhostapp.com/categories/search/" + name);
//
//        try {
//            String data = download.get();
//            Log.d("catesearch", data);
//            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

    public void postcategories(String name, int id, String description) {
        Post post =  new Post();
        post.execute("https://cuongmanh2311.000webhostapp.com/categories/store?name=" + name + "&parent_id=" + id + "&description=" + description);
    }

    public void updateCategories(int id, String name, int parent_id, String descripton) {
        Post post = new Post();
        post.execute("https://cuongmanh2311.000webhostapp.com/categories/update?id=" + id + "&name=" + name + "&parent_id=" + parent_id + "&description=" + descripton);
    }

    public void deleteCategories(int id) {
        Get get = new Get();
        get.execute("https://cuongmanh2311.000webhostapp.com/categories/destroy/" + id);
    }

    public void getAllChild(int id) {
        Get get = new Get();
        get.execute("https://cuongmanh2311.000webhostapp.com/categories/parent?id=" + id);

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
