/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
public class Node {
    
         
    private int name;
     
    private List<Node> shortestPath = new LinkedList<>();
     
    private Double distance = Double.MAX_VALUE;
     
    Map<Node, Double> adjacentNodes = new HashMap<>();
 
    public void addDestination(Node destination, double distance) {
        adjacentNodes.put(destination, distance);
    }
  
    public Node(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
    
    
    
}
