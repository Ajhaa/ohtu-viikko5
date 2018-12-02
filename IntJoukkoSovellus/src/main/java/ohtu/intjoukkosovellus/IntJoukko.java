
package ohtu.intjoukkosovellus;

import java.util.ArrayList;
import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti ei saa olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoko ei saa olla negatiivinen");
        }

        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        if (alkioidenLkm < luvut.length) {
            luvut[alkioidenLkm] = luku;
        } else {
            kasvataTaulukkoa();
            
            luvut[alkioidenLkm] = luku;
        }

        alkioidenLkm++;
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }

        return false;
    }

    private void kasvataTaulukkoa() {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(luvut, uusiTaulukko);
        luvut = uusiTaulukko;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                luvut[i] = 0;
                poistaTyhjaKeskelta(i);
                alkioidenLkm--;
                return true;
            }
        }

        return false;
    }

    private void poistaTyhjaKeskelta(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            int edellinen = luvut[j];
            luvut[j] = luvut[j + 1];
            luvut[j + 1] = edellinen;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        ArrayList<String> luvutMerkkijonoina = new ArrayList<>();
        
        for (int i = 0; i < alkioidenLkm; i++) {
            luvutMerkkijonoina.add(luvut[i] + "");
        }

        tuotos += String.join(", ", luvutMerkkijonoina);
        tuotos += "}";

        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
            yhdiste.lisaa(bTaulu[i]);
        }

        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                leikkaus.lisaa(aTaulu[i]);
            }
        }

        return leikkaus;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if (!b.kuuluu(aTaulu[i])) {
                erotus.lisaa(aTaulu[i]);
            }
            if (!a.kuuluu(bTaulu[i])) {
                erotus.lisaa(bTaulu[i]);
            }
        }

        return erotus;
    }
        
}