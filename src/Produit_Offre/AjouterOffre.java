/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Etablissement;
import Entities.Produit;
import Entities.Utilisateur;
import Services.ServiceEtablissement;
import Services.ServiceOffre;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import javafx.stage.FileChooser;

/**
 *
 * @author Nadia
 */
public class AjouterOffre {

    Form f;
    int i;
    TextField tfnomoff;
    TextField tfdescoff;
    TextField tfimgoff;
    Button addProd;
    Picker pickDateDebut;
    Picker pickDateFin;
//    TimeSpinner touver;
//    TimeSpinner tferm;
    Container cont, cont1, cont2;
    Button btnajout, btnaff, btnautre;
    private String newfilePath = "";
    ServiceOffre so = new ServiceOffre();
    ServiceEtablissement se = new ServiceEtablissement();

    public AjouterOffre(Resources res) {
        f = new Form("Créer une Offre");
        tfnomoff = new TextField("", "Titre Offre");
        tfdescoff = new TextField("", "Description");
        tfimgoff = new TextField("", "image");
        addProd = new Button("Ajouter un Poduit à votre Offre");
        pickDateDebut = new Picker();
        pickDateFin = new Picker();

        cont1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Button btnOpen = new Button("Choisir Image");

//                       touver = new TimeSpinner();
//                                    tferm = new TimeSpinner();
        cont = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("      ");
        cont.add(l3);
        cont.add(l4);
        cont.add(l5);

        ArrayList<Etablissement> petabs = se.getListEtab();

        ComboBox combo = new ComboBox();

        for (Etablissement etablissement : petabs) {

            combo.addItem(etablissement.getNom_etablissement());

        }

        cont2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        //  tcat.setWidth(200);
//        cont1.add(cetab);
        btnajout = new Button("Ajouter");
        btnaff = new Button("Annuler");
        cont2.add(btnajout);
        cont2.add(btnaff);
        f.add(tfnomoff);
        f.add(tfdescoff);
        f.add(pickDateDebut);
        f.add(pickDateFin);
        f.add(addProd);
        f.add(combo);
        f.add(cont);
        f.add(cont1);

        // f.add(touver);
        // f.add(tferm);
        // f.add(iduser);
        f.add(cont2);

        btnajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((pickDateFin.getTime() < pickDateDebut.getTime())) {
                    ToastBar.showErrorMessage("Try again please!\nThe start time cannot be bigger than the end time.");

                    System.out.println("777777777777777777777777");
                    System.out.println((int) (pickDateFin.getTime() - pickDateDebut.getTime()));
                    System.out.println("777777777777777777777777");

                } else {

                    System.out.println("444444444444444444444444444");
                    System.out.println((int) (pickDateFin.getTime() - pickDateDebut.getTime()));
                    System.out.println("444444444444444444444444444");


                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/createoffre"
                        + "/" + tfnomoff.getText()
                        + "/" + tfimgoff.getText()
                        + "/" + tfdescoff.getText()
                        + "/" + pickDateDebut.getTime()
                        + "/" + pickDateFin.getTime()
                        + "/" + getidetab(combo.getSelectedIndex())
                );

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        // if (s.equals("success")) {
                        Dialog.show("Confirmation", "ajout ok", "Ok", null);

                        AfficheOffres a = new AfficheOffres(res);
                        a.show();
                        //}

                    }
                    
                });

                NetworkManager.getInstance().addToQueueAndWait(req);

            }
                }

        });
        System.out.println(i);

        btnaff.addActionListener((e) -> {
            AfficheOffres a = new AfficheOffres(res);
            a.show();
        });

    }

    public int getidetab(int idarray) {

        ArrayList<Etablissement> petabs = se.getListEtab();

        return petabs.get(idarray).getId_etablissement();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
