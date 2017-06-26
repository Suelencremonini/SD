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
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("ver se eh culpa do semaforo. comunicateConnectionToCentralServer");
            semaphoreServersTable.acquire();
            System.out.println("ver se eh culpa do semaforo2. comunicateConnectionToCentralServer");
            this.serversTable.put(Long.valueOf(this.serversTable.size()), server);
            System.out.println("comunicateConnectionToCentralServer tableConnected: " + this.serversTable);
            
            sendNewServersTable(server);
            System.out.println("comunicateConnectionToCentralServer sai do sendNewServersTable");
        } catch(Exception e){
            System.out.println("exception em comunicateConnectionToCentralServer");
        }
        finally{
            semaphoreServersTable.release();
            System.out.println("released semaphoreServersTable da comunicateConnectionToCentralServer");
            return this.serversTable;
        }
    }
    
    /**
     * atualiza a tabela de servers de cada server atraves de um loop nos itens da propria tabela
     * @param parentServer 
     */
    public void sendNewServersTable(Server parentServer) {
        try {
            System.out.println("sendnewServers server: " + this.currentServer.getPortNumber());
            for(Iterator<Map.Entry<Long, Server>> it = this.serversTable.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, Server> entry = it.next();
                if(!this.areTheSameServers(entry.getValue(), parentServer)){
                    TTransport transport;
                    transport = new TSocket(entry.getValue().getIp(), entry.getValue().getPortNumber());
                    transport.open();
                    TProtocol protocol = new  TBinaryProtocol(transport);
                    Graph.Client client = new Graph.Client(protocol);
                    System.out.println("antes do newServersTable. table: " + this.serversTable.toString() + "current: " + this.currentServer.toString());
                    client.setServersTable(this.serversTable);
                     System.out.println("depois do newServersTable. table: " + this.serversTable.toString());
                    transport.close();
                }
            }
        } catch (Exception x) {
            System.out.println("falhou na conexão");
        }
    }
    public void teste(){
        System.out.println("testeeeeee");
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
          System.out.println("entrei no connectToServer");
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
        System.out.println("newServersTable server: " + currentServer + " table: " + serversTableLocal);
        this.serversTable = serversTableLocal;
        System.out.println("newServersTable server: " + currentServer + " table: " + this.serversTable);
    }
    
    /**
     * funcao para ver em qual servidor vai qual item (vertice ou aresta)
     * @param numForHash
     * @return 
     */
    private Long hashFunction(long numForHash) {
        System.out.println("hashFunction serversTable: " + this.serversTable + " numForHash: " + numForHash);
       return numForHash%this.serversTable.size();
    }
    
    /**
     * pega o server certo de acordo com o vertice passado como parametro e com a funcao hash
     * @param nameVertex
     * @return 
     */
    private Server getCorrectServerForVertex(long nameVertex){        
        try {
            semaphoreServersTable.acquire();
            System.out.println("getCorrectServerForVertex. hash: " + this.hashFunction(nameVertex));
            return this.serversTable.get(this.hashFunction(nameVertex));
        } catch (Exception x) {
            System.out.println("exception em newServersTable");
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
        System.out.println("areTheSameServers: server1 " + server1 + " server2: " + server2);
        if(server1.getIp().equals(server2.getIp()) && server1.getPortNumber() == server2.getPortNumber())
            return true;
        return false;
    }
    
   
    public boolean existenceVertex(long name){
         try{
            Server correctServer = this.getCorrectServerForVertex(name);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                System.out.println("existenceVertex como server errado. server correto eh: " + correctServer);
                Long[] listLong = new Long[1];
                listLong[0] = name;

                Class[] longType = new Class[1];
                longType[0] = long.class;

                return (Boolean)connectToServer (correctServer, listLong, Graph.Client.class.getMethod("existenceVertex", longType));
            }
            else {
                System.out.println("existenceVertex como server certo. lista de vertices: "+v);
                for(Vertex i: v){
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
        System.out.println("em existenceEdges. edge: " + edge);
        try{
            Server correctServer = this.getCorrectServerForVertex(edge.getV1id());
     
            if (!this.areTheSameServers(correctServer, currentServer)) {
                System.out.println("existenceEdges como server errado");
                Edges[] listEdges = new Edges[1];
                listEdges[0] = edge;

                Class[] edgesType = new Class[1];
                edgesType[0] = Edges.class;
System.out.println("aind sem excepetion. listEdges: "+listEdges[0]+"edgesType: "+edgesType[0]);
                return (Boolean)connectToServer (correctServer, listEdges, Graph.Client.class.getMethod("existenceEdges", edgesType));
            }
            else {
                System.out.println("existenceEdges como server certo");
                 for(Edges i: e){
                    if((i.getV1id() == edge.getV1id() && i.getV2id() == edge.getV2id()) || (i.getV1id() == edge.getV2id() && i.getV2id() == edge.getV1id())){
                        return true;
                    }
                }
                return false;
            }
        } catch(Exception e) {
            System.out.println("exception em existenceEdges: "+e);
            //nao da pra dar throw na NoSuchMethodException, entao facamos isso mesmo
            return false;
        }
    }
    
    public void CreateEdges(Edges edges) throws org.apache.thrift.TException{
        System.out.println("entrei no CreateEdges");
        if(!existenceEdges(edges)){
            if(existenceVertex(edges.getV1id()) && existenceVertex(edges.getV2id())){
                try{
                    List<Server> correctServerList = this.getCorrectServersForEdge(edges);
                    for(int i=0; i<correctServerList.size(); i++){
                        if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                            System.out.println("CreateEdges como server errado");
                            Edges[] listEdges = new Edges[1];
                            listEdges[0] = edges;

                            Class[] edgesType = new Class[1];
                            edgesType[0] = Edges.class;


                            connectToServer (correctServerList.get(i), listEdges, Graph.Client.class.getMethod("CreateEdges", edgesType));
                        }
                        else {
                             System.out.println("server certo");
                            e.add(edges);
                             System.out.println("adicionei a aresta: " + edges + " no server: "+currentServer);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println("exception em CreateEdges: "+e);
                }
                finally{
                    semaphore.release();
                }
            }
        }
    }

    public void CreateVertex(Vertex vertex) throws org.apache.thrift.TException{
        Server correctServer = this.getCorrectServerForVertex(vertex.getName());
        try{
            System.out.println("entrei no createvertex");
            if (!this.areTheSameServers(correctServer, currentServer)) {
                System.out.println("server errado!");
                Vertex[] vertexes = new Vertex[1];
                vertexes[0] = vertex;

                Class[] vertexType = new Class[1];
                vertexType[0] = Vertex.class;

                connectToServer(correctServer, vertexes, Graph.Client.class.getMethod("CreateVertex", vertexType));
            }
            else {                
                    System.out.println("server certo");
                if(!existenceVertex(vertex.getName())){    
                    semaphore.acquire();
                    v.add(vertex);
                    System.out.println("adicionei o vertice: " + v);
                }
           }
        }
        catch(Exception e){
            System.out.println("CreateVertex: cai na exception: "+e);
        }
        finally{
            semaphore.release();
        }
    }

    public Vertex ReadVertex(int vertex) throws org.apache.thrift.TException{
        Vertex result = null;
        try{
            Server correctServer = this.getCorrectServerForVertex(vertex);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                System.out.println("server errado em  ReadVertex");
                Integer[] listInteger = new Integer[1];
                listInteger[0] = vertex;

                Class[] integerType = new Class[1];
                integerType[0] = Integer.class;

                return (Vertex)connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("ReadVertex", integerType));
            }
            else {
                System.out.println("server certo em  ReadVertex. server: "+currentServer);
                for(Vertex i: v){
                   if((int)i.getName() == vertex){
                       result = i;
                   }
                }
                return result;
            }
        } catch(Exception e) {
            System.out.println("exception em ReadVertex: "+e);
            return null;
        } 
    }

    public Edges ReadEdges(Edges edge) throws org.apache.thrift.TException{      
        try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            if (!this.areTheSameServers(correctServerList.get(0), currentServer)) {
                System.out.println("server errado em  ReadEdges. server: "+currentServer);
                Edges[] listEdge = new Edges[1];
                listEdge[0] = edge;

                Class[] edgeType = new Class[1];
                edgeType[0] = Edges.class;

                return (Edges)connectToServer (correctServerList.get(0), listEdge, Graph.Client.class.getMethod("ReadEdges", edgeType));
            }
            else {
                System.out.println("server certo em  ReadEdges. server: "+currentServer+" lista de edges: "+ e);
                for(Edges item: e){
                    if((edge.getV1id() == item.getV1id() && edge.getV2id() == item.getV2id()) || (edge.getV2id() == item.getV1id() && edge.getV1id() == item.getV2id())){
                        return item;
                    }
                }
                return null;
            }
         }catch(Exception e){
            System.out.println("exception em ReadEdges: "+e);
             return null;
         }
    }

    public void DeleteEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        Edges edge = new Edges();
        edge.setV1id(vertex1);
        edge.setV2id(vertex2);
        Edges result = ReadEdges(edge);
         try{
            List<Server> correctServerList = this.getCorrectServersForEdge(edge);
            for(int i=0; i<correctServerList.size(); i++){
                if (!this.areTheSameServers(correctServerList.get(i), currentServer)) {
                    System.out.println("server errado em  DeleteEdges. server: "+currentServer);
                    Integer[] listInteger = new Integer[2];
                    listInteger[0] = vertex1;
                    listInteger[1] = vertex2;

                    Class[] integerType = new Class[2];
                    integerType[0] = int.class;
                    integerType[1] = int.class;

                    connectToServer (correctServerList.get(i), listInteger, Graph.Client.class.getMethod("DeleteEdges", integerType));
                }
                else {
                    System.out.println("server certo em  DeleteEdges. server: "+currentServer+ " result: "+result);
                    if(result!= null){
                        e.remove(result);
                        System.out.println("deletada aresta "+e+" do servidor "+currentServer);
                    }
                }
            }
         } catch(Exception e){
            System.out.println("exception em DeleteEdges: "+e);
         }
    }

    public void DeleteVertex(int vertex1) throws org.apache.thrift.TException{
        Vertex result = ReadVertex(vertex1);
         try{
            Server correctServer = this.getCorrectServerForVertex(vertex1);
            if (!this.areTheSameServers(correctServer, currentServer)) {
                Integer[] listInteger = new Integer[1];
                listInteger[0] = vertex1;

                Class[] integerType = new Class[1];
                integerType[0] = int.class;

                connectToServer (correctServer, listInteger, Graph.Client.class.getMethod("DeleteVertex", integerType));
            }
            else {
                if(result != null){
                    v.remove(result);
                    System.out.println("removido vertice "+result);
                }
            }
         } catch(Exception e){
            System.out.println("exception em DeleteVertex: "+e);
         }
    }
    
    public Set<Edges> GetEdges() throws org.apache.thrift.TException{
        Set<Edges> allEdges = new HashSet<>();
        System.out.println("table: " + this.serversTable);
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
        System.out.println("table: " + this.serversTable);
        try{
            this.semaphoreServersTable.acquire();
            for(Iterator<Map.Entry<Long, Server>> it = serversTable.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, Server> entry = it.next();
                allVertexes.addAll((List<Vertex>)connectToServer (entry.getValue(), null, Graph.Client.class.getMethod("getV", null)));
            }
            return allVertexes;
        }catch(Exception e){
            System.out.println("exception em GetVertex");
            return null;
        } finally{
            semaphoreServersTable.release();
        }
    }
    
    public List<Vertex> getV(){
        try{
            semaphore.acquire();
            return v;
        }catch(Exception e){
            System.out.println("exception em getV");
            return null;
        } finally{
            semaphore.release();
        }
    }
    
    public List<Edges> getE(){
        try{
            semaphore.acquire();
            return e;
        }catch(Exception e){
            System.out.println("exception em getE");
            return null;
        } finally{
            semaphore.release();
        }
    }

    public List<Vertex> GetVertexEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<Vertex>();
        lv.add(ReadVertex(vertex1));
        lv.add(ReadVertex(vertex2));
        return lv;
    }

    public List<Edges> GetEdgesVertex(int name) throws org.apache.thrift.TException{
        List<Edges> le = new ArrayList<>();
         try{
//            if (currentServer.getPortNumber() == 9090) {
//                Integer[] listInteger = new Integer[1];
//                listInteger[0] = name;
//
//                Class[] integerType = new Class[1];
//                integerType[0] = Integer.class;
//
//                return (List<Edges>)connectToServer ("localhost", 9091, listInteger, Graph.Client.class.getMethod("GetEdgesVertex", integerType));
//            }
//            else {
                for(Edges i: e){
                    if(i.getV1id() == name || i.getV2id() == name){
                        le.add(i);
                    }
                }
                return le;
//            }
        }catch(Exception e){
            System.out.println("exception em GetEdgesVertex");
            //nao da pra dar throw na NoSuchMethodException, entao facamos isso mesmo
            return null;
        }
    }

    public List<Vertex> GetAdjacentVertex(int name) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<>();
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Integer[] listInteger = new Integer[1];
//                listInteger[0] = name;
//
//                Class[] integerType = new Class[1];
//                integerType[0] = Integer.class;
//
//                return (List<Vertex>)connectToServer ("localhost", 9091, listInteger, Graph.Client.class.getMethod("GetAdjacentVertex", integerType));
//            }
//            else {
                for(Edges i: e){
                    if(i.getV1id() == name){
                        lv.add(ReadVertex((int) i.getV2id()));
                    }
                    else if(i.getV2id() == name){
                        lv.add(ReadVertex((int) i.getV1id()));
                    }
                }
                return lv;
//            }
        }catch(Exception e){
            System.out.println("exception em GetAdjacentVertex");
            //nao da pra dar throw na NoSuchMethodException, entao facamos isso mesmo
            return null;
        }
    }

    //TODO arrumar caso onde os vertex estao em servidores diferentes
    public void UpdateEdgesWeight(double weight, int vertex1, int vertex2) throws TException {
        int index;
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listObject = new Object[3];
//                listObject[0] = weight;
//                listObject[1] = vertex1;
//                listObject[2] = vertex2;
//
//                Class[] objectType = new Class[3];
//                objectType[0] = Double.class;
//                objectType[1] = Integer.class;
//                objectType[2] = Integer.class;
//
//                connectToServer ("localhost", 9091, listObject, Graph.Client.class.getMethod("UpdateEdgesWeight", objectType));
//            }
//            else {
                 for(Edges i: e){
                    semaphore.acquire();
                    if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                        index = e.indexOf(i);
                        i.setWeight(weight);
                        //TODO tem que procurar em qual servidor essa edge esta e atualizar a lista dele
                        e.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesWeight");
        }finally{
            semaphore.release();
        }
    }

    //TODO arrumar caso onde os vertex estao em servidores diferentes
    public void UpdateEdgesFlag(int flag, int vertex1, int vertex2) throws TException {
        int index;
         try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listInteger = new Object[3];
//                listInteger[0] = flag;
//                listInteger[1] = vertex1;
//                listInteger[2] = vertex2;
//
//                Class[] integerType = new Class[3];
//                integerType[0] = Integer.class;
//                integerType[1] = Integer.class;
//                integerType[2] = Integer.class;
//
//                connectToServer ("localhost", 9091, listInteger, Graph.Client.class.getMethod("UpdateEdgesFlag", integerType));
//            }
//            else {
                for(Edges i: e){
                    semaphore.acquire();
                    if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                        index = e.indexOf(i);
                        i.setFlag(flag);
                        //TODO tem que procurar em qual servidor essa edge esta e atualizar a lista dele
                        e.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesFlag");
        }finally{
            semaphore.release();
        }
    }
    
    //TODO arrumar caso onde os vertex estao em servidores diferentes
    public void UpdateEdgesDescription(String description, int vertex1, int vertex2) throws TException {
        int index;
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listObjects = new Object[3];
//                listObjects[0] = description;
//                listObjects[1] = vertex1;
//                listObjects[2] = vertex2;
//
//                Class[] objectType = new Class[3];
//                objectType[0] = String.class;
//                objectType[1] = Integer.class;
//                objectType[2] = Integer.class;
//
//                connectToServer ("localhost", 9091, listObjects, Graph.Client.class.getMethod("UpdateEdgesDescription", objectType));
//            }
//            else {
                for(Edges i: e){
                    semaphore.acquire();
                    if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                        index = e.indexOf(i);
                        i.setDescription(description);
                        //TODO tem que procurar em qual servidor essa edge esta e atualizar a lista dele
                        e.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateEdgesDescription");
        }finally{
            semaphore.release();
        }
    }

    public void UpdateVertexColor(int color, int name) throws TException {
        int index;
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listInteger = new Object[2];
//                listInteger[0] = color;
//                listInteger[1] = name;
//
//                Class[] integerType = new Class[2];
//                integerType[0] = Integer.class;
//                integerType[1] = Integer.class;
//
//                connectToServer ("localhost", 9091, listInteger, Graph.Client.class.getMethod("UpdateVertexColor", integerType));
//            }
//            else {
                for(Vertex i: v){
                    semaphore.acquire();
                    if(i.getName() == name){
                        index = v.indexOf(i);
                        i.setColor(color);
                        //TODO tem que procurar em qual servidor esse vertice esta e atualizar a lista dele
                        v.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexColor");
        }finally{
            semaphore.release();
        }
    }

    public void UpdateVertexDescription(String description, int name) throws TException {
        int index;
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listObject = new Object[2];
//                listObject[0] = description;
//                listObject[1] = name;
//
//                Class[] objectType = new Class[2];
//                objectType[0] = String.class;
//                objectType[1] = Integer.class;
//
//                connectToServer ("localhost", 9091, listObject, Graph.Client.class.getMethod("UpdateVertexDescription", objectType));
//            }
//            else {
                for(Vertex i: v){            
                    semaphore.acquire();
                    if(i.getName() == name){
                        index = v.indexOf(i);
                        i.setDescription(description);
                        //TODO tem que procurar em qual servidor esse vertice esta e atualizar a lista dele
                        v.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexDescription");
        }finally{
            semaphore.release();
        }
    }

    public void UpdateVertexWeight(double weight, int name) throws TException {
        int index;
        try{
//            if (currentServer.getPortNumber() == 9090) {
//                Object[] listObject = new Object[2];
//                listObject[0] = weight;
//                listObject[1] = name;
//
//                Class[] objectType = new Class[2];
//                objectType[0] = Double.class;
//                objectType[1] = Integer.class;
//
//                connectToServer ("localhost", 9091, listObject, Graph.Client.class.getMethod("UpdateVertexWeight", objectType));
//            }
//            else {
                 for(Vertex i: v){
                    semaphore.acquire();
                    if(i.getWeight() == weight){
                        index = v.indexOf(i);
                        i.setWeight(weight);
                        //TODO tem que procurar em qual servidor esse vertice esta e atualizar a lista dele
                        v.add(index, i);
                    }
                }
//            }
        }catch(Exception e){
            System.out.println("exception em UpdateVertexWeight");
        }finally{
            semaphore.release();
        }
    }
}
