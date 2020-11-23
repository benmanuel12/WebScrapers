package com.ben.scrapers;

import java.util.List;

public abstract class Scraper extends Thread{

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

    public void updateDatabase(String name, String cardName, String src, String purchaseUrl, double price, int code){

        List searchForName = cardDAO.searchCards("from CardAnnotation where cardName='" + escapeQuotes(cardName) + "'");
        if (searchForName.isEmpty()) {
            CardAnnotation newCard = new CardAnnotation();
            newCard.setCardName(cardName);
            newCard.setImageUrl(src);
            newCard.setCard_set_code(code);

            cardDAO.addCard(newCard);
        } else {
            System.out.println("Card already exists");
        }
        // Fetch relevant card Id from table
        List<CardAnnotation> searchForIDCard = cardDAO.searchCards("from CardAnnotation where cardName='" + escapeQuotes(cardName) + "'");

        // Search for Options with that card Id and this scraper name
        System.out.println(searchForIDCard.get(0).getId());
        List<OptionAnnotation> searchForIDOption = optionDAO.searchOptions("from OptionAnnotation where id=" + searchForIDCard.get(0).getId() + " and shopName='" + escapeQuotes(name) + "'");
        if (searchForIDOption.isEmpty()) {
            OptionAnnotation newOption = new OptionAnnotation();
            newOption.setCardId(searchForIDCard.get(0));
            newOption.setLink(purchaseUrl);
            newOption.setPrice(price);
            newOption.setShopName(name);

            System.out.println("Adding new option");
            optionDAO.addOption(newOption);

        } else if (searchForIDOption.get(0).getPrice() != price) {
            OptionAnnotation tempOption = optionDAO.getOption(searchForIDCard.get(0).getId());
            tempOption.setPrice(price);

            System.out.println("Updating existing option");
            optionDAO.updateOption(tempOption);

        } else {
            System.out.println("No change required");
        }
    }
}
