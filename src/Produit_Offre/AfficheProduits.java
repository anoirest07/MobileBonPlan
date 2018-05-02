/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit_Offre;

import Entities.Produit;
import Services.ServiceProduit;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class AfficheProduits {

    Form f, f2;
    Label lb1;
    Label lb2, label;
    SpanLabel lb3;
    Container c;
    Container c1;
    Container c3;
    Container c2;
    ImageViewer imgv;
    Button btnajout;

    public AfficheProduits(Resources res) {
        btnajout = new Button("Ajouter Produit");

        f = new Form("Liste des produits", new BoxLayout(BoxLayout.Y_AXIS));

        ServiceProduit serviceProd = new ServiceProduit();
        ArrayList<Produit> lis = serviceProd.getList2();
        for (Produit e : lis) {

            lb1 = new Label();
            lb2 = new Label();
            lb3 = new SpanLabel();
            c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            imgv = new ImageViewer();
            label = new Label();
            Button details = new Button("Détails");
            Button del = new Button("Supprimer");

//        lb1.setY(0);
//        lb3.setY(0);
//        lb1.setX(10);
//        lb3.setX(10);  
            c.add(lb1);
            c.add(lb2);
            c.add(lb3);

            c3.add(details);
            c3.add(del);
            c.add(c3);

            //c1.add(imgv);
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//            ServiceProduit sp = new ServiceProduit();
//                    sp.supprimerProduit(e);
//              AfficheProduits ap= new AfficheProduits(res);
//              ap.getF().show();
 Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this product??", "oui", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/deleteprod/"+e.getId_produit());
                    
                        NetworkManager.getInstance().addToQueue(req);
                        f.revalidate();
//                       PubliciteController pc = new PubliciteController(theme);
//                        pc.getForm().show();
                    new AfficheProduits(res).getF().show();
             }}
            });
            
            
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                    Toolbar tb = f2.getToolbar();
                    c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    tb.getAllStyles().setBgColor(0x990033);
                    tb.addMaterialCommandToRightBar("Retourner à la liste", FontImage.MATERIAL_ARROW_BACK, (evt2) -> {
                        AfficheProduits h = new AfficheProduits(res);
                        h.getF().show();
                    });
                    tb.getAllStyles().setBgColor(0x990033);

                    tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
                        AfficheProduits h = new AfficheProduits(res);
                        h.getF().show();
                    });
                    tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
                        AfficheProduits h = new AfficheProduits(res);
                        h.getF().show();
                    });
                    tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
                        AfficheProduits h = new AfficheProduits(res);
                        h.getF().show();
                    });

                    ImageViewer img = new ImageViewer();
                    int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                    Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                    img.setImage(URLImage.createToStorage(encImage,
                            "Large_" + "http://localhost/symfony/web/uploads/images/" + e.getPhoto_produit()
                            + "", "http://localhost/symfony/web/uploads/images/" + e.getPhoto_produit()
                            + "", URLImage.RESIZE_SCALE_TO_FILL));
                    SpanLabel sp = new SpanLabel("Nom du Produit: " + e.getNom_produit());
//                        SpanLabel sp2=new SpanLabel(e.getCategorie().toString());
                    SpanLabel sp4 = new SpanLabel("Prix du Produit: " + e.getPrix_produit());

                    //   c2.setHeight(700);  
                    // f2.getAllStyles().setBorder(Border.createLineBorder(3, 0xffa83b));
                    f2.getAllStyles().set3DText(true, false);
                    f2.getAllStyles().setUnderline(true);
                    f2.getAllStyles().setBgTransparency(200);
                    f2.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                    f2.add(img);
                    f2.add(sp);
                    //                          f2.add(sp2);
                    f2.add(sp4);

                    f2.show();
                }

            });
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
        Toolbar tb = f.getToolbar();
        tb.getAllStyles().setBgColor(0x990033);

        tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
            AfficheProduits h = new AfficheProduits(res);
            h.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
            AfficheProduits h = new AfficheProduits(res);
            h.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Retourner à la liste", FontImage.MATERIAL_RADIO_BUTTON_CHECKED, (evt2) -> {
            AfficheProduits h = new AfficheProduits(res);
            h.getF().show();
        });
        btnajout.addActionListener((e) -> {
            AjouterProduit a = new AjouterProduit(res);
            a.getF().show();
        });

        
        
        f.add(btnajout);
        
        

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
