
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    
    
    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(3,4);
        
        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }
    
    @Test
    public void leikkausToimii() {
        IntJoukko eka = teeJoukko(1,3);
        IntJoukko toka = teeJoukko(3,4);
        
        int[] tulos = IntJoukko.leikkaus(eka, toka).toIntArray();

        Arrays.sort(tulos);
        System.out.println(Arrays.toString(tulos));
        int[] o = {3};

        assertArrayEquals(o, tulos);

    }

    @Test
    public void erotusToimii() {
        IntJoukko eka = teeJoukko(1,3);
        IntJoukko toka = teeJoukko(3,4);
        
        int[] tulos = IntJoukko.erotus(eka, toka).toIntArray();

        Arrays.sort(tulos);
        System.out.println(Arrays.toString(tulos));
        int[] o = {1, 4};

        assertArrayEquals(o, tulos);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
