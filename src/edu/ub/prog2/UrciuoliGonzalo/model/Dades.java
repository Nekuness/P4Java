/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.UrciuoliGonzalo.model;

import edu.ub.prog2.UrciuoliGonzalo.controlador.Reproductor;
import edu.ub.prog2.UrciuoliGonzalo.controlador.EscoltadorReproductor;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author digit
 */
public class Dades implements Serializable {
    
    int MAX_SIZE = 100;
    int MAX_SIZE_ALBUM = 10;
    public boolean reproduccioCiclica, reproduccioAleatoria;
    public BibliotecaFitxerMultimedia biblio = new BibliotecaFitxerMultimedia(MAX_SIZE);
    ArrayList<AlbumFitxersMultimedia> albums = new ArrayList(MAX_SIZE_ALBUM);
    private EscoltadorReproductor e = new EscoltadorReproductor();
    private transient Reproductor r = new Reproductor("C:\\Program Files\\VideoLAN\\VLC", e);
    

    public Dades() {
    }
    
    public File seleccionBiblio(int id) {
        return biblio.getAt(id);
    }

    public AlbumFitxersMultimedia seleccionAlbum(int id) {
        return albums.get(id);
    }

    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException, IOException {
        boolean exists = true;
        try {
            Video v1 = new Video(path, nomVideo, codec, durada, alcada, amplada, fps, r);
            if (!v1.exists()) {
                exists = false;
                System.out.println("El fitxer no existeix en el camí");
            } else if (exists) {
                biblio.addFitxer(v1);
            }
        } catch (RuntimeException e) {
            System.out.println("No s'ha pogut carregar el VLC");
        }

        if (biblio.getSize()
                >= MAX_SIZE) {
            throw new AplicacioException("La biblioteca esta plena");
        }

    }

    public void afegirAudio(String cami, String camiImatge, String nomAudio, String codec, float durada, int kbps) throws AplicacioException {
        boolean exists = true;
        try {
            FitxerMultimedia img1 = new FitxerMultimedia(camiImatge);
            Audio a1 = new Audio(cami, img1, nomAudio, codec, durada, r, kbps);
            if (!a1.exists()) {
                exists = false;
                System.out.println("El fitxer no existeix en el camí");
            } else if (exists) {
                biblio.addFitxer(a1);
            }
        } catch (RuntimeException e) {
            System.out.println("No s'ha pogut carregar el VLC");
        }
        if (biblio.getSize() >= MAX_SIZE) {
            throw new AplicacioException("La biblioteca esta plena");
        }

    }

    public List<String> mostrarBiblioteca() {
        List<String> s1 = new ArrayList<String>();
        for (int i = 0; i < biblio.getSize(); i++) {
            s1.add(biblio.toString(i));
        }
        return s1;

    }

    public void esborrarFitxer(int id) throws AplicacioException {
        id--;
        biblio.carpeta.remove(id);
        String titolAlbum = biblio.carpeta.get(id).nom;
        FitxerMultimedia fitxer = (FitxerReproduible) biblio.getAt(id);
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titolAlbum)) {
                albums.get(i).carpeta.removeFitxer((FitxerReproduible) fitxer);
            }
        }

    }

    public void guardarDadesDisc(String camiDesti) throws AplicacioException, FileNotFoundException, IOException {
        File fitxer = new File(camiDesti + "dades.ser");
        if (fitxer.createNewFile()) {
            System.out.println("Fitxer creat");
        } else {
            throw new IOException("El fitxer ya esta creat");
        }
        FileOutputStream fout = new FileOutputStream(fitxer);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this.biblio);

    }

    public void carregarDadesDisc(String camiOrigen) throws AplicacioException, FileNotFoundException, IOException, ClassNotFoundException {
        File fitxer = new File(camiOrigen);
        FileInputStream fin = new FileInputStream(fitxer);
        try (ObjectInputStream ois = new ObjectInputStream(fin)) {
            BibliotecaFitxerMultimedia d2 = (BibliotecaFitxerMultimedia) ois.readObject();
            this.biblio = d2;
        }
        System.out.println("Fitxer carregat amb exit");

    }

    public void afegirAlbum(String titolAlbum) {
        AlbumFitxersMultimedia album = new AlbumFitxersMultimedia(titolAlbum);
        albums.add(album);
    }

    public List<String> mostrarLlistatAlbums() {
        ArrayList<String> mostrar = new ArrayList();
        String m;
        for (int i = 0; i < albums.size(); i++) {
            m = "[" + i + 1 + "] " + albums.get(i).toString();
            mostrar.add(m);
        }
        return mostrar;
    }

    public void esborrarAlbum(String titolAlbum) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titolAlbum)) {
                albums.remove(i);
            }
        }
    }

    public boolean existeixAlbum(String titolAlbum) {
        boolean existeix = false;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titolAlbum)) {
                existeix = true;
            }
        }
        return existeix;
    }

    public void afegirFitxer(String titolAlbum, int id) {
        if (biblio.getAt(id) instanceof Video) {
            Video fitxer = (Video) biblio.getAt(id);
            for (int i = 0; i < albums.size(); i++) {
                if (albums.get(i).titol.equals(titolAlbum)) {
                    albums.get(i).addFitxerVideo(fitxer);
                }
            }
        } else if (biblio.getAt(id) instanceof Audio) {
            Audio fitxer = (Audio) biblio.getAt(id);
            for (int i = 0; i < albums.size(); i++) {
                if (albums.get(i).titol.equals(titolAlbum)) {
                    albums.get(i).addFitxerAudio(fitxer);
                }
            }
        }
    }

    public List<String> mostrarAlbums(String titolAlbum) {
        List<String> mostrar = new ArrayList<>();
        String m;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titolAlbum)) {
                m = albums.get(i).carpeta.toString();
                mostrar.add(m);
            }
        }
        return mostrar;
    }
    
    public String mostrarAlbum(int id){
        String s;
        s = albums.get(id).carpeta.toString();
        return s;
        
                
    }

    public void esborrarFitxer(String titolAlbum, int id) {
        FitxerMultimedia fitxer = (FitxerReproduible) biblio.getAt(id);
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titolAlbum)) {
                albums.get(i).carpeta.removeFitxer((FitxerReproduible) fitxer);
            }
        }
    }

    public void reproduirFitxer(int i) throws AplicacioException {
        CarpetaFitxers singular = new CarpetaFitxers(1);
        singular.addFitxer(biblio.carpeta.get(i));
        e.iniciarReproduccio(singular, reproduccioCiclica, reproduccioAleatoria);
    }
    
    public void reproduirCarpeta(String string){
        try {
            e.iniciarReproduccio(biblio, reproduccioCiclica, reproduccioAleatoria);
            
        } catch (AplicacioException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void obrirFinestraReproductor(){
        this.r.open();
    }
    
    public void tancarFinestraReproductor(){
        this.r.stop();
    }
    public void reproduirFitxer(FitxerReproduible fr) throws AplicacioException{
        CarpetaFitxers singular = new CarpetaFitxers(1);
        singular.addFitxer(fr);
        e.iniciarReproduccio(singular, reproduccioCiclica, reproduccioAleatoria);
    }
    
    public void reproduirBiblioteca(){
        try {
            e.iniciarReproduccio(biblio, reproduccioCiclica, reproduccioAleatoria);
            
        } catch (AplicacioException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setReproductor(){
        for (FitxerReproduible carpeta : biblio.carpeta) {
            carpeta.setReproductor(r);
        }
    }
    public void repCiclica(){
        reproduccioCiclica = !reproduccioCiclica;
        if(reproduccioCiclica){
            System.out.println("La reproduccio ciclica ha sigut activada");
        }
        else{
            System.out.println("La reproduccio ciclica ha sigut desactivada");
        }
        
    }
    
    public void repAleatoria(){
        reproduccioAleatoria = !reproduccioAleatoria;
        if(reproduccioAleatoria){
            System.out.println("La reproduccio aleatoria ha sigut activada");
        }
        else{
            System.out.println("La reproduccio aleatoria ha sigut desactivada");
        }
        
    }
    public void reemprenReproduccio() throws AplicacioException {
        r.resume();
    }

    
    public void pausaReproduccio() throws AplicacioException {
        r.pause();
    }

    
    public void aturaReproduccio() throws AplicacioException {
        r.stop();
    }

    
    public void saltaReproduccio() throws AplicacioException {
        
    }
    
    public void reproduccioAlbum(String titol) throws AplicacioException{
        AlbumFitxersMultimedia temp;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).titol.equals(titol)) {
               temp = albums.get(i);
               e.iniciarReproduccio(temp.carpeta, reproduccioCiclica, reproduccioAleatoria);
            }
        }
        
    }
}
    
    
