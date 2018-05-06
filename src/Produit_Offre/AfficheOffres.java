/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Offre;
import Services.ServiceOffre;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class AfficheOffres extends SideMenuBaseForm{
Container f;
    Form  f2;
    Label lb1;
    Label lb3, lb4, label;
    SpanLabel lb2;
    Container c;
    Container c1;
    Container c2;
    ImageViewer imgv;
    Button btnajout;

    public AfficheOffres(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);     
        fab.addActionListener(e -> {          
//            AddPublicite add= new AddPublicite(theme);
//            add.getForm().show();
            AjouterOffre ao=  new AjouterOffre(res);
            ao.getF().show();
            Form f5 = ao.getF();
            Toolbar tg = f5.getToolbar();
            tg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            new AfficheOffres(res).show();
             });
        });
    
        btnajout = new Button("Ajouter Offre");
        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(l -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        
       
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Mes Offres", "Title")
                                )
                            )
                );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        setupSideMenu(res);
        

        ServiceOffre serviceOff = new ServiceOffre();
        ArrayList<Offre> lis = serviceOff.getList2(Authentification.connectedUser.getId());
//        f.add(btnajout);
//        btnajout.addActionListener((e) -> {
//            AjouterProduit a = new AjouterProduit();
//            a.getF().show();
//        });
                    

         
for (Offre e : lis) {
        Button details = new Button("Détails");
        Button deloff = new Button("Supprimer");

            lb1 = new Label();
            lb2 = new SpanLabel();
            lb3 = new Label();
            lb4 = new Label();
            c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            imgv = new ImageViewer();

            label = new Label();
//        lb1.setY(0);
//        lb3.setY(0);
//        lb1.setX(10);
//        lb3.setX(10);  
            c.add(lb1);
            c.add(lb2);
            c.add(lb3);
            c.add(lb4);
            c2.add(details);
            c2.add(deloff);
            c.add(c2);
//c1.add(imgv);


  deloff.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
            ServiceOffre so = new ServiceOffre();
                    so.supprimerOffre(e);
            f.revalidate();
                    new AfficheOffres(res).show();
                
                }
            });
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                    Toolbar tb = f2.getToolbar();
                    c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    tb.getAllStyles().setBgColor(0x990033);
                    tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt2) -> {
                        AfficheOffres h = new AfficheOffres(res);
                        h.show();
                    });
                    ImageViewer img = new ImageViewer();
                    int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                    Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                    img.setImage(URLImage.createToStorage(encImage,
                            "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto()
                            + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto()
                            + "", URLImage.RESIZE_SCALE_TO_FILL));
                    SpanLabel sp = new SpanLabel("Titre Offre: " + e.getTitre_offre());
                    SpanLabel sp1 = new SpanLabel("Date Début: " + e.getDate_debut());
                    SpanLabel sp2 = new SpanLabel("Date Fin: " + e.getDate_fin());
                    SpanLabel sp3 = new SpanLabel("Description: " + e.getDescription());

                    //   c2.setHeight(700);  
                    // f2.getAllStyles().setBorder(Border.createLineBorder(3, 0xffa83b));
                    f2.getAllStyles().set3DText(true, false);
                    f2.getAllStyles().setUnderline(true);
                    f2.getAllStyles().setBgTransparency(200);
                    f2.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                    f2.add(img);
                    f2.add(sp);  
                    f2.add(sp3);

                    f2.add(sp1);
                    f2.add(sp2);

                    f2.show();
                }

            });
            lb1.setText(e.getTitre_offre());
            lb2.setText(e.getDescription());
            lb3.setText("Du: " + e.getDate_debut());
            lb4.setText("Au: " + e.getDate_fin());
            c1.add(label);
            c1.add(c);
            f.addComponent(c1);
            //  c.getAllStyles().setPadding(Component.LEFT, 100);
            //  c1.getAllStyles().setPadding(Component.RIGHT, 400);
            c1.getAllStyles().setBorder(Border.createLineBorder(1, 0x990033));
            c1.setWidth(1200);
            c.getAllStyles().setTextDecoration(1, true);
            int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto()
                    + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));

        }
        add(f);

    }


    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
       //To change body of generated methods, choose Tools | Templates.
    }
}
