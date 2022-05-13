package com.example.ferreteria_mi_casa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private EditText mEditTextEmail;
    private Button mButtonResetPassword;

    private String email ="";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        mEditTextEmail=(EditText)  findViewById(R.id.etEmail);
        mButtonResetPassword=(Button) findViewById (R.id.btnreset);
        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                if (!email.isEmpty()){
                    mDialog.setMessage("Wait a minute...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();

                }

            }
        });
    }

    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful())   {
                Toast.makeText(ResetActivity.this, "Anemail has been sent to reset the password", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(ResetActivity.this, "Failed to send password reset email", Toast.LENGTH_SHORT ).show();

            }
            mDialog.dismiss();
            }

        });
    }
}