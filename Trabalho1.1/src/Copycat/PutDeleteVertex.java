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
public class PutDeleteVertex implements Command<Object> {
    private Vertex vertexToBeDeleted;
    
    public PutDeleteVertex(Vertex v){
        this.vertexToBeDeleted = v;
    }
    
     public Vertex getVertexToBeDeleted() {
        return this.vertexToBeDeleted;
    }

    public void setVertexToBeDeleted(Vertex vertexToBeDeleted) {
        this.vertexToBeDeleted = vertexToBeDeleted;
    }
}
