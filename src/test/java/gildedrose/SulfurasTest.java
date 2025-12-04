package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class SulfurasTest {

    @Test
    @DisplayName("Sulfuras has correct name")
    void sulfurasHasCorrectName() {
        Sulfuras sulfuras = new Sulfuras(10, 80);
        assertEquals("Sulfuras, Hand of Ragnaros", sulfuras.getName());
    }

    @Test
    @DisplayName("Sulfuras never changes quality or sellIn")
    void sulfurasNeverChanges() {
        Sulfuras sulfuras = new Sulfuras(10, 80);
        sulfuras.updateQuality();
        
        assertEquals(10, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras never changes with negative sellIn")
    void sulfurasNeverChangesWithNegativeSellIn() {
        Sulfuras sulfuras = new Sulfuras(-1, 80);
        sulfuras.updateQuality();
        
        assertEquals(-1, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras never changes with zero sellIn")
    void sulfurasNeverChangesWithZeroSellIn() {
        Sulfuras sulfuras = new Sulfuras(0, 80);
        sulfuras.updateQuality();
        
        assertEquals(0, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras with quality 80 remains unchanged after multiple updates")
    void sulfurasRemainsUnchangedAfterMultipleUpdates() {
        Sulfuras sulfuras = new Sulfuras(5, 80);
        
        for (int i = 0; i < 10; i++) {
            sulfuras.updateQuality();
        }
        
        assertEquals(5, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras can be created with different quality values")
    void sulfurasCanBeCreatedWithDifferentQuality() {
        Sulfuras sulfuras1 = new Sulfuras(10, 80);
        assertEquals(80, sulfuras1.getQuality());
        
        Sulfuras sulfuras2 = new Sulfuras(10, 50);
        assertEquals(50, sulfuras2.getQuality());
        
        Sulfuras sulfuras3 = new Sulfuras(10, 100);
        assertEquals(100, sulfuras3.getQuality());
    }

    @Test
    @DisplayName("Sulfuras quality above 50 stays unchanged")
    void sulfurasQualityAbove50StaysUnchanged() {
        Sulfuras sulfuras = new Sulfuras(10, 100);
        sulfuras.updateQuality();
        
        assertEquals(10, sulfuras.getSellIn());
        assertEquals(100, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras with very negative sellIn remains unchanged")
    void sulfurasWithVeryNegativeSellInRemainsUnchanged() {
        Sulfuras sulfuras = new Sulfuras(-100, 80);
        sulfuras.updateQuality();
        
        assertEquals(-100, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    @DisplayName("Sulfuras with quality 0 remains unchanged")
    void sulfurasWithQuality0RemainsUnchanged() {
        Sulfuras sulfuras = new Sulfuras(10, 0);
        sulfuras.updateQuality();
        
        assertEquals(10, sulfuras.getSellIn());
        assertEquals(0, sulfuras.getQuality());
    }
}
