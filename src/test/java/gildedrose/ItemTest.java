package gildedrose;

import gildedrose.itemtypes.AgedBrie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Item constructor sets name, sellIn, and quality correctly")
    void itemConstructorSetsPropertiesCorrectly() {
        Item item = new Item("Test Item", 10, 20);
        
        assertEquals("Test Item", item.getName());
        assertEquals(10, item.getSellIn());
        assertEquals(20, item.getQuality());
    }

    @Test
    @DisplayName("Item.create delegates to ItemFactory")
    void itemCreateDelegatesToFactory() {
        Item item = Item.create("Normal Item", 10, 20);
        
        assertNotNull(item);
        assertEquals("Normal Item", item.getName());
        assertEquals(10, item.getSellIn());
        assertEquals(20, item.getQuality());
    }

    @Test
    @DisplayName("Item.create returns specialized item for Aged Brie")
    void itemCreateReturnsSpecializedItemForAgedBrie() {
        Item item = Item.create("Aged Brie", 10, 20);
        
        assertInstanceOf(AgedBrie.class, item);
    }

    @Test
    @DisplayName("Normal item quality degrades by 1 before sell date")
    void normalItemQualityDegradesByOneBeforeSellDate() {
        Item item = new Item("Normal Item", 10, 20);
        item.updateQuality();
        
        assertEquals(9, item.getSellIn());
        assertEquals(19, item.getQuality());
    }

    @Test
    @DisplayName("Normal item quality degrades by 2 after sell date")
    void normalItemQualityDegradesByTwoAfterSellDate() {
        Item item = new Item("Normal Item", 0, 20);
        item.updateQuality();
        
        assertEquals(-1, item.getSellIn());
        assertEquals(18, item.getQuality());
    }

    @Test
    @DisplayName("Normal item quality never goes below 0")
    void normalItemQualityNeverGoesNegative() {
        Item item = new Item("Normal Item", 5, 0);
        item.updateQuality();
        
        assertEquals(4, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    @DisplayName("Normal item with quality 1 before sell date becomes 0")
    void normalItemWithQuality1BeforeSellDateBecomesZero() {
        Item item = new Item("Normal Item", 5, 1);
        item.updateQuality();
        
        assertEquals(4, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    @DisplayName("Normal item with quality 1 after sell date becomes 0")
    void normalItemWithQuality1AfterSellDateBecomesZero() {
        Item item = new Item("Normal Item", -1, 1);
        item.updateQuality();
        
        assertEquals(-2, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    @DisplayName("Item toString returns correct format")
    void itemToStringReturnsCorrectFormat() {
        Item item = new Item("Test Item", 10, 20);
        
        assertEquals("Test Item, 10, 20", item.toString());
    }

    @Test
    @DisplayName("Item toString with negative sellIn")
    void itemToStringWithNegativeSellIn() {
        Item item = new Item("Test Item", -5, 10);
        
        assertEquals("Test Item, -5, 10", item.toString());
    }

    @Test
    @DisplayName("Item toString with zero values")
    void itemToStringWithZeroValues() {
        Item item = new Item("Test Item", 0, 0);
        
        assertEquals("Test Item, 0, 0", item.toString());
    }

    @Test
    @DisplayName("Item with empty name")
    void itemWithEmptyName() {
        Item item = new Item("", 10, 20);
        
        assertEquals("", item.getName());
        assertEquals(", 10, 20", item.toString());
    }

    @Test
    @DisplayName("Item can have negative sellIn from construction")
    void itemCanHaveNegativeSellInFromConstruction() {
        Item item = new Item("Test Item", -10, 20);
        
        assertEquals(-10, item.getSellIn());
    }

    @Test
    @DisplayName("Item sellIn decreases on each update")
    void itemSellInDecreasesOnEachUpdate() {
        Item item = new Item("Test Item", 5, 20);
        
        item.updateQuality();
        assertEquals(4, item.getSellIn());
        
        item.updateQuality();
        assertEquals(3, item.getSellIn());
        
        item.updateQuality();
        assertEquals(2, item.getSellIn());
    }

    @Test
    @DisplayName("Normal item quality continues decreasing after reaching sellIn 0")
    void normalItemQualityContinuesDecreasingAfterSellIn0() {
        Item item = new Item("Normal Item", 1, 10);
        
        // Before expired: -1
        item.updateQuality();
        assertEquals(0, item.getSellIn());
        assertEquals(9, item.getQuality());
        
        // After expired: -2
        item.updateQuality();
        assertEquals(-1, item.getSellIn());
        assertEquals(7, item.getQuality());
    }

    @Test
    @DisplayName("Normal item with high quality degrades properly over time")
    void normalItemWithHighQualityDegradesProperly() {
        Item item = new Item("Normal Item", 2, 50);
        
        item.updateQuality();
        assertEquals(1, item.getSellIn());
        assertEquals(49, item.getQuality());
        
        item.updateQuality();
        assertEquals(0, item.getSellIn());
        assertEquals(48, item.getQuality());
        
        item.updateQuality();
        assertEquals(-1, item.getSellIn());
        assertEquals(46, item.getQuality());
    }

    @Test
    @DisplayName("Item with quality above 50 degrades normally")
    void itemWithQualityAbove50DegradesNormally() {
        // Note: Normal items can theoretically be created with quality > 50
        // even though game mechanics prevent this in most cases
        Item item = new Item("Normal Item", 5, 60);
        item.updateQuality();
        
        assertEquals(4, item.getSellIn());
        assertEquals(59, item.getQuality());
    }
}
