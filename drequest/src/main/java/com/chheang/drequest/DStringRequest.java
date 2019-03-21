package com.chheang.drequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.chheang.drequest2.BuildConfig;

import java.io.UnsupportedEncodingException;

public abstract class DStringRequest extends DRequest<String> {
    public DStringRequest(Context context) {
        super(context);
    }

    @Override
    protected Response<String> onParseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        try {
            if (BuildConfig.DEBUG) {
                Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " (" + getContext().getClass().getSimpleName() + ")");
                Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " Status Code : " + response.statusCode);
                Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " Data Response : " + parsed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
