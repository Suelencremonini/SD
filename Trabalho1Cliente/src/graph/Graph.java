/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author jose
 */
public class Graph {
 
    private Set<Node> nodes = new HashSet<>();
     
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
          
    // getters and setters 

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
    
    
    
}
