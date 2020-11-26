package com.ben.scrapers;

import java.util.List;

/**
 * Abstract super class for Scrapers to hold the shared code
 */
public abstract class Scraper extends Thread{

    final protected double EXCHANGE_RATE = 0.75;

    protected int crawlDelay = 11000;

    protected int loadDelay = 5000;

    protected CardDAO cardDAO;

    protected boolean stop;

    protected OptionDAO optionDAO;

    public CardDAO getCardDAO() {
        return cardDAO;
    }

    public void setCardDAO(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public OptionDAO getOptionDAO() {
        return optionDAO;
    }

    public void setOptionDAO(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    public void run(){

    }

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

    public double toPounds(double input){
        String intermediate = String.format("%.2f", (input * EXCHANGE_RATE));
        double output = Double.parseDouble(intermediate);
        return output;
    }

    public void updateDatabase(String name, String cardName, String src, String purchaseUrl, double price, int code){

        List searchForName = cardDAO.searchCards("from CardAnnotation where cardName='" + escapeQuotes(cardName) + "'");
        if (searchForName.isEmpty()) {
            CardAnnotation newCard = new CardAnnotation();
            newCard.setCardName(cardName);
            newCard.setImageUrl(src);
            newCard.setCard_set_code(code);

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
