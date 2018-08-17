package hamza.quizmaker.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;

import hamza.quizmaker.view.adapters.HomeAdapter;

public class MyRequests {

    private final static String url = "https://whispering-crag-85169.herokuapp.com/rbcs";

    public static void loginUser(String username, String password, Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url + "/login", jsonObject, listener, errorListener);
        Volley.newRequestQueue(context).add(request);
    }

    public static void signUp(HashMap<String, String> params, Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JSONObject jsonObject = new JSONObject();
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            try {
                jsonObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url + "/signup", jsonObject, listener, errorListener);
        Volley.newRequestQueue(context).add(request);
    }

    public static void getQuizzes(String quizGroup, Context context, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/" + quizGroup, null, listener, errorListener);
        Volley.newRequestQueue(context).add(request);
        Log.e("Url", request.getUrl());
    }

    public static void saveUserPoints(String points, Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){

    }

}
