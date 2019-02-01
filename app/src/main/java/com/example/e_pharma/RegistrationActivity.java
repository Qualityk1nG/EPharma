package com.example.e_pharma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;

    private ProgressDialog progressBar;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        firebaseAuth = FirebaseAuth.getInstance();
        progressBar =  new ProgressDialog(this);


        setupUIViews();
    }
        


        private void registerUser(){
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();

                return;
            }

            if(TextUtils.isEmpty(password)){

                Toast.makeText(this , "Pleease enter passsword", Toast.LENGTH_SHORT).show();
                return;
            }
            //if are validation
        //we will first show a progress bar.

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //user is successful registeresd and logged in
                                Toast.makeText(RegistrationActivity.this, "Register succ", Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                                Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                    }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

            }
        });
    }

    private  void setupUIViews(){
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword= (EditText) findViewById(R.id.etUserPassword);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);


    }

    private Boolean validate() {

        Boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    @Override
    public void onClick(View v) {

    }
}
