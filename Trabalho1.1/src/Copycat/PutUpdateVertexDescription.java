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
public class PutUpdateVertexDescription implements Command<Object> {
    private Vertex vertexToBeUpdated;
    private String description;
    
    public PutUpdateVertexDescription(Vertex vertex, String description){
        this.vertexToBeUpdated = vertex;
        this.description = description;
    }
    
     public Vertex getVertexToBeUpdated() {
        return this.vertexToBeUpdated;
    }

    public void setVertexToBeUpdated(Vertex vertexToBeUpdated) {
        this.vertexToBeUpdated = vertexToBeUpdated;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
