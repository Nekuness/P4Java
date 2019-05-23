/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.controlador;

import edu.ub.prog2.UrciuoliGonzalo.model.Audio;
import edu.ub.prog2.UrciuoliGonzalo.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import edu.ub.prog2.utils.ReproductorBasic;
import java.io.File;

/**
 *
 * @author digit
 */
public class Reproductor extends ReproductorBasic{
    
    public Reproductor(EscoltadorReproduccioBasic controlador) {
        super(controlador);
    }
    
    public Reproductor(String vlcPath, EscoltadorReproduccioBasic controlador) {
        super(vlcPath,controlador);
    }
    
    public void reprodueix(FitxerReproduible fr) throws AplicacioException{
        play(fr);
    }
    
    public void reprodueix(Audio audio, File fitxerImage) throws AplicacioException{
        play(audio,fitxerImage);
    }
}
