package ro.ase.grupa1086.seminar12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CadouAdapter extends ArrayAdapter<Cadou> {
    private Context context;
    private LayoutInflater inflater;
    private int resource;
    private List<Cadou> cadouri;

    public CadouAdapter(@NonNull Context context, int resource,List<Cadou> cadouri,
                        LayoutInflater inflater ) {
        super(context, resource,cadouri);
        this.context=context;
        this.cadouri=cadouri;
        this.resource=resource;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= inflater.inflate(resource,parent,false);
        Cadou cadou=cadouri.get(position);
        if(cadou!=null){
           adaugareDenumire(view, cadou.getDenumire());
           adaugaDimensiune(view, cadou.getDimensiune());
        }
        return view;
    }

    private void adaugaDimensiune(View view, String dimensiune) {
        TextView textView= view.findViewById(R.id.tv_row_dimensiune);
        populareContinut(textView,dimensiune);
    }

    private void adaugareDenumire(View view, String denumire) {
        TextView textView= view.findViewById(R.id.tv_row_denumire);
        populareContinut(textView,denumire);
    }

    private void populareContinut(TextView textView, String valoare) {
        if(valoare!=null && !valoare.isEmpty()){
            textView.setText(valoare+" ");
        }
        else{
            textView.setText(R.string.DEFAULT_VALUE);

        }
    }
}
