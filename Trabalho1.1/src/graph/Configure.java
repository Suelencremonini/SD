/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import Thrift.Edges;
import Thrift.Vertex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class Configure {
    
    
   
    public int findNode(List<Node> nodelist,int name){

        for(int i = 0; i < nodelist.size(); i++){
            if(nodelist.get(i).getName() == name)
                return i;
        }
        return -1;
    }
    
    public List<Integer> ConfigureVertex(List<Vertex> v, List<Edges> e, int begin, int end){
        Graph graph = new Graph();
        List<Node> n = new ArrayList<>();
        List<Integer> l = null;
        
        v.stream().forEach((i) -> {
            n.add(new Node((int)i.getName()));
        });
        int index;
        for(Edges i: e){
            n.get(findNode(n,(int)i.getV2id())).addDestination(n.get(findNode(n,(int)i.getV2id())), i.getWeight());
        }
        for(Node i: n){
            graph.addNode(i);
        }
        
        
        graph = Dijkstra.calculateShortestPathFromSource(graph,n.get(findNode(n,begin)));
        
        for(Node i: graph.getNodes()){
            if(i.getName() == end){
                for(Node j: i.getShortestPath()){
                   l.add(j.getName());
                }
            }
        }
        return l;
    }    
}
