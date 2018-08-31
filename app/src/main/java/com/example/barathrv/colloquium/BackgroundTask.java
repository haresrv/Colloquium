package com.example.barathrv.colloquium;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        String reg_url="http://192.168.42.58/webapp/register.php";
        String log_url="http://192.168.42.58/webapp/login.php";
        String method=voids[0];
if(method.equals("register"))
{
    String name=voids[1];
    String user_name=voids[2];
    String user_pass=voids[3];
    try {
        URL url=new URL(reg_url);
        HttpURLConnection httpurlConnection=(HttpURLConnection)url.openConnection();
        httpurlConnection.setRequestMethod("POST");
        httpurlConnection.setDoOutput(true);
        OutputStream OS=httpurlConnection.getOutputStream();
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
        System.out.print("ab");
        String data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
        bw.write(data);
        bw.flush();
        bw.close();
        OS.close();
        InputStream IS=httpurlConnection.getInputStream();
        IS.close();
        System.out.print("a");
        return "REGISTRATION SUCCESS";
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
        return "WRONG";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        Toast.makeText(ctx,aVoid, Toast.LENGTH_LONG).show();

    }
}
