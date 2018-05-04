/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experience;

import Entities.Experience;
import Publicite.AddPublicite;
import Services.ServiceExperience;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;

/**
 *
 * @author admin
 */
public class FilProp extends SideMenuBaseForm{
      Container f;
    TextField tnom;
    TextField tetat;
    Button modif;
    Button supp;

    public FilProp(Resources theme)
    {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);     
        fab.addActionListener(e -> {          
//          
        });
        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(l -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        
        ServicePublicite serviceTask = new ServicePublicite();
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Fil D'Actualité", "Title")
                                )
                            )
                );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        setupSideMenu(theme);
        modif=new Button("Modifier");
        supp=new Button("Supprimer");
        
//        btnajout.addActionListener((e) -> {
//            ServiceTask ser = new ServiceTask();
//            Task t = new Task(0, tnom.getText(), tetat.getText());
//            ser.ajoutTask(t);
//            
//
//        });

       

        Container glob = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ServiceExperience sxp = new ServiceExperience();
        ArrayList<Experience> listExps = sxp.getList2();
        
        for (Experience exp : listExps)
        {

             if (exp.getEtablissement().getUtilisateur().getId()==Authentification.connectedUser.getId())

             {  
            Container ct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            ct.getAllStyles().setBorder(Border.createDoubleBorder(1, 0x99CCCC));
            ct.getAllStyles().setPadding(ct.LEFT, 80);
            
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(70,70, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, exp.getPreuve(), "http://localhost/symfony/web/uploads/images/"+ exp.getPreuve(), URLImage.RESIZE_SCALE);
            ImageViewer iv = new ImageViewer(i);
            
           
            SpanLabel usr= new SpanLabel(exp.getUtilisateur().getPrenom());
            SpanLabel etb= new SpanLabel(exp.getEtablissement().getNom_etablissement());
            SpanLabel dt = new SpanLabel(exp.getDate_exp());
            
            System.out.println(" !!!!!!!!!  "+exp.getDate_exp());

            
            SpanLabel s1 = new SpanLabel(exp.getDescription_experience());
            SpanLabel s2= new SpanLabel(exp.getPreuve());
            SpanLabel s3= new SpanLabel(Integer.toString(exp.getNoteExp()));

            
//             Label img = new Label();
//          img.setIcon(i);
//          ct.add(img);
            ct.add(usr);
            ct.add(etb);
            ct.add(dt);
            ct.add(s1);
            ct.add(s2);
            ct.add(s3);
            ct.add(iv);

            System.out.println("ID USER"+exp.getUtilisateur().getId());

//          System.out.println("NOM USER"+exp.getUtilisateur().getNom());
//          System.out.println("PRENOM USER"+exp.getUtilisateur().getPrenom());
//          System.out.println("ID EXP"+exp.getId_exp());

            
            glob.add(ct);

            System.out.println("NOTE"+exp.getNoteExp());
            
            Button btnAff = new Button();
            btnAff.addActionListener((evt) -> {
               AffichExp aff =new AffichExp(exp);
               aff.getF().show();
               
                
            });
            ct.setLeadComponent(btnAff);
             }
        }
        
            f.add(glob);
            add(f);
     
 
                
            
    }

//    public Form getF() {
//        return f;
//    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    @Override
    protected void showOtherForm(Resources res) {
       //To change body of generated methods, choose Tools | Templates.
    }

}
