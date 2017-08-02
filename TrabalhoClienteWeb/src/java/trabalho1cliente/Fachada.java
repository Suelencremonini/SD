/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1cliente;

import Thrift.Cast;
import Thrift.ConnectServer;
import Thrift.Movie;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import Thrift.ConnectServer;
import Thrift.Server;
import Thrift.Edges;
import Thrift.Graph;
import Thrift.Vertex;
import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TException;
import java.util.Set;
import Auxiliar.*;
import Thrift.Caminho;
import graph.Configure;
import static javafx.beans.binding.Bindings.and;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
/**
 *
 * @author jose
 */
@Named("fachada")
@ManagedBean 
@ViewScoped

public class Fachada{
    
    int codcli;
    int senha;
    ArrayList<Vertex> lv;
    Set<Edges> sete;
    ArrayList<Edges> le;
    ArrayList<Movie> lm;
    ArrayList<Movie> auxlm;
    MyEdges me = new MyEdges();
    Edges e = new Edges();
    Vertex v = new Vertex();
    Movie m = new Movie();
    Cast c = new Cast();
    User u = new User();
    Server currentServer = new Server();
    MyMoviee my = new MyMoviee();
    MyCast mc = new MyCast();
    int pinicial;
    int pfinal;
    Caminho p = new Caminho();
    
    public String addUser() throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));   
        u.change(v);
        v.setMovies(new ArrayList<>());
        client.CreateVertex(v);
        transport.close();   
        return "cadastrausuario.xhtml";
    }
    
    public String allUsers() throws TException{
        
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));  
        List<Vertex> aux =  client.GetVertex();
        lv = null;
        if(aux != null){
            lv = (ArrayList<Vertex>) aux;
        }
        transport.close(); 
        
        return "cadastrausuario.xhtml";
    }
    
    public void addEdges() throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        me.changeEdge(e);
        e.setM(m);
        client.CreateEdges(e);
        transport.close();
    }
    
    public void allEdges() throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        sete =  client.GetEdges();
    }
    public void addMovies() throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        my.changeMovie(m);
        mc.changeCast(c);
        m.setCast(c);
        client.CreateMovie(m);
        transport.close();
    }
    
    public String allMovies() throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        lm =  (ArrayList<Movie>) client.GetMovie();
        transport.close();
        return "cadastrafilme.xhtml";
    }
    public void updatelistmovie(Movie m , int name) throws TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        client.UpdateVertexMovie(m, name);
        transport.close();
        
    }
    
    
    public void CreateCaminho() throws TTransportException, TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        client.send_actuallyCreateCaminho(p);
        transport.close();
    }
    
    public void returnCaminho() throws TTransportException, TException{
        TTransport transport = new TSocket("localhost",9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        Graph.Client client = new Graph.Client(protocol);
        Server currentServer = new Server();
        currentServer.setIp("localhost");
        currentServer.setPortNumber(Integer.parseInt("9090"));
        p = client.actuallyGetCaminho();
        transport.close();
    }
    
    public void updateedges(Movie movie) throws TException{
        boolean flag = false;
        allEdges();
        
        for(Edges i: sete){
            if(i.getM() != null){
                if((u.getSenha() == i.getV1id() && my.getId() == i.getM().getId()) || (u.getSenha() == i.getV2id() && my.getId() == i.getM().getId())){
                    flag = true;
                }
            }
        }
        
        ArrayList<Vertex> selecionados = new ArrayList<>();
        
        if(!flag){
            
            for(Vertex i: lv){
                if(i.getName() != u.getSenha()){
                    for(Movie j: i.getMovies()){
                        if(j.getId() == my.getId()){
                            me.setV1id((int) u.getSenha());
                            me.setV2id((int) i.getName());
                            m = movie;
                            addEdges();
                        }
                    }
                }
            }
        }
    }
    
    
    public void addFilmeList() throws TException{
        boolean flag = false;
        
        allMovies();
        Movie aux = null;
        for(Movie i: lm){
            if(i.getId() == my.getId()){
                aux = i;
            }      
        }
        if (aux != null){
            allUsers();
        
            for(Vertex i: lv){
                if(u.getSenha() == i.getName()){
                    for(Movie j: i.getMovies()){
                        if(j.getId() == aux.getId()){
                            flag = true;
                        }
                    }
                }
            }
            
            if(!flag){
                updatelistmovie(aux, (int) u.getSenha());
            }
            
            updateedges(aux);
        }
    
    }
    
    public void useDijikstra() throws TException{
        List<Integer> li;
        
        allUsers();
        allEdges();
        
        li = Configure.ConfigureVertex(lv, le, pinicial, pfinal);
        
        
    }
    
    public  void traduzDjikstra(Graph.Client client, List<Integer> li) throws TException{
        
      
        Set<Edges> edges = client.GetEdges();
        ArrayList<Vertex> aux = new ArrayList();
        
        
        for(Integer j: li){
            for(Vertex i: client.GetVertex()){
                if(j == i.getName()){
                    aux.add(i);
                }
            }
        }
        
        Edges take = null;
        
        for(int i = 1; i < li.size(); i = i + 2){
            
            for(Edges j: edges){
                if((j.getV1id() == li.get(i-1) && j.getV2id() == li.get(i)) || (j.getV2id() == li.get(i-1) && j.getV1id() == li.get(i)) ){
                    take = j;
                }
                
            }
            System.out.println(aux.get(i-1).getDescription()+" "+ take.getM().getName() +" "+aux.get(i).getDescription());
        }
        
        
    }
    

    

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public List<Vertex> getLv() {
        return lv;
    }

    public void setLv(List<Vertex> lv) {
        this.lv = (ArrayList<Vertex>) lv;
    }

    public Set<Edges> getSete() {
        return sete;
    }

    public void setSete(Set<Edges> sete) {
        this.sete = sete;
    }

    public List<Edges> getLe() {
        return le;
    }

    public void setLe(List<Edges> le) {
        this.le = (ArrayList<Edges>) le;
    }

    public Edges getE() {
        return e;
    }

    public void setE(Edges e) {
        this.e = e;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public Movie getM() {
        return m;
    }

    public void setM(Movie m) {
        this.m = m;
    }

    public ArrayList<Movie> getLm() {
        return lm;
    }

    public void setLm(ArrayList<Movie> lm) {
        this.lm = lm;
    }

    public Cast getC() {
        return c;
    }

    public void setC(Cast c) {
        this.c = c;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public MyMoviee getMy() {
        return my;
    }

    public void setMy(MyMoviee my) {
        this.my = my;
    }

    public MyCast getMc() {
        return mc;
    }

    public void setMc(MyCast mc) {
        this.mc = mc;
    }

    public ArrayList<Movie> getAuxlm() {
        return auxlm;
    }

    public void setAuxlm(ArrayList<Movie> auxlm) {
        this.auxlm = auxlm;
    }

    public MyEdges getMe() {
        return me;
    }

    public void setMe(MyEdges me) {
        this.me = me;
    }

    public int getPinicial() {
        return pinicial;
    }

    public void setPinicial(int pinicial) {
        this.pinicial = pinicial;
    }

    public int getPfinal() {
        return pfinal;
    }

    public void setPfinal(int pfinal) {
        this.pfinal = pfinal;
    }

    public Caminho getP() {
        return p;
    }

    public void setP(Caminho p) {
        this.p = p;
    }
    
    
    
    
}
