package com.unisob.vclibs.httprequest;


import static com.unisob.vclibs.mads.AppManage.app_failData;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class GetRequestList {


    private Context context;
    private HttpGetResponsable httpGetResponsable;

    public GetRequestList(Context context) {
        this.context = context;
    }

    public void sendRequest(final String url, RequestParams requestParams, final String datalist) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(app_failData, requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                callBackToCaller(response, datalist);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                callBackToCaller(null, datalist);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                callBackToCaller(null, datalist);
            }


            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }


    private void callBackToCaller(JSONObject jsonObject, String httpurl) {
        httpGetResponsable = (HttpGetResponsable) context;
        httpGetResponsable.onHttpGetResponse(jsonObject, httpurl);

    }


    public interface HttpGetResponsable {
        void onHttpGetResponse(JSONObject jsonObject, String httpurl);
    }

}
