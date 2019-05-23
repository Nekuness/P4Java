/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.controlador;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import edu.ub.prog2.UrciuoliGonzalo.model.Audio;
import edu.ub.prog2.UrciuoliGonzalo.model.CarpetaFitxers;
import edu.ub.prog2.UrciuoliGonzalo.model.Dades;
import edu.ub.prog2.UrciuoliGonzalo.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.util.ArrayList;

/**
 *
 * @author digit
 */
public class EscoltadorReproductor extends EscoltadorReproduccioBasic{
    
    private CarpetaFitxers llistaReproduint;
    private boolean [] llistaCtrl;
    
    
    public EscoltadorReproductor(){
        
    }

    public void iniciarReproduccio(CarpetaFitxers llistaReproduint, boolean reproduccioCiclica, boolean reproduccioAleatoria) throws AplicacioException{
        llistaCtrl = new boolean[llistaReproduint.size()];
        this.llistaReproduint = llistaReproduint;
        ArrayList<FitxerReproduible> llista = llistaReproduint.getCarpeta();
        /*if(reproduccioCiclica){
            for(int i = 0; i <= llista.size(); i++){
                if(i == llista.size()){
                    i = 0;
                }
                FitxerReproduible fr = llista.get(i);
                fr.reproduir();
                }
            }
        if(reproduccioAleatoria){
            int pos = (int) Math.round(Math.random()*(llistaCtrl.length-1));
            FitxerReproduible fr = llista.get(pos);
            fr.reproduir();
        }
        */
            for(int i = 0; i < llista.size(); i++){
                FitxerReproduible fr = llista.get(i);
                fr.reproduir();
            }
        
        }
        
    
    
    
    @Override
    protected void onEndFile() {
        System.out.println("S'ha acabat de reproduir el ");
    }

    @Override
    protected void next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
