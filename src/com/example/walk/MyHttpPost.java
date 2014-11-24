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

public class MyHttpPost extends AsyncTask<Data, Void, Void>
        implements
            HttpRequestHandler {
    private AsyncTaskInterface _interface;
    private Data _Data;
    boolean Debug =true;

    public MyHttpPost(AsyncTaskInterface ati) {
        super();
        this._interface = ati;
    }

    @Override
    protected  Void doInBackground(Data... arg) {
        _Data = arg[0];
        if (Debug){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
            _Data.ID = 500;
            return null;
        }

        // 送るデータをリストに入力
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", Integer.toString(_Data.ID)));
        params.add(new BasicNameValuePair("latitude", Integer.toString(_Data.Latitude)));
        params.add(new BasicNameValuePair("longitude", Integer.toString(_Data.Longitude)));

        // ポスト通信先のurlを入力してHttpPostインスタンスの生成
        HttpPost post = new HttpPost("10.29.31.145");// "http://kanai.k.ac.jp/TEAM-B");

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
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line);
                    }

//                    return Integer.parseInt(buf.toString());
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
        _interface.callback(_Data);
    }

    @Override
    public void handle(HttpRequest arg0, HttpResponse arg1, HttpContext arg2)
            throws HttpException, IOException {
    }
}
