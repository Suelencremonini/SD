/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Copycat;

import Thrift.Vertex;
import Thrift.Edges;
import io.atomix.copycat.server.Commit;
import io.atomix.copycat.server.StateMachine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suelen
 */
public class CopycatStateMachine extends StateMachine {  
    List<Vertex> listVertex = new ArrayList<>(); 
    List<Edges> listEdges = new ArrayList<>();
     
    public void putCreateVertex(Commit<PutCreateVertex> commit) {
        try {
            PutCreateVertex operation = commit.operation();
            Vertex newVertex = new Vertex();
            newVertex.setName(operation.getName());
            newVertex.setColor(operation.getColor());
            newVertex.setDescription(operation.getDescription());
            newVertex.setWeight(operation.getWeight());
            listVertex.add(newVertex);
            
            System.out.println("no putCreateVertex com lista: "+listVertex);
        
        } finally {
            commit.close();
        }
    }
    
    public void putCreateEdge(Commit<PutCreateEdge> commit) {
        try {
            PutCreateEdge operation = commit.operation();
            Edges newEdge = new Edges();
            newEdge.setV1id(operation.getNameVertice1());
            newEdge.setV2id(operation.getNameVertice2());
            newEdge.setFlag(operation.getFlag());
            newEdge.setDescription(operation.getDescription());
            newEdge.setWeight(operation.getWeight());
            listEdges.add(newEdge);
        
        } finally {
            commit.close();
        }
    }
    
    public void putDeleteVertex(Commit<PutDeleteVertex> commit) {
        try {
            listVertex.remove(commit.operation().getVertexToBeDeleted());
        } finally {
            commit.close();
        }
    }
    
    public void putDeleteEdge(Commit<PutDeleteEdge> commit) {
        try {
            listEdges.remove(commit.operation().getEdgeToBeDeleted());
        } finally {
            commit.close();
        }
    }
    
    public void putUpdateEdgeWeight(Commit<PutUpdateEdgeWeight> commit) {
        try {
            int index = listEdges.indexOf(commit.operation().getEdgeToBeUpdated());
            listEdges.get(index).setWeight(commit.operation().getWeight());
            
        } finally {
            commit.close();
        }
    }
    
    public void putUpdateEdgeDescription(Commit<PutUpdateEdgeDescription> commit) {
        try {
            int index = listEdges.indexOf(commit.operation().getEdgeToBeUpdated());
            listEdges.get(index).setDescription(commit.operation().getDescription());
            
        } finally {
            commit.close();
        }
    }
    
    public void putUpdateEdgeFlag(Commit<PutUpdateEdgeFlag> commit) {
        try {
            int index = listEdges.indexOf(commit.operation().getEdgeToBeUpdated());
            listEdges.get(index).setFlag(commit.operation().getFlag());
            
        } finally {
            commit.close();
        }
    }
    
     public void putUpdateVertexWeight(Commit<PutUpdateVertexWeight> commit) {
        try {
            int index = listVertex.indexOf(commit.operation().getVertexToBeUpdated());
            listVertex.get(index).setWeight(commit.operation().getWeight());
           
        } finally {
            commit.close();
        }
    }
     
    public void putUpdateVertexDescription(Commit<PutUpdateVertexDescription> commit) {
        try {
            int index = listVertex.indexOf(commit.operation().getVertexToBeUpdated());
            listVertex.get(index).setDescription(commit.operation().getDescription());
        } finally {
            commit.close();
        }
    }
    
    public void putUpdateVertexColor(Commit<PutUpdateVertexColor> commit) {
        try {
            int index = listVertex.indexOf(commit.operation().getVertexToBeUpdated());
            listVertex.get(index).setColor(commit.operation().getColor());

        } finally {
            commit.close();
        }
    }
    
    //###################################################################################################################
    //                                                       GETTERS
    //###################################################################################################################
    
    public List<Vertex> getVertex(Commit<GetVertex> commit) {
        try {
            return this.listVertex;
        } finally {
            commit.close();
        }
    }
    
    public List<Edges> getEdges(Commit<GetEdges> commit) {
        try {
            return this.listEdges;
        } finally {
            commit.close();
        }
    }

//    public String put(Commit<Put> commit) {
//        try {
//            return this.storage.put(commit.operation().getKey(), commit.operation().getValue());
//        } finally {
//            commit.release();
//        }
//    }
//
//    public String get(Commit<Get> commit) {
//        try {
//            return this.storage.get(commit.operation().getKey());
//        } finally {
//            commit.release();
//        }
//    }
}
