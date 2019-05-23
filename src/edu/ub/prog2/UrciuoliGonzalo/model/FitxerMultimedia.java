package edu.ub.prog2.UrciuoliGonzalo.model;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class FitxerMultimedia extends File {

    private String descripcio;

    public FitxerMultimedia(String cami) {
        super(cami);
    }

    public String getNom() {
        String s1 = getName();
        String[] resultat = s1.split(Pattern.quote("."));
        return resultat[0];
    }

    public String getExtensio() {
        String extension = getName();
        String[] array_extension = extension.split(Pattern.quote("."));
        return array_extension[1];
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    
    public String getCamiAbsolut() {
        return getAbsolutePath();
    }

    public Date getUltimaModificacio() {
        return new Date(lastModified());
    }

    public boolean equals(File fitxerMultimedia) {
        boolean equals = false;
        if (this.getName().equals(fitxerMultimedia.getName()))
            equals = true;
        return equals;
    }
    

    public String toString() {
        return "Nom: " + getNom() + "\n"
                + "Extensio: " + getExtensio() + "\n"
                + "Descripció: " + getDescripcio() + "\n"
                + "Data: " + getUltimaModificacio() + "\n"
                + "Camí: " + getCamiAbsolut() + "\n";
    }
}
