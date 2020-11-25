package com.ben.scrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Scanner;

/**
 * Generic class for a web scraper
 */
public class Scraper1 extends Scraper {
    /*
      A String to remember which scraper is which
     */
    private String name;

    public Scraper1() {
    }

    /**
     * Implements the run method from Runnable. This test one just prints the name.
     * Can be replaced with proper scraping code later
     */
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        WebDriver driver = new ChromeDriver(options);
        stop = false;

        driver.get("https://www.magicmadhouse.co.uk/magic-the-gathering-c1#t10659:t10778:t10779:t10780:t85:t86:t10781");

        try {
            sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<WebElement> cardList = driver.findElements(By.className("product"));
        for (WebElement card : cardList) {
            String src;
            String cardName;
            String purchaseUrl;
            double price;
            int code;


            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.product__image a img"));
            src = "https://www.magicmadhouse.co.uk/" + imageTag.getAttribute("data-src");
            //System.out.println("Image source: https://www.magicmadhouse.co.uk" + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.product__details__holder div.product__details div.product__details__title a"));
            String longName = nameTag.getAttribute("title");
            if (longName.indexOf('(') != -1) {
                cardName = longName.substring(0, longName.indexOf('('));
            } else {
                cardName = longName;
            }
            //System.out.println("Card name: " + cardName);


            // Find the purchase URL
            purchaseUrl = nameTag.getAttribute("href");
            //System.out.println("Purchase URL: " + purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("div.product__details__holder div.product__options div.product__details__prices span.product__details__prices__price > span > span.product-content__price--inc > span.GBP"));
            price = Double.parseDouble(priceTag.getAttribute("innerHTML").substring(1));
            //System.out.println("Price: " + price);

            // Find the set code
            if ((longName.indexOf('(') != -1) && (longName.indexOf(')') != -1)) {
                code = Integer.valueOf(longName.substring(longName.indexOf('(') + 2, longName.indexOf(')')));
                System.out.println("Set code: " + code);
            } else {
                code = 0;
            }

            // Add to database if required
            updateDatabase(this.getName(), cardName, src, purchaseUrl, price, code);

            }

        try {
            System.out.println("Sleeping");
            sleep(crawlDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//            Scanner input = new Scanner(System.in);
//            if (input.next() == " ") {
//                stop = true;
//            }
        driver.quit();
    }
}
