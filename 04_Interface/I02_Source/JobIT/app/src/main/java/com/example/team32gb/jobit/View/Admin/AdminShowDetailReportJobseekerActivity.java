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
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.Report.ReportJobseekerModel;
import com.example.team32gb.jobit.Model.Report.ReportWaitingAdminApprovalModel;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminApprovalReport;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminShowHistoryReport;
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

import static com.example.team32gb.jobit.Utility.Config.IS_JOB_SEEKER;
import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT;


public class AdminShowDetailReportJobseekerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDetailReportNameAccused;
    private TextView txtDetailReportDateSendReport;
    private TextView txtDetailReportCommentInvalid;
    private TextView txtDetailReportReporter;
    private TextView txtDetailReportDecripton;
    private Button btnShowDetailHistoryReport;
    private Button btnShowDetailReportSendWarning;
    private Button btnShowDetailReportIgnore;
    private DatabaseReference refReport;
    private String nameAccused;
    private String nameReporter;
    private ReportJobseekerModel model;
    private ReportWaitingAdminApprovalModel modelReportWaiting;
    private PresenterAdminApprovalReport presenter;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private ActionBar acitonBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_detail_report);
        txtDetailReportNameAccused = findViewById(R.id.txtDetailReportNameAccused);
        txtDetailReportDateSendReport = findViewById(R.id.txtDetailReportDateSendReport);
        txtDetailReportCommentInvalid = findViewById(R.id.txtDetailReportCommentInvalid);
        txtDetailReportReporter = findViewById(R.id.txtDetailReportReporter);
        txtDetailReportDecripton = findViewById(R.id.txtDetailReportDecripton);
        btnShowDetailHistoryReport = findViewById(R.id.btnShowDetailHistoryReport);
        btnShowDetailReportSendWarning = findViewById(R.id.btnShowDetailReportSendWarning);
        btnShowDetailReportIgnore = findViewById(R.id.btnShowDetailReportIgnore);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải dữ liệu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        toolbar = findViewById(R.id.tbDetailReport);
        toolbar.setTitle("Tố cáo người tìm việc");
        setSupportActionBar(toolbar);
        acitonBar = getSupportActionBar();
        acitonBar.setDisplayHomeAsUpEnabled(true);

        presenter = new PresenterAdminApprovalReport();

        Intent intent = getIntent();
        String idReport = intent.getStringExtra(AdminReportFragmentTab1.ID_REPORT);
        String date = intent.getStringExtra(AdminReportFragmentTab1.DATE_SEND_REPORT);
        String idAccused = intent.getStringExtra(AdminReportFragmentTab1.ID_ACCUSED);
        modelReportWaiting= new ReportWaitingAdminApprovalModel(idReport, idAccused, date);

        refReport = FirebaseDatabase.getInstance().getReference().
                child(REF_REPORT).child(REF_JOBSEEKERS_NODE).child(idAccused).child(idReport);
        refReport.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = new ReportJobseekerModel();
                model = dataSnapshot.getValue(ReportJobseekerModel.class); //Lấy model report

                //todo: đưa bình luận vi phạm lên
                txtDetailReportCommentInvalid.setText(model.getIdCommentInvalid());
                txtDetailReportDecripton.setText(model.getDecription());

                DatabaseReference refUser = FirebaseDatabase.getInstance().getReference().child(REF_JOBSEEKERS_NODE);
                /*Lấy tên người bị tố cáo*/
                refUser.child(model.getIdAccused()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
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
                refUser.child(model.getIdReporter()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        nameReporter = (String) dataSnapshot.getValue();
                        txtDetailReportReporter.setText(nameReporter);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                txtDetailReportDateSendReport.setText(modelReportWaiting.getDateSendReport());


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
                //todo: presentrt show history
                PresenterAdminShowHistoryReport presenter = new PresenterAdminShowHistoryReport();

                ViewGroup rootView = (ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0);
                final Dialog dialogShowHistory = new Dialog(AdminShowDetailReportJobseekerActivity.this);
                View viewHistory = LayoutInflater.from(AdminShowDetailReportJobseekerActivity.this)
                        .inflate(R.layout.admin_show_history_report, rootView, false);

                Button btnCloseHistory = viewHistory.findViewById(R.id.btnShowDetailHistoryReport);
                TextView txtHistoryReport = viewHistory.findViewById(R.id.txtHistoryReport);

                dialogShowHistory.setContentView(viewHistory);

                txtHistoryReport.setText( presenter.getHistory(model.getIdAccused(), IS_JOB_SEEKER));

//                btnCloseHistory.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogShowHistory.dismiss();
//                    }
//                });

                dialogShowHistory.show();
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

        View dialogView = LayoutInflater.from(AdminShowDetailReportJobseekerActivity.this)
                .inflate(R.layout.admin_report_dialog_send_warning, rootView , false);
        edtMessageFromAdmin = dialogView.findViewById(R.id.edtMessageFromAdmin);
        btnSenWarningOK = dialogView.findViewById(R.id.btnSenWarningOK);
        btnSenWarningCancel = dialogView.findViewById(R.id.btnSenWarningCancel);


        final Dialog dialog = new Dialog(AdminShowDetailReportJobseekerActivity.this);
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
                String message = edtMessageFromAdmin.getText().toString();
                if (message.equals("")){
                    edtMessageFromAdmin.setError("Bạn phải nhập nhắc nhở cảnh báo");
                }
                else{
                    presenter.onSendWarningReportToJobseeker(modelReportWaiting,message);
                    dialog.dismiss();
                    Toast.makeText(AdminShowDetailReportJobseekerActivity.this, "Đã gửi cảnh cáo", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdminShowDetailReportJobseekerActivity.this, AdminReportFragmentTab1.class);
                    startActivity(intent);
                }
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

        final Dialog dialog = new Dialog(AdminShowDetailReportJobseekerActivity.this);
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
                presenter.onIgnoreReportJobseeker(modelReportWaiting);
                Toast.makeText(AdminShowDetailReportJobseekerActivity.this, "Đã bỏ qua tố cáo", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

                Intent intent = new Intent(AdminShowDetailReportJobseekerActivity.this, AdminReportFragmentTab1.class);
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
