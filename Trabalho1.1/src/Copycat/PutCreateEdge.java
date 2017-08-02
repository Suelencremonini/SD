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
public class PutCreateEdge implements Command<Object>{
    private long nameVertice1;
    private long nameVertice2;
    private double weight;
    private int flag;
    private String description;
    
    public PutCreateEdge(long nameVertice1, long nameVertice2, double weight, int flag, String description) {
        this.nameVertice1 = nameVertice1;
        this.nameVertice2 = nameVertice2;
        this.weight = weight;
        this.flag = flag;
        this.description = description;
    }

    public long getNameVertice1() {
        return this.nameVertice1;
    }

    public void setNameVertice1(long nameVertice1) {
        this.nameVertice1 = nameVertice1;
    }

    public long getNameVertice2() {
        return this.nameVertice2;
    }

    public void setNameVertice2(long nameVertice2) {
        this.nameVertice2 = nameVertice2;
    }
    
    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
