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
public class Scraper4 extends Scraper {
    /**
     * A String to remember which scraper is which
     */
    private String name;

    public Scraper4() {
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


        driver.get("https://www.tcgplayer.com/search/magic/zendikar-rising?productLineName=magic&setName=zendikar-rising&page=1&productTypeName=Cards&rarityName=Rare%7CCommon%7CUncommon%7CMythic%7CLand");

        try {
            sleep(loadDelay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<WebElement> cardList = driver.findElements(By.className("search-result__product"));
        for (WebElement card : cardList) {

            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("section.search-result__image > div.progressive-image > span > div.progressive-image-wrapper > img"));
            String src = imageTag.getAttribute("src");
            System.out.println("Image source: " + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("span.search-result__title"));
            System.out.println("Card name: " + nameTag.getAttribute("innerHTML"));

            // Find the purchase URL
            System.out.println(card.getAttribute("href"));

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("section.search-result__market-price span.search-result__market-price--value"));
            System.out.println("Price: " + priceTag.getAttribute("innerHTML"));

            // Find the set code
            WebElement codeTag = card.findElement(By.xpath("//*[@id=\"app\"]/div/section[2]/section/section/span/section/div[2]/div/a[1]/section[2]/span[3]"));
            System.out.println("Code: " + codeTag.getAttribute("innerHTML").substring(1));
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
