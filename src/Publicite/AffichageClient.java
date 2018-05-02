/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicite;

import Entities.Publicite;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.myapp.SideMenuBaseForm1;
import com.mycompany.myapp.StatsForm;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AffichageClient extends SideMenuBaseForm1{
   // Form f;
    Container f;
    public AffichageClient(Resources res){ 
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
                                    new Label("Liste Publicites", "Title")
                                )
                            )
                );
        tb.setTitleComponent(titleCmp);
         FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(res);
        ServicePublicite serviceTask = new ServicePublicite();
         ArrayList<Publicite> lis = serviceTask.getList3();
         System.out.println(Authentification.connectedUser.getId());
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
            Button btn3 = new Button("Détails");
    btn3.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btn3.getUnselectedStyle()));
   btn3.addActionListener(new ActionListener() 
                        {
                            @Override
            public void actionPerformed(ActionEvent evt) {
            Form f2;
            f2 = new Form(t.getTitre(),BoxLayout.y());
            
            Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_BACKSPACE, e->{
            new AffichageClient(res).show();
          //  f.showBack();
            
            });
            
                                SpanLabel name = new SpanLabel(t.getDescription_publicite(), "TitlePictureSpace");
               
               
                
                
                //Label ville = new Label(t.getCafeVille().getNom());
                
                
                f2.add(name);
                
                f2.show();
}
        });
   
            C1.add(label);
             C1.add(l);
             root2.add(C1);
             Container btn = new Container(new BoxLayout(BoxLayout.X_AXIS));
             btn.add(btn3);
             root2.add(btn);         
         
          root.add(root2);
        
    }
        
       f.add(root);
        //f.show();
        add(f);
        
        
    }
//    public Form getForm(){
//    return f ;
//}

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show(); //To change body of generated methods, choose Tools | Templates.
    }
}
