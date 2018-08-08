package com.darraji.mehrdad.numnitio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etRegisterEmail, etRegisterPassword, etRegisterConfirmPassword;
    private Button bRegister;
    private ProgressBar pbRegisterLoading;
    private static String registerUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        bRegister = findViewById(R.id.bRegister);
        pbRegisterLoading = findViewById(R.id.pbRegisterLoading);

        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bRegister.setVisibility(View.GONE);
                pbRegisterLoading.setVisibility(View.VISIBLE);

                final String name = etName.getText().toString().trim();
                final String email = etRegisterEmail.getText().toString().trim();
                final String password = etRegisterPassword.getText().toString().trim();
                final String confirmPassword = etRegisterConfirmPassword.getText().toString().trim();

                /*
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.d("response: ", response);
                            boolean success = jsonResponse.getBoolean("success");
                            //Log.d("success: ", success + "");
                            if(success) {
                                Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed.").setNegativeButton("Retry", null).create().show();
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                */

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };

                Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl,
                        responseListener, responseErrorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return super.getParams();
                    }
                };

            }
        });
    }
}
