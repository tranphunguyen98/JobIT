package com.example.team32gb.jobit.View.SignIn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.ForgotPassword.ForgotPasswordActivity;
import com.example.team32gb.jobit.View.HomeJobSeeker.HomeActivity;
import com.example.team32gb.jobit.View.SignUp.SignUpActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Share;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.Arrays;

import static com.example.team32gb.jobit.Utility.Config.MAY_GET_LOCAL;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener, GoogleApiClient.OnConnectionFailedListener {
    private Button btnLogin, btnLoginGoogle, btnLoginFacebook, btnCreateAccount;
    private EditText edtEmail, edtPassword;
    private TextView tvForgotPassword;
    private ProgressDialog progressDialog;
    private GoogleApiClient apiClient;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    CallbackManager callbackManager;
    SharedPreferences sharedPreferences;

    public static int REQUEST_CODE_LOGIN_GOOGLE = 3;
    public static int REQUEST_CODE_LOGIN_FACEBOOK = 3;

    public static int provider = -1;
    public static int LOGIN_WITH_GMAIL = 0;
    public static int LOGIN_WITH_GOOGLE = 1;
    public static int LOGIN_WITH_FACEBOOK = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_sign_in);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.bgBlackTransparent40));

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        edtEmail = findViewById(R.id.edtEmailLogIn);
        edtPassword = findViewById(R.id.edtpasswordLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(Config.REF_USERS_NODE);
        sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        btnLoginGoogle.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        CreateClientGoogle();


        edtEmail.setText(sharedPreferences.getString(Config.EMAIL_USER, ""));
        edtPassword.setText(sharedPreferences.getString(Config.PASSWORD_USER, ""));
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLogin:
                LoginWithEmail();
                break;
            case R.id.btnLoginGoogle:
                LoginWithGoogle();
                break;
            case R.id.btnLoginFacebook:
                LoginWithFacebook();
                break;
            case R.id.btnCreateAccount:
                Intent intentCA = new Intent(this, SignUpActivity.class);
                startActivity(intentCA);
                break;
            case R.id.tvForgotPassword:
                Intent intentFG = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intentFG);
                break;
            default:
                break;
        }
    }

    public void LoginWithEmail() {
        if (checkInfoInput()) {
            progressDialog.setMessage("Đang xử lý...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();

            String email = edtEmail.getText().toString().trim();
            final String password = edtPassword.getText().toString().trim();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(Config.SIGN_UP_WITH_EMAIL, true);
                        editor.putString(Config.PASSWORD_USER, password);
                        editor.apply();
                    }
                }
            });
        }
    }

    private boolean checkInfoInput() {
        boolean isValid = true;
        if (edtEmail.getText().toString().trim().length() <= 0) {
            edtEmail.requestFocus();
            isValid = false;
            edtEmail.setError("Hãy nhập email");
        }
        if (edtPassword.getText().toString().trim().length() <= 0) {
            edtPassword.requestFocus();
            isValid = false;
            edtPassword.setError("Hãy nhập mật khẩu");
        }
        return isValid;
    }

    public void LoginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        String tokenID = loginResult.getAccessToken().getToken();
                        authenticateGoogle(tokenID);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void CreateClientGoogle() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void LoginWithGoogle() {
        Intent iGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        provider = LOGIN_WITH_GOOGLE;
        startActivityForResult(iGoogle, REQUEST_CODE_LOGIN_GOOGLE);
    }

    private void authenticateGoogle(String tokenID) {
        if (provider == LOGIN_WITH_GOOGLE) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        } else {
            AuthCredential authCredential = FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_LOGIN_GOOGLE) {
            if (resultCode == RESULT_OK) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                authenticateGoogle(tokenID);
            }
        } else {
            //callbackManager.onActivityResult(requestCode,resultCode,data);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String uid = user.getUid();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserModel model;
                    //Nếu tài khoản đã tồn tại trên sever
                    if (dataSnapshot.hasChild(uid)) {
                        model = dataSnapshot.child(uid).getValue(UserModel.class);
                        if (model.getAvatar() != "") {
                            //Tạo folder chứa hình ảnh
                            File folderDownloaded = new File(Environment.getExternalStorageDirectory() + "/avatar");
                            if (!folderDownloaded.exists()) {
                                folderDownloaded.mkdir();
                            }
                            final File fileDownload = new File(folderDownloaded, uid + ".jpg");
                            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(model.getAvatar());
                            storageReference.getFile(fileDownload);
                        }
                    } else {
                        model = new UserModel();
                        model.setEmail(user.getEmail());
                        if (user.getDisplayName() != null) {
                            model.setName(user.getDisplayName());
                        }
                        model.setUid(uid);
                        databaseReference.child(uid).setValue(model);
                    }


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(MAY_GET_LOCAL, true);
                    editor.putString(Config.EMAIL_USER, model.getEmail());
                    editor.putString(Config.NAME_USER, model.getName());
                    editor.putString(Config.UID_USER, model.getUid());
                    editor.apply();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);

            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
