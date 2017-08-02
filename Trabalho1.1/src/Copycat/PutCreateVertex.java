/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Copycat;

import io.atomix.copycat.Command;

/**
 *
 * @author Suelen
 */
public class PutCreateVertex implements Command<Object> {
    private long name;
    private int color;
    private String description;
    private double weight;
    
    public PutCreateVertex(long name, int color, String description, double weight) {
        this.name = name;
        this.color = color;
        this.description = description;
        this.weight = weight;
    }

    public long getName() {
        return this.name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
