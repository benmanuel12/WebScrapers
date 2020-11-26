package com.ben.scrapers;

//Hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Data Access Object for the options table in the database
 */
public class OptionDAO {

    // Creates new Sessions when we need to interact with the database
    private SessionFactory sessionFactory;


    /** Empty constructor */
    OptionDAO() {
    }


    /**
     * Adds a new option to the database
     * @param option OptionAnnotation instance
     */
    public void addOption(OptionAnnotation option){
        // Get a new Session instance from the session factory
        if(sessionFactory == null){
            System.out.println("session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();

        // Start transaction
        session.beginTransaction();

        // Add Option to database - will not be stored until we commit the transaction
        session.save(option);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Option added to database with ID: " + option.getId());
    }

    /**
     * Updates a option in the database
     * @param option OptionAnnotation instance
     */
    public void updateOption(OptionAnnotation option) {
        //Get a new Session instance from the session factory
        if (sessionFactory == null) {
            System.out.println("session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Add Option to database - will not be stored until we commit the transaction
        session.saveOrUpdate(option);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Option with ID: " + option.getId() + " updated in database");
    }

    /**
     * Returns a option from the database
     * @param id the id of the option to fetch
     * @return option
     */
    public OptionAnnotation getOption(int id){
        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factroy null");
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Add Option to database - will not be stored until we commit the transaction
        OptionAnnotation option = session.load(OptionAnnotation.class, id);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        return option;
    }

    /**
     * Returns all options from the database that fit the query
     * @param query Hibernate Query String
     * @return optionList
     */
    public List<OptionAnnotation> searchOptions(String query){

        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factory is null");

        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        List<OptionAnnotation> optionList = session.createQuery(query).getResultList();

        //Close the session and release database connection
        session.close();
        return optionList;
    }

    /**
     * Deletes a option from the database
     * @param id the id of the option to delete
     */
    public void deleteOptions(int id){
        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factroy null");
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        OptionAnnotation option = session.load(OptionAnnotation.class, id);
        if (option != null){
            session.delete(option);
        }

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Option deleted from database with ID: " + option.getId());
    }

    /**
     * Closes the session factory
     */
    public void shutDown(){
        sessionFactory.close();
    }

    /**
     * Returns the value of the sessionFactory attribute
     * @return sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets the value of the sessionFactory attribute
     * @param sessionFactory the session factory used to make sessions for the database
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

