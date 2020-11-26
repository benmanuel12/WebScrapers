package com.ben.scrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Scanner;

/**
 * Web Scraper class extending super class
 */
public class Scraper3 extends Scraper {
    /**
     * A String to remember which scraper is which
     */
    private String name;

    /**
     * Zero argument constructor
     */
    public Scraper3() {
    }

    /**
     * Scrapes the website
     */
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        WebDriver driver = new ChromeDriver(options);
        stop = false;


        driver.get("https://www.cardkingdom.com/mtg/zendikar-rising/singles");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<WebElement> cardList = driver.findElements(By.className("productItemWrapper"));
        for (WebElement card : cardList) {
            String src;
            String cardName;
            String purchaseUrl;
            double price;
            int code = 0;
            // No easy way to extract set code from this site, sadly

            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.catalogItem a img"));
            src = imageTag.getAttribute("src");
            //System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.itemContentWrapper table tbody tr.detailWrapper td span a"));
            cardName = nameTag.getAttribute("innerHTML");
            //System.out.println("Card name: " + cardName);

            // Find the purchase URL
            purchaseUrl = nameTag.getAttribute("href");
            //System.out.println("Purchase URL: " + purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("div.itemContentWrapper div.addToCartWrapper ul.addToCartByType li.itemAddToCart form.addToCartForm div.amtAndPrice span.stylePrice"));
            price = Double.parseDouble(priceTag.getAttribute("innerHTML").substring(2));
            price = toPounds(price);
            //System.out.println("Price: " + price);

            // Add to database if required
            updateDatabase(this.getName(), cardName, src, purchaseUrl, price, code);
        }


        try {
            System.out.println("Sleeping");
            sleep(crawlDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
/*
            Scanner input = new Scanner(System.in);
            if (input.next() == " ") {
                stop = true;
            }
        }
*/
        driver.quit();
    }
}
