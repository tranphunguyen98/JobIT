package com.example.team32gb.jobit.View.JobSeekerProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterInJobSeekerProfile;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterLogicJobSeekerProfile;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.ChangePassword.ChangePasswordActivity;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobSeekerProfileActivity extends AppCompatActivity implements View.OnClickListener, ViewJobSeekerProfile{
    private Button btnAuthenticateAccount, btnMyCV, btnChangePassword, btnSignOut;
    private ImageButton btnEditName;
    private CircleImageView imgAvatarProfile;
    private TextView tvNameProfile, tvAuthentication,tvEmailProfile;
    private FirebaseAuth firebaseAuth;
    private PresenterInJobSeekerProfile presenterJobSeekerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_profile);

        btnAuthenticateAccount = findViewById(R.id.btnAuthenticateAccount);
        btnMyCV = findViewById(R.id.btnMyCV);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnSignOut = findViewById(R.id.btnSignOutProfile);
        btnEditName = findViewById(R.id.btnEditName);
        imgAvatarProfile = findViewById(R.id.imgAvatarProfile);
        tvNameProfile = findViewById(R.id.tvNameProfile);
        tvAuthentication = findViewById(R.id.tvAuthentication);
        tvEmailProfile = findViewById(R.id.tvEmailProfile);

        btnAuthenticateAccount.setOnClickListener(this);
        btnMyCV.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnEditName.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        presenterJobSeekerProfile = new PresenterLogicJobSeekerProfile(this);
        presenterJobSeekerProfile.getProfile();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnMyCV:
                break;
            case R.id.btnChangePassword:
                Intent intent = new Intent(JobSeekerProfileActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignOutProfile:
                firebaseAuth.signOut();
                break;
            default:
                break;
        }
    }

    @Override
    public void showProfile(UserModel userModel) {
        tvEmailProfile.setText(userModel.getEmail());
    }
}
