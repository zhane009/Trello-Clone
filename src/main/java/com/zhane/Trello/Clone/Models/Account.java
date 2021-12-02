package com.zhane.Trello.Clone.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String email;
    private Date dob;
    private short verified = 0;

//    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "list_id"),
//            inverseJoinColumns = @JoinColumn(name = "account_id")
//    )
//    private List<com.zhane.Trello.Clone.Models.List> lists;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public short getVerified() {
        return verified;
    }

    public void setVerified(short verified) {
        this.verified = verified;
    }
}
