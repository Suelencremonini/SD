/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoservercopycat;

import Copycat.CopycatStateMachine;
import io.atomix.catalyst.transport.Address;
import io.atomix.copycat.server.CopycatServer;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;

/**
 *
 * @author Suelen
 */
public class ServerCopycat {
    public static void main(String[] args) {
        String ipServerLeader = args[0];
        int portServerLeader = Integer.parseInt(args[1]);
        
        String ipCurrentServer = args[2];
        int portCurrentServer = Integer.parseInt(args[3]);
        
        CopycatServer copycatServer = CopycatServer.builder(new Address(ipCurrentServer, portCurrentServer))
            .withStorage(Storage.builder()
              .withStorageLevel(StorageLevel.MEMORY)
              .build())            
            .withStateMachine(CopycatStateMachine::new).build();
        
        if(portServerLeader == portCurrentServer && ipServerLeader.equals(ipCurrentServer))
            copycatServer.bootstrap().join();
        else
            copycatServer.join(new Address(ipServerLeader, portServerLeader)).join();
        
        System.out.println("servidor subiu. Porta: "+portCurrentServer);
    }
}
