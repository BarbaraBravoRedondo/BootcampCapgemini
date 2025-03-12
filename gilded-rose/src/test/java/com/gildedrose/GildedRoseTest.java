package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


class GildedRoseTest {

//    @ParameterizedTest
//    @CsvSource({
//        "foo, 0, 20, -1, 18",
//        "foo, 4, 3, 3, 2"
//    })
//    @DisplayName("Producto estandar")
//    void testFoo(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
//        Item[] items = new Item[] { new Item(name, sellIn, quality) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertAll("Verificacion de producto estandar",
//            () -> assertEquals(expectedSellIn, items[0].sellIn),
//            () -> assertEquals(expectedQuality, items[0].quality)
//        );
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//        "Aged Brie, 2, 2, 1, 3",
//        "Aged Brie, 0, 10, -1, 12",
//        "Aged Brie, -1, 50, -2, 50"
//    })
//    @DisplayName("Aged Brie")
//    void testAgedBrie(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
//        Item[] items = new Item[] { new Item(name, sellIn, quality) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertAll("Verificacion de Aged Brie",
//            () -> assertEquals(expectedSellIn, items[0].sellIn),
//            () -> assertEquals(expectedQuality, items[0].quality)
//        );
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//        "'Sulfuras, Hand of Ragnaros', 10, 80, 10, 80",  
//        "'Sulfuras, Hand of Ragnaros', 20, 80, 20, 80",   
//        "'Sulfuras, Hand of Ragnaros', 5, 80, 5, 80"    
//    })
//    @DisplayName("Sulfuras, Hand of Ragnaros")
//    void sulfurasTest(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
//    	   Item[] items = new Item[] { new Item(name, sellIn, quality) };
//           GildedRose app = new GildedRose(items);
//           app.updateQuality();
//           assertAll("Verificacion Sulfuros",
//               () -> assertEquals(expectedSellIn, items[0].sellIn),
//               () -> assertEquals(expectedQuality, items[0].quality)
//           );
//    }
//
//
//
//
//    @ParameterizedTest
//    @CsvSource({
//        "Backstage passes to a TAFKAL80ETC concert, 9, 3, 8, 5",
//        "Backstage passes to a TAFKAL80ETC concert, 4, 3, 3, 6",
//        "Backstage passes to a TAFKAL80ETC concert, 10, 5, 9, 7",
//        "Backstage passes to a TAFKAL80ETC concert, 10, 5, 9, 7",
//        "Backstage passes to a TAFKAL80ETC concert, 0, 10, -1, 0",
//        
//    })
//    @DisplayName("Backstage passes")
//    void testBackstagePasses(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
//        Item[] items = new Item[] { new Item(name, sellIn, quality) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertAll("Verificación de Backstage passes",
//            () -> assertEquals(expectedSellIn, items[0].sellIn),
//            () -> assertEquals(expectedQuality, items[0].quality)
//        );
//    }


        
        @ParameterizedTest
        @CsvSource({
            "'Conjured Mana Cake', 5, 20, 4, 19", 
            "'Conjured Mana Cake', 0, 10, -1, 4", 
            "'Conjured Mana Cake', -1, 50, -2, 44" ,
            "'Conjured Mana Cake', 5, 1, 4, 0",
            "'Conjured Mana Cake', -1, 1, -2, 0" 
        })
        @DisplayName("Conjured items")
        void Conjured(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
            Item[] items = new Item[] { new Item(name, sellIn, quality) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertAll("Verificacion de conjurados",
                () -> assertEquals(expectedSellIn, items[0].sellIn),
                () -> assertEquals(expectedQuality, items[0].quality)
            );
        }}


