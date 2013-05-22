
package Pakkausohjelma;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Pakkausohjelma {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Valitse komento (pakkaa | pura)");
        String komento = s.nextLine();
        if("testi".equals(komento)){
            ajaTesti(s);
        }
        else if ("pakkaa".equals(komento)){
            System.out.println("anna pakattavan tiedoston nimi:");
            String pakattava = s. nextLine();
            System.out.println("anna pakatun tiedoston nimi:");
            String pakattu = s.nextLine();
            try {
                new Pakkaaja().pakkaaTiedosto(pakattava, pakattu);
            } catch (FileNotFoundException ex) {
                System.out.println("pieleen män");
            }
        }
        else if("pura".equals(komento)){
            System.out.println("anna purettavan tiedoston nimi:");
            String purettava = s. nextLine();
            System.out.println("anna puretun tiedoston nimi:");
            String purettu = s.nextLine();
            try {
                new Purkaja().puraTiedosto(purettava, purettu, "aakkosto.txt");
            } catch (Exception ex) {
                System.out.println("piäleen män");
            } 
        }
        else{
            System.out.println("virheellinen komento");
        }    
    }
    /**
     * testimetodi nopeaa debuggausta varten
     */
    private static void ajaTesti(Scanner s) {
        
            try {
            System.out.println("anna tiedoston nimi:");
            String nimi = s.nextLine();
            Pakkaaja pa = new Pakkaaja();
            pa.pakkaaTiedosto(nimi, nimi+".pakattu");
            Purkaja p = new Purkaja();
            p.puraTiedosto(nimi+".pakattu", nimi+".purettu", "aakkosto.txt");
        } catch (IOException ex) {
            System.out.println("fail");
        
    
        }
    }
    
}