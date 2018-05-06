/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicite;

import Entities.Publicite;
import Services.ServicePublicite;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.myapp.StatsForm;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author user
 */
public class PubliciteController extends SideMenuBaseForm{
   
    //private Form home;
    Container home;
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    Integer userId;
    public PubliciteController(Resources theme){
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD); 
        
        fab.addActionListener(e -> {          
//            AddPublicite add= new AddPublicite(theme);
//            add.getForm().show();
              new AddPublicite(theme).show();
        });
        home = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(l -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        
        ServicePublicite serviceTask = new ServicePublicite();
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Mes Publicites", "Title")
                                )
                            )
                );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        setupSideMenu(theme);
        
        ArrayList<Publicite> lis = serviceTask.getList2(Authentification.connectedUser.getId());
        System.err.println(Authentification.connectedUser.getId());
        Container root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Publicite t : lis) {
            Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label label = new Label();

            int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + t.getPhoto_publicite()
                    + "", "http://localhost/symfony/web/uploads/images/" + t.getPhoto_publicite()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));

            Label l = new Label(t.getTitre());
            Button partage = new Button("Partager sur FB");
            
            partage.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    String accessToken = "EAACEdEose0cBAIMpqbHY3hmAzAC5f5cJrriHaU465ZC2ZAsztuVW85fpRqZA0g0GceOVNXrEYnOpoJbySPlsAE06KkNVeWNAyhIco9DKpna7USenEdVktPfRETfhfRODwtyl2ty5934EC6xQDa0A2TijazCfNStmw8ySNszv71GMpiO1ov90qQsFakxnopxZCWnEKcgKJgZDZD";

                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                            Parameter.with("message", t.getTitre() + "\n" + t.getDescription_publicite()));
                    Dialog.show("Confirmation", "votre publication a été partager sur facebook", "Ok", null);

                }

            });
            Button del = new Button("Supprimer");
            
          
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "supprimer cette publicite??", "oui", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/deletepub/"
                                + t.getId_publicite());
                        System.out.println(t.getId_publicite());
                        NetworkManager.getInstance().addToQueue(req);
                        home.revalidate();
//                       PubliciteController pc = new PubliciteController(theme);
//                        pc.getForm().show();
                    new PubliciteController(theme).show();

                    }
                }
            });
            fab.animate();
            C1.add(label);
            C1.add(l);
            //C1.setLeadComponent(l);
            root2.add(C1);
            
            Container btn = new Container(new BoxLayout(BoxLayout.X_AXIS));

            btn.add(partage);
            btn.add(del);
            root2.add(btn);
            root.add(root2);
            root.add(showdate(t.getDateDebut()));

        }
        home.add(root);
        //home.show();
        add(home);
    }
    
   

//public Form getForm(){
//    return home ;
//}

    @Override
    protected void showOtherForm(Resources res) {
          new StatsForm(res).show();//To change body of generated methods, choose Tools | Templates.
    }
    
    public String showdate(Date c) {
        
        Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DATE, -7);
         Date dt = cal.getTime();
        String str = new SimpleDateFormat("dd/MM/yyyy : HH:mm").format(dt);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy : HH:mm");
        Date d1 = null;
        Date d2 = null;
        Container contr = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contr.getStyle().setBorder(Border.createLineBorder(1, 1));
        try {
            String date = new SimpleDateFormat("dd/MM/yyyy : HH:mm").format(c);
            d1 = format.parse(date);
            d2 = format.parse(str);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            if(diffDays < 7){
          String dateString = "Il vous reste : " + diffDays + " jour(s) " + diffHours + " heure(s) " + diffMinutes + " minute(s)";
   return dateString;
            }
            else {
                return "passé";
            }
            
        } catch (ParseException ex) {

        }
        return "";
    }
    
 }