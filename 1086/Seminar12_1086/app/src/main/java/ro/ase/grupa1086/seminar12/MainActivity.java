package ro.ase.grupa1086.seminar12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private TextInputEditText tietMarca;
private Spinner spnProcesoare;
private Button btnClear;
private FloatingActionButton fabDelete;
private FloatingActionButton fabSave;
private ListView lvLaptopuri;
private List<Laptop> laptopuri=new ArrayList<>();
private FirebaseService firebaseService;
private int indexLaptopSelectat=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(getApplicationContext(),"S-a conectat la firebase",
//                Toast.LENGTH_LONG).show();
        initializareComponente();
        firebaseService=FirebaseService.getInstance();
        firebaseService.adaugareDateListener(dataChangeCallBack());
    }

    private Callback<List<Laptop>> dataChangeCallBack() {
        return new Callback<List<Laptop>>() {
            @Override
            public void rulareRezultatPeUI(List<Laptop> rezultat) {
                laptopuri.clear();
                laptopuri.addAll(rezultat);
                notificareLVLaptopAdapter();
                clearText();

            }
        };
    }

    private void notificareLVLaptopAdapter() {
        LaptopAdapter adapter= (LaptopAdapter) lvLaptopuri.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initializareComponente() {
        tietMarca=findViewById(R.id.main_tiet_marca);
        spnProcesoare=findViewById(R.id.main_spn_procesoare);
        btnClear=findViewById(R.id.main_btn_clear);
        fabDelete=findViewById(R.id.main_fab_delete);
        fabSave=findViewById(R.id.main_fab_save);
        lvLaptopuri=findViewById(R.id.main_lv_laptopuri);
        adauagaSpinnerProcesoare();
        adaugareListViewLaptopAdapter();
        btnClear.setOnClickListener(getClearClickEvent());
        fabSave.setOnClickListener(getSaveClickEvent());
        fabDelete.setOnClickListener(getDeleteClickEvent());
        lvLaptopuri.setOnItemClickListener(getLaptopSelectatClickEvent());

    }

    private AdapterView.OnItemClickListener getLaptopSelectatClickEvent() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexLaptopSelectat=i;
                Laptop laptop= laptopuri.get(i);
                tietMarca.setText(laptop.getMarca());
                selecteazaProcesor(laptop.getProcesor());
            }
        };
    }

    private void selecteazaProcesor(String procesor) {
        ArrayAdapter<String> adapter= (ArrayAdapter<String>) spnProcesoare.getAdapter();
        for(int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).equals(procesor)){
                spnProcesoare.setSelection(i);
                break;
            }
        }
    }

    private View.OnClickListener getDeleteClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexLaptopSelectat !=-1){
                    firebaseService.delete(laptopuri.get(indexLaptopSelectat));
                }
            }
        };
    }

    private View.OnClickListener getSaveClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(esteValid()){
                    if(indexLaptopSelectat==-1){
                        Laptop laptop=updateLaptopFromView(null);
                        firebaseService.insert(laptop);
                    }
                    else{
                        Laptop laptop=updateLaptopFromView(laptopuri.get(indexLaptopSelectat).getId());
                        firebaseService.update(laptop);
                    }
                }
            }
        };
    }

    private boolean esteValid() {
        if(tietMarca.getText()==null || tietMarca.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Validarea nu a trecut", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Laptop updateLaptopFromView(String id) {
        Laptop laptop=new Laptop();
        laptop.setId(id);
        laptop.setMarca(tietMarca.getText().toString());
        laptop.setProcesor((String) spnProcesoare.getSelectedItem());
        return laptop;
    }

    private View.OnClickListener getClearClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
            }
        };
    }

    private void clearText() {
        tietMarca.setText(null);
        spnProcesoare.setSelection(0);
        indexLaptopSelectat= -1;
    }

    private void adaugareListViewLaptopAdapter() {
        LaptopAdapter adapter= new LaptopAdapter(getApplicationContext(),
                R.layout.lv_row_view,laptopuri,getLayoutInflater());
        lvLaptopuri.setAdapter(adapter);
    }

    private void adauagaSpinnerProcesoare() {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.optiuni_procesoare, android.R.layout.simple_spinner_dropdown_item);
        spnProcesoare.setAdapter(adapter);
    }
}