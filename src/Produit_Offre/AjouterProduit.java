/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Etablissement;
import Services.ServiceEtablissement;
import Services.ServiceProduit;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Nadia
 */
public class AjouterProduit {

    Form f;
    int i;
    TextField tfnomp;
    TextField tfprixp;
    TextField tfimgp;

//    TimeSpinner touver;
//    TimeSpinner tferm;
    Container cont, cont1, cont2;
    Button btnajout, btnaff, btnautre;
    private String newfilePath = "";
    ServiceProduit sp = new ServiceProduit();
    ServiceEtablissement se = new ServiceEtablissement();

    public AjouterProduit(Resources res) {
        f = new Form("Créer un Produit");
        tfnomp = new TextField("", "Nom Produit");
        tfprixp = new TextField("", "prix");
        tfimgp = new TextField("", "image");

        cont1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Button btnOpen = new Button("Choisir Image");

//                       touver = new TimeSpinner();
//                                    tferm = new TimeSpinner();
        cont = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("     ");
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
        f.add(tfnomp);
        f.add(tfprixp);
        f.add(tfimgp);
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
               
              
                             sp.ajouterProduit(tfnomp.getText(),tfimgp.getText(), Double.valueOf(tfprixp.getText()), 
                              getidetab(combo.getSelectedIndex()));

                        
                        
                 
                    }
              
        });
        System.out.println(i);

        btnaff.addActionListener((e) -> {
            AfficheProduits a = new AfficheProduits(res);
            a.show();
        });

    }

    public int getidetab(int idarray) {

        ArrayList<Etablissement> petabs = se.getListEtab();

      return  petabs.get(idarray).getId_etablissement();
       
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
