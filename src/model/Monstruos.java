/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author aitor
 */

public class Monstruos {
        
    private String firstName;
    private String especie;
    private String elemento;
    private String weakness;
    private String endure;
    
    public Monstruos(String fName, String especie, String elemento, String weakness, String endure) { //derrigortuta nago, ezta? public jartzera beste pakete batetik sortuko dudalako?
        this.firstName = fName;
        this.especie = especie;
        this.elemento = elemento;
        this.weakness = weakness;
        this.endure = endure;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fName) {
        this.firstName = firstName;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String fName) {
        this.especie=especie;
    }
    public String getElemento() {
        return elemento;
    }
    public void setElemento(String fName) {
        this.elemento=elemento;
    }

    public void setWeakness(String fName) {
        this.weakness=weakness;
    }
    
    public String getWeakness() {
        return weakness;
    }
    
    public void setEndure(String fName) {
        this.endure =endure;
    }
    
    public String getEndure() {
        return endure;
    }
}