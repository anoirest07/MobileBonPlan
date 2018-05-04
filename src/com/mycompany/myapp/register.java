/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entities.Utilisateur;
import Services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;

import java.util.Vector;

/**
 *
 * @author user
 */
public class register {
     Form f1;
    TextField nomField;
    TextField preField;
     TextField adrField;
      TextField userField;
       TextField passField;
        TextField emaField;
        Button ajoutuser;
          Vector<String> items1=new Vector<>();

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
          
//        items1.add("a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
//        items1.add("a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
//        items1.add("a:1:{i:0;s:10:\"ROLE_ECOLE\";}");
      //  ComboBox role = new ComboBox(items1);
        ComboBox<String> roleBox=new ComboBox<>("client","proprietaire");
   
      public register(Resources res) {
//          if (verification(nomField,preField, userField, passField,  adrField, emaField)) {
            
   //  f = new Form("home");
      f1 = new Form("registration", new BoxLayout(BoxLayout.Y_AXIS));
        nomField = new TextField("","nom");
        preField = new TextField("","prenom");
         userField = new TextField("","username");
        passField = new TextField("","password");
         
        emaField = new TextField("","email",20,TextArea.EMAILADDR);
                ajoutuser = new Button("ajouter");
                Validator validator = new Validator();
                validator.addSubmitButtons(ajoutuser);
                validator.addConstraint(userField, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid username";}
      });
                
                validator.addConstraint(passField, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid password";}
      });
          
                validator.addConstraint(preField, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid prenom";
          }
      }) ;
               
                validator.addConstraint(emaField, new Constraint() {
          @Override
          public boolean isValid(Object value) {
            return !String.valueOf(value).equals("");
                 
           
          }

          @Override
          public String getDefaultFailMessage() {
              return "invalid email";
          }
      });
                validator.addConstraint(nomField, new Constraint() {
          @Override
          public boolean isValid(Object value) {
               return !String.valueOf(value).equals("");
          }

          @Override
          public String getDefaultFailMessage() {
             return "invalid nom";
          }
      });
                
                 f1.add(nomField);
        f1.add(preField);
f1.add(userField);
f1.add(passField);

f1.add(emaField);
f1.add(roleBox);
f1.add(ajoutuser);

        
        ajoutuser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService uc = new UserService();
                Utilisateur u=new Utilisateur(nomField.getText(), preField.getText(), userField.getText(), passField.getText(), roleBox.getSelectedItem(), emaField.getText());
                uc.ajoutuser(u);
                
            }
        });
            
       f1.getToolbar().addCommandToRightBar("back", null, (ev)->{Authentification l;
         l = new Authentification(res);
         l.getF().show();
         
          });
      }}

//public boolean verification(TextField userField, TextField passField, TextField adrField, TextField emaField, TextField nomField, TextField preField) {
//
//        if (userField.getText() == "") {
//            Dialog.show("Erreur", "Veuillez verifier votre username", "OK", "CANCEL");
//            userField.requestFocus();
//            return false;
//        }
////        if (.getText().length() != 8) {
////            Dialog.show("Erreur", "Veuillez verifier votre CIN", "OK", "CANCEL");
////            cin.requestFocus();
////            return false;
////        }
//
//        if (nomField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifier votre NOM", "OK", "CANCEL");
//            nomField.requestFocus();
//            return false;
//        }
//        if (preField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifier votre Prenom", "OK", "CANCEL");
//            preField.requestFocus();
//            return false;
//        }
//        if (roleBox.getSelectedItem().toString() == null) {
//            Dialog.show("Erreur", "Veuillez verifier votre role", "OK", "CANCEL");
//            roleBox.requestFocus();
//            return false;
//        }
//        if (adrField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifie rvotre ADRESSE", "OK", "CANCEL");
//            adrField.requestFocus();
//            return false;
//        }
////        if (numtel.getText().length() == 0) {
////            Dialog.show("Erreur", "Veuillez verifie rvotre NUMERO", "OK", "CANCEL");
////            numtel.requestFocus();
////            return false;
////        }
////        if (numtel.getText().length() != 8) {
////            Dialog.show("Erreur", "Veuillez verifie rvotre NUMERO", "OK", "CANCEL");
////            numtel.requestFocus();
////            return false;
////        }
//        if (emaField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifier votre E-MAIL", "OK", "CANCEL");
//            emaField.requestFocus();
//            return false;
//        }
//        if (this.passField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifier votre PASSWORD", "OK", "CANCEL");
//            passField.requestFocus();
//            return false;
//        }
//        
//        return true;
//    }
//
//    public boolean verifier(TextField userField, TextField emaField, TextField adrField) {
//
//        if (adrField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifie rvotre ADRESSE", "OK", "CANCEL");
//            adrField.requestFocus();
//            return false;
//        }
//        
//        if (userField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifie rvotre ADRESSE", "OK", "CANCEL");
//            userField.requestFocus();
//            return false;
//        }
//        if (emaField.getText().length() == 0) {
//            Dialog.show("Erreur", "Veuillez verifier votre E-MAIL", "OK", "CANCEL");
//            emaField.requestFocus();
//            return false;
//        }
//
//        return true;
//    }
//
//    public boolean IsNumber(String x) {
//        boolean verif = true;
//        try {
//            Integer.parseInt(x);
//        } catch (NumberFormatException e) {
//            verif = false;
//        }
//        return verif;
//    }
   

