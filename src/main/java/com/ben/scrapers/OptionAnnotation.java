package com.ben.scrapers;

import javax.persistence.*;

/**
 * Represents an Option
 */
@Entity
@Table(name="options")
public class OptionAnnotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shop_name")
    private String shopName;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable=false)
    private CardAnnotation cardId;

    @Column(name = "price")
    private double price;

    @Column(name = "link")
    private String link;

    public OptionAnnotation(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

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
