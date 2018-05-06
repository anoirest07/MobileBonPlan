/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement;

import Entities.Evenement;
import Services.serviceInteresser;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.Authentification.connectedUser;
import java.io.IOException;

/**
 *
 * @author amine
 */
public class DetailEvenement {

    Form f;
    Resources theme;
    String url2 = "/imggd";
    String url = "http://localhost/symfony/web/uploads/images/";
    EncodedImage enc;

    public DetailEvenement(Evenement et) {
        theme = UIManager.initFirstTheme("/theme");

        Form f2;

        f2 = new Form(et.getNom_evenement(), BoxLayout.y());

        Toolbar tb = f2.getToolbar();

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> {

            new AffichageclientEvenement(theme).show();

        });
        serviceInteresser si = new serviceInteresser();
        Button abonner = new Button();
        abonner.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, abonner.getUnselectedStyle()));
        if (si.isInteresse(connectedUser.getId(), et.getId_evenement())) {
            abonner.setText("desabonner");
        } else {
            abonner.setText("abonner");
        }
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }
        abonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent k) {

                if (si.isInteresse(connectedUser.getId(), et.getId_evenement())) {
                    si.supprimerabo(connectedUser, et);
                    abonner.setText("abonner");
                } else {
                    si.interesser(connectedUser, et);
                    abonner.setText("desabonner");
//                       AuthMethod auth = new TokenAuthMethod("69ad0b93", "7wNWVLke0e9ddaPb");
//NexmoClient client = new NexmoClient(auth);
//TextMessage message = new TextMessage("Events", "21650189147", "un nouveau client a particité a votre evenement");
//                        try {
//                            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
//                        } catch (IOException ex) {
//                        } catch (NexmoClientException ex) {
//                        }
//////                    
                }
            }
        });
        URLImage imgsv = URLImage.createToStorage(enc, url2 + et.getPhoto(), url + et.getPhoto());
        ImageViewer imgvb = new ImageViewer(imgsv);
        imgvb.setWidth(100);
        imgvb.setHeight(100);
        Label name = new Label(et.getNom_evenement());
        SpanLabel fondation = new SpanLabel("Description: " + et.getDescription());
        Container ca = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ca.add(imgvb);
        f2.add(fondation);
        ca.add(abonner);
        f2.add(ca);
        f2.show();
    }

}
