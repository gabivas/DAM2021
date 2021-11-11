package ro.ase.grupa1088.seminar7.retea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {

    private URL url;
    private final String adresaUrl;
    private HttpURLConnection conexiune;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public HttpManager(String adresaUrl) {
        this.adresaUrl = adresaUrl;
    }
    public String process(){
        try{
            return getRezultatHttp();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            inchidereConexiuni();
        }
        return null;
    }

    private void inchidereConexiuni() {
        try {
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRezultatHttp() throws IOException {
        url=new URL(adresaUrl);
        conexiune= (HttpURLConnection) url.openConnection();
        inputStream=conexiune.getInputStream();
        inputStreamReader=new InputStreamReader(inputStream);
        bufferedReader=new BufferedReader(inputStreamReader);
        String linie;
        StringBuilder rezultat=new StringBuilder();
        while ((linie=bufferedReader.readLine())!=null)
            rezultat.append(linie);
        return rezultat.toString();

    }

}
