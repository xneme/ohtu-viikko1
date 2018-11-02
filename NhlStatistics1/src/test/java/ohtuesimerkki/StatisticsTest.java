package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchReturnsRightPlayer() {
        assertEquals(new Player("Kurri", "EDM", 37, 53).toString(), stats.search("Kurri").toString());
    }
    
    @Test
    public void searchingUnknownPlayerReturnsNull() {
        assertEquals(null, stats.search("Paavo Väyrynen"));
    }
    
    @Test
    public void teamReturnsATeam() {
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void topScorersReturnsRightSizeList() {
        assertEquals(2, stats.topScorers(2).size());
    }
    
    @Test
    public void topScorersIsSorted() {
        assertEquals(new Player("Gretzky", "EDM", 35, 89).toString(), stats.topScorers(5).get(0).toString());
    }
}
