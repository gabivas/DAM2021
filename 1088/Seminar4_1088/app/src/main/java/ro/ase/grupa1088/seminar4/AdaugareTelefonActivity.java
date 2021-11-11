package ro.ase.grupa1088.seminar4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdaugareTelefonActivity extends AppCompatActivity {
public static final String ADAUGARE_TELEFON="adaugaTelefon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_telefon);
        Spinner spinnerRetea = findViewById(R.id.spinnerRetea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.valori_retea, R.layout.support_simple_spinner_dropdown_item);
        spinnerRetea.setAdapter(adapter);
        final EditText etBrand = findViewById(R.id.editTextBrand);
        final EditText etModel = findViewById(R.id.editTextModel);
        final EditText etMemorie = findViewById(R.id.editTextMemorie);
        final EditText etDataAparitie = findViewById(R.id.editTextDataAparitie);
        //22/10/2021
        final String FORMAT_DATA="dd/MM/yyyy";
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        final Intent intent = getIntent();
        Button buttonSalvare = findViewById(R.id.buttonSalvare);
        buttonSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etBrand.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Introduceti brand", Toast.LENGTH_LONG).show();
                else if (etModel.getText().toString().isEmpty())
                    etModel.setError("Introduceti model");
                else if (etMemorie.getText().toString().isEmpty())
                    etMemorie.setError("Introduceti memorie");
                else {
                    SimpleDateFormat sdf=new SimpleDateFormat(FORMAT_DATA,Locale.US);
                    try {
                        sdf.parse(etDataAparitie.getText().toString());
                        Date dataAparitie=new Date(etDataAparitie.getText().toString());
                        String brand= etBrand.getText().toString();
                        String model= etModel.getText().toString();
                        int memorie=Integer.parseInt(etMemorie.getText().toString());
                        Retea retea= Retea.valueOf(spinnerRetea.getSelectedItem().toString().toUpperCase());
                        RadioButton radioButton=findViewById(radioGroup.getCheckedRadioButtonId());
                        Culoare culoare = Culoare.valueOf(radioButton.getText().toString());
                        Telefon telefon=new Telefon(brand,model,memorie,dataAparitie,retea, culoare);
                        //Log.e("telefon",telefon.toString());
                        intent.putExtra(ADAUGARE_TELEFON,telefon);
                        setResult(RESULT_OK,intent);
                        finish();



                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
