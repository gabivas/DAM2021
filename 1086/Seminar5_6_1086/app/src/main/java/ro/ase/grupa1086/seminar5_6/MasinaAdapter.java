package ro.ase.grupa1086.seminar5_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

public class MasinaAdapter extends ArrayAdapter<Masina> {
    private Context context;
    private int resource;
    private List<Masina> listaMasini;
    private LayoutInflater layoutInflater;

    public MasinaAdapter(@NonNull Context context, int resource,
                         List<Masina> listaMasini,LayoutInflater layoutInflater) {
        super(context, resource, listaMasini);
        this.context=context;
        this.resource=resource;
        this.listaMasini=listaMasini;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= layoutInflater.inflate(resource,parent,false);
        Masina masina= listaMasini.get(position);
        if(masina!=null){
            TextView tv1=view.findViewById(R.id.marca);
            tv1.setText(masina.getMarca());

            TextView tv2=view.findViewById(R.id.nrInmatriculare);
            tv2.setText(masina.getNrInmatriculare());

            TextView tv3=view.findViewById(R.id.dataInmatriculare);
            tv3.setText(new SimpleDateFormat("dd/MM/yyyy").format(masina.getDataInmatriculare()));

            TextView tv4=view.findViewById(R.id.anFabricatie);
            tv4.setText(String.valueOf(masina.getAnFabricatie()));

            TextView tv5=view.findViewById(R.id.tipCombustibil);
            tv5.setText(masina.getTipCombustibil().toString());

            TextView tv6=view.findViewById(R.id.culoareVehicul);
            tv6.setText(masina.getCuloareVehicul().toString());


        }

        return view;
    }
}
