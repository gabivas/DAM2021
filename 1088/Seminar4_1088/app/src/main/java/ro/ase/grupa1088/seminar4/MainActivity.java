package ro.ase.grupa1088.seminar4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private FloatingActionButton floatingActionButton;
public static final int REQUEST_CODE=200;
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
                ArrayAdapter<Telefon> adapter=new ArrayAdapter<Telefon>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaTelefoane);

                listView.setAdapter(adapter);
            }

        }

    }
}