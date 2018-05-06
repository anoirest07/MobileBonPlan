/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etablissement;

import Entities.Categorie;
import Entities.CriteresEvaluation;
import Entities.Etablissement;
import Produit_Offre.AfficheOffreClient;
import Produit_Offre.AfficheOffres;
import Produit_Offre.AfficheProduits;
import Produit_Offre.AfficheProduitsClient;
import Publicite.AddPublicite;
import Services.ServiceCategorie;
import Services.ServiceEtablissement;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nadia
 */
public class MesEtabs extends SideMenuBaseForm{
    Container f;
      Form f2;
    Label lb1,photooo;
    Label lb2,label,lcat,label2;
    SpanLabel lb3,lbadr,lbnom;
    Container c;
    Container c1;
    Container c2;
    Container c3;
    Container cgen;
    Container nadia;
   private EncodedImage enc;
    private Image img;
    private ImageViewer imgv;
    String reponse;
    String url ;
    int i;
    TextField tnom;
    TextField tadr;
    TextField tdesc;
    
    ComboBox<String> tbudg;
    TextField tcode;
    TextField tphoto;
    TextField tphotopat;
    TextField tlong;
    TextField tlat;
    TextField tsite;
    ComboBox<String>tcat;
<<<<<<< HEAD
    DateTimeSpinner touver;
    DateTimeSpinner tferm;
 Button produits = new Button("Nos produits");
                          Button offres = new Button("Nos offres");
=======
    Picker touver;
    Picker tferm;
  
>>>>>>> e849650ff3f5d6deccaf084cdbf726bf3a6006dd
    Container cont,cont1,cont2,containerCrit;
    Button btnajout,btnaff,btnautre;
     private String newfilePath = "";
    ServiceCategorie sc= new ServiceCategorie();
    public MesEtabs(Resources theme){
        
       super(BoxLayout.y());
        Toolbar tb = getToolbar();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);     
        fab.addActionListener(e -> {          
//            AddPublicite add= new AddPublicite(theme);
//            add.getForm().show();
          AjouterEtab ae=  new AjouterEtab(theme);
              ae.getF().show();
              //Form f5 = ae.getF();
//            Toolbar tg = f5.getToolbar();
//            tg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
//            {
//            
//            new MesEtabs(theme).show();
//             });
        });
        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(l -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        
       
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Mes Etablissements", "Title")
                                )
                            )
                );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        setupSideMenu(theme);

        ServiceEtablissement serviceTask = new ServiceEtablissement();
        ArrayList<Etablissement> lis = serviceTask.MesEtabs(Authentification.connectedUser.getId());
       for (Etablissement e : lis) {
            if (e.getEnabled() == 1) {
               produits.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                
                
               AfficheProduits apc= new  AfficheProduits(theme);
                   apc.show();
                
                }});
                
                 offres.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                
                
               AfficheOffres apc= new  AfficheOffres(theme);
                   apc.show();
                
                }});
                lb1 = new Label();
                lb2 = new Label();
                 lcat = new Label();
               
                lb3 = new SpanLabel();
                
                lbadr = new SpanLabel();
               // lbadr.setWidth(700);
               // lbadr.setHeight(100);
                lbnom = new SpanLabel();
                c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                imgv = new ImageViewer();
                c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                cgen = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        SpanLabel space6= new SpanLabel("              ");
               
                label =new Label();
                   
                label2 =new Label();
                
//        lb1.setY(0);
//        lb3.setY(0);
//        lb1.setX(10);
//        lb3.setX(10);  
                 lbnom.setText(e.getNom_etablissement());
                c.add(lbnom);
//                c.add(e.getCategorie().getNom_categorie());
                 lbadr.setText(e.getAdresse_etablissement());
                Label space = new Label("           ");
                space.setWidth(900);
                lcat.setText(e.getCategorie().getNom_categorie());
                c.add(lcat);
                c.add(lbadr);
               c.add(space);
   
           label.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   
                        f2= new Form(BoxLayout.y());
                    photooo = new Label();
                    photooo.addPointerPressedListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                           serviceTask.suppTask(e.getId_etablissement());
                                    Dialog.show("", "Suppression effectuée", "ok", null);
                            MesEtabs h = new MesEtabs(theme);
        h.show();
                            }
                        });
                        touver = new Picker();
                    tferm= new Picker();
                     int deviceWidth2 = Display.getInstance().getDisplayWidth() /5;
            Image placeholder1 = Image.createImage(deviceWidth2, deviceWidth2); //square image set to 10% of screen width
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
            photooo.setIcon(URLImage.createToStorage(encImage1,
                    "Large_" + "http://localhost/symfony/web/images/garbage.png" 
                    + "", "http://localhost/symfony/web/images/garbage.png"
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
                   photooo.getAllStyles().setPaddingLeft(35);
                    tnom= new TextField(e.getNom_etablissement());
                    tnom. setSingleLineTextArea(false); 
                     tdesc=new TextField();
                     tdesc. setSingleLineTextArea(false); 
                     tdesc.setText(e.getDescription_etablissement());
                          TextField   ttel = new TextField(""+e.getTelephone_etablissement(),TextField.NUMERIC);
                 ttel.setVisible(true);
                    System.out.println("Lalalala"+e.getSite_web());
                     tsite = new TextField(e.getSite_web());
                          TextField   up = new TextField(""+e.getPhoto_etablissement());
                  
                   tbudg = new ComboBox<>("Faible","Moyen","Cher");
                     
    cont1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
              Label l2= new Label("Budget:");
                    tcode = new TextField(""+e.getCode_postal(),TextField.NUMERIC);
            
                    
                                  cont = new Container(new BoxLayout(BoxLayout.X_AXIS));

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
                  
                          Label l6= new Label("                ");
        cont1.add(l2);  
        cont1.add(l6);  
        cont1.add(tbudg);
                     btnajout = new Button("Modifier");
                     Button back= new Button("Annuler");
                     touver.setType(Display.PICKER_TYPE_TIME);
                     tferm.setType(Display.PICKER_TYPE_TIME);
      Label n1=new Label("Nom établissement:");
        Label n2=new Label("Code Postal:");
          Label n3=new Label("Description:");
           Label n4 = new Label("Téléphone:");
              Label n5=new Label("Site web:");
                 Label n6=new Label("Horaires Ouverture:");
                 Label n7=new Label("Horaires Fermeture:");
     tcat.addActionListener( new ActionListener() {
                                    @Override
                public void actionPerformed(ActionEvent evt) {
                  
                                            for(Categorie cat: sc.getCaetgParNom(tcat.getSelectedItem())){
                                        i=cat.getId_categorie();
                                                System.out.println("++"+i);
           
                                            }
         }
     }
                                   );
     back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                   MesEtabs h = new MesEtabs(theme);
        h.show();
                            }
                        });
     f2.add(photooo);
        f2.add(n1);
                f2.add(tnom);
                 f2.add(n2);
          f2.add(tcode);
          f2.add(n3);
       f2.add(tdesc);
      
          f2.add(cont1);
//             f2.add(tphoto);
             f2.add(n5);
                 f2.add(tsite);
                
             f2.add(n4);
                 f2.add(ttel);
                          f2.add(cont);
                           
             f2.add(n6);
                          f2.add(touver);
                           f2.add(n7);
               f2.add(tferm);
                f2.add(produits);
                                      f2.add(offres);              
               
               f2.add(btnajout);
                              f2.add(back);
                              f2.show();
                              
                 btnajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                  
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/UpdateEtablissementMobile/"+e.getId_etablissement()+"/"+Authentification.connectedUser.getId()+"/"+i+"/"+e.getAdresse_etablissement()+"/"+e.getLat()
                        +"/"+e.getLong()+"/"+e.getPhoto_patente()
                        +"?nomEtablissement=" + tnom.getText() 
                        + "&descriptionEtablissement=" + tdesc.getText() 
                        + "&codePostal=" + tcode.getText() 
                        + "&budget=" + tbudg.getSelectedItem()
                      //  + "&photoEtablissement=" + up.getText()       
                        + "&siteWeb=" + tsite.getText() 
                        + "&telephoneEtablissement=" + ttel.getText() 
                        + "&fermeture=" + tferm.getText()
                        + "&ouverture=" + touver.getText()
                     
                );

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        // if (s.equals("success")) {
                             Dialog.show("Modification enregistrée", "Votre établissement a été modifié", "Ok", null);
                         
                             new MesEtabs(theme).show();
                             //}  
                    }
                });

                NetworkManager.getInstance().addToQueueAndWait(req);

            }
        });
                    }
           });
          
              
                c1.add(label);
               // c1.setWidth(500);
                c1.add(c);
                c1.add(space6);
              
                f.addComponent(c1);
                
              c.getAllStyles().setPadding(Component.LEFT, 30);
              c.getAllStyles().setPadding(Component.RIGHT, 0);
               
              c1.getAllStyles().setPadding(Component.RIGHT, 0);
                c1.getAllStyles().setBorder(Border.createLineBorder(1, 0x990033));
                System.out.println("++ddd"+c1.getWidth());
                c.getAllStyles().setTextDecoration(1, true);
                 int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto_etablissement()
                    + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto_etablissement()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
   // System.out.println(e.getBudget());
//              c.setWidth(500);
//                c1.setWidth(500);
            }
        }
       add(f);
    }
//      public Form getF() {
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
