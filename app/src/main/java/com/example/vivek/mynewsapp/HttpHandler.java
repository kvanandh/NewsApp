package com.example.vivek.mynewsapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vivek on 20-07-2018.
 */

public class HttpHandler {
    String Tag = HttpHandler.this.getClass().getName();
    static StringBuffer stringBuffer;

    public String getData(String url, String httpMethod, String requestBody) {
        HttpURLConnection connection = null;
        int responseCode = 0;

        Log.d(Tag,"URL: "+ url +" Method: "+ httpMethod +" Request Body: " + requestBody);


            try {
                if (httpMethod.equals("GET")) {
                    URL urls = new URL(url);
                    connection = (HttpURLConnection) urls.openConnection();
                    connection.setRequestMethod(httpMethod);
                    connection.connect();
                    responseCode = connection.getResponseCode();
                }
                if (connection!=null){
                    StringBuffer stringBuffer = new StringBuffer();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = bufferedReader.readLine())!=null){
                        stringBuffer.append(line);
                        stringBuffer.append("\r");
                    }
                    bufferedReader.close();
                    if (stringBuffer!=null){
                        return stringBuffer.toString();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d(Tag,"Response"+stringBuffer.toString());
                return stringBuffer.toString();

            }
            return null;

        }

}
