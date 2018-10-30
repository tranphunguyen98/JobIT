package com.example.team32gb.jobit.View.JobSeekerProfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterInJobSeekerProfile;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterLogicJobSeekerProfile;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.ChangePassword.ChangePasswordActivity;
import com.example.team32gb.jobit.View.CreateCV.CreateCVActivity;
import com.example.team32gb.jobit.View.HomeJobSeeker.HomeJobSeekerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    private SharedPreferences sharedPreferences;
    private String uid;
    private PresenterInJobSeekerProfile presenterJobSeekerProfile;
    private ProgressBar progressBar;

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
        sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME,MODE_PRIVATE);

        user = firebaseAuth.getCurrentUser();
        uid = user.getUid();
        presenterJobSeekerProfile = new PresenterLogicJobSeekerProfile(this, uid);
//        presenterJobSeekerProfile.getProfile();
        presenterJobSeekerProfile.onCreate();
//        presenterJobSeekerProfile.getProfile();
//        if(!sharedPreferences.getBoolean(Config.MAY_GET_LOCAL,false)) {
//            presenterJobSeekerProfile.getProfile();
//        } else {
//            UserModel userModel = new UserModel();
//            userModel.setName(sharedPreferences.getString("nameUser",""));
//            userModel.setEmail(sharedPreferences.getString("emailUser",""));
//            userModel.setAvatar(storageReference.child(Config.REF_FOLDER_AVATAR).child(uid).getPath());
//
//            String avatarPath = Environment.getExternalStorageDirectory() + "/avatar" +"/" + uid + ".jpg";
//            Log.e("kiemtrafile",avatarPath);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
//            Log.e("kiemtrashow","oncreate1");
//            showProfile(userModel,bitmap);
//        }
        Log.e("kiemtrashow","oncreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterJobSeekerProfile.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnMyCV:
                Intent intentcv = new Intent(JobSeekerProfileActivity.this, CreateCVActivity.class);
                startActivity(intentcv);
                break;
            case R.id.btnChangePassword:
                Intent intent = new Intent(JobSeekerProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignOutProfile:
                firebaseAuth.signOut();
                Intent intent1 = new Intent(JobSeekerProfileActivity.this, HomeJobSeekerActivity.class);
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
                String name = edtNameProfile.getText().toString();
                tvNameProfile.setText(name);
                presenterJobSeekerProfile.saveNameProfile(name);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nameUser",name);
                editor.apply();
                break;
            case R.id.imgAvatarProfile:
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent2, "Select Picture"), SELECT_PICTURE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageURI);
                    int heightBitmapThumbnail = (int) (100*((bitmap.getHeight()*1.f)/bitmap.getWidth()));
                    Bitmap bitmapThumbnail = ThumbnailUtils.extractThumbnail(bitmap,100,heightBitmapThumbnail);

                    imgAvatarProfile.setImageBitmap(bitmapThumbnail);

                    String avatarPath = Environment.getExternalStorageDirectory() + "/avatar" +"/" + uid + ".jpg";
                    File file = new File(avatarPath);
                    FileOutputStream fileOutputStream;
                    fileOutputStream = new FileOutputStream(file);
                    bitmapThumbnail.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);

                    fileOutputStream.flush();
                    fileOutputStream.close();

                    presenterJobSeekerProfile.saveImageProfile(bitmapThumbnail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Picasso.get().load(selectedImageURI).into(imgAvatarProfile);

                //Lấy type của hình ảnh
//                ContentResolver contentResolver = this.getContentResolver();
//                MimeTypeMap mime = MimeTypeMap.getSingleton();
//                String type = mime.getExtensionFromMimeType(contentResolver.getType(selectedImageURI));
//                presenterJobSeekerProfile.saveImageProfile(selectedImageURI, "");
            }
        }
    }

    @Override
    public void showProfile(UserModel userModel, Bitmap bitmap) {
        Log.e("kiemtrashow","show");
        if (!userModel.getAvatar().equals("")) {
            imgAvatarProfile.setImageBitmap(bitmap);
        }
        tvEmailProfile.setText(userModel.getEmail());
        if(!userModel.getName().equals("")) {
            tvNameProfile.setText(userModel.getName());
        } else {
            tvNameProfile.setVisibility(View.GONE);
            btnEditName.setVisibility(View.GONE);
            edtNameProfile.requestFocus();
            edtNameProfile.setVisibility(View.VISIBLE);
            btnSaveNameProfile.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProfile(UserModel userModel, Uri uri) {
        if (!userModel.getAvatar().equals("")) {
            Picasso.get().load(uri).into(imgAvatarProfile);
        }
        tvEmailProfile.setText(userModel.getEmail());
        tvNameProfile.setText(userModel.getName());

    }
}
