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
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.util.ArrayList;
import com.mycompany.myapp.Authentification;

/**
 *
 * @author admin
 */
public class FilProp {
      Form f;
    TextField tnom;
    TextField tetat;
    Button modif;
    Button supp;

    public FilProp()
    {
        f = new Form("Fil d'actualité",new BoxLayout(BoxLayout.Y_AXIS));
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
     
 
                
            
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
