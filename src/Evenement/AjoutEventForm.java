/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement;

import Entities.Etablissement;
import Entities.Evenement;
import Services.ServiceEvenement;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.DateSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;



/**
 *
 * @author tanga
 */
public class AjoutEventForm {
   
    Form f;
    TextField tnom;
    
 ImageViewer image;
  
    Button choose ; 
    TextField description;
    DateSpinner date=new DateSpinner();
     ServiceEvenement se = new ServiceEvenement();
     ArrayList<Etablissement> lsevent= se.getListEtab(Authentification.connectedUser.getId());
     
    
    Button btnajout,btnaff;
  Etablissement etabli  ;

    public AjoutEventForm(Resources res) {
        
        f = new Form("Events");
        
        tnom = new TextField();
        description = new TextField();
        ServiceEvenement se = new ServiceEvenement();
                ArrayList<Etablissement> lsevent= se.getListEtab(Authentification.connectedUser.getId());
                ComboBox combo = new ComboBox();
                                for (Etablissement etablissement : lsevent) {
                                    combo.addItem(etablissement);
                                }
        btnajout = new Button("ajouter");
        f.add(tnom);
        tnom.setHint("nom");
     //  int aaaa = combo.getSelectedIndex();
        description.setHint("description");
        f.add(description);
        f.add(date);
       f.add(combo);
        f.add(btnajout);




 choose=new Button("choose image");

                 f.add(choose);
        image =new ImageViewer();
        
                  f.add(image);

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceEvenement s=new ServiceEvenement();
           s.browseImage(image);
          
            }
        });

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
            Evenement t = new Evenement(tnom.getText(),description.getText(),debut, etabli,image.getImage().getImageName());
  System.out.println(etabli.getId_etablissement());

            ser.ajouteven(t);
                        AffichagePropEvenForm pc = new AffichagePropEvenForm(res);
                            pc.show();
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