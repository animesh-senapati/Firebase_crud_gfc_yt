package com.example.firebase_crud_gfc_yt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private TextInputEditText usernameEdt,pwdEdt;
    private Button loginBtn;
    private ProgressBar loadingPB;
    private TextView registerTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdt=findViewById(R.id.idEdtUserName);
        pwdEdt=findViewById(R.id.idEdtPassword);
        loginBtn=findViewById(R.id.idBtnLogin);
        loadingPB=findViewById(R.id.idPBLoading);
        registerTV=findViewById(R.id.TvRegister);
        mAuth= FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String username=usernameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this, "Please Enter  your credential", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                   return;
                }else {
                    mAuth.signInWithEmailAndPassword(username,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              loadingPB.setVisibility(View.GONE);
                              Toast.makeText(LoginActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                              Intent in=new Intent(LoginActivity.this,MainActivity.class);
                              finish();
                          }else {
                              loadingPB.setVisibility(View.GONE);
                              Toast.makeText(LoginActivity.this, "Fail to login", Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
         Intent in=new Intent(LoginActivity.this,MainActivity.class);
         startActivity(in);
         finish();
        }
    }
}