/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com_Fav;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import Entities.Etablissement;
import Entities.Favoris;
import Experience.FilClient;
import Publicite.AffichageClient;
import Services.ServiceFavoris;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author malek
 */
public class Affichage extends SideMenuBaseForm{
    ;
    Form f;
    SpanLabel lb;
        public Affichage(Resources res) {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        Toolbar tg = f.getToolbar();
         tg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            new AffichageClient(res).show();
             });
            ServiceFavoris serviceTask=new ServiceFavoris();
         ArrayList<Favoris> listTasks = serviceTask.getList2();
          Container root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container king = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           int i =0;
        for(Favoris pub:listTasks){
            i++;
               // Container C = new Container(new GridLayout(1, 2));
                Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 Container img = new Container(new BoxLayout(BoxLayout.X_AXIS));
          
                
              
               //  Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              
            ArrayList<Etablissement> listEtab=serviceTask.getListEtab("http://localhost/symfony/web/app_dev.php/BonPlan/favjsonid/"+Authentification.connectedUser.getId());
            int j =0;
               for(Etablissement etab:listEtab){
                   j++;
                   if(i==j){
                    SpanLabel Message = new SpanLabel();
                
                SpanLabel Message2 = new SpanLabel();
                  Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                   Message.setText("Nom : "+etab.getNom_etablissement());
                  
                Message2.setText("Adresse : "+etab.getAdresse_etablissement());
                  
                   System.out.println(Message);
                   System.out.println(Message2);
                  // System.out.println(etab);
                   
                     EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 150), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, etab.getPhoto_etablissement(),
                                    "http://localhost/symfony/web/uploads/images/"+etab.getPhoto_etablissement());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
                          //   Container C1 = new Container(BoxLayout.y());
                          Button b = new Button();
                 
               
                  //  C.add(b);
                    b.setText("Supprimer"); 
                   // b.setUIID("consulterbt");
                    
                     b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/favjsondelete/"+pub.getId_favoris();
        Dialog.show("Confirmation", "supprimer cette Favoris??", "oui", "Annuler");
        con.setUrl(Url);
                            System.out.println(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
         Affichage h=new Affichage(res);
                                h.getF().show();
         
                        }
                        
                        
                        }); 
                    
                      Container btn = new Container(new BoxLayout(BoxLayout.X_AXIS));
                     btn.add(b);
                     C1.add(Message);
                
                C1.add(Message2);
                    root2.add(btn);
                    img.add(imgv1);
                      root2.add(C1);
                      root.add(img);
            root.add(root2);
                   //  f.add(C1);
                   }
                   
               }
                       
                     
            
            
              
                
                 
              
                // f.add(C);
         //   System.out.println(Message);
          //  System.out.println(Message2);
        }
        
        king.add(root);
        f.add(king);
         
        
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
          
    }
        

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
