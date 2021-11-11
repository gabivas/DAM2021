package ro.ase.grupa1088.seminar3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AdaugareTelefonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_telefon);
        Spinner spinnerRetea=findViewById(R.id.spinnerRetea);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.valori_retea,R.layout.support_simple_spinner_dropdown_item);
        spinnerRetea.setAdapter(adapter);
        final EditText etBrand=findViewById(R.id.editTextBrand);
        final EditText etModel=findViewById(R.id.editTextModel);
        final EditText etMemorie=findViewById(R.id.editTextMemorie);
        final EditText etDataAparitie=findViewById(R.id.editTextDataAparitie);
        Button buttonSalvare= findViewById(R.id.buttonSalvare);
        buttonSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etBrand.getText().toString().isEmpty())
                    etBrand.setError("Nu ai introdus brandul");
            }
        });

    }
}