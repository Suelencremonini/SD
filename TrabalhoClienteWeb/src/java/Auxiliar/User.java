/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;
import Thrift.*;
/**
 *
 * @author jose
 */
public class User {
    private long senha;
    private String description;
    private String email;

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        this.senha = senha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void change(Vertex v){
        v.setDescription(this.description);
        v.setName((int)this.senha);
        v.setEmail(this.email);
    }
    
    
}
