package com.supervideo.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gacyou on 2017/10/17.
 */

public class Utils {

    //獲取Json資訊
    public static String getJson(String urlString) throws Exception {
        URL url =new URL(urlString);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        int resp = urlConnection.getResponseCode();

        String rJson = "";

        if (resp == HttpURLConnection.HTTP_OK) {
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                rJson = new String(readFully(in), "UTF-8");
            } catch (Exception e) {
                rJson = "";
            }
        }
        urlConnection.disconnect();
        return rJson;
    }

    static byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toByteArray();
    }
    //獲取Json資訊結束

}
