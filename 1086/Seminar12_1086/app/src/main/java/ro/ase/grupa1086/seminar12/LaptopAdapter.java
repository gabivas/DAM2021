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

public class LaptopAdapter extends ArrayAdapter<Laptop> {
    private Context context;
    private int resource;
    private LayoutInflater inflater;
    private List<Laptop> laptopuri;

    public LaptopAdapter(@NonNull Context context, int resource,
                         @NonNull List<Laptop> laptopuri, LayoutInflater inflater) {
        super(context, resource, laptopuri);

        this.context=context;
        this.inflater=inflater;
        this.laptopuri=laptopuri;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=inflater.inflate(resource,parent, false);
        Laptop laptop=laptopuri.get(position);
        if(laptop!=null){
            adaugaMarca(view, laptop.getMarca());
            adaugaProcesor(view, laptop.getProcesor());
        }
        return view;
    }

    private void adaugaProcesor(View view, String procesor) {
        TextView textView= view.findViewById(R.id.tv_row_procesor);
        populareContinut(textView,procesor);
    }

    private void populareContinut(TextView textView, String text) {
        if(text!=null && !text.isEmpty()){
            textView.setText(text+" ");
        }
        else
            textView.setText(R.string.default_value);
    }

    private void adaugaMarca(View view, String marca) {
        TextView textView= view.findViewById(R.id.tv_row_marca);
        populareContinut(textView,marca);
    }
}
