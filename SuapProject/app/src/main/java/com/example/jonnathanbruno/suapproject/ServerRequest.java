package com.example.jonnathanbruno.suapproject;

import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class ServerRequest {

    public void vai(){

    }

    public JSONObject requestWebServiceLogin(String serviceUrl,String login,String senha) {
        disableConnectionReuseIfNecessary();
        HttpURLConnection urlConnection = null;
        String postParameters =	"username="+login+"&password="+senha+"";

        try {
            URL urlToRequest =	new	URL(serviceUrl);
            urlConnection =	(HttpURLConnection)	urlToRequest.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConnection.setFixedLengthStreamingMode(postParameters.getBytes().length);
            PrintWriter out	= new PrintWriter(urlConnection.getOutputStream());
            out.print(postParameters);
            out.close();

            int statusCode = urlConnection.getResponseCode();
            InputStream in	=	new BufferedInputStream(urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        }	catch	(MalformedURLException e)	{
        }	catch	(SocketTimeoutException e)	{
        }	catch	(IOException e)	{
        }	catch	(JSONException e)	{
        }	finally {
            if (urlConnection !=	null)	{
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private static void disableConnectionReuseIfNecessary()	 {
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO)	{
            System.setProperty("http.keepAlive",	"false");
        }
    }
    private static String getResponseText(InputStream inStream)	{
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
