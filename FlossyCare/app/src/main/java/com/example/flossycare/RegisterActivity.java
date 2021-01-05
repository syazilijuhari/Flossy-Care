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

public class RegisterActivity extends AppCompatActivity {

    protected EditText etEmail,etUsername,etPassword,etConfirmPassword;
    protected Button btnRegister;
    protected TextView tvLogin;

   // List<User> users;

  //  DatabaseReference databaseUsers;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail= (EditText) findViewById(R.id.register_et_email);
        etUsername= (EditText) findViewById(R.id.register_et_username);
        etPassword= (EditText) findViewById(R.id.register_et_password);
        etConfirmPassword= (EditText) findViewById(R.id.register_et_confirm_password);

        btnRegister= (Button) findViewById(R.id.register_btn);

        tvLogin= (TextView) findViewById(R.id.register_tv_login);

      //  users=new ArrayList<>();


        mFirebaseAuth=FirebaseAuth.getInstance();



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentialsAndSuccess();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void checkCredentialsAndSuccess(){

        String email=etEmail.getText().toString().trim();
        String username=etUsername.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        String confirmpassword=etConfirmPassword.getText().toString().trim();
        if(email.isEmpty() || !email.contains("@")){
            etEmail.setError("Must Be Valid Email Address");
        }else  if (username.isEmpty() || username.length()<5){
            etUsername.setError("Must Not Be Empty & Must More Than 5 Characters");
        }else  if (password.isEmpty() || password.length()<6){
            etPassword.setError("Must Not Be Empty & Must More Than 6 Characters");
        }else if (confirmpassword.isEmpty() || !confirmpassword.equals(password)){
            etConfirmPassword.setError("Password Does Not Match");
        }else {

            mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        GoToMainActivity();

                    }else{

                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }

    private void GoToMainActivity(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}
