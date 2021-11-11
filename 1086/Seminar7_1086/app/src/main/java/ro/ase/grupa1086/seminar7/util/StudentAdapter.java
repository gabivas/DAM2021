package ro.ase.grupa1086.seminar7.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ro.ase.grupa1086.seminar7.R;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> listaStudenti;
    private LayoutInflater inflater;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> listaStudenti,
                          LayoutInflater inflater) {
        super(context, resource, listaStudenti);
        this.context = context;
        this.inflater = inflater;
        this.listaStudenti = listaStudenti;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Student student = listaStudenti.get(position);
        if (student == null) {
            return view;
        }
        adaugaNume(view, student.getNume());
        adaugaNumarIdentificare(view, student.getNumarIdentificare());
        adaugaDataInscriere(view, student.getLunaInscriereFacultate(), student.
                getAnInscriereFacultate());
        adaugaNumeFacultate(view, student.getNumeFacultate());
        return view;
    }

    private void adaugaNume(View view, String nume) {
        TextView textView = view.findViewById(R.id.row_tv_nume);
        populareContinutTextView(textView, nume);
    }

    private void adaugaNumarIdentificare(View view, long numarIdentificare) {
        TextView textView = view.findViewById(R.id.row_tv_numarIdentificare);
        populareContinutTextView(textView, String.valueOf(numarIdentificare));
    }

    private void adaugaDataInscriere(View view, int luna, int an) {
        TextView textView = view.findViewById(R.id.row_tv_dataInscriereFacultate);
        String data = context.getString(R.string.template_data, luna, an);
        populareContinutTextView(textView, data);
    }

    private void adaugaNumeFacultate(View view, String numeFacultate) {
        TextView textView = view.findViewById(R.id.row_tv_numeFacultate);
        populareContinutTextView(textView, numeFacultate);
    }

    private void populareContinutTextView(TextView textView, String valoare) {
        if (valoare != null && !valoare.trim().isEmpty()) {
            textView.setText(valoare);
        } else {
            textView.setText(R.string.valoare_default_row_view);
        }
    }
}
