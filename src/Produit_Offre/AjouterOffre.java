/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Etablissement;
import Entities.Produit;
import Services.ServiceEtablissement;
import Services.ServiceOffre;
import Services.ServiceProduit;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;

/**
 *
 * @author Nadia
 */
public class AjouterOffre {

    Form f;
    int i;
    TextField tfnomoff;
    TextField tfdescoff;
    
    DateSpinner pickDateDebut;
    DateSpinner pickDateFin;
    ImageViewer image;

    Button choose;

//    TimeSpinner touver;
//    TimeSpinner tferm;
    Container cont, cont1, cont2;
    Button btnajout, btnaff, btnautre;
    private String newfilePath = "";
    ServiceOffre so = new ServiceOffre();
    ServiceProduit sp = new ServiceProduit();
    ServiceEtablissement se = new ServiceEtablissement();

    public AjouterOffre(Resources res) {
        f = new Form("Créer une Offre");
        tfnomoff = new TextField("", "Titre Offre");
        tfdescoff = new TextField("", "Description");
        pickDateDebut = new DateSpinner();
        pickDateFin = new DateSpinner();

        ComboBox comboprod = new ComboBox();

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

        ArrayList<Etablissement> petabs = se.MesEtabs(Authentification.connectedUser.getId());

        ComboBox combo = new ComboBox();

        for (Etablissement etablissement : petabs) {

            combo.addItem(etablissement.getNom_etablissement());

        }

        ArrayList<Produit> prodoffre = sp.getList2(Authentification.connectedUser.getId());
        System.out.println("///>>>>>>>>>>>" + prodoffre);

        for (Produit pdts : prodoffre) {

            comboprod.addItem(pdts.getNom_produit());

        }

        cont2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        //  tcat.setWidth(200);
//        cont1.add(cetab);
        choose = new Button("choose image");

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceOffre s = new ServiceOffre();
                s.browseImage(image);

            }
        });
        btnajout = new Button("Ajouter");
        btnaff = new Button("Annuler");

        btnajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                int dday, dmonth, dyear;
                dday = pickDateDebut.getCurrentDay();
                dmonth = pickDateDebut.getCurrentMonth();
                dyear = pickDateDebut.getCurrentYear();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String datee = dday + "/" + dmonth + "/" + dyear;
                Date debut = new Date();

                try {
                    debut = format.parse(datee);
                } catch (ParseException ex) {

                }

                int fday, fmonth, fyear;
                fday = pickDateDebut.getCurrentDay();
                fmonth = pickDateDebut.getCurrentMonth();
                fyear = pickDateDebut.getCurrentYear();
                SimpleDateFormat fformat = new SimpleDateFormat("dd/MM/yyyy");
                String fdatee = fday + "/" + fmonth + "/" + fyear;
                Date fin = new Date();

                try {
                    fin = format.parse(datee);
                } catch (ParseException ex) {

                }
                so.ajouterOffre(tfnomoff.getText(), image.getImage().getImageName(), tfdescoff.getText(), debut,
                        fin, getidetab(combo.getSelectedIndex()));

                AfficheOffres a = new AfficheOffres(res);
                a.show();

            }

        });

        System.out.println(i);

        btnaff.addActionListener((e) -> {
            AfficheOffres a = new AfficheOffres(res);
            a.show();
        });
Label lbldd= new Label("Date Début: ");
Label lbldf= new Label("Date Fin: ");
        cont2.add(btnajout);
        cont2.add(btnaff);
        f.add(tfnomoff);
        f.add(tfdescoff);
        f.add(lbldd);
        f.add(pickDateDebut);
                f.add(lbldf);

        f.add(pickDateFin);
        f.add(combo);
        f.add(choose);
        image = new ImageViewer();

        f.add(image);

        f.add(comboprod);
        f.add(cont);
        f.add(cont1);

        // f.add(touver);
        // f.add(tferm);
        // f.add(iduser);
        f.add(cont2);

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
