/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etablissement;

import Entities.Categorie;
import Entities.CriteresEvaluation;
import Entities.Etablissement;
import Entities.Utilisateur;
import Services.ServiceCategorie;
import Services.ServiceEtablissement;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.TimeSpinner;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.mycompany.myapp.Authentification;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Nadia
 */
public class AjouterEtab {
    private static final  String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
        Form f;
        int i;
        final DefaultListModel<String> options = new DefaultListModel<>();
    TextField tnom;
    TextField tadr;
    TextField tdesc;
    TextField ttel;
    ComboBox<String> tbudg;
    TextField tcode;
    TextField tphoto;
    TextField tphotopat;
    TextField tlong;
    TextField tlat;
    TextField tsite;
    ComboBox<String>tcat;
    DateTimeSpinner touver;
    DateTimeSpinner tferm;
    Label lb1,lb2,lb3,lb4,lb5,test;
    Container cont,cont1,cont2,containerCrit;
    Button btnajout,btnaff,btnautre;
     private String newfilePath = "";
    ServiceCategorie sc= new ServiceCategorie();
    public AjouterEtab(Resources theme){
        
     f = new Form("Ajouter un établissement");
        tnom = new TextField("","Nom établissement");
                 Label label = new Label();

           tdesc = new TextField("","Saisissez une description détaillée");
              tdesc. setSingleLineTextArea(false); 
              ttel = new TextField("","Téléphone:+216");
              cont1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
              Label l2= new Label("Budget:");
               lb1=new Label();
              tbudg = new ComboBox<>("Faible","Moyen","Cher");
                    tcode = new TextField("","Code Postal");
                       tphoto = new TextField("","phot");
                   
                              Button btnOpen = new Button("Choisir Image");
          
                       tphotopat = new TextField("","phot2");
                             tlong = new TextField("","longitude");
                                tlat = new TextField("","latitude");
                                tsite = new TextField("","www.exemple.tn");
                                    touver = new DateTimeSpinner();
                                    tferm = new DateTimeSpinner();
                                  cont = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                 Container conten = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                             
                                    tcat= new ComboBox<>();
                                    Label l= new Label("Catégorie:");
                                    Label l3= new Label("");
                                     Label l4= new Label("");
                                      Label l5= new Label("     ");
                                    cont.add(l);                                   
                                    cont.add(l3);
                                    cont.add(l4);
                                    cont.add(l5);
                                    cont.add(tcat);
                                     for(Categorie c : sc.getListCat()){
                                    if(c.getEnabled()==1)
                                    { tcat.addItem(c.getNom_categorie());}
                                        }
                                  cont2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                     

                                 //  tcat.setWidth(200);
                              tbudg.setWidth(600);
                          Label l6= new Label("                ");
        cont1.add(l2);  
        cont1.add(l6);  
                    cont1.add(tbudg);
                           AutoCompleteTextField ac = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if(text.length() == 0) {
                    return false;
                }
                String[] l = searchLocations(text);
                if(l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for(String s : l) {
                    options.addItem(s);
                    
                }
                return true;
            }

        };
        ac.setMinimumElementsShownInPopup(5);
        ac.setHint("Adresse(Rue,Avenue...)");
        btnajout = new Button("Ajouter");
        btnaff=new Button("Annuler");
        cont2.add(btnajout);
        cont2.add(btnaff);
      
        f.add(tnom);
        f.add(ac);
      
          f.add(tcode);
         
            f.add(touver);
            f.add(tferm);
     
               tcat.addActionListener( new ActionListener() {
                                    @Override
                public void actionPerformed(ActionEvent evt) {
                  
                                            for(Categorie cat: sc.getCaetgParNom(tcat.getSelectedItem())){
                                        i=cat.getId_categorie();
                                                System.out.println("++"+i);
            
                          ArrayList<CriteresEvaluation>   criteres=   sc.getCritereParCateg(i);
                          
                          System.out.println("Tchetcheee"+criteres);
            conten.add("Vous serez évalué sur:");
                          for(CriteresEvaluation crit : criteres)
                                 
                             {      
                int deviceWidth2 = Display.getInstance().getDisplayWidth() / 11;
            Image placeholder1 = Image.createImage(deviceWidth2, deviceWidth2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder1, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/images/star.png" 
                    + "", "http://localhost/symfony/web/images/star.png" 
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
            Container ccccc= new Container(BoxLayout.x());
           Label Text= new Label();
           Text.setText(crit.getNom_critere_evaluation());
         
          ccccc.add(crit.getNom_critere_evaluation());
           
            
            conten.add(ccccc);
                                      
                                
                             }
                                            }
         }
     }
                                   );
               
        
         f.add(tdesc);
          f.add(ttel);
         
          f.add(cont1);
             f.add(tphoto);
              f.add(tphotopat);
               f.add(tlong);
                f.add(tlat);
                 f.add(tsite);
                    
                          f.add(cont);
                           f.add(conten);
                              f.add(cont2);
                 btnajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                   if(tnom.getText().equals("") ||ttel.getText().equals("")||tdesc.getText().equals("")||ac.getText().equals("")){
          Dialog.show("Erreur", "Champ Invalide !","OK",null);
                 }
                   else{
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/AjouterEtablissementMobile/"+Authentification.connectedUser.getId()+"/"+i+"?nomEtablissement=" + tnom.getText() 
                        + "&adresseEtablissement=" + ac.getText() 
                        + "&descriptionEtablissement=" + tdesc.getText() 
                        + "&codePostal=" + tcode.getText() 
                        + "&budget=" + tbudg.getSelectedItem()
                        + "&photoEtablissement=" + tphoto.getText()       
                        + "&photoPatente=" + tphotopat.getText() 
                        + "&siteWeb=" + tsite.getText() 
                        + "&latitude=" + tlat.getText() 
                        + "&longitude=" + tlong.getText() 
                        + "&telephoneEtablissement=" + ttel.getText() 
                        + "&fermeture=" + tferm.getCurrentHour()+":"+tferm.getCurrentMinute()
                        + "&ouverture=" + touver.getCurrentHour()+":"+touver.getCurrentMinute()
                     
                );
                   
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        // if (s.equals("success")) {
                             Dialog.show("Ajout effectué", "Votre établissement a été ajouté", "Ok", null);
                               MesEtabs h = new MesEtabs(theme);
        h.getF().show();
                        //}
                    }
                });

                NetworkManager.getInstance().addToQueueAndWait(req);
                   }
            }
        });
                System.out.println(i);
    
        btnaff.addActionListener((e)->{
        MesEtabs a=new MesEtabs(theme);
        a.getF().show();
        });
     
   //  f.add(containerCrit);  
    }
      String[] searchLocations(String text) {        
        try {
            if(text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apiKey);
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                System.out.println("+++jnqdnqj"+result.values());
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
      
            }
        } catch(Exception err) {
            Log.e(err);
        }
        return null;
    }
      
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
