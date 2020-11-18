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

        List<WebElement> cardList = driver.findElements(By.className(".productItemWrapper"));
        for (WebElement card : cardList) {

            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.catalogItem a img"));
            String src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.itemContentWrapper table tbody tr.detailWrapper td span a"));
            System.out.print("Card name: " + nameTag.getAttribute("innerHTML") + "\n");

            // Find the purchase URL
            System.out.print("Purchase URL: https://www.cardkingdom.com/" + nameTag.getAttribute("href") + "\n");

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("div.itemContentWrapper div.addToCartWrapper ul.addToCardByType li.itemAddToCart form div.amtAndPrice span.stylePrice"));
            System.out.print("Price: " + nameTag.getAttribute("innerHTML"));
        }


        try {
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
