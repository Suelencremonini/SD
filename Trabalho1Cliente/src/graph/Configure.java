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
    
    
   
    public static Node findNode(List<Node> nodelist,int name){
        /*
        for(int i = 0; i < nodelist.size(); i++){
            if(nodelist.get(i).getName() == name)
                return nodelist.get(i);
        }
        return -1;
        */
       
        
        for(Node i: nodelist){
            if(i.getName() == name)
                return i;
        }
        
        return null;
    }
    
    public static List<Integer> ConfigureVertex(List<Vertex> v, List<Edges> e, int begin, int end){
        Graph graph = new Graph();
        List<Node> n = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        System.out.println(begin);
        v.stream().forEach((i) -> {
            n.add(new Node((int)i.getName()));
        });
        int index;
        
        for(Node i: n){
            for(Edges j: e){
                if(i.getName() == (int)j.getV1id()){
                    i.addDestination(findNode(n,(int)j.getV2id()), j.Weight);
                }
            }
        }
        
        for(Node i: n){
            graph.addNode(i);
        }
        
        
        graph = Dijkstra.calculateShortestPathFromSource(graph,findNode(n,begin));
        
        for(Node i: graph.getNodes()){
            if(i.getName() == end){
                i.getShortestPath().stream().forEach((j) -> {
                    l.add(j.getName());
                });
            }
        }
        return l;
    }    
}
