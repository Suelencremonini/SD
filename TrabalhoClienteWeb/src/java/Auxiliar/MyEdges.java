/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;
import Thrift.*;
/**
 *
 * @author jose
 */
public class MyEdges {
    private int V1id;
    private int V2id;
    private double weight;

    public int getV1id() {
        return V1id;
    }

    public void setV1id(int V1id) {
        this.V1id = V1id;
    }

    public int getV2id() {
        return V2id;
    }

    public void setV2id(int V2id) {
        this.V2id = V2id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void changeEdge(Edges e){
        e.setV1id(V1id);
        e.setV2id(V2id);
        e.setWeight(1.0);
    }
    
    
}
