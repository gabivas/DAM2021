package ro.ase.grupa1088.seminar10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HorizontalScrollView sv = new HorizontalScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        Melodie melodie = new Melodie("Mos craciun", "Grupa1088",
                "Colind", "3");

        MelodieDB database = MelodieDB.getInstance(getApplicationContext());

        database.getMelodieDao().insert(melodie);
        List<Melodie> melodii = database.getMelodieDao().getAll();

        ArrayAdapter<Melodie> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, melodii);
        ListView lv = new ListView(this);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pozitia, long id) {
                Melodie melodie1 = melodii.get(pozitia);
                ArrayAdapter adapter1 = (ArrayAdapter) lv.getAdapter();
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare de stergere")
                        .setMessage("Esti sigur?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),
                                        "Nu s-a sters nimic!", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                melodii.remove(melodie1);
                                MelodieDB database = MelodieDB.getInstance(getApplicationContext());
                                database.getMelodieDao().delete(melodie1);
                                adapter.notifyDataSetChanged();

                                Toast.makeText(getApplicationContext(),
                                        "Stergerea a fost facuta!", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();
                dialog.show();
                return true;
            }
        });

        TextView tv1 = new TextView(this);
        tv1.setText("Lista melodiilor din baza de date:" + "\n");
        ll.addView(tv1);
        ll.addView(lv);
        sv.addView(ll);
        setContentView(sv);

    }
}