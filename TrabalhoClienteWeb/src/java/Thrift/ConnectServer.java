/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import Thrift.*;

/**
 *
 * @author jose
 */
public class ConnectServer {
    
   static TTransport transport = new TSocket("localhost",9090);
      
    public static Graph.Client getConnection() {
        try {
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            Graph.Client client = new Graph.Client(protocol);
            Server currentServer = new Server();
            currentServer.setIp("localhost");
            currentServer.setPortNumber(Integer.parseInt("9090"));
            return client;
            
        } catch (TException x) {
            transport.close();
            System.out.println("falhou na conexão");
        }
        
        return null;
    }
    
    public static void closeConnection(){
        transport.close();
    }
    
}
