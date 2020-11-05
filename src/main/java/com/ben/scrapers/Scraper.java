package com.ben.scrapers;

/**
 * Generic class for a web scraper
 */
public class Scraper implements Runnable{
    /**
     * A String to remember which scraper is which
     */
    private String name;
    // Something for the SessionFactory and Hibernate goes here as well

    public Scraper(){
    }

    /**
     * Implements the run method from Runnable. This test one just prints the name.
     * Can be replaced with proper scraping code later
     */
    public void run(){
        System.out.println(this.getName());
    }

    /**
     * Returns the class's name attribute value
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the class's name attribute
     * @param name a String naming the scraper
     */
    public void setName(String name) {
        this.name = name;
    }
}
