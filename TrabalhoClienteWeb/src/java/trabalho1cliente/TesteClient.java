/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1cliente;

import Thrift.Edges;
import Thrift.Graph;
import Thrift.Movie;
import Thrift.Vertex;
import java.util.List;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author jose
 */
public class TesteClient {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TTransportException, TException {
        

        
       
        
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new  TBinaryProtocol(transport);
            Graph.Client client = new Graph.Client(protocol);

            perform(client);

            transport.close();
        
    }
   
   private static void perform(Graph.Client client) throws TException{
      
      Vertex v = new Vertex();
      Movie m = new Movie();
      m.setId(123);
      v.setName(290);
      /*client.UpdateVertexMovie(m, 1234);*/
      
      List <Vertex> lv = client.GetVertex();
      
      for(Vertex i: lv){
          if(i.getName() == 1234){
              System.out.println(i.getMovies());
          }
              
      }
      
  }

  
}
