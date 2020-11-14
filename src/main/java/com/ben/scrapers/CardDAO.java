package com.ben.scrapers;

//Hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

        //Add Card to database - will not be stored until we commit the transaction
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

        //Add Card to database - will not be stored until we commit the transaction
        CardAnnotation card = session.load(CardAnnotation.class, id);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        return card;
    }
    /** Deletes a card from the database */
    public void deleteCard(int id){
        //Get a new Session instance from the session factory
        if(sessionFactory == null)
            System.out.println("session factroy null");
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

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

