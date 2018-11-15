package com.example.team32gb.jobit.View.Admin;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.team32gb.jobit.Model.Report.ReportWaitingAdminApprovalModel;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminApprovalReport;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT_WAITING_ADMIN_APPROVAL;


/**
 * A simple {@link Fragment} subclass.
 * TAB1: Employee's report
 */
public class AdminReportFragmentTab1 extends Fragment {
    private RecyclerView rvReportEmployee;
    List<AdminReportModel> arrReport = new ArrayList<>();
    DatabaseReference refData;
    private FirebaseRecyclerAdapter<ReportWaitingAdminApprovalModel, AdminListReportViewHolder> adaptor;
    private String nameAccused;
    private String timeLeftSendReport;
    private ReportWaitingAdminApprovalModel modelReportWaiting;
    private PresenterAdminApprovalReport presenter;
    public static final String ID_REPORT = "id_report";
    public static final String DATE_SEND_REPORT = "date_send_report";
    public static final String ID_ACCUSED = "id_accused";
    private ProgressDialog progressDialog;


    public AdminReportFragmentTab1() {
        // Required empty public constructor
        presenter = new PresenterAdminApprovalReport();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_report_tab1_, container, false);
        rvReportEmployee = v.findViewById(R.id.rvReportEmployee);

        refData = FirebaseDatabase.getInstance().getReference().child(REF_REPORT_WAITING_ADMIN_APPROVAL).child(REF_JOBSEEKERS_NODE);
        FirebaseRecyclerOptions<ReportWaitingAdminApprovalModel> options = new FirebaseRecyclerOptions.Builder<ReportWaitingAdminApprovalModel>()
                .setQuery(refData, ReportWaitingAdminApprovalModel.class).build();

        adaptor = new FirebaseRecyclerAdapter<ReportWaitingAdminApprovalModel, AdminListReportViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final AdminListReportViewHolder holder, int position, @NonNull final ReportWaitingAdminApprovalModel model) {
                /*Lấy tên người bị tố cáo*/
                DatabaseReference refNameAccused = FirebaseDatabase.getInstance().getReference().
                        child(REF_JOBSEEKERS_NODE).child(model.getIdAccused()).child("name");

                refNameAccused.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        nameAccused = (String) dataSnapshot.getValue();
                        timeLeftSendReport = Util.getSubTime(model.getDateSendReport());
                        holder.txtName.setText(nameAccused);
                        holder.txtDateSendReport.setText(timeLeftSendReport);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
            @NonNull
            @Override
            public AdminListReportViewHolder onCreateViewHolder(@NonNull  ViewGroup viewGroup, int pos) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_report_row_item, viewGroup, false);
                final AdminListReportViewHolder viewHolder = new AdminListReportViewHolder(v);

                viewHolder.SetOnClickListener(new AdminListReportViewHolder.ClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        modelReportWaiting = getItem(viewHolder.getAdapterPosition());
                        //todo: jum Activity
                        //Nhảy qua activity show detail report
                        Intent intent = new Intent(getActivity(), AdminShowDetailReportJobseekerActivity.class);

                        intent.putExtra(ID_REPORT, modelReportWaiting.getIdReport());
                        intent.putExtra(DATE_SEND_REPORT, modelReportWaiting.getDateSendReport());
                        intent.putExtra(ID_ACCUSED, modelReportWaiting.getIdAccused());
                        startActivity(intent);
                    }
                });

                viewHolder.ibtnSendWarningReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modelReportWaiting = getItem(viewHolder.getAdapterPosition());
                        setUpDialogSendWarning( modelReportWaiting);
                    }
                });
                viewHolder.ibtnIgnoreReportJobSeeker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modelReportWaiting = getItem(viewHolder.getAdapterPosition());
                        setupDialogIgnoreReport( modelReportWaiting);
                    }
                });
                return viewHolder;
            }


        };

        adaptor.startListening();

        rvReportEmployee.setAdapter(adaptor);
        rvReportEmployee.setLayoutManager(new LinearLayoutManager(getActivity()));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_recyclerview_admin);
        rvReportEmployee.setLayoutAnimation(animationController);
        runLayoutAnimation(rvReportEmployee);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adaptor.stopListening();
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_recyclerview_admin);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void setUpDialogSendWarning(final ReportWaitingAdminApprovalModel model) {
        final EditText edtMessageFromAdmin;
        Button btnSenWarningOK;
        Button btnSenWarningCancel;

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.admin_report_dialog_send_warning, null);
        edtMessageFromAdmin = dialogView.findViewById(R.id.edtMessageFromAdmin);
        btnSenWarningOK = dialogView.findViewById(R.id.btnSenWarningOK);
        btnSenWarningCancel = dialogView.findViewById(R.id.btnSenWarningCancel);


        final Dialog dialog = new Dialog(getActivity());
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
                //todo: presenter send warning, update data on firebase
                presenter.onSendWarningReportToJobseeker(model, edtMessageFromAdmin.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setupDialogIgnoreReport( final ReportWaitingAdminApprovalModel model){
        Button btnIgnoreReportCancel;
        Button btnIgnoreReportOK;

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.admin_report_dialog_ignore, null);
        btnIgnoreReportOK = view.findViewById(R.id.btnIgnoreReportOK);
        btnIgnoreReportCancel = view.findViewById(R.id.btnIgnoreReportCancel);

        final Dialog dialog = new Dialog(getActivity());
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
                //todo: presenter ...
                presenter.onIgnoreReportJobseeker(model);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
