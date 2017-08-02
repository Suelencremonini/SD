/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import Thrift.Cast;

/**
 *
 * @author jose
 */
public class MyCast {
    private String director;
    private String main_chracter;
    private String productor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMain_chracter() {
        return main_chracter;
    }

    public void setMain_chracter(String main_chracter) {
        this.main_chracter = main_chracter;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }
    
    public void changeCast(Cast c){
        c.setDirector(director);
        c.setProductor(productor);
        c.setMain_character(main_chracter);
    }
    
}
