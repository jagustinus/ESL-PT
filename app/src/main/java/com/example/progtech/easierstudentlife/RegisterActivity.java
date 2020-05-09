package com.example.progtech.easierstudentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button mLogin;
    private Button mRegister;
    private EditText mName, mEmail, mUniv, mPsw, mPsw2;
    private CheckBox mUserAgree;
    private ProgressBar mRegistrationPb;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//            finish();
//        }

        mName = findViewById(R.id.et_nameReg);
        mEmail = findViewById(R.id.et_emailReg);
        mUniv = findViewById(R.id.et_univReg);
        mPsw = findViewById(R.id.et_passwordReg);
        mPsw2 = findViewById(R.id.et_password2Reg);
        mUserAgree = findViewById(R.id.cb_userAgreeReg);
        mRegistrationPb = findViewById(R.id.pb_reg);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        mAuth = FirebaseAuth.getInstance();


        mRegister = findViewById(R.id.btn_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString();
                final String email = mEmail.getText().toString();
                final String univ = mUniv.getText().toString();
                String psw = mPsw.getText().toString();
                String psw2 = mPsw2.getText().toString();
                Boolean userAgree = false;

                if (TextUtils.isEmpty(name)){
                    mName.setError("Nama Lengkap tidak boleh kosong!");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email tidak boleh kosong!");
                    return;
                }
                if (TextUtils.isEmpty(univ)){
                    mUniv.setError("Email tidak boleh kosong!");
                    return;
                }
                if (TextUtils.isEmpty(psw)){
                    mPsw.setError("Password tidak boleh kosong!");
                    return;
                }else if (TextUtils.isEmpty(psw2)) {
                    mPsw.setError("Retype password tidak boleh kosong!");
                    return;
                }
                if (!psw.equals(psw2)) {
                    mPsw.setError("Password tidak sama!");
                    mPsw2.setError("Password tidak sama!");
                }
                if (psw.length() < 6){
                    mPsw.setError("Password harus lebih dari 6 karakter");
                    return;
                }
                if (mUserAgree.isChecked()){
                    userAgree = true;
                }else {
                    mUserAgree.setError("Kamu harus setuju persyaratan kami dulu!");
                    return;
                }

                mRegistrationPb.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, psw)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    UserData newUser = new UserData(name, email, univ);

                                    FirebaseDatabase.getInstance().getReference("UserData")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            mRegistrationPb.setVisibility(View.INVISIBLE);

                                            Toast.makeText(RegisterActivity.this, "Authentication succes.",
                                                    Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        mLogin = findViewById(R.id.loginBtn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLogin);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(toLogin);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}