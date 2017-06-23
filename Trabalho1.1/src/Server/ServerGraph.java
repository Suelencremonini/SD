/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Thrift.*;
import Thrift.GraphHandler;
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
            TServerTransport serverTransport = new TServerSocket(portNumber);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
        }
    }
}
