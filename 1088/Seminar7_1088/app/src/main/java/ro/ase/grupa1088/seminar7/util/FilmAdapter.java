package ro.ase.grupa1088.seminar7.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ro.ase.grupa1088.seminar7.R;

public class FilmAdapter extends ArrayAdapter<Film> {
    private Context context;
    private int resource;
    private List<Film> listaFilme;
    private LayoutInflater inflater;

    public FilmAdapter(@NonNull Context context, int resource,List<Film> listaFilme,
                       LayoutInflater inflater) {
        super(context, resource,listaFilme);
        this.context=context;
        this.listaFilme=listaFilme;
        this.inflater=inflater;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=inflater.inflate(resource,parent,false);
        Film film=listaFilme.get(position);
        if(film==null)
            return view;
        adaugaTitlu(view, film.getTitlu());
        adaugaGen(view, film.getGenFilm());
        adaugaData(view, film.getLunaAparitie(), film.getAnAparitie());
        adaugaId(view, film.getId());
        adaugaRegizor(view, film.getRegizor());

        return view;
    }

    private void adaugaTitlu(View view, String titlu) {
        TextView textView=view.findViewById(R.id.row_tv_titlu);
        populareContinutTextView(textView,titlu);
    }

    private void adaugaGen(View view, String gen) {
        TextView textView=view.findViewById(R.id.row_tv_gen);
        populareContinutTextView(textView,gen);
    }

    private void adaugaData(View view, int luna, int an) {
        TextView textView=view.findViewById(R.id.row_tv_dataAparitie);
        String data= context.getString(R.string.template_data,luna,an);
        populareContinutTextView(textView,data);
    }
    private void adaugaRegizor(View view, String regizor) {
        TextView textView=view.findViewById(R.id.row_tv_regizor);
        populareContinutTextView(textView,regizor);
    }
    private void adaugaId(View view, long idFilm) {
        TextView textView=view.findViewById(R.id.row_tv_idFilm);
        populareContinutTextView(textView,String.valueOf(idFilm));
    }

    private void populareContinutTextView(TextView textView, String value) {
        if(value!=null){// && !value.trim().isEmpty()) {
            textView.setText(value);
        }
        else {
            textView.setText(R.string.valoare_default_row_view);
        }
    }
}
