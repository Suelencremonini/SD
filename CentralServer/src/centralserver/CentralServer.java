/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

import Thrift.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 *
 * @author Suelen
 */
public class CentralServer {
    public static GraphHandler handler;
    public static Graph.Processor processor;
    public static int portNumber;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            portNumber = Integer.parseInt(args[0]);
            handler = new GraphHandler();
            processor = new Graph.Processor(handler);
            Runnable simple = () -> {

                startServer(processor);
            };
         new Thread(simple).start();
        } catch (Exception x) {
        }
    }
    
    public static void startServer(Graph.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(portNumber);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the central server...");
            server.serve();
        } catch (Exception e) {
        }
    }
}
