/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Copycat;

import Thrift.Edges;
import io.atomix.copycat.Command;

/**
 *
 * @author Suelen
 */
public class PutUpdateEdgeWeight implements Command<Object> {
    private Edges edgeToBeUpdated;
    private double weight;
    
    public PutUpdateEdgeWeight(Edges edge, double weight){
        this.edgeToBeUpdated = edge;
        this.weight = weight;
    }
    
     public Edges getEdgeToBeUpdated() {
        return this.edgeToBeUpdated;
    }

    public void setEdgeToBeUpdated(Edges edgeToBeUpdated) {
        this.edgeToBeUpdated = edgeToBeUpdated;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
