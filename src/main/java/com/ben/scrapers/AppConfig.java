package com.ben.scrapers;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;

import java.util.ArrayList;


@Configuration
public class AppConfig{
    private SessionFactory sessionFactory;

    @Bean
    public CardDAO cardDao(){
        CardDAO cardDAO = new CardDAO();
        //System.out.println("here 3");
        cardDAO.setSessionFactory(sessionFactory());
        return cardDAO;
    }

    @Bean
    public SessionFactory sessionFactory(){
        if (sessionFactory != null) {
            return sessionFactory;
        }
        else{
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
                    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
                    //System.out.println("here 2");
                }
                catch (Exception e) {
                /* The registry would be destroyed by the SessionFactory,
                    but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy( registry );
                }

                //Output result
                System.out.println("Session factory built.");

            }
            catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("SessionFactory creation failed: " + ex);
            }
            return sessionFactory;
        }
    }
    // This code just uses the ScraperManager and Scraper classes to prove the Spring beans works
    // Must be replaced with proper Beans later
    /*
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

     */
}
