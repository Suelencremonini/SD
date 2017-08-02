/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Thrift.*;
import Thrift.GraphHandler;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author jose
 */
public class ServerGraph {
    
    public static GraphHandler handler;
    public static Graph.Processor processor;
    public static int portNumber;
    
    public static void main(String [] args) {
        try {
            portNumber = Integer.parseInt(args[0]);
            handler = new GraphHandler();
            processor = new Graph.Processor(handler);
            Runnable simple = () -> {

                simple(processor);
            };
         new Thread(simple).start();
        } catch (Exception x) {
        }
    }
    
    public static void simple(Graph.Processor processor) {   
        try {
            TServerSocket serverSocket = new TServerSocket(portNumber);
            TServerTransport serverTransport = serverSocket;
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            if(portNumber != 9090){
                connectToCentralServer(serverSocket.getServerSocket().getInetAddress().getHostAddress(), true);
            }
            server.serve();
        } catch (Exception e) {
        }
    }
    
     public static void connectToCentralServer(String ipAddress, boolean connected) {
        Map<Long, Server> tableServers = new HashMap<>();
        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            Graph.Client client = new Graph.Client(protocol);
            
            Server self = new Server();
            self.setIp(ipAddress);
            self.setPortNumber(portNumber);
            
            Server central = new Server();
            central.setIp("localhost");
            central.setPortNumber(9090);
            client.serverConnected(central);
            handler.setServersTable(client.comunicateConnectionToCentralServer(self, connected));
            transport.close();
        } catch (TException x) {
            System.out.println("falhou na conexão");
        }
    }
}
