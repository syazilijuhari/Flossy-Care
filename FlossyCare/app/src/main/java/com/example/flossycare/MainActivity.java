package com.example.flossycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnAddAppoint, btnAbout;
    Button btnLogOut;


    //ref for firebase Authentication
    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  startActivity(new Intent(MainActivity.this, LoginActivity.class));
        
        //Toolbar toolbar= findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        //find the view
        btnAddAppoint= (Button) findViewById(R.id.btn_add_appoint);
        btnAbout= (Button) findViewById(R.id.btn_about);
        btnLogOut=(Button) findViewById(R.id.btn_logout);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mFirebaseAuth.signOut();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser==null){
            //go to login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

  /*  @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/
}