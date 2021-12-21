package ro.ase.grupa1088.seminar8;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Retea extends AsyncTask<URL, Void, InputStream> {
    public static final String STRING_LINE = "-";
    InputStream ist = null;
    public CursValutar cursValutar;
    public static String sBuf = "";

    @Override
    protected InputStream doInBackground(URL... urls) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            ist = connection.getInputStream();

            parsare(ist);
            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = null;
            while ((linie = br.readLine()) != null) {
                sBuf += linie;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ist;
    }

    private void parsare(InputStream ist) {
        try {
            StringBuilder sb = new StringBuilder();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(ist);
            domDoc.getDocumentElement().normalize();
            cursValutar = new CursValutar();
            Node cube = getNodeByName("Cube", domDoc.getDocumentElement());
            if (cube != null) {
                String data = getAttributeValue(cube, "date");
                sb.append(data).append(STRING_LINE);
                NodeList listaCopii = cube.getChildNodes();
                for (int i = 0; i < listaCopii.getLength(); i++) {
                    Node node = listaCopii.item(i);
                    String atribut = getAttributeValue(node, "currency");
                    if (atribut.equals("EUR"))
                        sb.append(node.getTextContent()).append(STRING_LINE);
                    if (atribut.equals("GBP"))
                        sb.append(node.getTextContent()).append(STRING_LINE);
                    if (atribut.equals("USD"))
                        sb.append(node.getTextContent()).append(STRING_LINE);
                    if (atribut.equals("XAU"))
                        sb.append(node.getTextContent());

                }
                cursValutar.setDateConcatenate(sb.toString());
            } else
                Log.e("eroare", "Eroare parsare! Nodul Cube este null!");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public static String getAttributeValue(Node node, String value) {
        try {
            return ((Element) node).getAttribute(value);
        } catch (Exception e) {
            return "";
        }

    }

    public static Node getNodeByName(String nodeName, Node parentNode) {
        if (parentNode.getNodeName().equals(nodeName))
            return parentNode;
        NodeList lista = parentNode.getChildNodes();
        for (int i = 0; i < lista.getLength(); i++) {
            Node node = getNodeByName(nodeName, lista.item(i));
            if (node != null)
                return node;
        }

        return null;
    }
}
