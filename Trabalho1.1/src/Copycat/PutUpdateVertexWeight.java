/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Copycat;

import Thrift.Vertex;
import io.atomix.copycat.Command;

/**
 *
 * @author Suelen
 */
public class PutUpdateVertexWeight implements Command<Object> {
    private Vertex vertexToBeUpdated;
    private double weight;
    
    public PutUpdateVertexWeight(Vertex vertex, double weight){
        this.vertexToBeUpdated = vertex;
        this.weight = weight;
    }
    
     public Vertex getVertexToBeUpdated() {
        return this.vertexToBeUpdated;
    }

    public void setVertexToBeUpdated(Vertex vertexToBeUpdated) {
        this.vertexToBeUpdated = vertexToBeUpdated;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
