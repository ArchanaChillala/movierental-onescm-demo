package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class AgedBrieTest {

    @Test
    @DisplayName("AgedBrie has correct name")
    void agedBrieHasCorrectName() {
        AgedBrie brie = new AgedBrie(10, 20);
        assertEquals("Aged Brie", brie.getName());
    }

    @Test
    @DisplayName("AgedBrie increases quality by 1 before sell date")
    void agedBrieIncreasesQualityByOneBeforeSellDate() {
        AgedBrie brie = new AgedBrie(10, 20);
        brie.updateQuality();
        
        assertEquals(9, brie.getSellIn());
        assertEquals(21, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie increases quality by 2 after sell date")
    void agedBrieIncreasesQualityByTwoAfterSellDate() {
        AgedBrie brie = new AgedBrie(0, 20);
        brie.updateQuality();
        
        assertEquals(-1, brie.getSellIn());
        assertEquals(22, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie quality never exceeds 50 before sell date")
    void agedBrieQualityNeverExceeds50BeforeSellDate() {
        AgedBrie brie = new AgedBrie(10, 50);
        brie.updateQuality();
        
        assertEquals(9, brie.getSellIn());
        assertEquals(50, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie quality never exceeds 50 after sell date")
    void agedBrieQualityNeverExceeds50AfterSellDate() {
        AgedBrie brie = new AgedBrie(0, 50);
        brie.updateQuality();
        
        assertEquals(-1, brie.getSellIn());
        assertEquals(50, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie quality caps at 50 when increasing from 49 after sell date")
    void agedBrieQualityCapsAt50FromIncreaseAfterSellDate() {
        AgedBrie brie = new AgedBrie(-1, 49);
        brie.updateQuality();
        
        assertEquals(-2, brie.getSellIn());
        assertEquals(50, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie with quality 0 increases properly")
    void agedBrieWithQuality0IncreasesProperly() {
        AgedBrie brie = new AgedBrie(5, 0);
        brie.updateQuality();
        
        assertEquals(4, brie.getSellIn());
        assertEquals(1, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie with quality 0 after sell date increases by 2")
    void agedBrieWithQuality0AfterSellDateIncreasesBy2() {
        AgedBrie brie = new AgedBrie(0, 0);
        brie.updateQuality();
        
        assertEquals(-1, brie.getSellIn());
        assertEquals(2, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie multiple updates before sell date")
    void agedBrieMultipleUpdatesBeforeSellDate() {
        AgedBrie brie = new AgedBrie(3, 20);
        
        brie.updateQuality();
        assertEquals(2, brie.getSellIn());
        assertEquals(21, brie.getQuality());
        
        brie.updateQuality();
        assertEquals(1, brie.getSellIn());
        assertEquals(22, brie.getQuality());
        
        brie.updateQuality();
        assertEquals(0, brie.getSellIn());
        assertEquals(23, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie transitions from before to after sell date correctly")
    void agedBrieTransitionsCorrectly() {
        AgedBrie brie = new AgedBrie(1, 20);
        
        // Before expired: +1
        brie.updateQuality();
        assertEquals(0, brie.getSellIn());
        assertEquals(21, brie.getQuality());
        
        // After expired: +2
        brie.updateQuality();
        assertEquals(-1, brie.getSellIn());
        assertEquals(23, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie with negative sellIn continues increasing quality by 2")
    void agedBrieWithNegativeSellInContinuesIncreasingBy2() {
        AgedBrie brie = new AgedBrie(-5, 30);
        brie.updateQuality();
        
        assertEquals(-6, brie.getSellIn());
        assertEquals(32, brie.getQuality());
    }

    @Test
    @DisplayName("AgedBrie respects max quality 50 during rapid aging")
    void agedBrieRespectsMaxQualityDuringRapidAging() {
        AgedBrie brie = new AgedBrie(-1, 48);
        
        brie.updateQuality();
        assertEquals(-2, brie.getSellIn());
        assertEquals(50, brie.getQuality());
        
        brie.updateQuality();
        assertEquals(-3, brie.getSellIn());
        assertEquals(50, brie.getQuality());
    }
}
