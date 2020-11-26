package com.ben.scrapers;

import javax.persistence.*;

/**
 * Represents a card
 */
@Entity
@Table(name="cards")
public class CardAnnotation {

    // Attribute to represent the id column of the cards table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Attribute to represent the card_name column of the cards table
    @Column(name = "card_name")
    private String cardName;

    // Attribute to represent the card_set_code column of the cards table
    @Column(name = "card_set_code")
    private int cardSetCode;

    // Attribute to represent the image_url column of the cards table
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Creates a CardAnnotation that maps to a row in the cards table (0 argument constructor)
     */
    public CardAnnotation(){
    }

    /**
     * Returns the value of the id attribute
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id attribute
     * @param id the id of the card (auto set by Hibernate under autoincrement)
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value of the cardName attribute
     * @return cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the value of the cardName attribute
     * @param cardName the name of the card
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * Returns the value of the card
     * @return cardSetCode
     */
    public int getCard_set_code() {
        return cardSetCode;
    }

    /**
     * Sets the value of the cardSetCode attribute
     * @param cardSetCode special value for the creators
     */
    public void setCardSetCode(int cardSetCode) {
        this.cardSetCode = cardSetCode;
    }

    /**
     * Returns the value of the imageUrl attribute
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the value of the imageUrl attribute
     * @param imageUrl link for an image
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
