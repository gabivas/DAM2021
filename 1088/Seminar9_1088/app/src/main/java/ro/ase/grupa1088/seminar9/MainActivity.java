package ro.ase.grupa1088.seminar9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput("text.txt", Context.MODE_PRIVATE));
            writer.write("Parsare-text-initial-si-preluare-cuvant-cheie");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getApplicationContext(), citireDinFisier(getApplicationContext(), "text.txt"), Toast.LENGTH_LONG).show();
        Log.i("textCitit",citireDinFisier(getApplicationContext(),"text.txt"));

    }

    private String citireDinFisier(Context context, String numeFisier) {
        String rezultat = "";
        try {
            InputStream inputStream = context.openFileInput(numeFisier);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
                StringBuilder stringBuilder = new StringBuilder();
                String linieCitita = "";
                while ((linieCitita = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(linieCitita);
                }
                inputStream.close();
                rezultat = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rezultat;
    }


}