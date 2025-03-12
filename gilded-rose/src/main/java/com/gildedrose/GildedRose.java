package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            applyExpiredItem(items[i]);
            updateSellIn(items[i]);
        }
    }

    public void applyExpiredItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
                updateAgedBrie(item);
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                updateBackstagePasses(item);
                break;
            case "Conjured Mana Cake":
                updateConjuredManaCake(item);
                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            default:
                updateDefaultItem(item);
                break;
        }
    }

    public void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality++;
        }
    }

    public void updateBackstagePasses(Item item) {
        if (Caducado(item)) {
            item.quality = 0;
        } else {
            if (item.quality < 50) {
                item.quality++;
                if (item.sellIn < 11 && item.quality < 50) {
                    item.quality++;
                }
                if (item.sellIn < 6 && item.quality < 50) {
                    item.quality++;
                }
            }
        }
    }

    public void updateConjuredManaCake(Item item) {
        int degradeAmount = (item.sellIn < 0) ? 4 : 2;
        item.quality -= degradeAmount;

        if (Caducado(item)) {
            item.quality = 0;
        }
    }

    public void updateDefaultItem(Item item) {
        int degradeAmount = (item.sellIn < 0) ? 2 : 1;
        if (Caducado(item)) {
            item.quality -= degradeAmount;
          
            
        }
    }

	private boolean Caducado(Item item) {
		return item.quality > 0;
	}

    public void updateSellIn(Item item) {
    	
    	    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
    	        item.sellIn--;
    	    }
    	   
}}
