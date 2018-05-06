/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Produit;
import Etablissement.ListeDesEtab;
import Services.ServiceProduit;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
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
public class AfficheProduitsClient extends SideMenuBaseForm{
    Container f;
    Form  f2;
    Label lb1;
    Label lb2, label;
    SpanLabel lb3;
    Container c;
    Container c1;
    Container c3;
    Container c2;
    ImageViewer imgv;

    public AfficheProduitsClient(Resources res, int idetab) {
        super(BoxLayout.y());
       
        
        Toolbar tb = getToolbar();
         FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_BACK);     
        fab.addActionListener(e -> {          
//            AddPublicite add= new AddPublicite(theme);
//            add.getForm().show();
            ListeDesEtab ao=  new ListeDesEtab(res);
            ao.show();
            
          
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
                                    new Label("Nos Produits", "Title")
                                )
                            )
                );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        setupSideMenu(res);


        ServiceProduit serviceProd = new ServiceProduit();
        ArrayList<Produit> lis = serviceProd.getListClient(idetab);
        for (Produit e : lis) {

            lb1 = new Label();
            lb2 = new Label();
            lb3 = new SpanLabel();
            c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            imgv = new ImageViewer();
            label = new Label();
           c.add(lb1);
            c.add(lb2);
            c.add(lb3);

            c.add(c3);

            //c1.add(imgv);
        
            
       
            lb1.setText(e.getNom_produit());
            //   lb1.setText(e.getOuverture().toString());
            lb3.setText("" + e.getPrix_produit());
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
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto_produit()
                    + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto_produit()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));

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
