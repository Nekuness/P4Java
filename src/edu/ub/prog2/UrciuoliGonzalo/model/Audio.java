/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.model;

import edu.ub.prog2.UrciuoliGonzalo.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author digit
 */
public class Audio extends FitxerReproduible{
    
    int kbps;
    File fitxerimatge;

    public Audio(String cami, File fitxerimatge, String nom, String codec, float durada, Reproductor r, int kbps) {
        super(cami, nom, codec, durada, r);
        this.kbps = kbps;
        this.fitxerimatge = fitxerimatge;
    }

    @Override
    public void reproduir() {
        try {
            r.reprodueix(this, fitxerimatge);
        } catch (AplicacioException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
