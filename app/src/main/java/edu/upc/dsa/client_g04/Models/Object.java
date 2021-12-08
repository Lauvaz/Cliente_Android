package edu.upc.dsa.client_g04.Models;

public class Object {
    private String name;
    private String description;

    public Object(){}

    public Object(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
