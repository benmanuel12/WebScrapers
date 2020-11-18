package com.ben.scrapers;

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
}
