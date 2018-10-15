package com.example.team32gb.jobit;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.team32gb.jobit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab3CreateCV extends Fragment {
    private ListView lvProject;
    private ImageButton imgbtnAdd;
    //Dialog
    private View vPopupInputDiaglog;
    private TextView tvNameProject;
    private TextView tvDecription;
    private TextView tvRole;
    private TextView tvNumberMeber;

    private EditText edtNameProject;
    private EditText edtDecription;
    private EditText edtRole;
    private EditText edtNumberMeber;

    private Button btnSaveAddProject;
    private Button btnCancelAddProject;

    private Context context=null;

    public FragmentTab3CreateCV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context= getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tabView= inflater.inflate(R.layout.tab3_create_cv, container, false);

        lvProject= tabView.findViewById(R.id.lvProject);
        imgbtnAdd = tabView.findViewById(R.id.imgbtnAdd);
        //Show dialog on click img btn Add
        imgbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()== R.id.imgbtnAdd){
                    final AlertDialog.Builder alerDialogBuider = new AlertDialog.Builder(context);
                    alerDialogBuider.setTitle("Project");

                    inItpopupViewControls();

                    alerDialogBuider.setView(vPopupInputDiaglog);
                    //Create diaglog and show
                    final AlertDialog alertDialog = alerDialogBuider.create();
                    alertDialog.show();
                    //OnClickListener: btn cancel and save
                    btnCancelAddProject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO
                            //Do sth with list view
                            alertDialog.cancel();
                        }
                    });
                    btnSaveAddProject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });

                }
            }
        });





        return tabView;
    }
    private void inItpopupViewControls(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        vPopupInputDiaglog = layoutInflater.inflate(R.layout.tab3_popup_dialog_project, null);

        edtNameProject= vPopupInputDiaglog.findViewById(R.id.edtNameProject);
        edtDecription = vPopupInputDiaglog.findViewById(R.id.edtDecription);
        edtRole = vPopupInputDiaglog.findViewById(R.id.edtRole);
        edtNumberMeber = vPopupInputDiaglog.findViewById(R.id.edtNumberMember);

        btnSaveAddProject= vPopupInputDiaglog.findViewById(R.id.btnSaveAddProject);
        btnCancelAddProject= vPopupInputDiaglog.findViewById(R.id.btnCancelAddProject);
    }
}
