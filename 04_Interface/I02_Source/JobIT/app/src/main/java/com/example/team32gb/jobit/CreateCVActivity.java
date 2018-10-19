package com.example.team32gb.jobit;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static android.view.View.GONE;

public class CreateCVActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAttachCV;
    ImageButton btnEditPersonalInfor;
    ImageButton btnEditCareerSkill;
    ImageButton btnEditProject;
    ImageButton btnAddProject;

    LinearLayout llEditPersonalInfor;
    LinearLayout llEditCareerSkill;
    LinearLayout llEditProject;
    LinearLayout llEditProject1;
    LinearLayout llEditProject2;

    //Other vars
    //TAB 1
    private TextInputEditText edtName;
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
    TextInputEditText edtNameProject;
    TextInputEditText edtDescription;
    TextInputEditText edtRole;
    TextInputEditText edtNumberMember;

    TextInputEditText edtNameProject2;
    TextInputEditText edtDescription2;
    TextInputEditText edtRole2;
    TextInputEditText edtNumberMember2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);

        btnAttachCV = findViewById(R.id.btnAttachCV);

        llEditPersonalInfor = findViewById(R.id.llEditPersonalInfor);
        llEditCareerSkill = findViewById(R.id.llEditCareerSkill);
        llEditProject = findViewById(R.id.lllEditProject);
        llEditProject1 = findViewById(R.id.llEditProject1);
        llEditProject2 = findViewById(R.id.llEditProject2);

        btnEditPersonalInfor = findViewById(R.id.btnEditPersonalInfor);
        btnEditCareerSkill = findViewById(R.id.btnEditCareerSkill);
        btnEditProject = findViewById(R.id.btnEditProject);
        btnAddProject = findViewById(R.id.btnAddProjectInCV);

        edtName = findViewById(R.id.edtNameEmployee);
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


        edtNameProject = findViewById(R.id.edtNameProject);
        edtDescription = findViewById(R.id.edtDescription);
        edtRole = findViewById(R.id.edtRole);
        edtNumberMember = findViewById(R.id.edtNumberMember);

        edtNameProject2 = findViewById(R.id.edtNameProject2);
        edtDescription2 = findViewById(R.id.edtDescription2);
        edtRole2 = findViewById(R.id.edtRole2);
        edtNumberMember2 = findViewById(R.id.edtNumberMember2);

        btnEditPersonalInfor.setOnClickListener(this);
        btnEditCareerSkill.setOnClickListener(this);
        btnEditProject.setOnClickListener(this);
        btnAddProject.setOnClickListener(this);
        btnAttachCV.setOnClickListener(this);
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
            case R.id.btnAttachCV:
                //todo
                break;
            default:
                break;


        }
    }
}
