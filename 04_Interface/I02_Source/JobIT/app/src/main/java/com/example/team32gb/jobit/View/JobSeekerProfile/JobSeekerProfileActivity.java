package com.example.team32gb.jobit.View.JobSeekerProfile;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterInJobSeekerProfile;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterLogicJobSeekerProfile;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.ChangePassword.ChangePasswordActivity;
import com.example.team32gb.jobit.View.HomeJobSeeker.HomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobSeekerProfileActivity extends AppCompatActivity implements View.OnClickListener, ViewJobSeekerProfile {
    private static final int SELECT_PICTURE = 10;
    private Button btnAuthenticateAccount, btnMyCV, btnChangePassword, btnSignOut;
    private EditText edtNameProfile;
    private ImageButton btnEditName, btnSaveNameProfile;
    private CircleImageView imgAvatarProfile;
    private TextView tvNameProfile, tvAuthentication, tvEmailProfile;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String uid;
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
        btnSaveNameProfile = findViewById(R.id.btnSaveNameProfile);
        imgAvatarProfile = findViewById(R.id.imgAvatarProfile);
        tvNameProfile = findViewById(R.id.tvNameProfile);
        tvAuthentication = findViewById(R.id.tvAuthentication);
        tvEmailProfile = findViewById(R.id.tvEmailProfile);
        edtNameProfile = findViewById(R.id.edtNameProfile);

        btnAuthenticateAccount.setOnClickListener(this);
        btnMyCV.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnEditName.setOnClickListener(this);
        btnSaveNameProfile.setOnClickListener(this);
        imgAvatarProfile.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        user = firebaseAuth.getCurrentUser();
        uid = user.getUid();
        presenterJobSeekerProfile = new PresenterLogicJobSeekerProfile(this,uid);
        presenterJobSeekerProfile.getProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnMyCV:
                break;
            case R.id.btnChangePassword:
                Intent intent = new Intent(JobSeekerProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignOutProfile:
                firebaseAuth.signOut();
                Intent intent1 = new Intent(JobSeekerProfileActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnEditName:
                btnEditName.setVisibility(View.GONE);
                tvNameProfile.setVisibility(View.GONE);
                edtNameProfile.setVisibility(View.VISIBLE);
                btnSaveNameProfile.setVisibility(View.VISIBLE);
                edtNameProfile.setText(tvNameProfile.getText().toString());
                break;
            case R.id.btnSaveNameProfile:
                btnEditName.setVisibility(View.VISIBLE);
                tvNameProfile.setVisibility(View.VISIBLE);
                edtNameProfile.setVisibility(View.GONE);
                btnSaveNameProfile.setVisibility(View.GONE);
                tvNameProfile.setText(edtNameProfile.getText().toString());
                presenterJobSeekerProfile.saveNameProfile(edtNameProfile.getText().toString());
                break;
            case R.id.imgAvatarProfile:
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent2,"Select Picture"),SELECT_PICTURE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == SELECT_PICTURE) {
                Uri selectedImageURI = data.getData();
                Picasso.get().load(selectedImageURI).into(imgAvatarProfile);

                //Lấy type của hình ảnh
//                ContentResolver contentResolver = this.getContentResolver();
//                MimeTypeMap mime = MimeTypeMap.getSingleton();
//                String type = mime.getExtensionFromMimeType(contentResolver.getType(selectedImageURI));
                presenterJobSeekerProfile.saveImageProfile(selectedImageURI,"");
            }
        }
    }

    @Override
    public void showProfile(UserModel userModel) {
        tvEmailProfile.setText(userModel.getEmail());
        tvNameProfile.setText(userModel.getName());

        storageReference.child(userModel.getAvatar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imgAvatarProfile);
            }
        });
    }
}
