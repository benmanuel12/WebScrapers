package com.ben.scrapers;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;

import java.util.ArrayList;


@Configuration
public class AppConfig {
    private SessionFactory sessionFactory;

    @Bean
    public CardDAO cardDAO() {
        CardDAO cardDAO = new CardDAO();
        cardDAO.setSessionFactory(sessionFactory());
        return cardDAO;
    }

    @Bean
    public OptionDAO optionDAO() {
        OptionDAO optionDAO = new OptionDAO();
        optionDAO.setSessionFactory(sessionFactory());
        return optionDAO;
    }

    @Bean
    public SessionFactory sessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        } else {
            try {
                //Create a builder for the standard service registry
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

                //Load configuration from hibernate configuration file.
                //Here we are using a configuration file that specifies Java annotations.
                //System.out.println("here 1.5");
                standardServiceRegistryBuilder.configure("hibernate.cfg.xml");
                //System.out.println("here 1");

                //Create the registry that will be used to build the session factory
                StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
                try {
                    //Create the session factory - this is the goal of the init method.
                    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                    //System.out.println("here 2");
                } catch (Exception e) {
                /* The registry would be destroyed by the SessionFactory,
                    but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy(registry);
                }

                //Output result
                System.out.println("Session factory built.");

            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("SessionFactory creation failed: " + ex);
            }
            return sessionFactory;
        }
    }


    @Bean
    public ScraperManager manager() {
        ScraperManager manager = new ScraperManager();
        ArrayList<Scraper> temp = new ArrayList<>();
        //temp.add(scraper1());
        temp.add(scraper2());
        //temp.add(scraper3());
        //temp.add(scraper4());
        //temp.add(scraper5());
        manager.setScraperList(temp);
        return manager;
    }

    @Bean
    public Scraper1 scraper1() {
        Scraper1 scraper1 = new Scraper1();
        scraper1.setName("Magic Madhouse");
        scraper1.setCardDAO(cardDAO());
        scraper1.setOptionDAO(optionDAO());
        return scraper1;
    }

    @Bean
    public Scraper2 scraper2() {
        Scraper2 scraper2 = new Scraper2();
        scraper2.setName("Chaos Cards");
        scraper2.setCardDAO(cardDAO());
        scraper2.setOptionDAO(optionDAO());
        return scraper2;
    }

    @Bean
    public Scraper3 scraper3() {
        Scraper3 scraper3 = new Scraper3();
        scraper3.setName("Card Kingdom");
        scraper3.setCardDAO(cardDAO());
        scraper3.setOptionDAO(optionDAO());
        return scraper3;
    }

    @Bean
    public Scraper4 scraper4(){
        Scraper4 scraper4 = new Scraper4();
        scraper4.setName("TCG Player");
        scraper4.setCardDAO(cardDAO());
        scraper4.setOptionDAO(optionDAO());
        return scraper4;
    }

    @Bean
    public Scraper5 scraper5() {
        Scraper5 scraper5 = new Scraper5();
        scraper5.setName("Troll and Toad");
        scraper5.setCardDAO(cardDAO());
        scraper5.setOptionDAO(optionDAO());
        return scraper5;
    }
}
