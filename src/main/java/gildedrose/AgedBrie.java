package gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        increaseQuality();
        decreaseSellIn();
        applyExpiredBonus();
    }

    private void applyExpiredBonus() {
        if (isExpired()) {
            increaseQuality();
        }
    }
}
