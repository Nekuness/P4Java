/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.controlador;

import edu.ub.prog2.UrciuoliGonzalo.model.AlbumFitxersMultimedia;
import edu.ub.prog2.UrciuoliGonzalo.model.Dades;
import edu.ub.prog2.UrciuoliGonzalo.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author digit
 */
public class Controlador implements InControlador {

    Dades d1 = new Dades();
    EscoltadorReproductor r = new EscoltadorReproductor();

    public Controlador() {
    }

    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException {
        try {
            d1.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps);
        } catch (AplicacioException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afegirAudio(String cami, String camiImatge, String nomAudio, String codec, float durada, int kbps) throws AplicacioException {
        try {
            d1.afegirAudio(cami, camiImatge, nomAudio, codec, durada, kbps);
        } catch (AplicacioException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> mostrarBiblioteca() {
        return d1.mostrarBiblioteca();

    }

    public void esborrarFitxer(int id) throws AplicacioException {
        d1.esborrarFitxer(id);
    }

    public void guardarDadesDisc(String camiDesti) throws AplicacioException {
        try {
            d1.guardarDadesDisc(camiDesti);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarDadesDisc(String camiOrigen) throws AplicacioException {
        try {
            d1.carregarDadesDisc(camiOrigen);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afegirAlbum(String titolAlbum) {
        d1.afegirAlbum(titolAlbum);
    }

    @Override
    public void reproduirFitxer(int i) throws AplicacioException {
       try{
           d1.reproduirFitxer(i);
           d1.reemprenReproduccio();
       }
       catch(IllegalArgumentException e){
           System.out.println("InvalidArgumentException, has introduit la ID correctament?");
       }
       catch(AplicacioException e){
           System.out.println("Aplicacio Exception, has introduit la ID correctament?");
       }
    }
    
    public void reproduirFitxer(FitxerReproduible fr) throws AplicacioException{
        d1.reproduirFitxer(fr);
    }
    @Override
    public List<String> mostrarLlistatAlbums() {
        return d1.mostrarLlistatAlbums();
    }

    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
        d1.esborrarAlbum(string);
    }

    @Override
    public boolean existeixAlbum(String string) {
        d1.existeixAlbum(string);
    }

    @Override
    public void afegirFitxer(String string, int i) throws AplicacioException {
        d1.afegirFitxer(string, i);
    }

    
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        return d1.mostrarAlbums(string);
    }  

    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
        d1.esborrarFitxer(string, i);
    }

    @Override
    public void obrirFinestraReproductor() {
        d1.obrirFinestraReproductor();
    }

    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        d1.tancarFinestraReproductor();
    }

    @Override
    public void reproduirCarpeta() throws AplicacioException {
        d1.obrirFinestraReproductor();
        r.iniciarReproduccio(d1.biblio, d1.reproduccioCiclica, d1.reproduccioCiclica);
        d1.tancarFinestraReproductor();
        d1.reemprenReproduccio();
    }

    @Override
    public void reemprenReproduccio() throws AplicacioException {
        d1.reemprenReproduccio();
    }

    @Override
    public void pausaReproduccio() throws AplicacioException {
        d1.pausaReproduccio();
    }

    @Override
    public void aturaReproduccio() throws AplicacioException {
        d1.aturaReproduccio();
        d1.tancarFinestraReproductor();
    }

    @Override
    public void saltaReproduccio() throws AplicacioException {
        d1.saltaReproduccio();
    }

    public AlbumFitxersMultimedia seleccionAlbum(int id) {
        return d1.seleccionAlbum(id);
    }

    public File seleccionBiblio(int id) {
        return d1.seleccionBiblio(id);
    }

    @Override
    public void reproduirCarpeta(String string) throws AplicacioException {
        d1.reproduirCarpeta(string);
    }
    
    public void reproduccioAleatoria(){
        d1.repAleatoria();
    }
    
    public void reproduccioCiclica(){
        d1.repCiclica();
}
    public void reproduirBiblioteca(){
        d1.setReproductor();
        d1.obrirFinestraReproductor();
        d1.reproduirBiblioteca();
        d1.tancarFinestraReproductor();
    }
    
    public void reproduirAlbum(String titol) throws AplicacioException{
        d1.reproduccioAlbum(titol);
        d1.reemprenReproduccio();
        
    }

    public String mostrarAlbumIndividual(int id) throws AplicacioException {
        return d1.mostrarAlbum(id);
    }
}
