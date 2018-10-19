package com.example.team32gb.jobit.View.ChangePassword;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtCurrentPassword, edtNewpassword, edtComfirmNewPassword;
    private Button btnSavePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        edtNewpassword = findViewById(R.id.edtNewpassword);
        edtComfirmNewPassword = findViewById(R.id.edtConfirmNewPassword);
        btnSavePassword = findViewById(R.id.btnSavePassword);

        btnSavePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = edtNewpassword.getText().toString();
        if (user != null) {
            user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
