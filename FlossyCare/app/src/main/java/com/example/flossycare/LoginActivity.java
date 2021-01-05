package com.example.flossycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    protected EditText etEmailUsername, etPassword;
    protected Button btnLogin;
    protected TextView tvHere,tvForgotPass;


    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailUsername=(EditText) findViewById(R.id.login_et_email_username);
        etPassword=(EditText) findViewById(R.id.login_et_password);
        btnLogin= (Button) findViewById(R.id.login_btn);
        tvHere=(TextView) findViewById(R.id.login_tv_here);
        tvForgotPass=(TextView) findViewById(R.id.login_tv_forgot_pass);

        //initialize firebaseAuth
        mFirebaseAuth=FirebaseAuth.getInstance();

        tvHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkCredentialsAndSuccess();
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void checkCredentialsAndSuccess(){
        String email=etEmailUsername.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        if (email.isEmpty() || !email.contains("@")){
            etEmailUsername.setError("Must Be Valid Email Address");
        }else if (password.isEmpty()){
            etPassword.setError("Must Not Be Empty");
        }else {
            mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        GotoMainActivity();
                    }else{
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }



    private void GotoMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
