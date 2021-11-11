package ro.ase.grupa1088.seminar5_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TelefonAdapter extends ArrayAdapter<Telefon> {

    private Context context;
    private int resource;
    private List<Telefon> movieList;
    private LayoutInflater layoutInflater;

    public TelefonAdapter(@NonNull Context context, int resource, List<Telefon> movieList, LayoutInflater layoutInflater) {
        super(context, resource, movieList);
        this.context = context;
        this.resource = resource;
        this.movieList = movieList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Telefon movie = movieList.get(position);

        if (movie!=null)
        {
            TextView tv1 = view.findViewById(R.id.titlu);
            tv1.setText(movie.getBrand());

            TextView tv2 = view.findViewById(R.id.data);
            tv2.setText(movie.getDataAparitie().toString());

            TextView tv3 = view.findViewById(R.id.regizor);
            tv3.setText(movie.getModel());

            TextView tv4 = view.findViewById(R.id.profit);
            tv4.setText(String.valueOf(movie.getMemorie()));

            TextView tv5 = view.findViewById(R.id.genFilm);
            tv5.setText(movie.getRetea().toString());

            TextView tv6 = view.findViewById(R.id.platforma);
            tv6.setText(movie.getCuloare().toString());
        }

        return view;
    }
}

