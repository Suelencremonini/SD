/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author jose
 */
public class Dijkstra {


private static void calculateMinimumDistance(Node evaluationNode,
    Double edgeWeigh, Node sourceNode) {
    Double sourceDistance = sourceNode.getDistance();
    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
        evaluationNode.setDistance(sourceDistance + edgeWeigh);
        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
        shortestPath.add(sourceNode);
        evaluationNode.setShortestPath(shortestPath);
    }
}
    
private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
    Node lowestDistanceNode = null;
    Double lowestDistance = Double.MAX_VALUE;
    for (Node node: unsettledNodes) {
        Double nodeDistance = node.getDistance();
        if (nodeDistance < lowestDistance) {
            lowestDistance = nodeDistance;
            lowestDistanceNode = node;
        }
    }
    return lowestDistanceNode;
}
    
public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
    source.setDistance(0.0);
 
    Set<Node> settledNodes = new HashSet<>();
    Set<Node> unsettledNodes = new HashSet<>();
 
    unsettledNodes.add(source);
 
    while (!unsettledNodes.isEmpty()) {
        Node currentNode = getLowestDistanceNode(unsettledNodes);
        unsettledNodes.remove(currentNode);
        currentNode.getAdjacentNodes().entrySet().stream().forEach((adjacencyPair) -> {
            Node adjacentNode = adjacencyPair.getKey();
            Double edgeWeight = adjacencyPair.getValue();
            if (!settledNodes.contains(adjacentNode)) {
                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                unsettledNodes.add(adjacentNode);
            }
        });
        settledNodes.add(currentNode);
    }
    return graph;
}
}
