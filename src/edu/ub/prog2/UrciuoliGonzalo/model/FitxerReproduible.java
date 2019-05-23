/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.model;

import edu.ub.prog2.UrciuoliGonzalo.controlador.Reproductor;

/**
 *
 * @author digit
 */
public abstract class FitxerReproduible extends FitxerMultimedia{
    
    protected String cami;
    protected String nom;
    protected String codec;
    protected float durada;
    protected Reproductor r;
    
    public FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r) {
        super(cami);
        this.cami = cami;
        this.nom = nom;
        this.codec = codec;
        this.durada = durada;
        this.r = r;
        
    }
    
    public abstract void reproduir();
    
    public void setReproductor(Reproductor r){
        this.r = r;
    }
}
