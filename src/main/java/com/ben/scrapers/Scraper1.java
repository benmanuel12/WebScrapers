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
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<WebElement> cardList = driver.findElements(By.className("product"));
        for (WebElement card : cardList) {
            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.product__image a img"));
            String src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.product__details__holder div.product__details div.product__details__title a"));
            System.out.print("Card name: " + nameTag.getAttribute("title") + "\n");

            // Find the purchase URL
            System.out.print("Purchase URL: https://www.magicmadhouse.co.uk/" + nameTag.getAttribute("href") + "\n");

            // Find the price
            //WebElement priceTag = card.findElement(By.cssSelector("div.product__details__holder div.product__options > div.product__detail__prices > span.product__details__prices__price > span > span.product-content__price--inc > span.GBP"));
            //System.out.print("Price: " + nameTag.getAttribute("innerHTML"));
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
