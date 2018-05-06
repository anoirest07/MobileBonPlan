/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;



/**
 *
 * @author user
 */
public class MonProfilProp extends SideMenuBaseForm{

    Container f;
    String imgPath;
   

    

    public MonProfilProp(Resources res) {
        super(BoxLayout.y());
         Toolbar tb = getToolbar();
        f =new Container(new BoxLayout(BoxLayout.Y_AXIS));
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Modifier Profil", "Title")
                                )
                            )
                );
        tb.setTitleComponent(titleCmp);
         FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu(res);
        
        
        
        Container cc = new Container(BoxLayout.y());
        cc.getAllStyles().setAlignment(Component.LEFT, true);
        cc.getAllStyles().setMarginTop(15);

        Label nom = new Label("Nom:");
        nom.setUIID("CustomLabelLeft");
        TextField nomT = new TextField("", "Votre nom");
        nomT.setUIID("TextFieldUnderlined");

        nomT.setText(Authentification.connectedUser.getNom());

        Label prenom = new Label("Prenom:");
        prenom.setUIID("CustomLabelLeft");
        TextField prenomT = new TextField("", "Votre prenom");
        prenomT.setUIID("TextFieldUnderlined");

        prenomT.setText(Authentification.connectedUser.getPrenom());

        Label email = new Label("Email:", "CustomLabelLeft");
        TextField emailT = new TextField("votre eamil");
        emailT.setUIID("TextFieldUnderlined");

        emailT.setText(String.valueOf(Authentification.connectedUser.getEmail()));
  System.out.println("  ++++  "+String.valueOf(Authentification.connectedUser.getEmail()));
        

        Label username = new Label("Username:", "CustomLabelLeft");
        TextField usernameT = new TextField("", "Votre cin");
        usernameT.setUIID("TextFieldUnderlined");
        usernameT.setText(String.valueOf(Authentification.connectedUser.getUsername()));
      
        
        
        

//        Label datenaiss = new Label("Date de naissance:", "CustomLabelLeft");
//        Picker datenaissV = new Picker();
//        datenaissV.setType(Display.PICKER_TYPE_DATE);
////        Calendar datenaissV = new Calendar();
////        datenaissV.setYearRange(1930, 1999);  
//        datenaissV.setUIID("TextFieldUnderlined");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//
//            datenaissV.setDate(sdf.parse(ConnectedUser.getConnected().getDate_de_naissance()));
//        } catch (ParseException ex) {
//        }
        
        usernameT.setEnabled(false);
        
       // datenaissV.setEnabled(false);
        Button Modifier = new Button("Mettre à jour");
        Modifier.getAllStyles().setMarginRight(5);
        Modifier.getAllStyles().setMarginLeft(5);
        FontImage.setMaterialIcon(Modifier, FontImage.MATERIAL_ACCOUNT_BOX);
      //  cc.addAll( username, usernameT, nom, nomT, prenom, prenomT, email, emailT,password,passwordT, Modifier);
        cc.add(username);
        cc.add(usernameT);
        cc.add(nom);
        cc.add(nomT);
        cc.add(prenom);
        cc.add(prenomT);
        cc.add(email);
        cc.add(emailT);
        
        cc.add(Modifier);
        f.add(cc);
        add(f);
        UserService us = new UserService();
        Modifier.addActionListener((evt) -> {
            us.update(Authentification.connectedUser.getId(), nomT.getText(), prenomT.getText(), emailT.getText());
            Dialog.show("Succès!", "Informations modifiés avec succès!", "OK", null);
            f.refreshTheme();
            f.revalidate();
            
        });
    }

//    public Form getF() {
//        return f;
//    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
