package com.example.reportcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private TextInputEditText textInputEmailReg;
    private TextInputEditText textInputPasswordReg;
    private TextInputEditText confirmPassword;
    private Button login,signup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textInputEmailReg=findViewById(R.id.inputEmailReg);
        textInputPasswordReg=findViewById(R.id.inputPasswordReg);
        confirmPassword=findViewById(R.id.confirmPassword);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signUp);

        mAuth=FirebaseAuth.getInstance();

        mAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=textInputEmailReg.getText().toString();
                String pass=textInputPasswordReg.getText().toString();
                String confirmpass=confirmPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty() && pass.equals(confirmpass)) {
                        mAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(Register.this, MainActivity.class));
//                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Registered error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else
                        textInputPasswordReg.setError("Empty Fields are not allowed");

                } else if (email.isEmpty()) {
                    textInputEmailReg.setError("Empty Fields are not allowed");
                } else
                    textInputEmailReg.setError("Please enter correct email");

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,LoginActivity.class));
            }
        });

    }
}