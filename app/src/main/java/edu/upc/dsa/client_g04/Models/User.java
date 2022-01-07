package edu.upc.dsa.client_g04.Models;

public class User {
    private String name;
    private String mail;
    private String password;
    private int active;
    private int highScore;
    private int id;

    public User(){}

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public User(String name, String mail, String password, int active, int highScore, int id) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.active = active;
        this.highScore = highScore;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = mail;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {this.id = id; }
}
