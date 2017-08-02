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
public class PutDeleteEdge implements Command<Object> {
    private Edges edgeToBeDeleted;
    
    public PutDeleteEdge(Edges e){
        this.edgeToBeDeleted = e;
    }
    
     public Edges getEdgeToBeDeleted() {
        return this.edgeToBeDeleted;
    }

    public void setEdgeToBeDeleted(Edges edgeToBeDeleted) {
        this.edgeToBeDeleted = edgeToBeDeleted;
    } 
}
