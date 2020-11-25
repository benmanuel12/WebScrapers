package com.ben.scrapers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main Class
 */
public class Main {

    /**
     * Main Method
     * @param args needed for main method
     */
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ScraperManager scraperManager = (ScraperManager) context.getBean("manager");
        scraperManager.scrape();
    }
}
