package com.bd.tpfinal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, updatable = false, unique = true, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column
    private Date dateOfBirth;

    @Column
    private boolean active;

    @Column
    private int score;

    protected User(){}

    protected User(String name, String email, String username, String password, Date dateOfBirth){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.score = 0;
        this.active = true;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Date getDateOfBirth() {return dateOfBirth;}

    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}
}
