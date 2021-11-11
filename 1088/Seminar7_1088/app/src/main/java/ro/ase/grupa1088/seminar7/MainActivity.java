package ro.ase.grupa1088.seminar7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.grupa1088.seminar7.retea.HttpManager;
import ro.ase.grupa1088.seminar7.util.Film;
import ro.ase.grupa1088.seminar7.util.FilmAdapter;
import ro.ase.grupa1088.seminar7.util.FilmJsonParser;

public class MainActivity extends AppCompatActivity {
private final static String URL_FILME="https://jsonkeeper.com/b/0R8B";
private ListView lvFilme;
private List<Film> listaFilme=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializareComponente();
        listaFilme.add(new Film("Corpse Bride","Muzical",8,
                2005,123456789L,"Mike Johnson"));
        listaFilme.add(new Film("The Terminator","actiune",7
                ,2019,12345676L,"James Cameron"));
        incarcareFilmeHttp();

    }

    private void incarcareFilmeHttp() {
        Thread thread=new Thread(){
            @Override
            public void run() {
                HttpManager manager= new HttpManager(URL_FILME);
                String rezultat= manager.process();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        getFilmeFromHttpCallback(rezultat);
                    }
                });
            }
        };
        thread.start();
    }

    private void getFilmeFromHttpCallback(String rezultat) {
        Toast.makeText(getApplicationContext(),rezultat,
                Toast.LENGTH_SHORT).show();
        listaFilme.addAll(FilmJsonParser.fromJson(rezultat));
        notificareAdapter();
    }

    private void notificareAdapter() {
        FilmAdapter adapter= (FilmAdapter) lvFilme.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initializareComponente() {
        lvFilme=findViewById(R.id.main_lv_filme);
        seteazaFilmAdapter();
    }

    private void seteazaFilmAdapter() {
        FilmAdapter adapter=new FilmAdapter(getApplicationContext(),R.layout.lv_inregistrare_view,
                listaFilme,getLayoutInflater());
        lvFilme.setAdapter(adapter);
    }
}