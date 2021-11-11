package ro.ase.grupa1086.seminar7.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentJsonParser {
    public static final String NUME = "nume";
    public static final String NUME_FACULTATE = "numeFacultate";
    public static final String NUMAR_IDENTIFICARE = "numarIdentificare";
    public static final String LUNA_INSCRIERE_FACULTATE = "lunaInscriereFacultate";
    public static final String AN_INSCRIERE_FACULTATE = "anInscriereFacultate";

    public static List<Student> fromJson(String json){
        try {
            JSONArray array= new JSONArray(json);
            return citireStudenti(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    private static List<Student> citireStudenti(JSONArray array) throws JSONException {
        List<Student> studenti= new ArrayList<>();
        for(int i=0;i<array.length();i++){
            Student student= citireStudent(array.getJSONObject(i));
            studenti.add(student);
        }
        return studenti;
    }

    private static Student citireStudent(JSONObject jsonObject) throws JSONException {
        String nume= jsonObject.getString(NUME);
        String numeFacultate=jsonObject.getString(
                NUME_FACULTATE);
        long numarIdentificare=jsonObject.getLong(
                NUMAR_IDENTIFICARE);
        int luna= jsonObject.getInt(
                LUNA_INSCRIERE_FACULTATE);
        int an= jsonObject.getInt(
                AN_INSCRIERE_FACULTATE);
        return new Student(nume,numarIdentificare,luna,an,numeFacultate);
    }
}
