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

    public static final String LAPTOP_REFFERENCE = "laptopuri";
    private final DatabaseReference reference;

    private static FirebaseService firebaseService;

    private FirebaseService() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseService getInstance() {
        if (firebaseService == null) {
            synchronized (FirebaseService.class) {
                if (firebaseService == null) {
                    firebaseService = new FirebaseService();
                }
            }
        }

        return firebaseService;
    }

    public void insert(Laptop laptop) {
        if (laptop == null || (laptop.getId() != null && !laptop.getId().trim().isEmpty())) {
            return;
        }

        String id = reference.push().getKey();
        laptop.setId(id);
        reference.child(laptop.getId()).setValue(laptop);

    }

    public void update(Laptop laptop){
        if (laptop == null || laptop.getId() == null && laptop.getId().trim().isEmpty()) {
            return;
        }
        reference.child(laptop.getId()).setValue(laptop);

    }

    public void delete(Laptop laptop){
        if (laptop == null || laptop.getId() == null && laptop.getId().trim().isEmpty()) {
            return;
        }
        reference.child(laptop.getId()).removeValue();

    }

    public void adaugareDateListener(Callback<List<Laptop>> callback){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Laptop> laptopList=new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    Laptop laptop=data.getValue(Laptop.class);
                    if(laptop!=null){
                        laptopList.add(laptop);
                    }
                }
                callback.rulareRezultatPeUI(laptopList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService","Laptopul nu este disponibil");
            }
        });
    }

}
