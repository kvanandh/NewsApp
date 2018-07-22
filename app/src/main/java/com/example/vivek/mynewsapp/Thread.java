package com.example.vivek.mynewsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vivek on 20-07-2018.
 */

public class Thread extends AsyncTask<String,Void,String>  {
    ResponseHandler listener;
    public String url2;

    public Thread(Context context,String url1) {
        listener = (ResponseHandler)context;
        url2 = url1;
    }

    @Override
    protected void onPreExecute() {
        Log.d("Pre Execution: ","Pre Execution");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpHandler data = new HttpHandler();
        String webData = data.getData(url2,"GET","");
        Log.d("WebData",webData);
        return webData;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Post Execution: ", s);
        listener.getData(s);
        /*NewsModel model = new NewsModel();
        try {
            JSONObject mainObj = new JSONObject(s);
            JSONArray articlesArray = mainObj.getJSONArray("articles");
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject articlesObj = articlesArray.getJSONObject(i);
                model.setTitle(articlesObj.getString("title"));
                model.setDescription(articlesObj.getString("description"));
                model.setUrl(articlesObj.getString("url"));
                model.setUrl_to_image(articlesObj.getString("urlToImage"));
                model.setPublished_at(articlesObj.getString("publishedAt"));
                listener.getData(model);
            }


        } catch (JSONException e) {
            Log.d("JSON Exception",e.toString());
        }*/
        super.onPostExecute(s);
    }
}
