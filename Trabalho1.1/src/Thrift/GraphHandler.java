/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * thrift --gen java chavevalor.thrift
 * ou
 * thrift-0.9.3.exe -gen java graph.thrift
 *
 * o central server eh localhost com porta 9090. Sempre! Ele que é responsavel por ver quem ser conectou e quem se desconectou
 *
 *
 */
package Thrift;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import java.util.concurrent.Semaphore;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import graph.Configure;

import Copycat.*;
import io.atomix.catalyst.transport.Address;
import io.atomix.copycat.client.ConnectionStrategies;
import io.atomix.copycat.client.CopycatClient;
import io.atomix.copycat.server.CopycatServer;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
/**
 *
 * @author jose
 */
public class GraphHandler implements Graph.Iface{
    
    List<Vertex> v = new ArrayList<>();
    List<Edges> e = new ArrayList<>();
    Semaphore semaphore = new Semaphore(1);
    Semaphore semaphoreServersTable = new Semaphore(1);
    Semaphore semaphoreCurrentServer = new Semaphore(1);
    Server currentServer = new Server();
    Map<Long, Server> serversTable = new HashMap<>();
    
    CopycatClient copycatClient1;
    CopycatClient copycatClient2;
    CopycatClient copycatClient3;
   
    CopycatServer copycatServer1;
    CopycatServer copycatServer2;
    CopycatServer copycatServer3;
    
    @Override
    public void serverConnected(Server current){
        try {
            this.semaphoreCurrentServer.acquire();
            this.currentServer.setIp(current.getIp());
            this.currentServer.setPortNumber(current.getPortNumber());

        } catch(Exception e){
            System.out.println("exception em serverConnected");
        }
        finally{
            this.semaphoreCurrentServer.release();
        }
    }
    
    /**
     * ao ser conectador, o server usa essa funcao para dizer ao servidor central que ele esta na rede. 
     * o retorno eh usado para setar a tabela de servers do servidor que chamou essa funcao
     * @param server
     * @param connected
     * @return 
     */
    @Override
    public Map<Long, Server> comunicateConnectionToCentralServer(Server server, boolean connected){ 
        try {
            semaphoreServersTable.acquire();
            this.serversTable.put(Long.valueOf(this.serversTable.size()), server);

            setCopycatClient(server);
            
            sendNewServersTable(server);
        } catch(Exception e){
            System.out.println("exception em comunicateConnectionToCentralServer: "+e);
        }
        finally{
            semaphoreServersTable.release();
            return this.serversTable;
        }
    }
    
    private void setCopycatClient(Server server) {
        try {
        this.copycatClient1 = CopycatClient.builder()
                .withConnectionStrategy(ConnectionStrategies.FIBONACCI_BACKOFF)
                .build();
        
        List<Address> cluster = new ArrayList<>();
        cluster.add(new Address(server.getIp(), server.getPortNumber()+1));
        cluster.add(new Address(server.getIp(), server.getPortNumber()+2));
        cluster.add(new Address(server.getIp(), server.getPortNumber()+3));

        this.copycatClient1.connect(cluster).join();
        
        }catch (Exception e) {
            System.out.println("exception: "+e);
        }
    }
    
    /**
     * atualiza a tabela de servers de cada server atraves de um loop nos itens da propria tabela
     * @param parentServer 
     */
    public void sendNewServersTable(Server parentServer) {
        try {
            for(Iterator<Map.Entry<Long, Server>> it = this.serversTable.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, Server> entry = it.next();
                if(!this.areTheSameServers(entry.getValue(), parentServer)){
                    TTransport transport;
                    transport = new TSocket(entry.getValue().getIp(), entry.getValue().getPortNumber());
                    transport.open();
                    TProtocol protocol = new  TBinaryProtocol(transport);
                    Graph.Client client = new Graph.Client(protocol);
                    client.setServersTable(this.serversTable);
                    transport.close();
                }
            }
        } catch (Exception x) {
            System.out.println("falhou na conexão");
        }
    }
    
    /**
     * connecta ao servidor correto e chama a funcao passada por parametro (Method methodToBeCalled) 
     * com os argumentos tambem passados por parametro (Object[] parameters)
     * -> ver java reflect para referencia
     * @param serverToBeConnectedTo
     * @param parameters
     * @param methodToBeCalled
     * @return 
     */
    private Object connectToServer (Server serverToBeConnectedTo, Object[] parameters, Method methodToBeCalled){
        Object returnValue = new Object();
        try {
          TTransport transport;
          transport = new TSocket(serverToBeConnectedTo.getIp(), serverToBeConnectedTo.getPortNumber());
          transport.open();
          TProtocol protocol = new  TBinaryProtocol(transport);
          Graph.Client client = new Graph.Client(protocol);
  
          client.serverConnected(serverToBeConnectedTo);
          returnValue = methodToBeCalled.invoke(client, parameters);
          
          transport.close();
        } catch (Exception x) {
          System.out.println("falhou na conexão");
        } finally {
          return returnValue;
        }
    }
    
    /**
     * seta a tabela de servers global do servidor atual com a tabela que o servidor central enviou como parametro
     * @param serversTableLocal 
     */
    public void setServersTable(Map<Long, Server> serversTableLocal){
        this.serversTable = serversTableLocal;
    }
    
    /**
     * funcao para ver em qual servidor vai qual item (vertice ou aresta)
     * @param numForHash
     * @return 
     */
    private Long hashFunction(long numForHash) {
       return (numForHash%this.serversTable.size());
    }
    
    /**
     * pega o server certo de acordo com o vertice passado como parametro e com a funcao hash
     * @param nameVertex
     * @return 
     */
    private Server getCorrectServerForVertex(long nameVertex){        
        try {
            semaphoreServersTable.acquire();
            Server correctServer = this.serversTable.get(this.hashFunction(nameVertex));
            setCopycatClient(currentServer);
            
            return correctServer;
        } catch (Exception x) {
            System.out.println("exception em newServersTable: "+x);
            return null;
        } finally{
            semaphoreServersTable.release();
        }
    }
    
    /**
     * pega o server certo de acordo com a aresta passada como parametro e com a funcao hash em cima de um ou de ambos os seus vertices
     * @param edge
     * @return 
     */
    private List<Server> getCorrectServersForEdge(Edges edge) {
        List<Server> correctServers = new ArrayList<>();
        
        correctServers.add(this.getCorrectServerForVertex(edge.getV1id()));
        if(edge.getFlag() == 0)
            correctServers.add(this.getCorrectServerForVertex(edge.getV2id()));
        return correctServers;
    }
    
    /**
     * compara dois servidores ~preguica de implementar comparable huehue
     * @param server1
     * @param server2
     * @return 
     */
    private boolean areTheSameServers(Server server1, Server server2){
        if(server1.getIp().equals(server2.getIp()) && server1.getPortNumber() == server2.getPortNumber())
            return true;
        return false;
    }
    
    public boolean existenceVertex(long name){
         try{
            Server correctServer = this.getCorrectServerForVertex(name);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Long[] listLong = new Long[1];
                listLong[0] = name;

                Class[] longType = new Class[1];
                longType[0] = long.class;

                return (Boolean)connectToServer (correctServer, listLong, Graph.Client.class.getMethod("existenceVertex", longType));
            }
            else {
                for(Vertex i: getV()){
                    if(i.getName() == name){
                        return true;
                    }
                }
                return false;
            }
        } catch(Exception e) {           
            System.out.println("exception em existenceVertex: "+e);
            //nao da pra dar throw na NoSuchMethodException, entao facamos isso mesmo
            return false;
        }
    }
    
    public boolean existenceEdges(Edges edge){
        try{
            Server correctServer = this.getCorrectServerForVertex(edge.getV1id());
     
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Edges[] listEdges = new Edges[1];
                listEdges[0] = edge;

                Class[] edgesType = new Class[1];
                edgesType[0] = Edges.class;
                return (Boolean)connectToServer (correctServer, listEdges, Graph.Client.class.getMethod("existenceEdges", edgesType));
            }
            else {
                 for(Edges i: getE()){
                    if((i.getV1id() == edge.getV1id() && i.getV2id() == edge.getV2id()) || (i.getV1id() == edge.getV2id() && i.getV2id() == edge.getV1id())){
                        return true;
                    }
                }
                return false;
            }
        } catch(Exception e) {
            System.out.println("exception em existenceEdges: "+e);
            return false;
        }
    }
    
    public void CreateEdges(Edges edge) throws org.apache.thrift.TException{
        if(!existenceEdges(edge) && existenceVertex(edge.getV1id()) && existenceVertex(edge.getV2id())){
            try{
                List<Server> correctServerList = this.getCorrectServersForEdge(edge);
                for(int i=0; i<correctServerList.size(); i++){
                    if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                        Edges[] listEdges = new Edges[1];
                        listEdges[0] = edge;

                        Class[] edgesType = new Class[1];
                        edgesType[0] = Edges.class;


                        connectToServer (correctServerList.get(i), listEdges, Graph.Client.class.getMethod("actuallyCreateEdge", edgesType));
                    }
                    else {
                        this.actuallyCreateEdge(edge);
                    }
                }
            }
            catch(Exception e){
                System.out.println("exception em CreateEdges: "+e);
            }
        }
    }

    public void actuallyCreateEdge(Edges edge) {
        try{
            System.out.println("server "+currentServer+" para edge: "+edge);  
            copycatClient1.submit(new PutCreateEdge(edge.V1id, edge.V2id, edge.Weight, edge.flag, edge.description)).join();
        }catch(Exception e){
            System.out.println("exception em actuallyCreateEdge: "+e);
        }
    }

    public void CreateVertex(Vertex vertex) throws org.apache.thrift.TException{
        if(!existenceVertex(vertex.getName())){
            try{
                Server correctServer = this.getCorrectServerForVertex(vertex.getName());
                if (!this.areTheSameServers(correctServer, currentServer)) {
                       Vertex[] vertexes = new Vertex[1];
                       vertexes[0] = vertex;

                       Class[] vertexType = new Class[1];
                       vertexType[0] = Vertex.class;

                       connectToServer(correctServer, vertexes, Graph.Client.class.getMethod("actuallyCreateVertex", vertexType));
                }
                else {
                    this.actuallyCreateVertex(vertex);
                }
            }
            catch(Exception e){
                System.out.println("CreateVertex: cai na exception: "+e);
            }
        }
    }
    
    public void actuallyCreateVertex(Vertex vertex) {
        try {
            System.out.println("server "+currentServer+" para vertice: "+vertex);  
            copycatClient1.submit(new PutCreateVertex(vertex.name, vertex.color, vertex.description, vertex.weight)).join();
        } catch(Exception e){
            System.out.println("actuallyCreateVertex: cai na exception: "+e);
        }
    }

    public Vertex ReadVertex(int vertex) throws org.apache.thrift.TException{
        for(Vertex i: getV()){
           if((int)i.getName() == vertex){
               return i;
           }
        }
        return null;
    }
    
     public Edges ReadEdges(Edges edge) throws org.apache.thrift.TException{
        for(Edges item: getE()){
            if((edge.getV1id() == item.getV1id() && edge.getV2id() == item.getV2id()) || (edge.getV2id() == item.getV1id() && edge.getV1id() == item.getV2id())){
                return item;
            }
        }
        return null;
    }

    public void DeleteEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        Edges edge = new Edges();
        edge.setV1id(vertex1);
        edge.setV2id(vertex2);
         try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            for(int i=0; i<correctServerList.size(); i++){
                if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                    Edges[] listEdges = new Edges[1];
                    listEdges[0] = edge;

                    Class[] edgeType = new Class[1];
                    edgeType[0] = Edges.class;

                    connectToServer (correctServerList.get(i), listEdges, Graph.Client.class.getMethod("actuallyDeleteEdge", edgeType));
                } else
                    this.actuallyDeleteEdge(edge);
            }
         } catch(Exception e){
            System.out.println("exception em DeleteEdges: "+e);
         }
    }
    
    public void actuallyDeleteEdge(Edges edge) {
        try{
            Edges result = ReadEdges(edge);
            if(result!= null){
                copycatClient1.submit(new PutDeleteEdge(result)).join();
            }
        } catch(Exception e){
            System.out.println("exception em actuallyDeleteEdge: "+e);
        }
    }

    public void DeleteVertex(int vertex) throws org.apache.thrift.TException{
       try{
            Server correctServer = this.getCorrectServerForVertex(vertex);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Integer[] listInteger = new Integer[1];
                listInteger[0] = vertex;

                Class[] integerType = new Class[1];
                integerType[0] = int.class;

                connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("actuallyDeleteVertex", integerType));
            }
            else {
                this.actuallyDeleteVertex(vertex);
            }
         } catch(Exception e){
            System.out.println("exception em DeleteVertex: "+e);
         }
    }
    
    public void actuallyDeleteVertex(int vertex){
        try {
            Vertex result  = ReadVertex(vertex);
            if(result != null){
                copycatClient1.submit(new PutDeleteVertex(result)).join();
                this.deleteEdgesVertex(vertex);
            }
        }catch(Exception e){
            System.out.println("exception em actuallyDeleteVertex: "+e);
        }
    }
    
    public void deleteEdgesVertex(int vertex){
        try{
            List<Edges> edges = new ArrayList<>();
            edges = this.GetEdgesVertex(vertex);
            for(int i=0; i<edges.size(); i++){
                copycatClient1.submit(new PutDeleteEdge(edges.get(i))).join();
            }
        }catch(Exception e){
            System.out.println("exception em deleteEdgesVertex: "+e);
        }
    }
    
    public Set<Edges> GetEdges() throws org.apache.thrift.TException{
        Set<Edges> allEdges = new HashSet<>();
         try{
            this.semaphoreServersTable.acquire();
            for(Iterator<Map.Entry<Long, Server>> it = serversTable.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, Server> entry = it.next();
                allEdges.addAll((List<Edges>)connectToServer (entry.getValue(), null, Graph.Client.class.getMethod("getE", null)));
            }
            return allEdges;
        }catch(Exception e){
            System.out.println("exception em GetEdges");
            return null;
        } finally{
            semaphoreServersTable.release();
        }
    }

    public List<Vertex> GetVertex() throws org.apache.thrift.TException {
        List<Vertex> allVertexes = new ArrayList<>();
        try{
            this.semaphoreServersTable.acquire();
            for(Iterator<Map.Entry<Long, Server>> it = serversTable.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, Server> entry = it.next();
                allVertexes.addAll((List<Vertex>)connectToServer(entry.getValue(), null, Graph.Client.class.getMethod("getV", null)));
            }
            return allVertexes;
        }catch(Exception e){
            System.out.println("exception em GetVertex: "+e);
            return null;
        } finally{
            semaphoreServersTable.release();
        }
    }
    
    public List<Vertex> getV(){
        try{
            return (List<Vertex>)copycatClient1.submit(new GetVertex()).join();
        }catch(Exception e){
            System.out.println("exception em getV: "+e);
            return null;
        }
    }
    
    public List<Edges> getE(){
         try{
            return (List<Edges>)copycatClient1.submit(new GetEdges()).join();
        }catch(Exception e){
            System.out.println("exception em getE");
            return null;
        }
    }

    public List<Vertex> GetVertexEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<Vertex>();
        try{
            List<Server> servers = new ArrayList<>();
            servers.add(this.getCorrectServerForVertex(vertex1));
            servers.add(this.getCorrectServerForVertex(vertex2));
            
            List<Integer> vertexes = new ArrayList<>();
            vertexes.add(vertex1);
            vertexes.add(vertex2);
            
            for(int i=0; i<servers.size(); i++){
                if (!this.areTheSameServers(servers.get(i), currentServer)) {
                    Integer[] listInteger = new Integer[1];
                    listInteger[0] = vertexes.get(i);

                    Class[] integerType = new Class[1];
                    integerType[0] = int.class;

                    lv.add((Vertex)connectToServer (servers.get(i), listInteger, Graph.Client.class.getMethod("ReadVertex", integerType)));
                }
                else {
                    lv.add(this.ReadVertex(vertexes.get(i)));
                }
            }
            return lv;
        }catch(Exception e){
            System.out.println("exception em GetVertexEdges: "+e);
            return null;
        }
    }

    public List<Edges> GetEdgesVertex(int name) throws org.apache.thrift.TException{
        List<Edges> le = new ArrayList<>();
        for(Edges i: this.GetEdges()){
            if(i.getV1id() == name || i.getV2id() == name){
                le.add(i);
            }
        }
        return le;
    }

    public List<Vertex> GetAdjacentVertex(int name) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<>();
        try{
            for(Edges i: this.GetEdges()){
                if(i.getV1id() == name){
                    Server correctServer = this.getCorrectServerForVertex(i.getV2id());
                    if (!this.areTheSameServers(correctServer, currentServer)) {
                        Integer[] listInteger = new Integer[1];
                        listInteger[0] = (int)i.getV2id();

                        Class[] integerType = new Class[1];
                        integerType[0] = int.class;

                        lv.add((Vertex)connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("ReadVertex", integerType)));
                    }
                    else {
                        lv.add(ReadVertex((int) i.getV2id()));
                    }
                }
                else if(i.getV2id() == name){
                    Server correctServer = this.getCorrectServerForVertex(i.getV1id());
                    if (!this.areTheSameServers(correctServer, currentServer)) {
                        Integer[] listInteger = new Integer[1];
                        listInteger[0] = (int)i.getV1id();

                        Class[] integerType = new Class[1];
                        integerType[0] = int.class;

                        lv.add((Vertex)connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("ReadVertex", integerType)));
                    }
                    else {
                        lv.add(ReadVertex((int) i.getV1id()));
                    }
                }
            }
            return lv;
        }catch(Exception e){
            System.out.println("exception em GetAdjacentVertex: "+e);
            return null;
        }
    }

    public void UpdateEdgesWeight(double weight, int vertex1, int vertex2) throws TException {
        Edges edge = new Edges();
        edge.setV1id(vertex1);
        edge.setV2id(vertex2);
        try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            for(int i=0; i<correctServerList.size(); i++){
                if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                    Object[] listObject = new Object[3];
                    listObject[0] = weight;
                    listObject[1] = vertex1;
                    listObject[2] = vertex2;

                    Class[] objectType = new Class[3];
                    objectType[0] = double.class;
                    objectType[1] = int.class;
                    objectType[2] = int.class;

                    connectToServer (correctServerList.get(i), listObject, Graph.Client.class.getMethod("actuallyUpdateEdgesWeight", objectType));
                }
                else {
                    this.actuallyUpdateEdgesWeight(weight, vertex1, vertex2);
                }
            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesWeight: "+e);
        }
    }
            
    public void actuallyUpdateEdgesWeight(double weight, int vertex1, int vertex2){
        try{
            for(Edges i: getE()){
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    copycatClient1.submit(new PutUpdateEdgeWeight(i, weight)).join();
                }
            }
        }catch(Exception e){
        }
    }

    public void UpdateEdgesFlag(int flag, int vertex1, int vertex2) throws TException {
        Edges edge = new Edges();
        edge.setV1id(vertex1);
        edge.setV2id(vertex2);
        try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            for(int i=0; i<correctServerList.size(); i++){
                if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                    Object[] listInteger = new Object[3];
                    listInteger[0] = flag;
                    listInteger[1] = vertex1;
                    listInteger[2] = vertex2;

                    Class[] integerType = new Class[3];
                    integerType[0] = int.class;
                    integerType[1] = int.class;
                    integerType[2] = int.class;

                    connectToServer (correctServerList.get(i), listInteger, Graph.Client.class.getMethod("actuallyUpdateEdgesFlag", integerType));
                }
                else {
                    this.actuallyUpdateEdgesFlag(flag, vertex1, vertex2);
                }
            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesFlag: "+e);
        }
    }
    
    public void actuallyUpdateEdgesFlag(int flag, int vertex1, int vertex2){
        try{
            for(Edges i: getE()){
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    copycatClient1.submit(new PutUpdateEdgeFlag(i, flag)).join();
                }
            }
        }catch(Exception e){
        }
    }

    public void UpdateEdgesDescription(String description, int vertex1, int vertex2) throws TException {
        Edges edge = new Edges();
        edge.setV1id(vertex1);
        edge.setV2id(vertex2);
        try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            for(int i=0; i<correctServerList.size(); i++){
                if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                    Object[] listObjects = new Object[3];
                    listObjects[0] = description;
                    listObjects[1] = vertex1;
                    listObjects[2] = vertex2;

                    Class[] objectType = new Class[3];
                    objectType[0] = String.class;
                    objectType[1] = int.class;
                    objectType[2] = int.class;

                    connectToServer (correctServerList.get(i), listObjects, Graph.Client.class.getMethod("actuallyUpdateEdgesDescription", objectType));
                }
                else {
                    this.actuallyUpdateEdgesDescription(description, vertex1, vertex2);
                }
            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesDescription: "+e);
        }
    }
    
    public void actuallyUpdateEdgesDescription(String description, int vertex1, int vertex2){
        try{
            for(Edges i: getE()){
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    copycatClient1.submit(new PutUpdateEdgeDescription(i, description)).join();
                }
            }
        }catch(Exception e){
        }
    }

    public void UpdateVertexColor(int color, int name) throws TException {
        try{
            Server correctServer = this.getCorrectServerForVertex(name);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Object[] listInteger = new Object[2];
                listInteger[0] = color;
                listInteger[1] = name;

                Class[] integerType = new Class[2];
                integerType[0] = int.class;
                integerType[1] = int.class;

                connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("actuallyUpdateVertexColor", integerType));
            }
            else {
                this.actuallyUpdateVertexColor(color, name);
            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexColor: "+e);
        }
    }
    
    public void actuallyUpdateVertexColor(int color, int name){
        try{
             for(Vertex i: getV()){
                if(i.getName() == name){
                    copycatClient1.submit(new PutUpdateVertexColor(i, color)).join();
                }
            }
        }catch(Exception e){
        }
    }

    public void UpdateVertexDescription(String description, int name) throws TException {
        try{
            Server correctServer = this.getCorrectServerForVertex(name);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Object[] listObject = new Object[2];
                listObject[0] = description;
                listObject[1] = name;

                Class[] objectType = new Class[2];
                objectType[0] = String.class;
                objectType[1] = int.class;

                connectToServer (correctServer, listObject, Graph.Client.class.getMethod("actuallyUpdateVertexDescription", objectType));
            }
            else {
                this.actuallyUpdateVertexDescription(description, name);
            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexDescription: "+e);
        }
    }
    
    public void actuallyUpdateVertexDescription(String description, int name){
        try{
            for(Vertex i: getV()){
                if(i.getName() == name){
                    copycatClient1.submit(new PutUpdateVertexDescription(i, description)).join();
                }
            }
        } catch(Exception e){
        }
    }

    public void UpdateVertexWeight(double weight, int name) throws TException {
        try{
            Server correctServer = this.getCorrectServerForVertex(name);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Object[] listObject = new Object[2];
                listObject[0] = weight;
                listObject[1] = name;

                Class[] objectType = new Class[2];
                objectType[0] = double.class;
                objectType[1] = int.class;

                connectToServer (correctServer, listObject, Graph.Client.class.getMethod("actuallyUpdateVertexWeight", objectType));
            }
            else {
                 this.actuallyUpdateVertexWeight(weight, name);
            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexWeight: "+e);
        }
    }
    
    public void actuallyUpdateVertexWeight(double weight, int name){
        try{
            for(Vertex i: getV()){
                if(i.getName() == name){
                    copycatClient1.submit(new PutUpdateVertexWeight(i, weight)).join();
                }
            }
        } catch(Exception e){
        }
    }
    
    @Override
    public List<Integer> Dijkstra(int source, int goal) throws TException {
        List<Integer> result = null;
        try{
            if(existenceVertex(source) && existenceVertex(goal)){
                List<Edges> edges = new ArrayList<>(this.GetEdges());
                result = Configure.ConfigureVertex(this.GetVertex(), edges, source, goal);
            }
        }
        catch(Exception excp){}
        
        return result;
    }
}
