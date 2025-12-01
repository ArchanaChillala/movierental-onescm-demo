package gildedrose;

public class ItemFactory {
    
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static Item createItem(String name, int sellIn, int quality) {
        if (AGED_BRIE.equals(name)) {
            return new AgedBrie(sellIn, quality);
        }
        
        if (BACKSTAGE_PASSES.equals(name)) {
            return new BackstagePass(sellIn, quality);
        }
        
        if (SULFURAS.equals(name)) {
            return new Sulfuras(sellIn, quality);
        }
        
        return new Item(name, sellIn, quality);
    }
}
