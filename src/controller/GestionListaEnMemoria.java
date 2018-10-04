/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Monstruos;

/**
 *
 * @author aitor
 */
public class GestionListaEnMemoria {
    
    static FileReader frMonstruos = null;
    static FileReader frEspecies = null;
    static BufferedReader br = null;
    
 //  
    
    public static ArrayList<Monstruos> cargarDatos(){
        
        try {
            //Strima irekitzen dugu.
            frMonstruos = new FileReader("Monstruos.txt");
            br = new BufferedReader(frMonstruos);
            String aux;
            String[] arrString;
            ArrayList<Monstruos> arrMonstruos= new ArrayList();
            while ((aux = br.readLine()) != null) {
                if (!"".equals(aux)) {
                    arrString = aux.split(",");
                    arrMonstruos.add(new Monstruos(arrString[0], arrString[1], arrString[2], arrString[3], arrString[4]));
                }
            }
            br.close();
            return arrMonstruos;
        } catch (FileNotFoundException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Artxiboa ez da aurkitu.");
            error.showAndWait();
            return null;
        } catch (IOException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Errorea egon da irakurketan.");
            error.showAndWait();
            return null;
        }
        
//        return FXCollections.observableArrayList(new Monstruos("Deviljho", "Wyvern Bruto", "Dragon","Dragon, Rayo, Hielo","-"),
//            new Monstruos("Lagiacrus", "Leviathan", "Rayo","Fuego","Rayo"),
//            new Monstruos("Nargacuga", "Wivern Volador", "-","Fuego, Rayo","-"),
//            new Monstruos("Nergigante", "Dragon Anciano", "-","Rayo, Dragon","Fuego"),
//            new Monstruos("Gore Magala", "????", "Dragon","Dragon","-")
//        );
    }
    
    public static ArrayList<String> cargarDatosEspecie()
    {

        try {
            //Strima irekitzen dugu.
            frEspecies = new FileReader("Especies.txt");
            br = new BufferedReader(frEspecies);
            String strAux;
            String[] arrAux;
            ArrayList<String> arrEspecies= new ArrayList();
            while ((strAux = br.readLine()) != null) {
               arrAux= strAux.split(",");
               for(int i = 0; i < arrAux.length; i++){
                   arrEspecies.add(arrAux[i]);
               }
            }
            br.close();
            return arrEspecies;
        } catch (FileNotFoundException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Artxiboa ez da aurkitu.");
            error.showAndWait();
            return null;
        } catch (IOException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Errorea egon da irakurketan.");
            error.showAndWait();
            return null;
        }
    }
    
}
