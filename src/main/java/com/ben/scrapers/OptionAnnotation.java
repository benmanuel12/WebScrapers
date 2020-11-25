package com.ben.scrapers;

import javax.persistence.*;

/**
 * Represents an Option
 */
@Entity
@Table(name="options")
public class OptionAnnotation {

    // Attribute to represent the id column of the options table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Attribute to represent the shop_name column of the options table
    @Column(name = "shop_name")
    private String shopName;

    // Attribute to represent the card_id column of the options table
    @ManyToOne
    @JoinColumn(name = "card_id", nullable=false)
    private CardAnnotation cardId;

    // Attribute to represent the price column of the options table
    @Column(name = "price")
    private double price;

    // Attribute to represent the link column of the options table
    @Column(name = "link")
    private String link;

    /**
     * Zero argument constructor
     */
    public OptionAnnotation(){
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value of the shopName attribute
     * @return shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Sets the value of the shopName attribute
     * @param shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Returns the value of the cardId attribute
     * @return cardId
     */
    public CardAnnotation getCardId() {
        return cardId;
    }

    public void setCardId(CardAnnotation cardId) {
        this.cardId = cardId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
