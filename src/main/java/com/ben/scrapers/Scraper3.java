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
public class Scraper3 extends Scraper {
    /**
     * A String to remember which scraper is which
     */
    private String name;

    public Scraper3() {
    }

    /**
     * Implements the run method from Runnable. This test one just prints the name.
     * Can be replaced with proper scraping code later
     */
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);

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
            int code;

            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.catalogItem a img"));
            src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.itemContentWrapper table tbody tr.detailWrapper td span a"));
            cardName = nameTag.getAttribute("innerHTML");
            System.out.println("Card name: " + cardName);

            // Find the purchase URL
            purchaseUrl = nameTag.getAttribute("href");
            System.out.println("Purchase URL: " + purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("div.itemContentWrapper div.addToCartWrapper ul.addToCartByType li.itemAddToCart form.addToCartForm div.amtAndPrice span.stylePrice"));
            price = Double.parseDouble(priceTag.getAttribute("innerHTML").substring(2));
            price = price * 0.75;
            price = Math.round(price*100)/100;
            System.out.println("Price: " + price);
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
