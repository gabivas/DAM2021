package ro.ase.grupa1086.seminar11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Double> salariiMedii = new ArrayList<>();
        salariiMedii.add(2800.0);
        salariiMedii.add(2950.0);
        salariiMedii.add(3075.5);
        salariiMedii.add(3333.33);

        List<Double> pensiiMedii = new ArrayList<>();
        pensiiMedii.add(1362.5);
        pensiiMedii.add(1480.0);
        pensiiMedii.add(1600.0);
        pensiiMedii.add(1565.5);

        XYPlot plot= findViewById(R.id.myXYPlot);
        plot.setTitle("Grafic pensii si salarii");

        XYSeries series1= new SimpleXYSeries(salariiMedii, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Salarii");
        plot.addSeries(series1, new LineAndPointFormatter(getApplicationContext(),R.layout.formatter1));

        XYSeries series2= new SimpleXYSeries(pensiiMedii, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Pensii");
        plot.addSeries(series2, new LineAndPointFormatter(getApplicationContext(),R.layout.formatter2));


    }
}