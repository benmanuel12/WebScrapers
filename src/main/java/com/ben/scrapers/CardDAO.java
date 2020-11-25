package com.ben.scrapers;

//Hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CardDAO {
    //Creates new Sessions when we need to interact with the database
    private SessionFactory sessionFactory;


    /** Empty constructor */
    CardDAO() {
    }


    /** Adds a new card to the database */
    public void addCard(CardAnnotation card){
        //Get a new Session instance from the session factory
        if(sessionFactory == null){
            System.out.println("session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Add Card to database - will not be stored until we commit the transaction
        session.save(card);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Card added to database with ID: " + card.getId());
    }

    /** Updates a card in the database */
    public void updateCard(CardAnnotation card){
        //Get a new Session instance from the session factory
        if(sessionFactory == null) {
            System.out.println("session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Updates Card in database - will not be stored until we commit the transaction
        session.saveOrUpdate(card);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Card with ID: " + card.getId() + " updated in database");
    }
    /** Returns a card from the database */
    public CardAnnotation getCard(int id){
        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factroy null");
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Get Card from database
        CardAnnotation card = session.load(CardAnnotation.class, id);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        return card;
    }

    /** Returns all cards from the database that fit the query */
    public List<CardAnnotation> searchCards(String query){

        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factory is null");

        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        // Query the database
        List<CardAnnotation> cardList = session.createQuery(query).getResultList();

        //Close the session and release database connection
        session.close();
        return cardList;
    }

    /** Deletes a card from the database */
    public void deleteCard(int id){
        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factroy null");
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        // Loads a card from the database and deletes it
        CardAnnotation card = session.load(CardAnnotation.class, id);
        if (card != null){
            session.delete(card);
        }

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Card deleted from database with ID: " + card.getId());
    }

    /**
     * Closes the SessionFactory instance
     */
    public void shutDown(){
        sessionFactory.close();
    }

    /**
     * Returns the SessionFactory instance assigned to this DAO
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets the value of the SessionFactory assigned to this DAO
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

