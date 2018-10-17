package com.example.team32gb.jobit;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.ProjectInCV;
import com.example.team32gb.jobit.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab3CreateCV extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    final static String tag = "TAB3";
    public final static int MAX_PROJECT = 2;
    private int countProject = 0;
    private int pos = 0;
    private ListView lvProject;
    private ImageButton imgbtnAdd;
    //Dialog
    private View vPopupInputDiaglog;
    private TextInputLayout wrappeNameProject;
    private TextInputLayout wrapperDescription;
    private TextInputLayout wrapperRole;
    private TextInputLayout wrapperNumberMember;
    private TextInputEditText edtNameProject;
    private TextInputEditText edtDescription;
    private TextInputEditText edtRole;
    private TextInputEditText edtNumberMeber;
    private Button btnSaveAddProject;
    private Button btnCancelAddProject;
    private Button btnSaveEditProject;
    private Button btnDeleteProject;
    private Dialog dialog;  //dialog shows to fill project information

    private CustomAdaptorProjectInCV adaptor;
    private Context context = null;

    public FragmentTab3CreateCV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tabView = inflater.inflate(R.layout.tab3_create_cv, container, false);

        lvProject = tabView.findViewById(R.id.lvProject);
        imgbtnAdd = tabView.findViewById(R.id.imgbtnAdd);

        adaptor = new CustomAdaptorProjectInCV(context);
        lvProject.setAdapter(adaptor);
        lvProject.setOnItemClickListener(this);
        //Show dialog on click img btn Add
        imgbtnAdd.setOnClickListener(this);
        return tabView;
    }

    private void inItpopupDiaglog() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        vPopupInputDiaglog = layoutInflater.inflate(R.layout.tab3_popup_dialog_project, null);
        dialog = new Dialog(context);
        dialog.setContentView(vPopupInputDiaglog);
        dialog.setTitle("Project");

        wrappeNameProject = vPopupInputDiaglog.findViewById(R.id.wrappernameProject);
        wrapperDescription = vPopupInputDiaglog.findViewById(R.id.wrapperDescription);
        wrapperRole = vPopupInputDiaglog.findViewById(R.id.wrapperRole);
        wrapperNumberMember = vPopupInputDiaglog.findViewById(R.id.wrapperNumberMember);

        edtNameProject = vPopupInputDiaglog.findViewById(R.id.edtNameProject);
        edtDescription = vPopupInputDiaglog.findViewById(R.id.edtDescription);
        edtRole = vPopupInputDiaglog.findViewById(R.id.edtRole);
        edtNumberMeber = vPopupInputDiaglog.findViewById(R.id.edtNumberMember);

        btnSaveAddProject = vPopupInputDiaglog.findViewById(R.id.btnSaveAddProject);
        btnCancelAddProject = vPopupInputDiaglog.findViewById(R.id.btnCancelAddProject);
        btnSaveEditProject = vPopupInputDiaglog.findViewById(R.id.btnSaveEditProject);
        btnDeleteProject = vPopupInputDiaglog.findViewById(R.id.btnDeleteProject);

        btnSaveAddProject.setOnClickListener(this);
        btnCancelAddProject.setOnClickListener(this);
        btnSaveEditProject.setOnClickListener(this);
        btnDeleteProject.setOnClickListener(this);
    }

    private ProjectInCV getProjectInfor() {
        try {
            if (edtNameProject.getText().toString().equals("")) {
                wrappeNameProject.setError("Bạn phải nhập tên project");
            } else if (edtDescription.getText().toString().equals("")) {
                wrappeNameProject.setError("Bạn phải nhập mô tả");
            }

            ProjectInCV project = new ProjectInCV(edtNameProject.getText().toString(), edtDescription.getText().toString(), edtRole.getText().toString(), edtNumberMeber.getInputType());
            return project;
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtnAdd:
                if (countProject >= MAX_PROJECT) {
                    Toast.makeText(getContext(), "Số project tối đa là " + MAX_PROJECT, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    break;
                }
                inItpopupDiaglog();

                btnSaveAddProject.setEnabled(true);
                dialog.show();
                break;
            case R.id.btnSaveAddProject:
                ProjectInCV newProject = getProjectInfor();
                Log.e(tag, "Da lay thong tin project");
                adaptor.getItem().add(newProject);

                Log.e(tag, "Da add thong tin project vao adaptor");
                //adaptor.notifyDataSetChanged();
                adaptor.notifyDataSetInvalidated();
                //TODO listview not update to show imidiately

                countProject++;
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                break;
            case R.id.btnCancelAddProject:
                dialog.dismiss();
                break;
            case R.id.btnSaveEditProject:
                ProjectInCV newProject2 = getProjectInfor();
                adaptor.deleteItem(pos);
                adaptor.getItem().add(pos, newProject2);
                //TODO listview not update to show imidiately
                adaptor.notifyDataSetInvalidated();
                //adaptor.notifyDataSetChanged();
                dialog.dismiss();
                btnSaveEditProject.setVisibility(View.INVISIBLE);
                btnDeleteProject.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnDeleteProject:
                adaptor.deleteItem(pos);
                adaptor.notifyDataSetInvalidated();
                countProject--;
                dialog.dismiss();

                btnSaveEditProject.setVisibility(View.INVISIBLE);
                btnDeleteProject.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        inItpopupDiaglog();
        ProjectInCV project = (ProjectInCV) adaptor.getItem(position);

        edtNameProject.setText(project.getName());
        edtDescription.setText(project.getDecription());
        edtRole.setText(project.getRole());
        edtNumberMeber.setText(String.valueOf(project.getNumberMember()));

        btnSaveAddProject.setEnabled(false);
        btnSaveEditProject.setVisibility(View.VISIBLE);
        btnDeleteProject.setVisibility(View.VISIBLE);

        pos = position;
        dialog.show();
    }
}
