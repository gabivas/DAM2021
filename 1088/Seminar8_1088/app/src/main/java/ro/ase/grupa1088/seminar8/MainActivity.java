package ro.ase.grupa1088.seminar8;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retea retea= new Retea(){
            @Override
            protected void onPostExecute(InputStream inputStream) {
                //Toast.makeText(getApplicationContext(),Retea.sBuf,Toast.LENGTH_LONG).show();
                //Log.i("sBuf",Retea.sBuf);
                Log.i("cursValutarConcatenat",cursValutar.toString());
            }
        };
        try {
            retea.execute(new URL("https://www.bnr.ro/nbrfxrates.xml"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}