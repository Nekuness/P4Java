/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.model;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author digit
 */
public class BibliotecaFitxerMultimedia extends CarpetaFitxers implements Serializable {

    public BibliotecaFitxerMultimedia(int tamany) {
        super(tamany);
    }
    
    
    public void mostrarDades(){
        System.out.println("Has triat la opció 3");
        System.out.println(carpeta.toString());
    }
    
    
}
