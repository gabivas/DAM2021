package ro.ase.grupa1086.seminar12;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    private final DatabaseReference databaseReference;
    public static final String CADOU_REFERENCE="cadouri";

    private static FirebaseService firebaseService;

    public FirebaseService() {
        databaseReference= FirebaseDatabase.getInstance().getReference(CADOU_REFERENCE);
    }

    public static FirebaseService getInstance(){
        if(firebaseService==null){
            synchronized (FirebaseService.class){
                if(firebaseService==null){
                   firebaseService=new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void insert(Cadou cadou){
        if(cadou == null || (cadou.getId()!=null && !cadou.getId().trim().isEmpty())){
            return;
        }
        String id= databaseReference.push().getKey();
        cadou.setId(id);
        databaseReference.child(cadou.getId()).setValue(cadou);
    }

    public void update(Cadou cadou){
        if(cadou == null || cadou.getId()==null || cadou.getId().trim().isEmpty()){
            return;
        }
        databaseReference.child(cadou.getId()).setValue(cadou);
    }
    public void delete(Cadou cadou){
        if(cadou == null || cadou.getId()==null || cadou.getId().trim().isEmpty()){
            return;
        }
        databaseReference.child(cadou.getId()).removeValue();
    }

    public void notificareEventListener(Callback<List<Cadou>> callback){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Cadou> cadouri=new ArrayList<>();
                for(DataSnapshot data:snapshot.getChildren()){
                    Cadou cadou= data.getValue(Cadou.class);
                    if(cadou!=null){
                        cadouri.add(cadou);
                    }
                }
                callback.rulareRezultatPeUI(cadouri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService","Cadoul nu este disponibil");
            }
        });

    }

}
