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

//
//
//        CardDAO newdao = new CardDAO();
     //   SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

        //CardDAO cardDAO = (CardDAO) context.getBean("cardDao");

//        //Create an instance of a Card class
//        CardAnnotation card = new CardAnnotation();
//
//        //Set values of Card class that we want to add
//        card.setCardName("Omnath, Locus of Creation");
//        card.setCard_set_code(232);
//        card.setImageUrl("https://c1.scryfall.com/file/scryfall-cards/large/front/4/e/4e4fb50c-a81f-44d3-93c5-fa9a0b37f617.jpg?1604264252");
//
//        cardDAO.addCard(card);

        //cardDAO.deleteCard(1);

        //cardDAO.shutDown();
        //System.exit(0);

        //Scraper1 scraper1 = (Scraper1) context.getBean("scraper1");
        //scraper1.run();
    }
}
