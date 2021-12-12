package com.zhane.Trello.Clone.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int position;
    private int status = 1;

    @OneToMany(fetch = FetchType.LAZY ,
        cascade = CascadeType.REMOVE ,
        mappedBy = "list")
    @OrderBy("position asc")
    private Set<Card> cards = new HashSet<Card>();

    @OneToMany(
            mappedBy = "cardId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @OrderBy("position asc")
    private java.util.List<Checklist> checklists;

//    @ManyToMany(mappedBy = "account")
//    private java.util.List<Account> accounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public java.util.List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(java.util.List<Checklist> checklists) {
        this.checklists = checklists;
    }
}
