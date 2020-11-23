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
public class Scraper5 extends Scraper {
    /**
     * A String to remember which scraper is which
     */
    private String name;

    public Scraper5() {
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


        driver.get("https://www.trollandtoad.com/magic-the-gathering/zendikar-rising-singles/16776");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<WebElement> cardList = driver.findElements(By.xpath("//div[@class='card h-100 p-3']"));
        String src;
        String cardName;
        String purchaseUrl;
        double price;
        int code;

        for (WebElement card : cardList) {
            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.row div.prod-img-container a img"));
            src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.row div.product-info div.mb-1 div.prod-title a"));
            String longName = nameTag.getAttribute("innerHTML");
            cardName = longName.substring(0, longName.indexOf('/') - 4);
            if (cardName.indexOf('-') != -1) {
                cardName = cardName.substring(0, cardName.indexOf('-') - 1);
            }
            System.out.println("Card name: " + cardName);

            // Find the purchase URL
            purchaseUrl = nameTag.getAttribute("href");
            System.out.println("Purchase URL: https://www.trollandtoad.com/" + purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div[3]/div/div[2]/div[4]"));
            price = Double.parseDouble(priceTag.getAttribute("innerHTML").substring(1));
            price = price * 0.75;
            price = Math.round(price*100)/100;
            System.out.println("Price: " + price);

            // Find the set code
            int myIndex = longName.indexOf('/');
            String codeString = longName.substring(0, myIndex);
            codeString = codeString.substring(codeString.lastIndexOf(' ') + 1);
            code = Integer.parseInt(codeString);
            System.out.println("Set code: " + code);

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
