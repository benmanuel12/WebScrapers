package com.ben.scrapers;

import java.util.ArrayList;

/**
 * Class to manage Scraper class instances
 */
public class ScraperManager {

    // List to store references to Scraper instances
    private ArrayList<Scraper> scraperList = new ArrayList<Scraper>();

    public ScraperManager(){
    }

    /**
     * Returns the class's scraperList value
     * @return scraperList
     */
    public ArrayList<Scraper> getScraperList() {
        return scraperList;
    }

    /**
     * Sets the class's scraperList attribute
     * @param scraperList ArrayList of Scraper class instances
     */
    public void setScraperList(ArrayList<Scraper> scraperList) {
        this.scraperList = scraperList;
    }

    /**
     * Creates an ArrayList for threads, then iterates through scraperList, creates a thread for each, adds the thread
     * to the new ArrayList and runs the thread
     * Then waits for the threads to end
     */
    public void scrape(){
        for (Scraper currentScraper : scraperList) {
            System.out.println(currentScraper.getName() + " is scraping");
            currentScraper.start();
        }
    }
}
