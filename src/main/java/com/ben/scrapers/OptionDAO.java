package com.ben.scrapers;

//Hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OptionDAO {
    //Creates new Sessions when we need to interact with the database
    private SessionFactory sessionFactory;


    /** Empty constructor */
    OptionDAO() {
    }


    /** Adds a new option to the database */
    public void addOption(OptionAnnotation option){
        //Get a new Session instance from the session factory
        if(sessionFactory == null){
            System.out.println("session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Add Option to database - will not be stored until we commit the transaction
        session.save(option);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Option added to database with ID: " + option.getId());
    }

    /** Updates a option in the database */
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

    /** Returns a option from the database */
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

    /** Returns all options from the database that fit the query */
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

    /** Deletes a option from the database */
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

    public void shutDown(){
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

