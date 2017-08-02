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
public class PutUpdateVertexColor implements Command<Object> {
    private Vertex vertexToBeUpdated;
    private int color;
    
    public PutUpdateVertexColor(Vertex vertex, int color){
        this.vertexToBeUpdated = vertex;
        this.color = color;
    }
    
     public Vertex getVertexToBeUpdated() {
        return this.vertexToBeUpdated;
    }

    public void setVertexToBeUpdated(Vertex vertexToBeUpdated) {
        this.vertexToBeUpdated = vertexToBeUpdated;
    }
    
    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
