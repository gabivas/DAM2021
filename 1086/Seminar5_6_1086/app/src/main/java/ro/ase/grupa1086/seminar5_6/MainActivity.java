package ro.ase.grupa1086.seminar5_6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

    public static final int REQUEST_CODE_EDIT=300;
    public static final String EDIT_MASINA="editareMasina";

    public int poz;
    private Intent intent;

    private ListView listView;
    List<Masina> listaMasini = new ArrayList<Masina>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AdaugareMasinaActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        listView=findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                poz=position;
                intent=new Intent(getApplicationContext(),AdaugareMasinaActivity.class);
                intent.putExtra(EDIT_MASINA,listaMasini.get(position));
                startActivityForResult(intent,REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
               final Masina masina=listaMasini.get(position);
               final MasinaAdapter adapter= (MasinaAdapter) listView.getAdapter();
                AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Condirmare stergere masina din lista")
                        .setMessage("Sigur doriti stergerea")
                        .setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Nu s-a sters nimic!",
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listaMasini.remove(masina);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(),"S-a sters masina: "+
                                        masina.toString(),
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
                Intent intent = new Intent(this,NewActivity.class);
                startActivity(intent);
                break;
            case R.id.optiune2:
                Toast.makeText(this,"Ai selectat opt2", Toast.LENGTH_LONG).show();
                break;
            case R.id.optiune3:
                Toast.makeText(this,"Ai selectat opt3", Toast.LENGTH_LONG).show();
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Masina masina= (Masina) data.getSerializableExtra(AdaugareMasinaActivity.ADAUGA_MASINA_LABEL);
            if(masina!=null){
                //Log.e("masinaPrimita",masina.toString());
                listaMasini.add(masina);
                //ArrayAdapter<Masina> adapter= new ArrayAdapter<Masina>(getApplicationContext(),
                        //android.R.layout.simple_list_item_1,listaMasini);
                MasinaAdapter adapter=new MasinaAdapter(getApplicationContext(),R.layout.elemlistview,
                        listaMasini,getLayoutInflater()){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        Masina masina= listaMasini.get(position);
                        TextView tvAnFabricatie=view.findViewById(R.id.anFabricatie);
                        if(masina.getAnFabricatie()>=2016)
                            tvAnFabricatie.setTextColor(Color.GREEN);
                        else
                            tvAnFabricatie.setTextColor(Color.RED);

                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }
        }
        else if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null){
            Masina masina= (Masina) data.getSerializableExtra(AdaugareMasinaActivity.ADAUGA_MASINA_LABEL);
            {
                if(masina!=null){
                    listaMasini.get(poz).setMarca(masina.getMarca());
                    listaMasini.get(poz).setDataInmatriculare(masina.getDataInmatriculare());
                    listaMasini.get(poz).setNrInmatriculare(masina.getNrInmatriculare());
                    listaMasini.get(poz).setAnFabricatie(masina.getAnFabricatie());
                    listaMasini.get(poz).setTipCombustibil(masina.getTipCombustibil());
                    listaMasini.get(poz).setCuloareVehicul(masina.getCuloareVehicul());
                    MasinaAdapter adapter=new MasinaAdapter(getApplicationContext(),R.layout.elemlistview,
                            listaMasini,getLayoutInflater()){

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view=super.getView(position, convertView, parent);
                            Masina masina= listaMasini.get(position);
                            TextView tvAnFabricatie=view.findViewById(R.id.anFabricatie);
                            if(masina.getAnFabricatie()>=2016)
                                tvAnFabricatie.setTextColor(Color.GREEN);
                            else
                                tvAnFabricatie.setTextColor(Color.RED);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);

                }

            }
        }

    }
}