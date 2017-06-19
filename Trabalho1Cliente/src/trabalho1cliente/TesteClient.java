/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1cliente;

import Thrift.Edges;
import Thrift.Graph;
import Thrift.Vertex;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author jose
 */
public class TesteClient {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            Graph.Client client = new Graph.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            System.out.println("falhou na conexão");
        }
    }
   private static void perform(Graph.Client client) throws TException{
      
      Vertex v = new Vertex();
      
      v.setName(290);
      client.CreateVertex(v);
      
      
  }   

  
}
