package gildedrose.itemtypes;

import gildedrose.Item;

public class BackstagePass extends Item {

    private static final int MEDIUM_URGENCY_THRESHOLD = 10;
    private static final int HIGH_URGENCY_THRESHOLD = 5;

    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        increaseQualityBasedOnUrgency();
        decreaseSellIn();
        handleExpiredPass();
    }

    private void increaseQualityBasedOnUrgency() {
        increaseQuality();
        addMediumUrgencyBonus();
        addHighUrgencyBonus();
    }

    private void addMediumUrgencyBonus() {
        if (isMediumUrgency()) {
            increaseQuality();
        }
    }

    private void addHighUrgencyBonus() {
        if (isHighUrgency()) {
            increaseQuality();
        }
    }

    private void handleExpiredPass() {
        if (isExpired()) {
            resetQuality();
        }
    }

    private boolean isMediumUrgency() {
        return isSellInWithin(MEDIUM_URGENCY_THRESHOLD);
    }

    private boolean isHighUrgency() {
        return isSellInWithin(HIGH_URGENCY_THRESHOLD);
    }
}
