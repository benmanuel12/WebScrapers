import com.ben.scrapers.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for holding test code
 */
public class ScraperTest {

    /**
     * Tests the CardAnnotation Class
     */
    @Test
    public void CardAnnotationTest(){
        CardAnnotation tester = new CardAnnotation();
        tester.setId(1);
        tester.setCardName("Omnath");
        tester.setCardSetCode(1);
        tester.setImageUrl("www.123.com");

        assertEquals(1, tester.getId(), "ID should be 1");
        assertEquals("Omnath", tester.getCardName(),"Card name should be Omnath");
        assertEquals(1, tester.getCardSetCode(), "Set code should be 1");
        assertEquals("www.123.com", tester.getImageUrl(), "Image URL should be www.123.com");
    }

    /**
     * Tests the OptionAnnotation Class
     */
    @Test
    public void OptionAnnotationTest(){
        OptionAnnotation tester = new OptionAnnotation();
        tester.setId(1);
        tester.setPrice(10.00);
        tester.setShopName("Awesome Shop");
        tester.setLink("www.123.com");
        CardAnnotation tester2 = new CardAnnotation();
        tester.setCardId(tester2);

        assertEquals(1, tester.getId(), "ID should be 1");
        assertEquals(10.00, tester.getPrice(), "Price should be 10.00");
        assertEquals("Awesome Shop", tester.getShopName(), "Shop name should be Awesome Shop");
        assertEquals("www.123.com", tester.getLink(), "Link should be www.123.com");
        assertEquals(tester2, tester.getCardId(), "Should be tester2 instance");
    }

    /**
     * Tests the ScraperManager Class
     */
    @Test
    public void ScraperManagerTest(){
        ScraperManager tester = new ScraperManager();
        Scraper1 tempScraper1 = new Scraper1();
        Scraper2 tempScraper2 = new Scraper2();

        assertTrue(tester.getScraperList().isEmpty(), "ArrayList must be empty upon initialisation");

        ArrayList<Scraper> tempList = new ArrayList<>();
        tempList.add(tempScraper1);
        tempList.add(tempScraper2);

        tester.setScraperList(tempList);

        assertEquals(2, tester.getScraperList().size(), "ArrayList must contain 2 scrapers");
    }

    /**
     * Tests the Scraper1 Class
     */
    @Test
    public void Scraper1Test(){
        Scraper1 tester = new Scraper1();

        assertNotNull(tester);
    }

    /**
     * Tests the Scraper2 Class
     */
    @Test
    public void Scraper2Test(){
        Scraper2 tester = new Scraper2();

        assertNotNull(tester);
    }

    /**
     * Tests the Scraper3 Class
     */
    @Test
    public void Scraper3Test(){
        Scraper3 tester = new Scraper3();

        assertNotNull(tester);
    }

    /**
     * Tests the Scraper4 Class
     */
    @Test
    public void Scraper4Test(){
        Scraper4 tester = new Scraper4();

        assertNotNull(tester);
    }

    /**
     * Tests the Scraper5 Class
     */
    @Test
    public void Scraper5Test(){
        Scraper5 tester = new Scraper5();

        assertNotNull(tester);
    }
}
