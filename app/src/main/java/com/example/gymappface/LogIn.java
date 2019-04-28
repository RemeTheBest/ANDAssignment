package com.example.gymappface;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener{
    private Button logInButton;
    private EditText email;
    private EditText password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailLog);
        password = (EditText) findViewById(R.id.passwordLog);
        logInButton = (Button) findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        logInButton.setOnClickListener(this);



    Button buttonSign = findViewById(R.id.signup);
        buttonSign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LogIn.this, Register.class));
        }
    });
}

    public void userLogIn(){

        String emailLog2 = email.getText().toString().trim();
        String passwordLog2 = password.getText().toString().trim();

        if(TextUtils.isEmpty(emailLog2)){

            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordLog2)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailLog2,passwordLog2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }
                else{
                    Toast.makeText(LogIn.this, "Could not log in. Try Again!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == logInButton){
            userLogIn();

        }
    }
}
