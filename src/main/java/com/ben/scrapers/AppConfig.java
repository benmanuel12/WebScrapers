package com.ben.scrapers;

import org.springframework.context.annotation.*;

import java.util.ArrayList;


@Configuration
public class AppConfig {

    @Bean
    public ScraperManager manager(){
        ScraperManager manager = new ScraperManager();
        ArrayList<Scraper> temp = new ArrayList<>();
        temp.add(scraper1());
        temp.add(scraper2());
        temp.add(scraper3());
        temp.add(scraper4());
        temp.add(scraper5());
        manager.setScraperList(temp);
        return manager;
    }

    @Bean
    public Scraper scraper1(){
        Scraper scraper = new Scraper();
        scraper.setName("Magic Madhouse");
        return scraper;
    }

    @Bean
    public Scraper scraper2(){
        Scraper scraper = new Scraper();
        scraper.setName("Chaos Cards");
        return scraper;
    }

    @Bean
    public Scraper scraper3(){
        Scraper scraper = new Scraper();
        scraper.setName("Card Kingdom");
        return scraper;
    }

    @Bean
    public Scraper scraper4(){
        Scraper scraper = new Scraper();
        scraper.setName("TCG Player");
        return scraper;
    }

    @Bean
    public Scraper scraper5(){
        Scraper scraper = new Scraper();
        scraper.setName("Troll and Toad");
        return scraper;
    }
}
