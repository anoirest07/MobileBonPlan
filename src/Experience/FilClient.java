/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experience;

import Entities.Experience;
import Services.ServiceExperience;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm1;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class FilClient extends SideMenuBaseForm1 {
    Container f;
    TextField tnom;
    TextField tetat;
    Button btnajout;
    Button modif;
    Button supp;
    Resources theme;

    public FilClient(Resources theme)
    { 
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
                                    new Label("Fil D'Actualité", "Title")
                                )
                            )
                );
        tb.setTitleComponent(titleCmp);
         FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(theme);
        btnajout = new Button("Ajouter une expérience");
        btnajout.getAllStyles().setFgColor(0x9900CC);

        modif=new Button("Modifier");
        supp=new Button("Supprimer");
        f.add(btnajout);
        
Font mediumPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
Font mediumItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
Font largeBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        btnajout.addActionListener((e)->{
            try {
                AjoutExpClient a=new AjoutExpClient();
                a.getF().show();
            } catch (IOException ex) {
            }
        });
        
        Container glob = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ServiceExperience sxp = new ServiceExperience();
        ArrayList<Experience> listExps = sxp.getList2();
        
        for (Experience exp : listExps)
        {
           Container ctfor = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   
           Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            Container xbtn = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Container xbtn = new Container();
            xbtn.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            
            Container ct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            ctfor.getAllStyles().setBorder(Border.createDoubleBorder(1, 0x9900CC));
            
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(120,120, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, exp.getPreuve(), "http://localhost/symfony/web/uploads/images/"+ exp.getPreuve(), URLImage.RESIZE_SCALE);
            ImageViewer iv = new ImageViewer(i);      
           
            SpanLabel usr= new SpanLabel(exp.getUtilisateur().getPrenom());
            SpanLabel etb= new SpanLabel(exp.getEtablissement().getNom_etablissement());
            SpanLabel dt = new SpanLabel(exp.getDate_exp());
                        
            SpanLabel s1 = new SpanLabel(exp.getDescription_experience());
            
            Rating moy = new Rating();
            Slider sl= new Slider();
            sl=moy.createStarRankSlider();
            sl.setEditable(false);
            sl.setProgress(exp.getNoteExp());

            s1.getTextAllStyles().setFont(mediumPlainMonospaceFont);
            dt.getTextAllStyles().setFont(mediumItalicMonospaceFont);

            ct.add(usr);
            ct.add(etb);
            ct.add(dt);
            ct.add(s1);
            ct.add(sl);
            
            x.add(iv);
            x.add(ct);
            
            ctfor.setHeight(500);
            ctfor.add(x);
            ctfor.add(xbtn);
            
            if (exp.getUtilisateur().getId()==Authentification.connectedUser.getId())
            {
                
                Button modiif = new Button("Modifier");
//                xbtn.addComponent(modiif);
                    xbtn.add(BorderLayout.WEST, modiif);

                Button suppr = new Button("Supprimer");
//                xbtn.addComponent(suppr);
                    xbtn.add(BorderLayout.EAST, suppr);

                modiif.addActionListener((evt) -> {
                ModifExp a=new ModifExp(exp);
                a.getF().show();
                });  
                
                suppr.addActionListener((ev) -> {
                sxp.suppTask(exp.getId_exp());
                FilClient fc = new FilClient(theme);
                fc.showBack();
                });      
            }
            
            glob.add(ctfor);
            
            Button btnAff = new Button();
            btnAff.addActionListener((evt) -> {
               AffichExp aff =new AffichExp(exp);
               aff.getF().show();
            });
            
            x.setLeadComponent(btnAff);

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
