package com.darraji.mehrdad.numnitio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button bSignIn;
    private ProgressBar pbLoading;
    private TextView tvRegister;
    private static String signInURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bSignIn = findViewById(R.id.bSignIn);
        pbLoading = findViewById(R.id.pbLoading);
        tvRegister = findViewById(R.id.tvRegister);

        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if(email.isEmpty()) {
                    etEmail.setError("Please enter email");
                } else if(password.isEmpty()) {
                    etPassword.setError("Please enter password");
                } else {
                    pbLoading.setVisibility(View.VISIBLE);
                    bSignIn.setVisibility(View.GONE);

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String success = jsonResponse.getString("success");
                                JSONArray jsonArray = jsonResponse.getJSONArray("signin");

                                if(success.equals("1")) {
                                    for(int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        String objName = obj.getString("name").trim();
                                        String objEmail = obj.getString("email").trim();
                                        Intent intentRegister = new Intent(SigninActivity.this, HomeActivity.class);
                                        intentRegister.putExtra("name", objName);
                                        intentRegister.putExtra("email", objEmail);
                                        SigninActivity.this.startActivity(intentRegister);
                                    }
                                }
                            } catch(JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(SigninActivity.this, "Sign in Failed! "
                                        + e.toString(), Toast.LENGTH_SHORT).show();
                                pbLoading.setVisibility(View.GONE);
                                bSignIn.setVisibility(View.VISIBLE);
                            }
                        }
                    };

                    Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SigninActivity.this, "Register Failed! "
                                    + error.toString(), Toast.LENGTH_SHORT).show();
                            pbLoading.setVisibility(View.GONE);
                            bSignIn.setVisibility(View.VISIBLE);
                        }
                    };

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, signInURL,
                            responseListener, responseErrorListener) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", email);
                            params.put("password", password);
                            return params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(SigninActivity.this);
                    queue.add(stringRequest);

                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(SigninActivity.this, RegisterActivity.class);
                SigninActivity.this.startActivity(intentRegister);
            }
        });

    }
}
