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
            String src;
            String name;
            String purchaseUrl;
            String price;
            int code;


            // Find the picture
            WebElement imageTag = card.findElement(By.cssSelector("div.product__image a img"));
            src = imageTag.getAttribute("data-src");
            System.out.println("Image source: https://www.magicmadhouse.co.uk" + src);

            // Find the name
            WebElement nameTag = card.findElement(By.cssSelector("div.product__details__holder div.product__details div.product__details__title a"));
            name = nameTag.getAttribute("title");
            if (name.indexOf('(') != -1) {
                name = name.substring(0, name.indexOf('('));
            }
            System.out.println("Card name: " + name);


            // Find the purchase URL
            purchaseUrl = nameTag.getAttribute("href");
            System.out.println("Purchase URL: " + purchaseUrl);

            // Find the price
            WebElement priceTag = card.findElement(By.cssSelector("div.product__details__holder div.product__options div.product__details__prices span.product__details__prices__price > span > span.product-content__price--inc > span.GBP"));
            price = priceTag.getAttribute("innerHTML");
            System.out.println("Price: " + price);

            // Find the set code
            if ((name.indexOf('(') != -1) && (name.indexOf(')') != -1)) {
                code = Integer.valueOf(name.substring(name.indexOf('(') + 2, name.indexOf(')')));
                System.out.println("Set code: " + code);
            }

            // Add to database if required


            List searchForName = cardDAO.searchCards("from CardAnnotation where card_name=" + name);
            if (searchForName.isEmpty()) {
                CardAnnotation newCard = new CardAnnotation();
                newCard.setCardName(name);
                newCard.setImageUrl(src);
                newCard.setCard_set_code(code);

                cardDAO.addCard(newCard);
            }
            List searchForIDCard = cardDAO.searchCards("select id from CardAnnotation where cardName=" + name);
            List searchForIDOption = optionDAO.searchOptions("from OptionAnnotation where id=" + searchForIDCard.get(0)); //add shopname
            if (searchForIDOption.isEmpty()) {
                OptionAnnotation newOption = new OptionAnnotation();
                newOption.setCardId((CardAnnotation) searchForIDCard.get(0));
                newOption.setLink(purchaseUrl);
                newOption.setPrice(Float.parseFloat(price));
                newOption.setShopName("Magic Madhouse");

                optionDAO.addOption(newOption);
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
