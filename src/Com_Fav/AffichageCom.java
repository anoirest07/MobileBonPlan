/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com_Fav;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import Entities.Client;
import Entities.Commentaire;
import Services.ServiceCommentaire;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.Authentification;
import java.util.ArrayList;

/**
 *
 * @author malek
 */
public class AffichageCom {
    Form f;
    SpanLabel lb;
    int y=2;
    Commentaire commentaire = new Commentaire();
    public static int n;
    
    public AffichageCom(){
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
            ServiceCommentaire serviceTask=new ServiceCommentaire();
         ArrayList<Commentaire> listTasks = serviceTask.getListCom(2);
         Container root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container king = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container Ajout = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          TextField nv =new TextField();
          Ajout.add(nv);
          Container bout = new Container(new BoxLayout(BoxLayout.X_AXIS));
          Button aj = new Button("ajout");
          aj.setText("Ajouter");
          ShareButton sb = new ShareButton();
          sb.setText("envoyer mail");
          sb.setTextToShare("vous avez un nouveau comm");
          bout.add(sb);
          bout.add(aj);
          Ajout.add(bout);
          
          aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(n<2){
                    
                     ConnectionRequest con = new ConnectionRequest();
                String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/comjsonnew2?commentaire=" + nv.getText()
                        + "&id="+Authentification.connectedUser.getId()+"&idExp="+commentaire.getIdExp();

             //   Message m = new Message("yo");
              //  Display.getInstance().sendMessage(new String[]{" lmail "}, "hey", m);
                con.setUrl(Url);
                NetworkManager.getInstance().addToQueueAndWait(con);
                AffichageCom h = new AffichageCom();
                h.getF().show();
                }
                   else{
          Dialog.show("erreur","vous avez atteint la limite","ok",null);
    }
               
            }
        });
          
//          aj.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               ConnectionRequest con = new ConnectionRequest();
//                String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/comjsonnew2?commentaire="+nv.getText()+
//                        Authentification.connectedUser.getId()+commentaire.getIdExp();
//                
//                Message m = new Message("yo");
//       Display.getInstance().sendMessage(new String[]{"hot lhné lmail "}, "hey", m);
//        con.setUrl(Url);
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        AffichageCom h=new AffichageCom();
//                                h.getF().show();
//            }
//        });
                  
           int i =0;
        for(Commentaire pub:listTasks){
            i++;
               // Container C = new Container(new GridLayout(1, 2));
                Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          
                Label Message3 = new Label();
                Message3.setText(pub.getCommentaire());
                Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                C2.add(Message3);
              
               //  Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Button b = new Button();
                 
               
                  //  C.add(b);
                    b.setText("Supprimer"); 
                   // b.setUIID("consulterbt");
                    
                     b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/comjsondelete/"+pub.getId_commentaire();
        con.setUrl(Url);
                            System.out.println(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
         AffichageCom h=new AffichageCom();
                                h.getF().show();
         
                        }
                        
                        
                        }); 
            ArrayList<Client> listclient=serviceTask.getListClient("http://localhost/symfony/web/app_dev.php/BonPlan/comjsonuser/"+pub.getId_commentaire());
            
               for(Client cl:listclient){
                   
                   
                    Label Message = new Label();
                
                Label Message2 = new Label();
                  Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                   Message.setText("Nom : "+cl.getNom());
                  
                Message2.setText("Prenom : "+cl.getPrenom());
                  C1.add(Message);
                
                C1.add(Message2);
                   System.out.println(Message);
                   System.out.println(Message2);
                  // System.out.println(etab);
                   
                     EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 150), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, cl.getPhoto_user(),
                                    "http://localhost/symfony/web/images/"+cl.getPhoto_user());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
                          //   Container C1 = new Container(BoxLayout.y());
                    img.add(imgv1);
                      root2.add(C1);
                   
                   //  f.add(C1);
                   
               }
                       
                     Container btn = new Container(new BoxLayout(BoxLayout.X_AXIS));
                     btn.add(b);
            
            
                 root2.add(C2);
                 root2.add(btn);
                 root.add(img);
            root.add(root2);
              
                // f.add(C);
         //   System.out.println(Message);
          //  System.out.println(Message2);
        }
          king.add(root);
        f.add(king);
        f.add(Ajout);
         
    } 
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
