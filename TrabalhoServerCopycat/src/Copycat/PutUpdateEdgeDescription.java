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
public class PutUpdateEdgeDescription implements Command<Object> {
    private Edges edgeToBeUpdated;
    private String description;
    
    public PutUpdateEdgeDescription(Edges edge, String description){
        this.edgeToBeUpdated = edge;
        this.description = description;
    }
    
     public Edges getEdgeToBeUpdated() {
        return this.edgeToBeUpdated;
    }

    public void setEdgeToBeUpdated(Edges edgeToBeUpdated) {
        this.edgeToBeUpdated = edgeToBeUpdated;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
