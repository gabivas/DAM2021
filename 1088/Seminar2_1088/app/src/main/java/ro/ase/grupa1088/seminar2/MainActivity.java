package ro.ase.grupa1088.seminar2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText EUR, USD,GBP,XAU;
public static final int OPTIUNE1=0;
public static final int OPTIUNE2=1;



    @Override
    public void onClick(View view) {
        EUR.setText("4.8");
        USD.setText("4.4");
        GBP.setText("5.2");
        XAU.setText("240");
    }

    public enum Location{
    INTERN, DATABASE, STICK;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EUR=findViewById(R.id.editTextEUR);
        USD=findViewById(R.id.editTextUSD);
        GBP=findViewById(R.id.editTextGBP);
        XAU=findViewById(R.id.editTextXAU);

        Spinner sp1=findViewById(R.id.spinner1);
        //String[] values={"INTERN","DATABASE","STICK"};
        String[] values=new String[Location.values().length];
        int i=0;
        for(Location loc: Location.values())
            values[i++]=loc.toString();
        ArrayAdapter<String> adaptor= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,values);
        sp1.setAdapter(adaptor);

        Button btn1= findViewById(R.id.btnAfisare);
        Button btn2=findViewById(R.id.btnSalveaza);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("onClick","S a apelat metoda onClick de pe btn2");

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,OPTIUNE1,0,"Click pe Optiune1");
        menu.add(0,OPTIUNE2,1,"Click pe Optiune2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case OPTIUNE1:
                Log.e("menu","Am apasat pe Optiune1");
                break;
            case OPTIUNE2:
                Log.e("menu","Am apasat pe Optiune2");
                return true;
        }
        return false;
    }
}