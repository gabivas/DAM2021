package ro.ase.grupa1086.seminar2;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int OPTIUNE1 = 0;
    private static final int OPTIUNE2 = 1;
    private EditText EUR, USD,GBP,XAU;

    @Override
    public void onClick(View view) {
        EUR.setText("4.95");
        USD.setText("4.28");
        GBP.setText("5.83");
        XAU.setText("122.96");
    }

    public enum Location{
    INTERN,DATABASE,STICK
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
for(Location loc: Location.values()){
    values[i++]=loc.toString();
}
        ArrayAdapter<String> adaptor=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,values);
sp1.setAdapter(adaptor);
        Button btn1=findViewById(R.id.buttonAfisare);
        btn1.setOnClickListener(this);
        Button btn2=findViewById(R.id.buttonSalveaza);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("button2","Cursul valutar a fost salvat cu succes!");
                Log.e("spinner",sp1.getSelectedItem().toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,OPTIUNE1,1,"Click pe opt1");
        menu.add(0,OPTIUNE2,0,"Click pe opt2");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
switch (item.getItemId()){
    case OPTIUNE1:
        Log.e("menu","S a apasat pe OPT1");
        Toast.makeText(getApplicationContext(),"testttt",Toast.LENGTH_LONG)
                .show();
        break;
    case OPTIUNE2:
        Log.e("menu","S a apasat pe OPT2");
        Toast.makeText(getApplicationContext(),"testttt",Toast.LENGTH_LONG)
                .show();
        return true;
}        return false;
    }
}