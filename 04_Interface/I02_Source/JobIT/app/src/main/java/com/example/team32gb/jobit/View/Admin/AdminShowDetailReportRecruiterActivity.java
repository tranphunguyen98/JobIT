package com.example.team32gb.jobit.View.Admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.Report.ReportJobseekerModel;
import com.example.team32gb.jobit.Model.Report.ReportModel;
import com.example.team32gb.jobit.Model.Report.ReportWaitingAdminApprovalModel;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminApprovalReport;
import com.example.team32gb.jobit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY;
import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_RECRUITERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT;

public class AdminShowDetailReportRecruiterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDetailReportNameAccused;
    private TextView txtDetailReportDateSendReport;
    private TextView txtDetailReportReporter;
    private TextView txtDetailReportDecripton;
    private Button btnShowDetailHistoryReport;
    private Button btnShowDetailReportSendWarning;
    private Button btnShowDetailReportIgnore;
    private DatabaseReference refReport;
    private String nameAccused;
    private String nameReporter;
    private ReportModel model;
    private ReportWaitingAdminApprovalModel modelReportWaiting;
    private PresenterAdminApprovalReport presenter;
    private LinearLayout llCommentReportToJobSeeker;
    private TextView txtTitleNameAccusedDetail;

    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private ActionBar acitonBar;

    public AdminShowDetailReportRecruiterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_detail_report);
        txtDetailReportNameAccused = findViewById(R.id.txtDetailReportNameAccused);
        txtDetailReportDateSendReport = findViewById(R.id.txtDetailReportDateSendReport);
        txtDetailReportReporter = findViewById(R.id.txtDetailReportReporter);
        txtDetailReportDecripton = findViewById(R.id.txtDetailReportDecripton);
        btnShowDetailHistoryReport = findViewById(R.id.btnShowDetailHistoryReport);
        btnShowDetailReportSendWarning = findViewById(R.id.btnShowDetailReportSendWarning);
        btnShowDetailReportIgnore = findViewById(R.id.btnShowDetailReportIgnore);
        llCommentReportToJobSeeker = findViewById(R.id.llCommentReportToJobSeeker);
        llCommentReportToJobSeeker.setVisibility(View.GONE);    //không hiển thị commnet bị tố cáo vì nhà tuyển dụng không có phần này
        txtTitleNameAccusedDetail = findViewById(R.id.txtTitleNameAccusedDetail);
        txtTitleNameAccusedDetail.setText("Tên công ty bị tố cáo: ");
        presenter = new PresenterAdminApprovalReport();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải dữ liệu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        toolbar = findViewById(R.id.tbDetailReport);
        toolbar.setTitle("Tố cáo nhà tuyển dụng");
        setSupportActionBar(toolbar);
        acitonBar = getSupportActionBar();
        acitonBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String idReport = intent.getStringExtra(AdminReportFragmentTab2.ID_REPORT);
        String date = intent.getStringExtra(AdminReportFragmentTab2.DATE_SEND_REPORT);
        String idAccused = intent.getStringExtra(AdminReportFragmentTab2.ID_ACCUSED);
        modelReportWaiting= new ReportWaitingAdminApprovalModel(idReport, idAccused, date);

        //Lấy model report
        refReport = FirebaseDatabase.getInstance().getReference().
                child(REF_REPORT).child(REF_RECRUITERS_NODE).child(idAccused).child(idReport);
        refReport.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = new ReportJobseekerModel();
                model = dataSnapshot.getValue(ReportModel.class);
                txtDetailReportDecripton.setText(model.getDecription());

                DatabaseReference refData = FirebaseDatabase.getInstance().getReference();
                /*Lấy tên công ty bị tố cáo*/
                refData.child(REF_INFO_COMPANY).child(model.getIdAccused()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        nameAccused = (String) dataSnapshot.getValue();
                        txtDetailReportNameAccused.setText(nameAccused);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                /*Lấy tên người tố cáo*/
                refData.child(REF_JOBSEEKERS_NODE).child(model.getIdReporter()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        nameReporter = (String) dataSnapshot.getValue();
                        txtDetailReportReporter.setText(nameReporter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                txtDetailReportDateSendReport.setText(modelReportWaiting.getDateSendReport());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnShowDetailHistoryReport.setOnClickListener(this);
        btnShowDetailReportIgnore.setOnClickListener(this);
        btnShowDetailReportSendWarning.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowDetailHistoryReport:
                //todo: presenter show history






                break;
            case R.id.btnShowDetailReportIgnore:
                setupDialogIgnoreReport();
                break;
            case R.id.btnShowDetailReportSendWarning:
                setUpDialogSendWarning();
                break;
        }
    }


    public void setUpDialogSendWarning() {
        final EditText edtMessageFromAdmin;
        Button btnSenWarningOK;
        Button btnSenWarningCancel;
        ViewGroup rootView = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        View dialogView = LayoutInflater.from(AdminShowDetailReportRecruiterActivity.this)
                .inflate(R.layout.admin_report_dialog_send_warning, rootView , false);
        edtMessageFromAdmin = dialogView.findViewById(R.id.edtMessageFromAdmin);
        btnSenWarningOK = dialogView.findViewById(R.id.btnSenWarningOK);
        btnSenWarningCancel = dialogView.findViewById(R.id.btnSenWarningCancel);

        final Dialog dialog = new Dialog(AdminShowDetailReportRecruiterActivity.this);
        dialog.setContentView(dialogView);
        dialog.setTitle("Gửi cảnh cáo");

        btnSenWarningCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSenWarningOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSendWarningReportToRecruiter(modelReportWaiting, edtMessageFromAdmin.getText().toString());
                Toast.makeText(AdminShowDetailReportRecruiterActivity.this, "Đã gửi cảnh cáo", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AdminShowDetailReportRecruiterActivity.this, AdminReportFragmentTab1.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setupDialogIgnoreReport(){
        Button btnIgnoreReportCancel;
        Button btnIgnoreReportOK;

        ViewGroup rootView = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(this)
                .inflate(R.layout.admin_report_dialog_ignore, rootView, false);
        btnIgnoreReportOK = view.findViewById(R.id.btnIgnoreReportOK);
        btnIgnoreReportCancel = view.findViewById(R.id.btnIgnoreReportCancel);

        final Dialog dialog = new Dialog(AdminShowDetailReportRecruiterActivity.this);
        dialog.setContentView(view);
        dialog.setTitle("Bỏ qua tố cáo");

        btnIgnoreReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnIgnoreReportOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onIgnoreReportRecruiter(modelReportWaiting);
                Toast.makeText(AdminShowDetailReportRecruiterActivity.this, "Đã bỏ qua tố cáo", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

                Intent intent = new Intent(AdminShowDetailReportRecruiterActivity.this, AdminReportFragmentTab1.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeAdmin:
                Intent intent = new Intent(this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
