package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.net.Credentials;
import android.os.Build;
import android.util.Base64;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class ServerRequest {


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


    public String requestWebServiceDados(String serviceUrl,String token, String login) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resposta = null;
        try {
            URL url = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("content-type","application/json");
            urlConnection.setRequestProperty("user",login);
            urlConnection.setRequestProperty("connection", "keep-alive");
            urlConnection.setRequestProperty("Authorization","Basic MjAxMzEwMTQwNDAxNDU6Y2xhcmluaGE5OUBA");
            urlConnection.setRequestProperty("X-CSRFToken",token);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            resposta = buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resposta;
    }

    public String requestWebServiceNotasAluno(String serviceUrl,String token, String login) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resposta = null;
        try {
            URL url = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("content-type","application/json");
            urlConnection.setRequestProperty("user",login);
            urlConnection.setRequestProperty("connection", "keep-alive");
            urlConnection.setRequestProperty("Authorization","Basic MjAxMzEwMTQwNDAxNDU6Y2xhcmluaGE5OUBA");
            urlConnection.setRequestProperty("X-CSRFToken",token);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            resposta = buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resposta;
    }

    public String requestWebServiceTurmasVirtuais(String serviceUrl,String token, String login) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resposta = null;
        try {
            URL url = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("content-type","application/json");
            urlConnection.setRequestProperty("user",login);
            urlConnection.setRequestProperty("connection", "keep-alive");
            urlConnection.setRequestProperty("Authorization","Basic MjAxMzEwMTQwNDAxNDU6Y2xhcmluaGE5OUBA");
            urlConnection.setRequestProperty("X-CSRFToken",token);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            resposta = buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resposta;
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
