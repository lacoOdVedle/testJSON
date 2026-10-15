import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Path path = Path.of("vyprava.json");
        String text = Files.readString(path);

        JSONObject root = new JSONObject(text);
        JSONArray opicky = root.getJSONArray("opicky");
        JSONArray koberce = root.getJSONArray("koberce");


        int pocetOpicek = 0;
        for (int i = 0; i < opicky.length(); i++) {
            //System.out.println(opicky.get(i));
            pocetOpicek++;
        }
        System.out.println("Počet opiček: " + pocetOpicek);


        System.out.println("Koberce:");
        for (int i = 0; i < koberce.length(); i++) {
            JSONObject koberceObj = koberce.getJSONObject(i);
            System.out.println("Koberec " + koberceObj.getInt("id") + "\n" + koberceObj.getString("nazev") + "\n" + koberceObj.getInt("nosnost"));
            if (!koberceObj.getBoolean("aktivni")) {
                System.out.println("Nedostupný");
            } else System.out.println("Dostupný");
        }

        System.out.println("Zadej počet banánů.");
        int pocetBananu = sc.nextInt();
        sc.nextLine();
        int hmontostOpicek = 15 * pocetOpicek;
        int celkovaHmotnostVypravy = pocetBananu + hmontostOpicek;
        ArrayList<JSONObject> listVhodnychKobercu = new ArrayList<>();
        System.out.println("Vhodné koberce: ");
        for (int i = 0; i < koberce.length(); i++) {
            JSONObject koberceObj = koberce.getJSONObject(i);
            if (koberceObj.getInt("nosnost") >= celkovaHmotnostVypravy && koberceObj.getBoolean("aktivni") && koberceObj.get("vybava").toString().contains("kompas")) {
                System.out.println(koberceObj.getString("nazev") + " "  + koberceObj.getInt("nosnost"));
                listVhodnychKobercu.add(koberce.getJSONObject(i));
            }
        }

        int nejvissiNosnostKobercu = 0;
        int indexNejvhodnejsihoKoberce = 0;
        ArrayList<JSONObject> nejvhodnejsiKoberec = new ArrayList<>();
        System.out.println("Nejvhodnější koberec: ");
        for (int i = 0; i < nejvhodnejsiKoberec.size(); i++) {
            if (listVhodnychKobercu.get(i).getInt("nosnost") >= nejvissiNosnostKobercu) {
                indexNejvhodnejsihoKoberce = i;
                nejvissiNosnostKobercu = listVhodnychKobercu.get(i).getInt("nosnost");
            }
        }
        nejvhodnejsiKoberec.add(listVhodnychKobercu.get(indexNejvhodnejsihoKoberce));
        System.out.println(nejvhodnejsiKoberec);


    }
}