package com.chheang.drequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.chheang.drequest2.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public abstract class DJSONArrayRequest extends DRequest<JSONArray> {

    public DJSONArrayRequest(Context context) {
        super(context);
    }

    @Override
    protected Response<JSONArray> onParseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

            try {
                if (BuildConfig.DEBUG) {
                    Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " (" + getContext().getClass().getSimpleName() + ")");
                    Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " Status Code : " + response.statusCode);
                    Log.i("RESPONSE<<<:", this.getClass().getSimpleName() + " Data Response : " + jsonString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
