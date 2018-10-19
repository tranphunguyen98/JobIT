package com.example.team32gb.jobit.View.ForgotPassword;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.SignIn.SignInActivity;
import com.example.team32gb.jobit.View.SignUp.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateAccount, btnSendEmail;
    private EditText edtEmail;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnCreateAccount = findViewById(R.id.btnCreateAccountFg);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        edtEmail = findViewById(R.id.edtEmailReset);

        btnSendEmail.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnCreateAccountFg:
                Intent intent = new Intent(this,SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSendEmail:
                String email = edtEmail.getText().toString().trim();
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                            } else
                            {
                                Toast.makeText(ForgotPasswordActivity.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    edtEmail.requestFocus();
                    edtEmail.setError("Hãy nhập email hợp lệ");
                }
                break;
            default:
                break;

        }

    }
}
