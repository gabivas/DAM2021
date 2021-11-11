package ro.ase.grupa1086.seminar7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.grupa1086.seminar7.retea.HttpManager;
import ro.ase.grupa1086.seminar7.util.Student;
import ro.ase.grupa1086.seminar7.util.StudentAdapter;
import ro.ase.grupa1086.seminar7.util.StudentJsonParser;

public class MainActivity extends AppCompatActivity {
    private ListView lvStudenti;
    private List<Student> listaStudenti = new ArrayList<>();
    private final static String URL_STUDENTI = "https://jsonkeeper.com/b/FOD7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializareComponente();
        listaStudenti.add(new Student("Diana",3241L,3,
                2017,"CSIE"));
        listaStudenti.add(new Student("Ioana",4321L,7,
                2015,"BT"));
        incarcareStudentiHttp();

    }

    private void incarcareStudentiHttp() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpManager manager=new HttpManager(URL_STUDENTI);
                String rezultat= manager.process();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        getStudentiFromHttpCallBack(rezultat);
                    }
                });
            }
        };
        thread.start();
    }

    private void getStudentiFromHttpCallBack(String rezultat) {
        Toast.makeText(getApplicationContext(),rezultat,Toast.LENGTH_SHORT).show();
        listaStudenti.addAll(StudentJsonParser.fromJson(rezultat));
        notificareAdapter();
    }

    private void notificareAdapter() {
        StudentAdapter adapter= (StudentAdapter) lvStudenti.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initializareComponente() {
        lvStudenti = findViewById(R.id.main_lv_studenti);
        adaugareStudentAdapter();
    }

    private void adaugareStudentAdapter() {
        StudentAdapter adapter= new StudentAdapter(getApplicationContext(),R.layout.lv_inregistrare_view,
                listaStudenti,getLayoutInflater());
        lvStudenti.setAdapter(adapter);
    }
}