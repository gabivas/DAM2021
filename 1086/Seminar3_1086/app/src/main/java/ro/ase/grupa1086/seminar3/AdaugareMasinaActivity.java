package ro.ase.grupa1086.seminar3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AdaugareMasinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_masina);
        Spinner spinnerMotorizare=findViewById(R.id.spinnerMotorizare);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.adaugare_motorizare,
                R.layout.support_simple_spinner_dropdown_item);
        spinnerMotorizare.setAdapter(adapter);
        final EditText etMarca=findViewById(R.id.editTextMarca);
        final EditText etNrInmatriculare=findViewById(R.id.editTextNrInmatriculare);
        final EditText etDataInmatriculare=findViewById(R.id.editTextDataInmatriculare);
        final EditText etAnFabricatie=findViewById(R.id.editTextAnFabricatie);
        Button btnSalvare=findViewById(R.id.buttonSalvare);
        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if(etMarca.getText().toString().isEmpty())
            etMarca.setError("Introduceti marca");
        }
        });

    }
}