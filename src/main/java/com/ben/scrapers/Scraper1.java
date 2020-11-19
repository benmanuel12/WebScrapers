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
    /**
     * A String to remember which scraper is which
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
        options.setHeadless(false);

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
            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.product__image a img"));
            String src = imageTag.getAttribute("data-src");
            System.out.println("Image source: https://www.magicmadhouse.co.uk" + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.product__details__holder div.product__details div.product__details__title a"));
            String name = nameTag.getAttribute("title");
            if (name.indexOf('(') != -1) {
                String newName = name.substring(0, name.indexOf('('));
                System.out.println("New Card name: " + newName);
            }
            System.out.println("Old Card name: " + name);


            // Find the purchase URL
            System.out.println("Purchase URL: " + nameTag.getAttribute("href"));

            // Find the price
            WebElement priceTag1 = card.findElement(By.cssSelector("div.product__details__holder"));
            //System.out.println("Got priceTag1");
            WebElement priceTag0 = priceTag1.findElement(By.cssSelector("div.product__options"));
            //System.out.println("Got priceTag0");
            WebElement priceTag7 = priceTag0.findElement(By.cssSelector("div.product__details__prices"));
            //System.out.println("Got priceTag7");
            WebElement priceTag2 = priceTag7.findElement(By.cssSelector("span.product__details__prices__price > span > span.product-content__price--inc > span.GBP"));
            //System.out.println("Got priceTag2");
            System.out.println("Price: " + priceTag2.getAttribute("innerHTML"));

            // Find the setcode
            if ((name.indexOf('(') != -1) && (name.indexOf(')') != -1)) {
                String code = name.substring(name.indexOf('(') + 2, name.indexOf(')'));
                System.out.println("Set code: " + code);
            }
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
