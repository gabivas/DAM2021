package ro.ase.grupa1088.seminar1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("cicluDeViata","S-a apelat metoda onCreate()");
        Toast.makeText(getApplicationContext(),"Buna din aplicatia mea!",Toast.LENGTH_LONG).show();

        //tv1.findViewById(R.id.textView1);
        //tv1.setText("Hello 1088");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("cicluDeViata","S-a apelat metoda onStart()");
    }
}