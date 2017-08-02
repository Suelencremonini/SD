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
public class PutUpdateEdgeFlag implements Command<Object> {
    private Edges edgeToBeUpdated;
    private int flag;
    
    public PutUpdateEdgeFlag(Edges edge, int flag){
        this.edgeToBeUpdated = edge;
        this.flag = flag;
    }
    
     public Edges getEdgeToBeUpdated() {
        return this.edgeToBeUpdated;
    }

    public void setEdgeToBeUpdated(Edges edgeToBeUpdated) {
        this.edgeToBeUpdated = edgeToBeUpdated;
    }
    
    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
