package com.example.walk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import android.os.AsyncTask;
import android.util.Log;

public class MyHttpPost extends AsyncTask<Void, Void, Void>
        implements
            HttpRequestHandler {
    private AsyncTaskInterface _interface;
    private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    String[] send = new String[3];
    boolean Debug =false;
    HttpPost post;

    public MyHttpPost(AsyncTaskInterface ati,String var1,String value1,String var2,String value2,String var3,String value3 , String URI) {
        super();
        this._interface = ati;
        if((var1!=null)&&(value1!=null))
        params.add(new BasicNameValuePair(var1, value1));
        if((var2!=null)&&(value2!=null))
        params.add(new BasicNameValuePair(var2, value2));
        if((var3!=null)&&(value3!=null))        
        params.add(new BasicNameValuePair(var3, value3));
        
        // ポスト通信先のurlを入力してHttpPostインスタンスの生成        
        post = new HttpPost(URI);
    }

    public MyHttpPost(AsyncTaskInterface ati, NameValuePair[] pair, String URI) {
        super();
        this._interface = ati;
        post = new HttpPost(URI);
        for(NameValuePair i:pair)
            params.add(i);
    }

    @Override
    protected  Void doInBackground(Void... arg) {
    	if(true){return null;}

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            // 配列をutf-8でエンコードした結果得られたentityを引数としている
            // そのentityをpostにセット
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            // ポスト通信を行い、返答をresにセット
            HttpResponse res = httpClient.execute(post);
            if (res.getStatusLine().getStatusCode() < 400) {// HttpStatus.SC_OK
                // 返答をinputstream型として受け取る?
                InputStream content = res.getEntity().getContent();
                // inputStream内部01データをバイトごとに読み文字としてreaderに入れる?
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(content));
                try {
//                    StringBuilder buf = new StringBuilder();
                    String line;
                    int i =0;
                    while((line = reader.readLine())!=null){
                        send[i] = line;
                        i++;
                    }
//                    send[0] = reader.readLine();
//                    send[1] = reader.readLine();
//                    send[2] = reader.readLine();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // streamとreaderを閉じる
                    content.close();
                    reader.close();
                }
            } else if (res.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                Log.d("ayncTask", "404 Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // クライアントを終了させる(メモリリークを防ぐ)
            httpClient.getConnectionManager().shutdown();
        }
//        return 0;
        return null;
    }

    @Override
    public void onPostExecute(Void result) {
        _interface.callback(send);
    }

    @Override
    public void handle(HttpRequest arg0, HttpResponse arg1, HttpContext arg2)
            throws HttpException, IOException {
    }
}
