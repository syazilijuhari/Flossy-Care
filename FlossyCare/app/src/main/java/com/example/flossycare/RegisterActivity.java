package com.example.flossycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    protected EditText etEmail,etUsername,etPassword,etConfirmPassword;
    protected Button btnRegister;

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

      //  users=new ArrayList<>();


        mFirebaseAuth=FirebaseAuth.getInstance();



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=etEmail.getText().toString().trim();
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                String confirmpassword=etConfirmPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || username.isEmpty() || confirmpassword.isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Please Complete all the necessary information").setTitle("").setPositiveButton("OK",null);

                    AlertDialog dialog=builder.create();
                    dialog.show();
                }else{

                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                GoToMainActivity();

                            }else{

                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }

            }
        });


    }

   // private

    private void GoToMainActivity(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
