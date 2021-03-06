package com.example.panicbutton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView ca;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        ca = findViewById(R.id.ca);
        firebaseAuth= FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String e=email.getText().toString().trim();
                    String p=password.getText().toString().trim();

                    if(TextUtils.isEmpty((e)))
                    {
                        email.setError("Email is required");
                        return;
                    }
                    if(TextUtils.isEmpty(p))
                    {
                        password.setError("Password is required");
                        return;
                    }

                    //autheticat user

                    firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else
                                    {
                                        Toast.makeText(login.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT ).show();
                                    }
                        }
                    });
                }
            });

            ca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Register.class));
                }
            });

    }
}
