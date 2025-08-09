package com.messaging.byor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private static String Url="http://10.0.2.2:4000/get-token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.startTransaction);
        editText=findViewById(R.id.amount);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient=new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json;charset=utf-8");
                JSONObject actualdata=new JSONObject();
                try{
                    actualdata.put("amount",editText.getText().toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                RequestBody body=RequestBody.create(JSON,actualdata.toString());
                Request request=new Request.Builder().url(Url).post(body).build();
                try{
                    Response response=okHttpClient.newCall(request).execute();
                    Log.d("Response :",response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }


}