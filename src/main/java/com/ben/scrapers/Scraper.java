package com.ben.scrapers;

import java.util.List;

public abstract class Scraper extends Thread{

    protected int crawlDelay = 11000;

    protected int loadDelay = 3000;

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

    public void updateDatabase(String name, String cardName, String src, String purchaseUrl, double price, int code){

        List searchForName = cardDAO.searchCards("from CardAnnotation where cardName=" + cardName);
        if (searchForName.isEmpty()) {
            CardAnnotation newCard = new CardAnnotation();
            newCard.setCardName(name);
            newCard.setImageUrl(src);
            newCard.setCard_set_code(code);

            cardDAO.addCard(newCard);
        }
//        // Fetch relevant card Id from table
//        List searchForIDCard = cardDAO.searchCards("select id from CardAnnotation where cardName=" + cardName);
//
//        // Search for Options with that card Id and this scraper name
//        System.out.println(searchForIDCard.toString());
//        int currentCardID = (int) searchForIDCard.get(0);
//
//        List searchForIDOption = optionDAO.searchOptions("from OptionAnnotation where id=" + searchForIDCard.get(0) + " and shopName=" + name );
//        if (searchForIDOption.isEmpty()) {
//            OptionAnnotation newOption = new OptionAnnotation();
//            newOption.setCardId((CardAnnotation) searchForIDCard.get(0));
//            newOption.setLink(purchaseUrl);
//            newOption.setPrice(price);
//            newOption.setShopName("Magic Madhouse");
//
//            optionDAO.addOption(newOption);
//
//        } else if (searchForIDOption.get(0).price != price) {
//            OptionAnnotation tempOption = optionDAO.getOption(currentCardID);
//            tempOption.setPrice(price);
//
//            optionDAO.updateOption(tempOption);
//
//        } else {
//            System.out.println("No change required");
//        }
    }
}
