package com.ben.scrapers;

import java.util.List;

/**
 * Abstract super class for Scrapers to hold the shared code
 */
public abstract class Scraper extends Thread{

    /**
     * double to store the exchange rate from American Dollars to British Pounds. Only set as final to prevent edits by code
     */
    final protected double EXCHANGE_RATE = 0.75;

    /**
     * int to store the number of milliseconds all scrapers must wait before scraping another page
     */
    protected int crawlDelay = 11000;

    /**
     * int to store the number of milliseconds all scrapers must wait for a page to load
     */
    protected int loadDelay = 5000;

    /**
     * Stores the scraper's Card Data Access Object
     */
    protected CardDAO cardDAO;

    /**
     * Stores the scraper's Option Data Access Object
     */
    protected OptionDAO optionDAO;

    /**
     * Boolean flag to manually stop the scraper loop
     */
    protected boolean stop;

    /**
     * Returns the value of the cardDAO attribute
     * @return cardDAO
     */
    public CardDAO getCardDAO() {
        return cardDAO;
    }

    /**
     * Sets the value of the cardDAO attribute
     * @param cardDAO card Data Access Object
     */
    public void setCardDAO(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    /**
     * Return the value of the optionDAO attribute
     * @return optionDAO
     */
    public OptionDAO getOptionDAO() {
        return optionDAO;
    }

    /**
     * Sets the value of the optionDAO attribute
     * @param optionDAO option Data Access Object
     */
    public void setOptionDAO(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    /**
     * empty function for action in threads to be overridden by sub classes
     */
    public void run(){

    }

    /**
     * Escapes single quotes in strings scraped from sites so they don't break the SQL
     * @param input a string that might have single quotes
     * @return newString a string with all single quotes escaped
     */
    public String escapeQuotes(String input){
        String newString = "";
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '\''){
                newString += "''";
            } else {
                newString += input.charAt(i);
            }
        }
        return newString;
    }

    /**
     * Converts prices in dollars to GBP
     * @param input a price in dollars
     * @return output a price in GBP
     */
    public double toPounds(double input){
        String intermediate = String.format("%.2f", (input * EXCHANGE_RATE));
        double output = Double.parseDouble(intermediate);
        return output;
    }

    /**
     * Updates the database using scraped data
     * If no card exists with the name given, makes a new one
     * If no option exists from that site for that card makes a new one
     * Then updates price of option
     * @param name the name of the scraper (and site being scraped)
     * @param cardName the name of the card scraped
     * @param src image source
     * @param purchaseUrl link to buy card
     * @param price price of card
     * @param code set code of card
     */
    public void updateDatabase(String name, String cardName, String src, String purchaseUrl, double price, int code){

        List searchForName = cardDAO.searchCards("from CardAnnotation where cardName='" + escapeQuotes(cardName) + "'");
        if (searchForName.isEmpty()) {
            CardAnnotation newCard = new CardAnnotation();
            newCard.setCardName(cardName);
            newCard.setImageUrl(src);
            newCard.setCardSetCode(code);

            cardDAO.addCard(newCard);
            System.out.println("Adding new card");
        } else {
            System.out.println("Card already exists");
        }
        // Fetch relevant card Id from table
        List<CardAnnotation> searchForIDCard = cardDAO.searchCards("from CardAnnotation where cardName='" + escapeQuotes(cardName) + "'");

        // Search for Options with that card Id and this scraper name
        System.out.println(searchForIDCard.get(0).getId());
        List<OptionAnnotation> searchForIDOption = optionDAO.searchOptions("from OptionAnnotation where card_id=" + searchForIDCard.get(0).getId() + " and shopName='" + escapeQuotes(name) + "'");

        OptionAnnotation optionAnnotation;
        if(searchForIDOption.isEmpty())
            optionAnnotation = null;
        else
            optionAnnotation = searchForIDOption.get(0);

        if (optionAnnotation == null) {
            optionAnnotation = new OptionAnnotation();
            optionAnnotation.setCardId(searchForIDCard.get(0));
            optionAnnotation.setLink(purchaseUrl);
//            optionAnnotation.setPrice(price);
            optionAnnotation.setShopName(name);
            System.out.println("Creating new option");
        }
        optionAnnotation.setPrice(price);
        optionDAO.updateOption(optionAnnotation); //Save or update
        System.out.println("SaveOrUpdating Option");
    }
}
