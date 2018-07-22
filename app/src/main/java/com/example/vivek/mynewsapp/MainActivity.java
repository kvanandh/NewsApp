package com.example.vivek.mynewsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*To Do
* change contentDescription in imageView with dynamic description @string resource
*
* */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ResponseHandler {
    Button toiNews;
    ImageView imageNews;
    TextView titleNews;
    String urlGetNews;
    LinearLayout linear;
    ScrollView scrollView;
    Button abcNews;
    Button bbcNews;
    Button cbcNews;
    String urlABCNews;
    String urlBBCNews;
    String urlCBCNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toiNews = findViewById(R.id.toiNews);
        imageNews = findViewById(R.id.imageNews);
        titleNews = findViewById(R.id.titleNews);
        linear = findViewById(R.id.linear);
        abcNews = findViewById(R.id.abcNews);
        bbcNews = findViewById(R.id.bbcNews);
        cbcNews = findViewById(R.id.cbcNews);

        toiNews.setOnClickListener(this);
        abcNews.setOnClickListener(this);
        bbcNews.setOnClickListener(this);
        cbcNews.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toiNews:
                linear.removeAllViews();
                urlGetNews = Constants.urlNews+Constants.TOINews+Constants.appid;
                Thread thread = new Thread(MainActivity.this,urlGetNews);
                thread.execute();
                break;
            case R.id.abcNews:
                linear.removeAllViews();
                urlGetNews = Constants.urlNews+Constants.ABCNews+Constants.appid;
                Thread thread1 = new Thread(MainActivity.this,urlGetNews);
                thread1.execute();
                break;
            case R.id.bbcNews:
                linear.removeAllViews();
                urlGetNews = Constants.urlNews+Constants.BBCNews+Constants.appid;
                Thread thread2 = new Thread(MainActivity.this,urlGetNews);
                thread2.execute();
                break;
            case R.id.cbcNews:
                linear.removeAllViews();
                urlGetNews = Constants.urlNews+Constants.CBCNews+Constants.appid;
                Thread thread3 = new Thread(MainActivity.this,urlGetNews);
                thread3.execute();
                break;
            default:

                break;

        }

    }
    public void getData(String s) {
        if (s.contains("{\"status\":\"ok\","))
        {
        parseJSONData(s);
        }
        //else parseImageData(s);
    }

    private void parseImageData(String s) {
        Bitmap image = loadImage(s);
        imageNews.setImageBitmap(image);
    }

    private void parseJSONData(String s) {
        NewsModel model = new NewsModel();
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
                //listener.getData(model);
                updateUI(model);
            }


        } catch (JSONException e) {
            Log.d("JSON Exception",e.toString());
        }
    }

    ;

    public void getData(NewsModel model){
        updateUI(model);
    }

    private void updateUI(NewsModel model) {
        TextView textTitle = new TextView(MainActivity.this);
        TextView textDescription = new TextView(MainActivity.this);
        TextView textPublishedAt = new TextView(MainActivity.this);
        textTitle.setTextSize(20);
        textDescription.setTextSize(14);
        textPublishedAt.setTextSize(10);
        textTitle.append(model.getTitle());
        textDescription.append(model.getDescription());
        textPublishedAt.append(model.getPublished_at());
        textPublishedAt.setPadding(0,0,0,10);
        /*TextView textView = new TextView(MainActivity.this);
        textView.setTextSize(12);
        textView.setVerticalScrollBarEnabled(true);
        textView.append(model.getTitle());
        textView.append("\n");
        textView.append(model.getDescription());
        textView.append("\n");*/
        linear.addView(textTitle);
        linear.addView(textDescription);
        ImageView imageView = new ImageView(MainActivity.this);
        //imageView.setMaxHeight(200);
        Picasso.get().load(model.getUrl_to_image()).into(imageView);

        linear.addView(imageView);
        linear.addView(textPublishedAt);
        final String urlWebView = model.getUrl();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView webView = new WebView(MainActivity.this);
                linear.addView(webView);
                webView.loadUrl(urlWebView);

            }
        });

        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView webView = new WebView(MainActivity.this);
                linear.addView(webView);
                ProgressBar progressBar = new ProgressBar(MainActivity.this);
                progressBar.getProgress();
                webView.loadUrl(urlWebView);

            }
        });

        textDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView webView = new WebView(MainActivity.this);
                linear.addView(webView);
                webView.loadUrl(urlWebView);

            }
        });


        //Picasso.get().load(model.getUrl_to_image()).resize(400,300).into(imageView);

        //textView.append(model.getPublished_at());
        //linear.canScrollVertically(0);
        //linear.addView(textView);
        /*titleNews.append(model.getTitle());
        titleNews.append("\n");
        titleNews.append(model.getDescription());
        titleNews.append("\n");
        titleNews.append(model.getUrl());
        titleNews.append("\n");
        titleNews.append(model.getUrl_to_image());
        titleNews.append("\n");
        //Thread thread = new Thread(MainActivity.this,model.getUrl_to_image());
        //thread.execute();
        String imagePath = model.getUrl_to_image();
        //Bitmap imageBitmap = loadImage(MainActivity.this,imagePath);
        //imageNews.setImageBitmap(imageBitmap);
        Picasso.get().load(imagePath).resize(250,250).into(imageNews);
        titleNews.append(model.getPublished_at());
        titleNews.append("\n");
        titleNews.append("\n");*/

    }

    private Bitmap loadImage(String imagePath) {
        Bitmap bitmap = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(imagePath));
            //FileInputStream fileInputStream = new FileInputStream(imagePath);
            //bitmap = BitmapFactory.decodeStream(imagePath.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
