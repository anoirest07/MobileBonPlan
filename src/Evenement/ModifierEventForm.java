/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement;

import Entities.Etablissement;
import Entities.Evenement;
import Services.ServiceEvenement;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amine
 */
public class ModifierEventForm {
    
     Form f;
    TextField tnom;
    
    TextField description;
    DateSpinner date=new DateSpinner();
     ServiceEvenement se = new ServiceEvenement();
     ArrayList<Etablissement> lsevent= se.getListEtab(Authentification.connectedUser.getId());
     
    
    Button btnajout,btnaff;
  Etablissement etabli  ;

    public ModifierEventForm(Resources res, Evenement event) {
        
        f = new Form("Events");
                              tnom = new TextField();
        description = new TextField();
        ServiceEvenement se = new ServiceEvenement();
                ArrayList<Etablissement> lsevent= se.getListEtab(Authentification.connectedUser.getId());
                ComboBox combo = new ComboBox();
                                for (Etablissement etablissement : lsevent) {
                                    combo.addItem(etablissement);
                                }
        btnajout = new Button("Modifier");
        f.add(tnom);
        tnom.setHint(event.getNom_evenement());
     //  int aaaa = combo.getSelectedIndex();
        description.setHint(event.getDescription());
        f.add(description);
        f.add(date);
       f.add(combo);
        f.add(btnajout);
//        f.add(btnaff);  
System.out.println(event.getId_evenement());
        btnajout.addActionListener((e) -> {
            if (isInputValid()) {
            ServiceEvenement ser = new ServiceEvenement();
            int day , month , year ;
            day=date.getCurrentDay();
            month=date.getCurrentMonth();
            year=date.getCurrentYear();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String datee = day + "/" +month + "/"+year;
            Date debut = new Date();
            
            try {
                debut = format.parse(datee);
            } catch (ParseException ex) {
                
            }
           etabli=lsevent.get(combo.getSelectedIndex());
           event.setNom_evenement(tnom.getText());
           event.setDescription(description.getText());
           event.setDate_evenement(debut);
           event.setEtab(etabli);
           event.setId_evenement(event.getId_evenement());
  System.out.println(etabli.getId_etablissement());

            ser.Updateeven(event);

            }});
//        btnaff.addActionListener((e)->{
        //Affichage a=new Affichage();
        //a.getF().show();
        //});
    }
private boolean isInputValid() 
    {
        String errorMessage = "";

        if (tnom.getText() == null || tnom.getText().length() == 0) 
        {
            errorMessage += "Champ titre invalide !\n"; 
        }
        if (description.getText() == null || description.getText().length() == 0) 
        {
            errorMessage += "Champ description invalide !\n"; 
        }
      
       
        
        if (errorMessage.length() == 0) 
        {
            return true;
        }
         else 
        {
            Dialog.show("Champs invalides", errorMessage, "OK", null);
            return false;
        }
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
