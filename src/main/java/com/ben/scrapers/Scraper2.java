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


        driver.get("https://www.chaoscards.co.uk/shop/magic-the-gathering/singles-magic/brand/magic-the-gathering/edition-magic/zendikar-rising");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("here");
        List<WebElement> cardList = driver.findElements(By.className(".prod-el__link"));
        for (WebElement card : cardList) {
            System.out.println("loop");
            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.prod-el__image-wrap > img"));
            String src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.prod-el__title > span"));
            System.out.print("Card name: " + nameTag.getAttribute("innerHTML") + "\n");

            // Find the purchase URL
            System.out.print("Purchase URL: https://www.chaoscards.co.uk/" + card.getAttribute("href") + "\n");

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("p.prod-el__pricing"));
            System.out.print("Price: " + nameTag.getAttribute("innerHTML"));

            // Find the set code
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
