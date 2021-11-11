package ro.ase.grupa1086.seminar5_6;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdaugareMasinaActivity extends AppCompatActivity {
public static final String ADAUGA_MASINA_LABEL="adaugaMasina";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_masina);
        Spinner spinnerMotorizare = findViewById(R.id.spinnerMotorizare);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adaugare_motorizare,
                R.layout.support_simple_spinner_dropdown_item);
        spinnerMotorizare.setAdapter(adapter);
        final EditText etMarca = findViewById(R.id.editTextMarca);
        final EditText etNrInmatriculare = findViewById(R.id.editTextNrInmatriculare);
        final EditText etDataInmatriculare = findViewById(R.id.editTextDataInmatriculare);
        final EditText etAnFabricatie = findViewById(R.id.editTextAnFabricatie);
        //22/10/2021
        final String DATA_FORMAT = "dd/MM/yyyy";
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        final Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.EDIT_MASINA)){
            Masina masina= (Masina) intent.getSerializableExtra(MainActivity.EDIT_MASINA);
            etMarca.setText(masina.getMarca());
            etDataInmatriculare.setText(new SimpleDateFormat(DATA_FORMAT,Locale.US).
                    format(masina.getDataInmatriculare()));
            etNrInmatriculare.setText(masina.getNrInmatriculare());
            etAnFabricatie.setText(""+masina.getAnFabricatie());
            ArrayAdapter<String> adaptor= (ArrayAdapter<String>) spinnerMotorizare.getAdapter();
            for(int i=0;i<adaptor.getCount();i++){
                if(adaptor.getItem(i).equals(masina
                .getTipCombustibil())){
                    spinnerMotorizare.setSelection(i);
                    break;
                }
            }
            if(masina.getCuloareVehicul().equals("ALB"))
                radioGroup.check(R.id.radioButton1);
            else if(masina.getCuloareVehicul().equals("NEGRU"))
                radioGroup.check(R.id.radioButton2);
            else
                radioGroup.check(R.id.radioButton3);
        }


        Button btnSalvare = findViewById(R.id.buttonSalvare);
        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etMarca.getText().toString().isEmpty())
                    etMarca.setError("Introduceti marca");
                else if (etNrInmatriculare.getText().toString().isEmpty())
                    etNrInmatriculare.setError("Introduceti nr");
                else if (etAnFabricatie.getText().toString().isEmpty())
                    etMarca.setError("Introduceti anul");
                else if (etDataInmatriculare.getText().toString().isEmpty())
                    etDataInmatriculare.setError("Introduceti data");
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT, Locale.US);
                    try {
                        Date dataInmatriculare = sdf.parse(etDataInmatriculare.getText().toString());
                        String marca = etMarca.getText().toString();
                        String nrInmatriculare = etNrInmatriculare.getText().toString();
                        //Date dataInmatriculare = new Date(etDataInmatriculare.getText().toString());
                        int anFabricatie = Integer.parseInt(etAnFabricatie.getText().toString());
                        Motorizare motorizare = Motorizare.valueOf(spinnerMotorizare.getSelectedItem().
                                toString().toUpperCase());
                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        Culoare culoare = Culoare.valueOf(radioButton.getText().toString().toUpperCase());

                        Masina masina = new Masina(marca, nrInmatriculare, dataInmatriculare, anFabricatie,
                                motorizare, culoare);
                        //Log.e("masina", masina.toString());
                        intent.putExtra(ADAUGA_MASINA_LABEL,masina);
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