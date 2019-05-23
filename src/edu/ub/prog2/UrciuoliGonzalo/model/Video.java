/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.model;

import edu.ub.prog2.UrciuoliGonzalo.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author digit
 */
public class Video extends FitxerReproduible{
    
    int alcada;
    int amplada;
    float fps;

    public Video(String cami, String nom, String codec, float durada, int alcada, int amplada, float fps, Reproductor r) {
        super(cami,nom,codec,durada,r);
        this.alcada = alcada;
        this.amplada = amplada;
        this.fps = fps;
        
    }

    @Override
    public void reproduir() {
        try {
            r.reprodueix(this);
        } catch (AplicacioException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
