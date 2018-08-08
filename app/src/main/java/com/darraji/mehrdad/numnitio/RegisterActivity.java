package com.darraji.mehrdad.numnitio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etRegisterEmail = (EditText) findViewById(R.id.etRegisterEmail);
        final EditText etRegisterPassword = (EditText) findViewById(R.id.etRegisterPassword);
        final EditText etRegisterConfirmPassword = (EditText) findViewById(R.id.etRegisterConfirmPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String name = etName.getText().toString();
                final String email = etRegisterEmail.getText().toString();
                final String password = etRegisterPassword.getText().toString();
                final String confirmPassword = etRegisterConfirmPassword.getText().toString();

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
            }
        });
    }
}
