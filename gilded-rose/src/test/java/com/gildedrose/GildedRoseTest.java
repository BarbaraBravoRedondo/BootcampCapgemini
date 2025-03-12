package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.List;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
//@UseReporter(DiffReporter.class)


class GildedRoseTest {

    @ParameterizedTest
    @CsvSource({
        "foo, 0, 20, -1, 19",
        "foo, 4, 3, 3, 2"
    })
    @DisplayName("Producto estandar")
    void testFoo(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Verificacion de producto estandar",
            () -> assertEquals(expectedSellIn, items[0].sellIn),
            () -> assertEquals(expectedQuality, items[0].quality)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "Aged Brie, 2, 2, 1, 3",
        "Aged Brie, 0, 10, -1, 11",
        "Aged Brie, -1, 50, -2, 50"
    })
    @DisplayName("Aged Brie")
    void testAgedBrie(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Verificacion de Aged Brie",
            () -> assertEquals(expectedSellIn, items[0].sellIn),
            () -> assertEquals(expectedQuality, items[0].quality)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "'Sulfuras, Hand of Ragnaros', 10, 80, 10, 80",  
        "'Sulfuras, Hand of Ragnaros', 20, 80, 20, 80",   
        "'Sulfuras, Hand of Ragnaros', 5, 80, 5, 80"    
    })
    @DisplayName("Sulfuras, Hand of Ragnaros")
    void sulfurasTest(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
    	   Item[] items = new Item[] { new Item(name, sellIn, quality) };
           GildedRose app = new GildedRose(items);
           app.updateQuality();
           assertAll("Verificacion Sulfuros",
               () -> assertEquals(expectedSellIn, items[0].sellIn),
               () -> assertEquals(expectedQuality, items[0].quality)
           );
    }
   @Disabled
	@ParameterizedTest(name = "{0} => sellIn: {1} quality: {2} –> sellIn: {3} quality: {4}")
	@CsvFileSource(resources = "/casos-de-prueba.csv", numLinesToSkip = 1)
	void datasourceTest(String producto, int sellIn, int quality, int sellInResult, int qualityResult) {
	String name = producto.replace("\'", "");
		//assumeFalse("Conjured Mana Cake".equals(name));
	Item product = new Item(name, sellIn, quality);
        GildedRose app = new GildedRose(new Item[] { 
        		product
       });        app.updateQuality();
       assertAll(name,
      		() -> assertEquals(name, product.name, "name"),
      		() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
       		() -> assertEquals(qualityResult, product.quality, "quality")
       		);
	}}









