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
public class Scraper2 extends Scraper {
    /**
     * A String to remember which scraper is which
     */
    private String name;

    public Scraper2() {
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


        driver.get("https://www.chaoscards.co.uk/shop/magic-the-gathering/singles-magic/brand/magic-the-gathering/edition-magic/zendikar-rising/page/30");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<WebElement> cardList = driver.findElements(By.className("prod-el__link"));
        for (WebElement card : cardList) {
            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.prod-el__image-wrap > img"));
            String src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("h6.prod-el__title"));
            WebElement nameSpan = nameTag.findElement((By.cssSelector("span")));
            String name = nameSpan.getAttribute("innerHTML");
            if (name.indexOf('(') != -1) {
                String newName = name.substring(0, name.indexOf('('));
                System.out.println("New Card name: " + newName);
            } else {
                String newName = name.substring(0, name.indexOf(" :"));
                System.out.println("New Card name: " + newName);
            }
            System.out.println("Card name: " + name);

            // Find the purchase URL
            System.out.println(card.getAttribute("href"));

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("p.prod-el__pricing"));
            System.out.println("Price: " + priceTag.getAttribute("innerHTML"));

            // Find the set code
            String code = name.substring(name.indexOf("ZENDIKAR RISING ") + 16, name.indexOf("- Magic the Gathering Single Card") - 1);
            code = code.substring(0, code.indexOf('/'));
            System.out.println("Set Code: " + code);


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
