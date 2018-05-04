/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement;

import Entities.Evenement;
import Services.ServiceEvenement;
import Services.serviceInteresser;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
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
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.Authentification.connectedUser;
import com.mycompany.myapp.SideMenuBaseForm1;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.restfb.types.StoryAttachment.Media;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author tanga
 */
public class AffichageclientEvenement extends SideMenuBaseForm1 {

    ConnectionRequest connectionRequest;
    Container f;
    String url2 = "/imggd";
    String url = "http://localhost/symfony/web/uploads/images/";
    EncodedImage enc;

    public AffichageclientEvenement(Resources theme) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        String file = "C:/Users/amine/Desktop/jeuvoiture.mp4";
        try {
            com.codename1.media.Media video = MediaManager.createMedia(file, false);
            video.setVolume(150);

            video.play();

        } catch (IOException err) {

        }

        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Liste Evenements", "Title")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(theme);

        //menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        ServiceEvenement serviceTask = new ServiceEvenement();

        ServiceEvenement sp = new ServiceEvenement();
         serviceInteresser si = new serviceInteresser();

        ArrayList<Evenement> p = sp.getList();
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Evenement pr : p) {

            Button abonner = new Button("Abonner");
            abonner.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, abonner.getUnselectedStyle()));

            abonner.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent k) {


                    if(si.isInteresse(connectedUser.getId(), pr.getId_evenement())){
                        si.supprimerabo(connectedUser, pr);
                        
                    }else{
                              si.interesser(connectedUser, pr);

                    }
//                    Dialog d = new Dialog();
//
//                    if (Dialog.show("Confirmation", "voulez vous abonnez a cette evenement??", "Ok", "Annuler")) {
////                       AuthMethod auth = new TokenAuthMethod("8b5a02fa", "a6QPxefji9TzLl4S");
////NexmoClient client = new NexmoClient(auth);
////TextMessage message = new TextMessage("Events", "21697485816", "un nouveau client a particité a votre evenement");
////                        try {
////                            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
////                        } catch (IOException ex) {
////                        } catch (NexmoClientException ex) {
////                        }
//
//                        AffichageclientEvenement pc = new AffichageclientEvenement(theme);
//                        pc.show();
//
//                    }
                }
            });

            Button btmap = new Button("Carte");
            btmap.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MAP, btmap.getUnselectedStyle()));
            btmap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double latitude = pr.getEtab().getLat();
                    double longitude = pr.getEtab().getLong();

                    System.out.println("coordinates: " + latitude + " " + longitude);
                    Form f7;
                    MapForm a = new MapForm(latitude, longitude, pr.getNom_evenement());
                    a.getF().show();
                    f7 = a.getF();
                    Toolbar fg = f7.getToolbar();

                    fg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g
                            -> {

                        new AffichageclientEvenement(theme).show();

                    });
                }
            });

            Button btn3 = new Button("Détails");
            btn3.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btn3.getUnselectedStyle()));

            btn3.setWidth(15);
            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form f2;

                    f2 = new Form(pr.getNom_evenement(), BoxLayout.y());

                    Toolbar tb = f2.getToolbar();

                    tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> {

                        new AffichageclientEvenement(theme).show();

                    });

                    URLImage imgsv = URLImage.createToStorage(enc, url2 + pr.getPhoto(), url + pr.getPhoto());
                    ImageViewer imgvb = new ImageViewer(imgsv);
                    imgvb.setWidth(100);
                    imgvb.setHeight(100);
                    Label name = new Label(pr.getNom_evenement());
                    SpanLabel fondation = new SpanLabel("Description: " + pr.getDescription());
                    Container ca = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    ca.add(imgvb);
                    f2.add(fondation);
                    ca.add(abonner);
                    f2.add(ca);
                    f2.show();
                }
            });

            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label label = new Label();
            System.out.println(pr.getPhoto());
            int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + url2 + pr.getPhoto()
                    + "", url + pr.getPhoto()
                    + ""));

            c.add(label);
            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cnt.add(pr.getNom_evenement());
            System.out.println(pr.getEtab().getLat());
            System.out.println(pr.getEtab().getLong());

            c.add(cnt);
            Container cnt1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            cnt1.add(btn3);
            cnt1.add(btmap);

            Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cc.add(c);
            cc.add(cnt1);
            ctnlistProduct.add(cc);
            SpanLabel dd = new SpanLabel(pr.getDate_evenement().toString());
            cnt.add(dd);
            Label ab = new Label(pr.getEtab().getNom_etablissement().toString());
            cnt.add(ab);

        }
        f.add(ctnlistProduct);
        add(f);
    }

    public static Image getImageFromTheme(String name) {
        try {
            Resources resFile = Resources.openLayered("/theme");
            Image image = resFile.getImage(name);
            return image;
        } catch (IOException ioe) {
            System.out.println("Image " + name + " not found: " + ioe);
        }
        return null;
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
