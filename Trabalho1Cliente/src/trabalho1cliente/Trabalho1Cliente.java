/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1cliente;

/**
 *
 * @author jose
 */

import Thrift.Server;
import Thrift.Edges;
import Thrift.Graph;
import Thrift.Vertex;
import java.util.List;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.util.Scanner;
import java.util.Set;
public class Trabalho1Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TTransport transport;
            transport = new TSocket(args[0], Integer.parseInt(args[1]));
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            Graph.Client client = new Graph.Client(protocol);

            perform(client, args);

            transport.close();
        } catch (TException x) {
            System.out.println("falhou na conexão");
        } 
    }
    public static void printVertex(Vertex v){
        System.out.println("Name vertex:"+v.getName());
        System.out.println("Peso do vertice:"+v.getWeight());
        System.out.println("Cor:"+v.getColor());
        System.out.println("Descrição:\n"+v.getDescription());
    }
    public static void printEdges(Edges e){
        System.out.println("Vertex1:"+e.getV1id());
        System.out.println("Vertex2:"+e.getV2id());
        System.out.println("Peso:"+e.getWeight());
        System.out.println("Descrição:"+e.getDescription());
        System.out.println("Orientação:\n"+e.getFlag());
    }
    private static void perform(Graph.Client client, String[] args) throws TException{

        Server currentServer = new Server();
        currentServer.setIp(args[0]);
        currentServer.setPortNumber(Integer.parseInt(args[1]));
        client.serverConnected(currentServer);
        Edges e = new Edges();
        Vertex v = new Vertex();
        List<Vertex> lv;
        Set<Edges> setEdges;
        List<Edges> le;
        Scanner sc = new Scanner(System.in);
        int v1, v2;
        boolean flag = true;
        while(flag){
            int choose;
            
            
            System.out.println("1-Inserir Vértice");
            System.out.println("2-Inserir Aresta");
            System.out.println("3-Deletar Vértice");
            System.out.println("4-Deletar Aresta");
            System.out.println("5-Mostrar todos os vértices");
            System.out.println("6-Mostrar todas as arestas");
            System.out.println("7-Listar vértices de uma aresta");
            System.out.println("8-Listar aresta de um vértice");
            System.out.println("9-Listar vértices vizinhos de um vértice");
            System.out.println("10-Updates");
            System.out.println("11-Menor caminho(Dijkstra)");
            System.out.println("12-Sair");
            choose = sc.nextInt();
            switch(choose){
                case 1:
                    System.out.println("Insira o nome");
                    v.setName(sc.nextInt());
                    System.out.println("Insira a cor");
                    v.setColor(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Insira a descrição");
                    v.setDescription(sc.nextLine());
                    System.out.println("Insira o peso");
                    sc = new Scanner(System.in);
                    v.setWeight(sc.nextDouble());
                    sc.nextLine();
                    client.CreateVertex(v);
                    break;
                case 2:
                    System.out.println("Insira o vértice 1");
                    e.setV1id(sc.nextInt());
                    System.out.println("Insira o vértice 2");
                    e.setV2id(sc.nextInt());
                    System.out.println("Insira o peso");
                    e.setWeight(sc.nextDouble());
                    System.out.println("Insira a direção do grafo");
                    e.setFlag(sc.nextInt());
                    System.out.println("Insira a descrição");
                    sc = new Scanner(System.in);
                    e.setDescription(sc.nextLine());
                    client.CreateEdges(e);
                    break;
                case 3:
                    System.out.println("Insira o nome do vértice que deseja deletar:");
                    client.DeleteVertex(sc.nextInt());
                    break;
                case 4:
                    System.out.println("Insira as vértices da aresta");
                    v1 = sc.nextInt();
                    v2 = sc.nextInt();
                    client.DeleteEdges(v1, v2);
                    break;
                case 5:
                    lv = client.GetVertex();
                    for(Vertex i: lv){
                        printVertex(i);
                    }
                    break;
                case 6:
                    setEdges = client.GetEdges();
                    for(Edges i: setEdges){
                        printEdges(i);
                    }
                    break;
                case 7:
                    System.out.println("Insira a aresta:");
                    v1 = sc.nextInt();
                    v2 = sc.nextInt();
                    lv = client.GetVertexEdges(v1, v2);
                    for(Vertex i: lv){
                        printVertex(i);
                    }
                    break;
                case 8:
                    System.out.println("Insira o vértice:");
                    le = client.GetEdgesVertex(sc.nextInt());
                    for(Edges i: le){
                        printEdges(i);
                    }
                    break;
                case 9:
                    System.out.println("Insira o vértice:");
                    lv = client.GetAdjacentVertex(sc.nextInt());
                    for(Vertex i: lv){
                        printVertex(i);
                    }
                    break;
                case 10:
                    int op, pos;
                    String desc;
                    float peso;
                    System.out.println("1-Update Vértice Color");
                    System.out.println("2-Update Vértice Descrição");
                    System.out.println("3-Update Vértice Peso");
                    System.out.println("4-Update Aresta Orientação");
                    System.out.println("5-Update Aresta Descrição");
                    System.out.println("6-Update Aresta Peso");
                    op = sc.nextInt();
                    if(op == 1){
                       int nova_cor;
                       System.out.println("Digit a nova cor");
                       nova_cor = sc.nextInt();
                       System.out.println("Digite o vértice que você deseja alterar");
                       pos = sc.nextInt();
                       client.UpdateVertexColor(nova_cor, pos);
                    }
                    else if(op == 2){
                        System.out.println("Digite a descrição");
                        sc = new Scanner(System.in);
                        desc = sc.nextLine();
                        System.out.println("Digite o vértice que você deseja alterar");
                        pos = sc.nextInt();
                        client.UpdateVertexDescription(desc, pos);
                    }
                    else if(op == 3){
                        System.out.println("Digite o novo peso");
                        peso = sc.nextFloat();
                        System.out.println("Digite o vértice que você deseja alterar");
                        pos = sc.nextInt();
                        client.UpdateVertexWeight(peso, pos);
                    }
                    else if(op == 4){
                        int ori;
                        System.out.println("Digite a nova orientção da aresta");
                        ori = sc.nextInt();
                        System.out.println("Digite os vertices da aresta");
                        v1 = sc.nextInt();
                        v2 = sc.nextInt();
                        client.UpdateEdgesFlag(ori, v1, v2);
                        
                    }
                    else if(op == 5){
                        System.out.println("Digite a nova descrição");
                        sc = new Scanner(System.in);
                        desc = sc.nextLine();
                        System.out.println("Digiete os vertices da aresta");
                        v1 = sc.nextInt();
                        v2 = sc.nextInt();
                        client.UpdateEdgesDescription(desc, v1, v2);
                    }
                    else if(op == 6){
                        System.out.println("Digite o novo peso");
                        peso = sc.nextFloat();
                        System.out.println("Digite os vertices da aresta");
                        v1 = sc.nextInt();
                        v2 = sc.nextInt();
                        client.UpdateEdgesWeight(peso, v1, v2);
                    }
                    break;
                case 11:
                    List<Integer> li;
                    System.out.println("Insira o vértice inicial");
                    v1 = sc.nextInt();
                    System.out.println("Insira o vértice final");
                    v2 = sc.nextInt();
                    li = client.Dijkstra(v1,v2);
                    System.out.println("Menor caminho:");
                    for(Integer i: li){
                        System.out.printf("%d ->", i);
                    }
                    System.out.printf("%d", v2);
                    System.out.printf("\n");
                    break;
                case 12:
                    flag = false;
                    
            }
        }
    }
}
