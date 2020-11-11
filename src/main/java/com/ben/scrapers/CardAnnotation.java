package com.ben.scrapers;

import javax.persistence.*;

/**
 * Represents a card
 */
@Entity
@Table(name="cards")
public class CardAnnotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_set_code")
    private int card_set_code;

    @Column(name = "image_url")
    private String imageUrl;

    public CardAnnotation(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCard_set_code() {
        return card_set_code;
    }

    public void setCard_set_code(int card_set_code) {
        this.card_set_code = card_set_code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
