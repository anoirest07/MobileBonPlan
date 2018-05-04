/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etablissement;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import Entities.Etablissement;
import Entities.Favoris;
import Services.ServiceEtablissement;
import Services.ServiceFavoris;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm1;
import java.util.ArrayList;


/**
 *
 * @author Nadia
 */
public class ListeDesEtab extends SideMenuBaseForm1{
    Container f;
    Form f2;
    Label lb1;
    Label lb2,label,lcat,label2;
    SpanLabel lb3,lbadr,lbnom;
    Container c;
    Container c1;
    Container c2;
    Container c3;
    Container cgen;
    Container nadia;
    ImageViewer imgv;
ListModel<String> etabRech = new DefaultListModel<String>();

    
    AutoCompleteTextField ac = new AutoCompleteTextField(etabRech);
    
    public ListeDesEtab(Resources theme) {
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
                                    new Label("Liste des établissements", "Title")
                                )
                            )
                );
        tb.setTitleComponent(titleCmp);
         FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(theme);
       

        ServiceEtablissement serviceTask = new ServiceEtablissement();
        ac.setHint("Chercher un établissement");
        ac.setMinimumElementsShownInPopup(5);
   nadia= new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label Photo2=new Label();
              
                  int deviceWidth4 = Display.getInstance().getDisplayWidth() / 11;
            Image placeholder15 = Image.createImage(deviceWidth4, deviceWidth4); //square image set to 10% of screen width
            EncodedImage encImage11 = EncodedImage.createFromImage(placeholder15, false);
            Photo2.setIcon(URLImage.createToStorage(encImage11,
                    "Large_" + "http://localhost/symfony/web/images/explore.png" 
                    + "", "http://localhost/symfony/web/images/explore.png" 
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
         nadia.add(Photo2);
         nadia.add(ac);
            f.add(nadia);
        ArrayList<Etablissement> lis = serviceTask.getList2();
        for (Etablissement e : lis) {
            if (e.getEnabled() == 1) {
            etabRech.addItem(e.getNom_etablissement());
            ac.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
         
                for (Etablissement et: serviceTask.RechercheEtabParNom(ac.getText()))
                {      f.removeAll();
                 Container Cnt= new Container(new BoxLayout(BoxLayout.X_AXIS));
                 Container Cnt2= new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 Label Photo=new Label();
                 Cnt2.add(et.getNom_etablissement());
                 Cnt2.add(et.getAdresse_etablissement());
                 Cnt2.add(et.getCategorie().getNom_categorie());
                  int deviceWidth3 = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder14 = Image.createImage(deviceWidth3, deviceWidth3); //square image set to 10% of screen width
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder14, false);
            Photo.setIcon(URLImage.createToStorage(encImage1,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + et.getPhoto_etablissement()
                    + "", "http://localhost/symfony/web/uploads/images/" + et.getPhoto_etablissement()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
                Cnt.add(Photo);
                Cnt.add(Cnt2);
                f.add(Cnt);
                  Photo.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   
                        f2= new Form();
                        Toolbar tb= f2.getToolbar();
                      int deviceWidth2 = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder1 = Image.createImage(deviceWidth2, deviceWidth2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder1, false);
            label2.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + et.getPhoto_etablissement()
                    + "", "http://localhost/symfony/web/uploads/images/" + et.getPhoto_etablissement()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));

                        c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        tb.getAllStyles().setBgColor(0x990033);
                        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt2) -> {
                        ListeDesEtab h=new ListeDesEtab(theme);
                          h.show();
                        });
                        ImageViewer img=new ImageViewer();
                        SpanLabel sp=new SpanLabel(et.getNom_etablissement());
                     //   SpanLabel sp1=new SpanLabel("Travaille aujourd'hui à :"+et.getOuverture().toString()+"_"+et.getFermeture().toString());
                        SpanLabel sp2=new SpanLabel("Catégorie :"+et.getCategorie().getNom_categorie());
                        SpanLabel sp3=new SpanLabel(et.getAdresse_etablissement()+","+et.getCode_postal());
                        SpanLabel sp4=new SpanLabel("");

                        if((et.getBudget().equals("Cher"))&&(et.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 150DT à 400DT");}
                        else if((et.getBudget().equals("Faible"))&&(et.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 30DT à 80DT");}
                        else if((et.getBudget().equals("Moyen"))&&(et.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 80DT à 150DT");}
                        else if((et.getBudget().equals("Faible"))){
                        sp4.setText("Budget : De 5DT à 10DT");}
                         else if((et.getBudget().equals("Moyen"))){
                        sp4.setText("Budget : De 10DT à 30DT");}
                          else if((e.getBudget().equals("Cher"))){
                        sp4.setText("Budget : De 30DT à 100DT");}
                        SpanLabel sp5=new SpanLabel("Site web :"+et.getSite_web());
                        SpanLabel sp6=new SpanLabel("Téléphone :+216"+et.getTelephone_etablissement());
                        SpanLabel sp7=new SpanLabel("Description :"+et.getDescription_etablissement());
                        SpanLabel space1= new SpanLabel("                                                  ");
                        SpanLabel space2= new SpanLabel("                                                  ");

                        SpanLabel space3= new SpanLabel("                   ");
                        SpanLabel space4= new SpanLabel("                                                  ");
                        SpanLabel space5= new SpanLabel("                                                  ");
Button favoris = new Button("Ajouter aux favoris");
                         Button produits = new Button("Nos produits");
                          Button offres = new Button("Nos offres");

                        //   c2.setHeight(700);  
               // f2.getAllStyles().setBorder(Border.createLineBorder(3, 0xffa83b));
//                f2.getAllStyles().set3DText(true, false);
//                f2.getAllStyles().setUnderline(true);
//                f2.getAllStyles().setBgTransparency(200);
//                f2.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                       // f2.add(img);
                        c2.add(sp);
                          c2.add(sp3);
                          c2.add(favoris);
                           c2.add(space1);
                       //   c3.add(sp1);
                          
                            c3.add(sp2);
                          
                               c3.add(sp4);
                                
                             
                                  c3.add(sp5);
                                  
                                    c3.add(sp6);
                                      c3.add(sp7);
                                      c3.add(produits);
                                      c3.add(offres);
                                     c3.add(space2);
                                      c3.add(space3);
                                       c3.add(space4);
                                        c3.add(space5);
//                                     
                                       cgen.add(label2);
                                       cgen.add(c2);
                                       f2.add(cgen);
                                       f2.add(c3);
           // c2.setScrollableY(true);                           
                                       f2.show();
                    }
                    
                });
                }
                }
            });
            
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
//        lb3.setY(0););
//        lb3.setX(10);  
//        lb1.setX(10
                 lbnom.setText(e.getNom_etablissement());
                c.add(lbnom);
                c.add(e.getCategorie().getNom_categorie());
                 lbadr.setText(e.getAdresse_etablissement());
                Label space = new Label("           ");
                space.setWidth(900);
                c.add(lbadr);
              //  c.add(space);
               // c.setWidth(500);
         //       c.add(lb3);
                      
                //c1.add(imgv);
               
           label.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   
                        f2= new Form();
                        Toolbar tb= f2.getToolbar();
                      int deviceWidth2 = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder1 = Image.createImage(deviceWidth2, deviceWidth2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder1, false);
            label2.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto_etablissement()
                    + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto_etablissement()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));

                        c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        tb.getAllStyles().setBgColor(0x990033);
                        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, (evt2) -> {
                        ListeDesEtab h=new ListeDesEtab(theme);
                          h.show();
                        });
                        ImageViewer img=new ImageViewer();
                        SpanLabel sp=new SpanLabel(e.getNom_etablissement());
                      //  SpanLabel sp1=new SpanLabel("Travaille aujourd'hui à :"+e.getOuverture().toString()+"_"+e.getFermeture().toString());
                        SpanLabel sp2=new SpanLabel("Catégorie :"+e.getCategorie().getNom_categorie());
                        SpanLabel sp3=new SpanLabel(e.getAdresse_etablissement()+","+e.getCode_postal());
                        SpanLabel sp4=new SpanLabel("");

                        if((e.getBudget().equals("Cher"))&&(e.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 150DT à 400DT");}
                        else if((e.getBudget().equals("Faible"))&&(e.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 30DT à 80DT");}
                        else if((e.getBudget().equals("Moyen"))&&(e.getCategorie().getNom_categorie().equals("hotel"))){
                        sp4.setText("Budget : De 80DT à 150DT");}
                        else if((e.getBudget().equals("Faible"))){
                        sp4.setText("Budget : De 5DT à 10DT");}
                         else if((e.getBudget().equals("Moyen"))){
                        sp4.setText("Budget : De 10DT à 30DT");}
                          else if((e.getBudget().equals("Cher"))){
                        sp4.setText("Budget : De 30DT à 100DT");}
                        SpanLabel sp5=new SpanLabel("Site web :"+e.getSite_web());
                        SpanLabel sp6=new SpanLabel("Téléphone :+216"+e.getTelephone_etablissement());
                        SpanLabel sp7=new SpanLabel("Description :"+e.getDescription_etablissement());
                        SpanLabel space1= new SpanLabel("                                                  ");
                        SpanLabel space2= new SpanLabel("                                                  ");

                        SpanLabel space3= new SpanLabel("                   ");
                        SpanLabel space4= new SpanLabel("                                                  ");
                        SpanLabel space5= new SpanLabel("                                                  ");

                        Button favoris = new Button("Ajouter aux favoris");
                        favoris.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent t) {
                                Favoris favoris = new Favoris();
                                ConnectionRequest con = new ConnectionRequest();
                String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/favjsonnew?id="+
                        Authentification.connectedUser.getId()+"&idEtablissement="+e.getId_etablissement();
                
                
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
                            }
                        }
                        );
                         Button produits = new Button("Nos produits");
                          Button offres = new Button("Nos offres");
                        c2.add(sp);
                          c2.add(sp3);
                           c2.add(favoris);
                           c2.add(space1);
                          
//                          c3.add(sp1);
                          
                            c3.add(sp2);
                          
                               c3.add(sp4);
                                
                             
                                  c3.add(sp5);
                                  
                                    c3.add(sp6);
                                      c3.add(sp7);
                                      c3.add(produits);
                                      c3.add(offres);
                                     c3.add(space2);
                                      c3.add(space3);
                                       c3.add(space4);
                                        c3.add(space5);
//                                      
                                       cgen.add(label2);
                                       cgen.add(c2);
                                       f2.add(cgen);
                                       f2.add(c3);
           // c2.setScrollableY(true);                           
                                       f2.show();
                    }
                    
                });
          
                lb1.setText(e.getNom_etablissement());
                //   lb1.setText(e.getOuverture().toString());
                lb3.setText(e.getAdresse_etablissement());
                lcat.setText(e.getCategorie().getNom_categorie());
               
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
