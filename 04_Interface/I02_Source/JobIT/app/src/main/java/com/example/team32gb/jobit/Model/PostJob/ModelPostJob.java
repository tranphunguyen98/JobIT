package com.example.team32gb.jobit.Model.PostJob;

        import android.content.ClipData;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.List;

public class ModelPostJob {
    DatabaseReference databaseReference;
    public ModelPostJob() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tinTuyenDungs");
    }
    public void SavePost(String Uid, DataPostJob dataPostJob){
        databaseReference.child(Uid).push().setValue(dataPostJob);
    }
    public void savePostEdit(String idJob, String uid, DataPostJob dataPostJob) {
        databaseReference.child(uid).child(idJob).setValue(dataPostJob);
    }
}
