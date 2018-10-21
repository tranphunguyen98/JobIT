package com.example.team32gb.jobit.View.ChangePassword;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.facebook.ShareGraphRequest;
import com.facebook.share.Share;
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
        int id = v.getId();
        if (id == R.id.btnSavePassword) {
            if (checkInfoInput()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String newPassword = edtNewpassword.getText().toString();
                if (user != null) {
                    user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                SharedPreferences sharedPreferences;
                                sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Config.PASSWORD_USER,newPassword);
                                editor.apply();
                                Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }
    }

    public boolean checkInfoInput() {
        boolean isValid = true;
        String password = edtCurrentPassword.getText().toString();
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        String currentPassword = sharedPreferences.getString(Config.PASSWORD_USER, "");
        if (!password.equals(currentPassword)) {
            edtCurrentPassword.requestFocus();
            edtCurrentPassword.setError("Sai mật khẩu");
            isValid = false;
        }
        String newPassword = edtNewpassword.getText().toString();
        if (newPassword.trim().length() < 6) {
            edtNewpassword.requestFocus();
            edtNewpassword.setError("Mật khẩu phải dài hơn 6 ký tự");
            isValid = false;
        }
        String newConfirmNewpassword = edtComfirmNewPassword.getText().toString();
        if (!newPassword.equals(newConfirmNewpassword)) {
            edtComfirmNewPassword.requestFocus();
            edtComfirmNewPassword.setError("Mật khẩu không trùng");
            isValid = false;
        }
        return isValid;
    }
}
