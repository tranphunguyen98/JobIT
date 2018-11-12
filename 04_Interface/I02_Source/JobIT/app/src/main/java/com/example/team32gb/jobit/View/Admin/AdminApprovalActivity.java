package com.example.team32gb.jobit.View.Admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminApproval;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY;
import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class AdminApprovalActivity extends AppCompatActivity {
    public final static String ID_COMPANY = "Id company";
    public final static String DATE_SEND_APPROVAL = "Date send";

    private RecyclerView rvApproval;
    private FirebaseRecyclerAdapter<CompanyWaitingApprovalModel, AdminApprovalViewHolder> adaptor;
    private DatabaseReference refDatabase;
    private DatabaseReference refDataCompany;

    private PresenterAdminApproval presenter = new PresenterAdminApproval();
    private CompanyWaitingApprovalModel model = new CompanyWaitingApprovalModel();
    private String nameCompany;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval);
        rvApproval = findViewById(R.id.rvApproval);
        progressDialog = new ProgressDialog(AdminApprovalActivity.this);
        progressDialog.setTitle("Đang tải dữ liệu...");
        progressDialog.show();

        refDatabase = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY_WAITING_APPROVAL);
        refDataCompany = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY);

        FirebaseRecyclerOptions<CompanyWaitingApprovalModel> options = new FirebaseRecyclerOptions.Builder<CompanyWaitingApprovalModel>()
                .setQuery(refDatabase, CompanyWaitingApprovalModel.class)
                .build();

        adaptor = new FirebaseRecyclerAdapter<CompanyWaitingApprovalModel, AdminApprovalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final AdminApprovalViewHolder holder, int position,
                                            @NonNull final CompanyWaitingApprovalModel model) {

                refDataCompany.child(model.getIdCompany()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        nameCompany = (String) dataSnapshot.getValue();
                        holder.txtCompanyNameSendApproval.setText(nameCompany);
                        String timeSendLeft = Util.getSubTime(model.getDateSendApproval());
                        holder.txtDateSendApproval.setText(timeSendLeft);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public AdminApprovalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_approval_row_item, viewGroup, false);
                final AdminApprovalViewHolder viewHolder = new AdminApprovalViewHolder(view);

                viewHolder.imgbtnShowDetailApproval.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model = getItem(viewHolder.getAdapterPosition());
                        Intent intent = new Intent(AdminApprovalActivity.this, ShowDetailCopanyApprovalActivity.class);
                        intent.putExtra(ID_COMPANY, model.getIdCompany());
                        intent.putExtra(DATE_SEND_APPROVAL, model.getDateSendApproval());
                        startActivity(intent);
                    }
                });

                viewHolder.imgbtnCheckApproval.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model = getItem(viewHolder.getAdapterPosition());
                        setUpDialog(true, model);
                    }
                });
                viewHolder.imgbtnUncheckApproval.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model = getItem(viewHolder.getAdapterPosition());
                        setUpDialog(false, model);
                    }
                });

                return viewHolder;
            }
        };

        adaptor.startListening();
        rvApproval.setAdapter(adaptor);
        rvApproval.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.dismiss();
    }

    void setUpDialog(final boolean isApproval, final CompanyWaitingApprovalModel model) {
        presenter = new PresenterAdminApproval();
        final Dialog dialog = new Dialog(AdminApprovalActivity.this);

        View v = LayoutInflater.from(this).inflate(R.layout.admin_approval_dialog, null);
        Button btnApproval = v.findViewById(R.id.btnAdminApprovalOKDialog);
        Button btnApprovalCancel = v.findViewById(R.id.btnAdminApprovalCancelDialog);
        TextView txtAdminAskBeforeApproval = v.findViewById(R.id.txtAdminAskBeforeApproval);

        if (isApproval) {
            txtAdminAskBeforeApproval.setText("Bạn có chắc chắn muốn duyệt hồ sơ này?");
        } else {
            txtAdminAskBeforeApproval.setText("Bạn có chắc chắn hủy hồ sơ này?");
        }

        btnApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isApproval) {
                    if (presenter.onApproval(model, isApproval)) {
                        dialog.dismiss();
                        Toast.makeText(AdminApprovalActivity.this, "Duyệt thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        dialog.dismiss();
                        Toast.makeText(AdminApprovalActivity.this, "Duyệt thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {  //Không duyệt
                    if (presenter.onApproval(model, isApproval)) {
                        dialog.dismiss();
                        Toast.makeText(AdminApprovalActivity.this, "Hủy hồ sơ thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        dialog.dismiss();
                        Toast.makeText(AdminApprovalActivity.this, "Hủy hồ sơ thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnApprovalCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(v);
        dialog.show();
    }

}
