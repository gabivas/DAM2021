package ro.ase.grupa1088.seminar5_6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private FloatingActionButton floatingActionButton;
public static final int REQUEST_CODE=200;

    public static final int REQUEST_CODE_EDIT = 300;

    public static final String EDIT_MOVIE = "editMovie";


    public int poz;

    private Intent intent;


    private ListView listView;
List<Telefon> listaTelefoane= new ArrayList<Telefon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),AdaugareTelefonActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
listView=findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getApplicationContext(), AdaugareTelefonActivity.class);
                intent.putExtra(EDIT_MOVIE, listaTelefoane.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Telefon movie = listaTelefoane.get(position);


                final TelefonAdapter adapter = (TelefonAdapter) listView.getAdapter();


                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Sigur doriti stergerea?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nu s-a sters nimic!",
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listaTelefoane.remove(movie);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "S-a sters filmul: "+movie.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optiune1:
                Intent intent=new Intent(this, NewActivity.class);
                startActivity(intent);
                break;

            case R.id.optiune2:
                Toast.makeText(this,"Ai selectat opt2",Toast.LENGTH_LONG).show();
                break;
            case R.id.optiune3:
                Toast.makeText(this,"Ai selectat opt3",Toast.LENGTH_LONG).show();
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Telefon telefon = (Telefon) data.getSerializableExtra(AdaugareTelefonActivity.ADAUGARE_TELEFON);
            //Log.e("obiectPrimit",telefon.toString());
            if(telefon!=null){
                listaTelefoane.add(telefon);
               // ArrayAdapter<Telefon> adapter=new ArrayAdapter<Telefon>(getApplicationContext(),
                     //   android.R.layout.simple_list_item_1, listaTelefoane);

                TelefonAdapter adapter = new TelefonAdapter(getApplicationContext(), R.layout.elemlistview,
                        listaTelefoane, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Telefon movie =  listaTelefoane.get(position);

                        TextView tvProfit = view.findViewById(R.id.profit);
                        if(movie.getMemorie() > 100000)
                            tvProfit.setTextColor(Color.GREEN);
                        else
                            tvProfit.setTextColor(Color.RED);

                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }

        }

        else
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            Telefon movie = (Telefon) data.getSerializableExtra(AdaugareTelefonActivity.ADAUGARE_TELEFON);
            {
                if (movie!=null)
                {
                    listaTelefoane.get(poz).setBrand(movie.getBrand());
                    listaTelefoane.get(poz).setDataAparitie(movie.getDataAparitie());
                    listaTelefoane.get(poz).setModel(movie.getModel());
                    listaTelefoane.get(poz).setMemorie(movie.getMemorie());
                    listaTelefoane.get(poz).setRetea(movie.getRetea());
                    listaTelefoane.get(poz).setCuloare(movie.getCuloare());

                    TelefonAdapter adapter = new TelefonAdapter(getApplicationContext(), R.layout.elemlistview,
                            listaTelefoane, getLayoutInflater()){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            Telefon movie =  listaTelefoane.get(position);

                            TextView tvProfit = view.findViewById(R.id.profit);
                            if(movie.getMemorie() > 100000)
                                tvProfit.setTextColor(Color.GREEN);
                            else
                                tvProfit.setTextColor(Color.RED);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }
        }
    }
}