/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Publicite.AffichageClient;
import Publicite.PubliciteController;
import Entities.Utilisateur;
import Services.UserService;
import com.codename1.components.SpanButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author user
 */
public class Authentification {

    public static Utilisateur connectedUser;
    Form f;
    Container icon;
    Container data;
    TextField username;
    TextField password;
    SpanButton login;
    SpanButton reset;
    SpanButton fb;
    Label img;
    String role;
    Integer userId;
    EncodedImage enc;

    public Authentification(Resources theme) {
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        
        f = new Form(BoxLayout.y());
        Image imu = URLImage.createToStorage(enc, "/img/frfr.jpg", "http://localhost/img.jpg", URLImage.RESIZE_SCALE);
        f.setLayout(new LayeredLayout());
        f.getToolbar().hideToolbar();
        Label l = new Label("");
        l.getUnselectedStyle().setBgImage(imu);
        l.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        l.getUnselectedStyle().setBgTransparency(255);
        l.setSelectedStyle(l.getUnselectedStyle());
        f.addComponent(l);
        icon = new Container();
        data = new Container();
        username = new TextField();
        username.setHint("Username");
        password = new TextField();
        password.setHint("Password");
        password.setConstraint(TextField.PASSWORD);
        login = new SpanButton("LOGIN");

        reset = new SpanButton("RESET");
        reset.addActionListener(e -> {
            username.clear();
            password.clear();
        });
        //username.setDisabledStyle(TextField);

        data.add(username);
        data.add(password);
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest con = new ConnectionRequest();
                String name = username.getText();
                String pswd = password.getText();

                con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/login1/" + name + "/" + pswd);

                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        try {
                            String json = new String(con.getResponseData());
                            JSONParser j = new JSONParser();

                            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            System.out.println("roleeeee" + users.get("id") + users.get("nom"));
                            userId = (int) Float.parseFloat(users.get("id").toString());
                            connectedUser = new Utilisateur();
                            UserService us = new UserService();
                            connectedUser = us.GetUserById(userId);
                            System.out.println(userId + "allalala");
                            if (users.get("password").equals("0")) {
                                Dialog.show("Erreur d'authentification", "Verifier votre Nom d'utilisateur ou mot de passe!!", "OK", "Annuler");

                            } else {
                                //boolean x = (connectedUser.getRoles().equals("ROLE_PROPRIETAIRE"+","+" "+"ROLE_USER"));
                                // boolean x = (connectedUser.getRoles().equals("roles"));

                                boolean x = (connectedUser.getRoles().equals("[ROLE_PROPRIETAIRE" + "," + " " + "ROLE_USER]"));
                                System.out.println(x);
                                if (x) {
                                    new PubliciteController(theme).show();
                                } else {
                                    new AffichageClient(theme).show();
                                }

                            }

                        } catch (IOException ex) {
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(con);

            }
        });

        data.add(login);
        data.add(reset);
         Button reg = new Button("s'inscrire ");
        reg.addActionListener(e->{
        new register(theme).getF1().show();
        
        });

//        Label reg = new Label("Register ?");
//        reg.addPointerPressedListener(e->{
//        Register h = new Register(theme);
//        h.getF().show();
//        
//        });
//      
             data.add(reg);
        f.add(data);

//        UserService us = new UserService();
//        System.out.println("lé");
//        us.GetUserById(2);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
