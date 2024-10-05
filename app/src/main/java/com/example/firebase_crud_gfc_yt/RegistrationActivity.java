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

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt,pwdEdt,cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userNameEdt=findViewById(R.id.idEdtUserName);
        pwdEdt=findViewById(R.id.idEdtPassword);
        cnfPwdEdt=findViewById(R.id.idEdtCnfPass);
        registerBtn=findViewById(R.id.idBtnRegister);
        loadingPB=findViewById(R.id.idPBLoading);
        loginTV=findViewById(R.id.TvLogin);
        mAuth=FirebaseAuth.getInstance();
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String username=userNameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();
                String cnfpwd=cnfPwdEdt.getText().toString();
                if(!pwd.equalsIgnoreCase(cnfpwd)){
                    Toast.makeText(RegistrationActivity.this, "Please check both password", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                }else if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(cnfpwd)){
                    Toast.makeText(RegistrationActivity.this, "Please add your credential", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                }else {
                    mAuth.createUserWithEmailAndPassword(username,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "User Registered..", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(in);
                                finish();
                            }else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "Fail to register user..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}