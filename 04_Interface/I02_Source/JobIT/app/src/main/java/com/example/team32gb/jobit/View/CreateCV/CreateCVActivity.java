package com.example.team32gb.jobit.View.CreateCV;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.team32gb.jobit.InviteJobFragment;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.CreateCV.CVEmployeeModel;
import com.example.team32gb.jobit.Model.CreateCV.ProjectInCVModel;
import com.example.team32gb.jobit.Presenter.CreateCV.PresenterInCreateCV;
import com.example.team32gb.jobit.Presenter.CreateCV.PresenterLogicCreateCV;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;

public class CreateCVActivity extends AppCompatActivity implements View.OnClickListener, ViewCreateCV {
    private static final String TAG = "kiemtraactivity";
    Button btnAttachCV;
    Button btnSaveCV;
    Button BtnSelectDateOfBird;

    ImageButton btnEditPersonalInfor;
    ImageButton btnEditCareerSkill;
    ImageButton btnEditProject;
    ImageButton btnAddProject;
    ImageButton btnRemoveProject2;

    LinearLayout llEditPersonalInfor;
    LinearLayout llEditCareerSkill;
    LinearLayout llEditProject;
    LinearLayout llEditProject1;
    LinearLayout llEditProject2;

    //Other vars
    //TAB 1
    private TextInputEditText edtNameUser;
    private TextInputEditText edtDateOfBird;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPhone;
    private TextInputEditText edtAddress;
    private TextInputEditText edtHobbies;
    private Button btnSelectDateOfBird;
    private RadioGroup rbgGender;
    private RadioGroup rbgMaritalStatus;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioButton rbMarried;
    private RadioButton rbSingle;
    //TAB2
    TextInputEditText edtCareerObject;
    TextInputEditText edtEduQuali;
    TextInputEditText edtWorkExperience;
    TextInputEditText edtSkill;
    TextInputEditText edtLanguage;
    //TAB3
    TextInputEditText edtNameProject1;
    TextInputEditText edtDescription1;
    TextInputEditText edtRole1;
    TextInputEditText edtNumberMember1;

    TextInputEditText edtNameProject2;
    TextInputEditText edtDescription2;
    TextInputEditText edtRole2;
    TextInputEditText edtNumberMember2;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private PresenterInCreateCV presenterInCreateCV;

    private String uid;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TextView txtMyCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);

        btnAttachCV = findViewById(R.id.btnAttachCV);
        btnSaveCV = findViewById(R.id.btnSaveCV);
        BtnSelectDateOfBird = findViewById(R.id.btnSelectDateOfBird);
        txtMyCv = findViewById(R.id.txtMyCV);

        llEditPersonalInfor = findViewById(R.id.llEditPersonalInfor);
        llEditCareerSkill = findViewById(R.id.llEditCareerSkill);
        llEditProject = findViewById(R.id.lllEditProject);
        llEditProject1 = findViewById(R.id.llEditProject1);
        llEditProject2 = findViewById(R.id.llEditProject2);

        btnEditPersonalInfor = findViewById(R.id.btnEditPersonalInfor);
        btnEditCareerSkill = findViewById(R.id.btnEditCareerSkill);
        btnEditProject = findViewById(R.id.btnEditProject);
        btnAddProject = findViewById(R.id.btnAddProjectInCV);
        btnRemoveProject2 = findViewById(R.id.btnRemoveProject2);

        edtNameUser = findViewById(R.id.edtNameEmployee);
        edtDateOfBird = findViewById(R.id.edtDateOfBird);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAdress);
        edtHobbies = findViewById(R.id.edtHobbies);
        btnSelectDateOfBird = findViewById(R.id.btnSelectDateOfBird);
        rbgGender = findViewById(R.id.rbgGender);
        rbgMaritalStatus = findViewById(R.id.rbgMaritalStatus);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbMarried = findViewById(R.id.rbMarried);
        rbSingle = findViewById(R.id.rbSingle);

        edtCareerObject = findViewById(R.id.edtCareerObject);
        edtEduQuali = findViewById(R.id.edtEduQuali);
        edtWorkExperience = findViewById(R.id.edtWorkExperience);
        edtSkill = findViewById(R.id.edtSkill);
        edtLanguage = findViewById(R.id.edtLanguage);


        edtNameProject1 = findViewById(R.id.edtNameProject);
        edtDescription1 = findViewById(R.id.edtDescription);
        edtRole1 = findViewById(R.id.edtRole);
        edtNumberMember1 = findViewById(R.id.edtNumberMember);

        edtNameProject2 = findViewById(R.id.edtNameProject2);
        edtDescription2 = findViewById(R.id.edtDescription2);
        edtRole2 = findViewById(R.id.edtRole2);
        edtNumberMember2 = findViewById(R.id.edtNumberMember2);

        btnRemoveProject2.setOnClickListener(this);
        btnEditPersonalInfor.setOnClickListener(this);
        btnEditCareerSkill.setOnClickListener(this);
        btnEditProject.setOnClickListener(this);
        btnAddProject.setOnClickListener(this);
        btnAttachCV.setOnClickListener(this);
        btnSaveCV.setOnClickListener(this);
        btnSelectDateOfBird.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        uid = FirebaseAuth.getInstance().getUid();
        edtNameUser.setText(sharedPreferences.getString(Config.NAME_USER,""));
        edtEmail.setText(sharedPreferences.getString(Config.EMAIL_USER,""));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        presenterInCreateCV = new PresenterLogicCreateCV(this);
        presenterInCreateCV.onCreate();
        if(sharedPreferences.getInt(Config.USER_TYPE,0) == Config.IS_RECRUITER) {
            btnAttachCV.setVisibility(View.INVISIBLE);
            btnSaveCV.setVisibility(View.INVISIBLE);
            btnAddProject.setVisibility(View.INVISIBLE);
            btnRemoveProject2.setVisibility(View.INVISIBLE);
            rbFemale.setEnabled(false);
            rbMale.setEnabled(false);
            rbMarried.setEnabled(false);
            rbSingle.setEnabled(false);
            edtNameUser.setEnabled(false);
            edtDateOfBird.setEnabled(false);
            edtEmail.setEnabled(false);
            edtPhone.setEnabled(false);
            edtAddress.setEnabled(false);
            edtHobbies.setEnabled(false);
            edtCareerObject.setEnabled(false);
            edtEduQuali.setEnabled(false);
            edtWorkExperience.setEnabled(false);
            edtSkill.setEnabled(false);
            edtLanguage.setEnabled(false);
            txtMyCv.setVisibility(GONE);


            edtNameProject1.setEnabled(false);
            edtDescription1.setEnabled(false);
            edtRole1.setEnabled(false);
            edtNumberMember1.setEnabled(false);

            edtNameProject2.setEnabled(false);
            edtDescription2.setEnabled(false);
            edtRole2.setEnabled(false);
            edtNumberMember2.setEnabled(false);
            btnSelectDateOfBird.setVisibility(View.INVISIBLE);
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            uid = bundle.getString("idJobSeeker");
            Log.e("ktid",uid);
        }


        presenterInCreateCV.getCVFromUid(uid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInCreateCV.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditPersonalInfor:
                if (llEditPersonalInfor.getVisibility() == View.VISIBLE) {
                    llEditPersonalInfor.setVisibility(GONE);
                } else {
                    llEditPersonalInfor.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnEditCareerSkill:
                if (llEditCareerSkill.getVisibility() == View.VISIBLE) {
                    llEditCareerSkill.setVisibility(GONE);
                } else {
                    llEditCareerSkill.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnSelectDateOfBird:
                calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = null;
                simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDateOfBird.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, day, month, year);
                datePickerDialog.show();
                break;
            case R.id.btnEditProject:
                if (llEditProject1.getVisibility() == View.VISIBLE) {
                    llEditProject1.setVisibility(GONE);
                    llEditProject2.setVisibility(GONE);
                    btnAddProject.setVisibility(GONE);
                } else {
                    llEditProject1.setVisibility(View.VISIBLE);
                    btnAddProject.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnAddProjectInCV:
                llEditProject2.setVisibility(View.VISIBLE);
                break;
            case R.id.btnRemoveProject2:
                llEditProject2.setVisibility(View.GONE);
                break;
            case R.id.btnAttachCV:
                //todo
                break;
            case R.id.btnSaveCV:
                saveCV();
                break;
            default:
                break;


        }
    }
    public void saveCV() {
        CVEmployeeModel cvEmployeeModel = new CVEmployeeModel();
        cvEmployeeModel.setAddress(edtAddress.getText().toString());
        cvEmployeeModel.setCareerObjective(edtCareerObject.getText().toString());
        cvEmployeeModel.setDateOfBird(edtDateOfBird.getText().toString());
        cvEmployeeModel.setEduQuali(edtEduQuali.getText().toString());
        cvEmployeeModel.setEmail(edtEmail.getText().toString());
        cvEmployeeModel.setHobbies(edtHobbies.getText().toString());
        cvEmployeeModel.setIsMale(rbMale.isChecked());
        cvEmployeeModel.setIsSingle(rbSingle.isChecked());
        cvEmployeeModel.setLanguage(edtLanguage.getText().toString());
        cvEmployeeModel.setNameUser(edtNameUser.getText().toString());
        cvEmployeeModel.setPhoneNumber(edtPhone.getText().toString());
        cvEmployeeModel.setSkill(edtSkill.getText().toString());
        cvEmployeeModel.setWorkExperience(edtWorkExperience.getText().toString());

        ProjectInCVModel projectInCVModel1 = new ProjectInCVModel();
        ProjectInCVModel projectInCVModel2 = new ProjectInCVModel();

        projectInCVModel1.setDecription(edtDescription1.getText().toString());
        projectInCVModel1.setName(edtNameProject1.getText().toString());

        projectInCVModel1.setRole(edtRole1.getText().toString());
        String strNumberMember1 = edtNumberMember1.getText().toString();
        if( strNumberMember1 != null && !strNumberMember1.isEmpty()) {
            projectInCVModel1.setNumberMember(Long.parseLong(strNumberMember1));
        } else projectInCVModel1.setNumberMember(0l);

        projectInCVModel2.setDecription(edtDescription2.getText().toString());
        projectInCVModel2.setName(edtNameProject2.getText().toString());
        projectInCVModel2.setRole(edtRole2.getText().toString());
        String strNumberMember2 = edtNumberMember2.getText().toString();
        if( strNumberMember2 != null && !strNumberMember2.isEmpty()) {
            projectInCVModel2.setNumberMember(Long.parseLong(strNumberMember2));
        } else projectInCVModel2.setNumberMember(0l);

        cvEmployeeModel.getProjects().add(projectInCVModel1);
        cvEmployeeModel.getProjects().add(projectInCVModel2);

        presenterInCreateCV.saveCV(uid, cvEmployeeModel, cvEmployeeModel.getProjects());
        Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCV(CVEmployeeModel cvEmployeeModel) {
        edtNameUser.setText(cvEmployeeModel.getNameUser());
        edtWorkExperience.setText(cvEmployeeModel.getWorkExperience());
        edtSkill.setText(cvEmployeeModel.getSkill());
        edtPhone.setText(cvEmployeeModel.getPhoneNumber());
        edtLanguage.setText(cvEmployeeModel.getLanguage());
        edtHobbies.setText(cvEmployeeModel.getHobbies());
        edtEmail.setText(cvEmployeeModel.getEmail());
        edtEduQuali.setText(cvEmployeeModel.getEduQuali());
        edtDateOfBird.setText(cvEmployeeModel.getDateOfBird());
        edtAddress.setText(cvEmployeeModel.getAddress());
        edtCareerObject.setText(cvEmployeeModel.getCareerObjective());
        rbMale.setChecked(cvEmployeeModel.getIsMale());
        rbSingle.setChecked(cvEmployeeModel.getIsSingle());

        edtDescription1.setText(cvEmployeeModel.getProjects().get(0).getDecription());
        edtNameProject1.setText(cvEmployeeModel.getProjects().get(0).getName());
        edtNumberMember1.setText(cvEmployeeModel.getProjects().get(0).getNumberMember().toString());
        edtRole1.setText(cvEmployeeModel.getProjects().get(0).getRole());

        edtDescription2.setText(cvEmployeeModel.getProjects().get(1).getDecription());
        edtNameProject2.setText(cvEmployeeModel.getProjects().get(1).getName());
        edtNumberMember2.setText(cvEmployeeModel.getProjects().get(1).getNumberMember().toString());
        edtRole2.setText(cvEmployeeModel.getProjects().get(1).getRole());
    }
}
