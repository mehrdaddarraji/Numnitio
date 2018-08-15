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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etRegisterEmail, etRegisterPassword, etRegisterConfirmPassword;
    private Button bRegister;
    private ProgressBar pbRegisterLoading;
    private static String registerUrl = "http://numnitio.000webhostapp.com/Register.php";

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

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");
                            if(success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Register Successful!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch(JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Failed! "
                                    + e.toString(), Toast.LENGTH_SHORT).show();
                            pbRegisterLoading.setVisibility(View.GONE);
                            bRegister.setVisibility(View.VISIBLE);
                        }
                    }
                };

                Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Failed! "
                                + error.toString(), Toast.LENGTH_SHORT).show();
                        pbRegisterLoading.setVisibility(View.GONE);
                        bRegister.setVisibility(View.VISIBLE);
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl,
                        responseListener, responseErrorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(stringRequest);

            }
        });
    }
}
