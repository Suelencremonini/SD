/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import Thrift.Movie;

/**
 *
 * @author jose
 */
public class MyMoviee {
    private long id;
    private String name;
    private double rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void changeMovie(Movie m){
        m.setId(id);
        m.setName(name);
        m.setRating(rating);
    }
    
}
