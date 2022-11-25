package com.example.testing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.chromium.net.CronetEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Declaring
    int[] array = {9,9,9,9,9,9};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPatty = findViewById(R.id.btnPatty);
            btnPatty.setOnClickListener(this);
        Button btnOnion = findViewById(R.id.btnOnion);
            btnOnion.setOnClickListener(this);
        Button btnLettuce = findViewById(R.id.btnLettuce);
            btnLettuce.setOnClickListener(this);
        Button btnMayo = findViewById(R.id.btnMayo);
            btnMayo.setOnClickListener(this);
        Button btnChilli = findViewById(R.id.btnChilli);
            btnChilli.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(this);
        Button btnOrder = findViewById(R.id.btnOrder);
            btnOrder.setOnClickListener(this);

    } // end protected void onCreate

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOrder:
                //startActivity(new Intent(MainActivity.this, MainActivity3.class));
                String URL ="http://10.103.110.100/api";
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("1", array[0]);
                    jsonBody.put("2", array[1]);
                    jsonBody.put("3", array[2]);
                    jsonBody.put("4", array[3]);
                    jsonBody.put("5", array[4]);
                    final String mRequestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("LOG_RESPONSE", response);
                            //startActivity(new Intent(MainActivity.this, MainActivity.class));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("LOG_RESPONSE", error.toString());

                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                return null;
                            }
                        }

                    };

                    requestQueue.add(stringRequest);
                    //setContentView(R.layout.activity_main3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnDelete:
                if(i > 0)
                {
                    array[i - 1] = 9;
                    i -= 1;
                }else if(i == 9){
                    array[0] = 9;
                    i = 0;
                }

                break;
            case R.id.btnPatty:
                array[i] = 2;
                //Toast.makeText(getApplicationContext(), "Patty added", Toast.LENGTH_SHORT).show();
                if(i < 5) {
                    i++;
                }
                break;
            case R.id.btnOnion:
                array[i] = 4;
                //Toast.makeText(getApplicationContext(), "Onion added", Toast.LENGTH_SHORT).show();
                if(i < 5) {
                    i++;
                }
                break;
            case R.id.btnLettuce:
                array[i] = 5;
                //Toast.makeText(getApplicationContext(), "Lettuce added", Toast.LENGTH_SHORT).show();
                if(i < 5) {
                    i++;
                }
                break;
            case R.id.btnMayo:
                array[i] = 3;
                //Toast.makeText(getApplicationContext(), "Mayo added", Toast.LENGTH_SHORT).show();
                if(i < 5) {
                    i++;
                }
                break;
            case R.id.btnChilli:
                array[i] = 6;
                //Toast.makeText(getApplicationContext(), "Chilli added", Toast.LENGTH_SHORT).show();
                if(i < 5) {
                    i++;
                }
                break;
            default:
                break;
        } // end switch
        System.out.println(array[i]);
        System.out.println(i);

        // for s1 array[0]
        if (array[0] == 2) {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#815F3F")); //patty
        } else if (array[0] == 4) {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#AC8AE8")); //onion
        } else if (array[0] == 5) {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#76C14D")); //lettuce
        } else if (array[0] == 3) {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#FFC107"));//mayo
        } else if (array[0] == 6) {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#FF5722"));//chilli
        } else {
            TextView s1 = findViewById(R.id.s1);
            s1.setBackgroundColor(Color.parseColor("#FFE6E5E5"));//default
        }

        // for s2 array[1]
        if (array[1] == 2) {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#815F3F")); //patty
        } else if (array[1] == 4) {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#AC8AE8")); //onion
        } else if (array[1] == 5) {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#76C14D")); //lettuce
        } else if (array[1] == 3) {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#FFC107"));//mayo
        } else if (array[1] == 6) {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#FF5722"));//chilli
        } else {
            TextView s2 = findViewById(R.id.s2);
            s2.setBackgroundColor(Color.parseColor("#FFE6E5E5"));//default
        }

        // for s3 array[2]
        if (array[2] == 2) {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#815F3F")); //patty
        } else if (array[2] == 4) {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#AC8AE8")); //onion
        } else if (array[2] == 5) {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#76C14D")); //lettuce
        } else if (array[2] == 3) {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#FFC107"));//mayo
        } else if (array[2] == 6) {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#FF5722"));//chilli
        } else {
            TextView s3 = findViewById(R.id.s3);
            s3.setBackgroundColor(Color.parseColor("#FFE6E5E5"));//default
        }

        // for s4 array[3]
        if (array[3] == 2) {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#815F3F")); //patty
        } else if (array[3] == 4) {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#AC8AE8")); //onion
        } else if (array[3] == 5) {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#76C14D")); //lettuce
        } else if (array[3] == 3) {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#FFC107"));//mayo
        } else if (array[3] == 6) {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#FF5722"));//chilli
        } else {
            TextView s4 = findViewById(R.id.s4);
            s4.setBackgroundColor(Color.parseColor("#FFE6E5E5"));//default
        }

        // for s5 array[4]
        if (array[4] == 2) {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#815F3F")); //patty
        } else if (array[4] == 4) {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#AC8AE8")); //onion
        } else if (array[4] == 5) {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#76C14D")); //lettuce
        } else if (array[4] == 3) {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#FFC107"));//mayo
        } else if (array[4] == 6) {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#FF5722"));//chilli
        } else {
            TextView s5 = findViewById(R.id.s5);
            s5.setBackgroundColor(Color.parseColor("#FFE6E5E5"));//default
        }


        AlertDialog alertDialog;
        if (i == 5) {
            alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("You fat fuk");
            alertDialog.setMessage("Beautiful burger, send to kitchen?");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Lemme edit ah",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Send!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            String URL ="http://10.103.109.227/api";
                            try {
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                JSONObject jsonBody = new JSONObject();
                                jsonBody.put("1", array[0]);
                                jsonBody.put("2", array[1]);
                                jsonBody.put("3", array[2]);
                                jsonBody.put("4", array[3]);
                                jsonBody.put("5", array[4]);
                                final String mRequestBody = jsonBody.toString();

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.i("LOG_RESPONSE", response);
                                        //startActivity(new Intent(MainActivity.this, MainActivity.class));
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e("LOG_RESPONSE", error.toString());

                                    }
                                }) {
                                    @Override
                                    public String getBodyContentType() {
                                        return "application/json; charset=utf-8";
                                    }

                                    @Override
                                    public byte[] getBody() throws AuthFailureError {
                                        try {
                                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                                        } catch (UnsupportedEncodingException uee) {
                                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                            return null;
                                        }
                                    }

                                };

                                requestQueue.add(stringRequest);
                                //setContentView(R.layout.activity_main3);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //startActivity(new Intent(MainActivity.this, MainActivity3.class));


                        }
        });
        alertDialog.show();
    }// end if

    } // end public void

}