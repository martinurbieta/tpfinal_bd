package com.bd.tpfinal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Version
    private int version;

    @Field
    private String name;

    @Field
    private String username;

    @Field
    private String password;

    @Field
    private String email;

    @Field
    private Date dateOfBirth;

    @Field
    private boolean active;

    @Field
    private int score;

    protected User(){}

    protected User(String name, String email, String username, String password, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.score = 0;
        this.active = true;
    }
    public ObjectId getID() {return id;}
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

    public int addScore(int points) {return this.score = this.score + points;}

    public void setScore(int score) {this.score = score;}

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
