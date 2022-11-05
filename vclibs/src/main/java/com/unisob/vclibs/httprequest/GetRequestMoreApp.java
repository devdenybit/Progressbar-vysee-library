package com.unisob.vclibs.httprequest;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class GetRequestMoreApp {


    private Context context;
    private HttpGetResponsable httpGetResponsable;
   private String  file = "";

    public GetRequestMoreApp(Context context) {
        this.context = context;
    }

    public void sendRequest(final String url, RequestParams requestParams, final String moreapp){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


        asyncHttpClient.get(url + file, requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                callBackToCaller(response, moreapp);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                callBackToCaller(null, moreapp);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                callBackToCaller(null, moreapp);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }


    private void callBackToCaller(JSONObject jsonObject, String httpurl){
        httpGetResponsable = (HttpGetResponsable) context;
        httpGetResponsable.onHttpGetResponse(jsonObject,httpurl);
    }



    public interface HttpGetResponsable{
        void onHttpGetResponse(JSONObject jsonObject, String httpurl);
    }

}
