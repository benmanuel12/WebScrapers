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
public class Scraper2 extends Scraper {

    // A String to remember which scraper is which
    private String name;

    /**
     * Zero argument constructor
     */
    public Scraper2() {
    }

    /**
     * Scrapes the website
     */
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        WebDriver driver = new ChromeDriver(options);
        stop = false;


        driver.get("https://www.chaoscards.co.uk/shop/magic-the-gathering/singles-magic/brand/magic-the-gathering/edition-magic/zendikar-rising");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<WebElement> cardList = driver.findElements(By.className("prod-el__link"));
        for (WebElement card : cardList) {
            String src;
            String cardName;
            String purchaseUrl;
            double price;
            int code;

            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.prod-el__image-wrap > img"));
            src = imageTag.getAttribute("src");
            // System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("h6.prod-el__title"));
            WebElement nameSpan = nameTag.findElement((By.cssSelector("span")));
            String longName = nameSpan.getAttribute("innerHTML");
            if (longName.indexOf('(') != -1) {
                cardName = longName.substring(0, longName.indexOf('('));
            } else {
                cardName = longName.substring(0, longName.indexOf(" :"));
            }
            //System.out.println("Card name: " + cardName);

            // Find the purchase URL
            purchaseUrl = card.getAttribute("href");
            //System.out.println(purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("p.prod-el__pricing"));
            price = Double.parseDouble(priceTag.getAttribute("innerHTML").substring(2));
            //System.out.println("Price: " + price);

            // Find the set code
            String codeString = longName.substring(longName.indexOf("ZENDIKAR RISING ") + 16, longName.indexOf("- Magic the Gathering Single Card") - 1);
            if (codeString.indexOf('/') != -1) {
                codeString = codeString.substring(0, codeString.indexOf('/'));
            }
            code = Integer.parseInt(codeString);
            //System.out.println("Set Code: " + code);

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
