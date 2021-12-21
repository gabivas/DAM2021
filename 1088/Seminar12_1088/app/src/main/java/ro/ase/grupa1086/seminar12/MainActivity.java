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
private TextInputEditText tietDenumire;
private Spinner spnDimensiune;
private Button btnClear;
private FloatingActionButton fabSave;
private FloatingActionButton fabDelete;
private ListView lvCadouri;
private List<Cadou> cadouri=new ArrayList<>();
private int indexCadouSelectat=-1;
private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(getApplicationContext(),"S-a conectat la firebase",
//                Toast.LENGTH_LONG).show();
        initializareComponente();
        firebaseService=FirebaseService.getInstance();
        firebaseService.notificareEventListener(modificareDateCallback());
    }

    private Callback<List<Cadou>> modificareDateCallback() {
        return new Callback<List<Cadou>>() {
            @Override
            public void rulareRezultatPeUI(List<Cadou> rezultat) {
                if(rezultat!=null){
                    cadouri.clear();
                    cadouri.addAll(rezultat);
                    notificareListViewCadouAdapter();
                    curatareFields();

                }
            }
        };
    }

    private void initializareComponente() {
        tietDenumire=findViewById(R.id.main_tiet_denumire);
        spnDimensiune=findViewById(R.id.main_spn_dimensiuni);
        btnClear=findViewById(R.id.main_btn_clear);
        fabDelete=findViewById(R.id.main_fab_delete);
        fabSave=findViewById(R.id.main_fab_save);
        lvCadouri=findViewById(R.id.main_lv_cadouri);
        adaugareDimensiuniSpinner();
        adaugareListViewCadouAdapter();
        btnClear.setOnClickListener(curatareClickEvent());
        fabSave.setOnClickListener(salvareCadouClickEvent());
        fabDelete.setOnClickListener(stergereClickEvent());
        lvCadouri.setOnItemClickListener(selectareCadouClickListner());


    }

    private AdapterView.OnItemClickListener selectareCadouClickListner() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               indexCadouSelectat=position;
               Cadou cadou=cadouri.get(position);
               tietDenumire.setText(cadou.getDenumire());
               selectareDimensiune(cadou.getDimensiune());
            }
        };
    }

    private void selectareDimensiune(String dimensiune) {
        ArrayAdapter<String> adapter= (ArrayAdapter<String>) spnDimensiune.getAdapter();
        for(int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).equals(dimensiune)){
                spnDimensiune.setSelection(i);
                break;
            }
        }
    }

    private View.OnClickListener stergereClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexCadouSelectat!=-1){
                    firebaseService.delete(cadouri.get(indexCadouSelectat));
                }
            }
        };
    }

    private View.OnClickListener salvareCadouClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(esteValid()){
                    if(indexCadouSelectat==-1){
                        Cadou cadou= adaugareCadouDinView(null);
                        firebaseService.insert(cadou);
                    }
                    else{
                        Cadou cadou= adaugareCadouDinView(cadouri.get(indexCadouSelectat).getId());
                                firebaseService.update(cadou);
                    }
                }
            }
        };
    }

    private boolean esteValid() {
        if(tietDenumire.getText()==null || tietDenumire.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Denumire invalida",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Cadou adaugareCadouDinView(String id) {
        Cadou cadou=new Cadou();
        cadou.setId(id);
        cadou.setDenumire(tietDenumire.getText().toString());
        cadou.setDimensiune((String) spnDimensiune.getSelectedItem());
        return cadou;
    }

    private View.OnClickListener curatareClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curatareFields();
            }
        };
    }

    private void curatareFields() {
        tietDenumire.setText(null);
        spnDimensiune.setSelection(0);
        indexCadouSelectat=-1;
    }

    private void adaugareListViewCadouAdapter() {
        CadouAdapter adapter= new CadouAdapter(getApplicationContext(),
                R.layout.lv_row_view,cadouri,getLayoutInflater());
        lvCadouri.setAdapter(adapter);
    }

    private void notificareListViewCadouAdapter() {
        CadouAdapter adapter= (CadouAdapter) lvCadouri.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void adaugareDimensiuniSpinner() {
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.optiuni_dimensiune, android.R.layout.simple_spinner_dropdown_item);
        spnDimensiune.setAdapter(adapter);
    }
}