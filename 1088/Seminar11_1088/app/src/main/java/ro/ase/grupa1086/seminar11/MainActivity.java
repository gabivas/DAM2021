package ro.ase.grupa1086.seminar11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Double> pibPeCapLocuitor= new ArrayList<>();
        pibPeCapLocuitor.add(4.12);
        pibPeCapLocuitor.add(5.20);
        pibPeCapLocuitor.add(6.09);

        List<Double> impozitPeVenit= new ArrayList<>();
        impozitPeVenit.add(7.26);
        impozitPeVenit.add(5.75);
        impozitPeVenit.add(6.94);

        XYPlot plot= findViewById(R.id.myXYPlot);
        plot.setTitle("Grafic PIB si Impozit");

        XYSeries series1= new SimpleXYSeries(pibPeCapLocuitor,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"PIB");
        plot.addSeries(series1, new LineAndPointFormatter(getApplicationContext(),
                R.layout.formatter1));

        XYSeries series2= new SimpleXYSeries(impozitPeVenit,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Impozit");
        plot.addSeries(series2, new LineAndPointFormatter(getApplicationContext(),
                R.layout.formatter2));
    }
}