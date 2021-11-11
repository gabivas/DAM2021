package ro.ase.grupa1088.seminar7.util;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilmJsonParser {

    public static final String TITLU = "titlu";
    public static final String GEN_FILM = "genFilm";
    public static final String REGIZOR = "regizor";
    public static final String ID = "id";
    public static final String AN_APARITIE = "anAparitie";
    public static final String LUNA_APARITIE = "lunaAparitie";

    public static List<Film> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return parsareFilme(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    private static List<Film> parsareFilme(JSONArray array) throws JSONException {
        List<Film> filme = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Film film = parsareFilm(array.getJSONObject(i));
            filme.add(film);
        }
        return filme;
    }

    private static Film parsareFilm(JSONObject jsonObject) throws JSONException {
        String titlu = jsonObject.getString(TITLU);
        String gen = jsonObject.getString(
                GEN_FILM);
        String regizor = jsonObject.getString(
                REGIZOR);
        int luna = jsonObject.getInt(LUNA_APARITIE);
        int an = jsonObject.getInt(AN_APARITIE);
        long id = jsonObject.getLong(ID);
        Film film=new Film(titlu, gen, luna, an, id, regizor);
        Log.e("test",film.toString());
        return film;


    }
}
