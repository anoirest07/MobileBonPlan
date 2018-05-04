/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experience;

import Entities.Experience;
import Services.ServiceExperience;
import com.BonPlan.Utils.UploadFile;
import com.codename1.components.ImageViewer;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class ModifExp {
    Form f;
    
    TextField desc;
    
    Button btnajout;
    Button btnmod;

    String newfilePath = "";
    
    Resources theme;
    
    Container ctx = new Container(new BoxLayout(BoxLayout.X_AXIS));
  
    public ModifExp(Experience expr) {
        f = new Form("Modification d'expérience", new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme");

        Toolbar.setGlobalToolbar(true);
        FilClient fc = new FilClient(theme);
//        f.getToolbar().addCommandToLeftBar("", theme.getImage("back-command.png"), b -> {
//                fc.showBack();
//            });
Toolbar tg = f.getToolbar();
         tg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            new FilClient(theme).show();
             });

        btnajout = new Button("Valider");
        btnmod = new Button("Modifier la photo");
        btnmod.setWidth(120);
        btnmod.setHeight(60);
        desc = new TextField(expr.getDescription_experience());
//        ImageViewer preuve = new ImageViewer();
 

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100,130, 0xffff2700), true);
        Image i = URLImage.createToStorage(placeholder, expr.getPreuve(), "http://localhost/symfony/web/uploads/images/"+ expr.getPreuve(), URLImage.RESIZE_SCALE);
        ImageViewer iv = new ImageViewer(i);        

        btnmod.addActionListener((evt1) -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        try {
                            this.newfilePath = (String) e.getSource();
                            iv.setImage(Image.createImage(this.newfilePath));
                            //Here goes the file upload logic
                            System.out.println("fil"+this.newfilePath);
                            try {
                                this.newfilePath = UploadFile.uploadImage(newfilePath, null);
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                };
                if (FileChooser.isAvailable()) {
                    FileChooser.showOpenDialog(".jpg,image/jpg,.jpeg", callback);
                } else {
                    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
                }
            });
        
         btnajout.addActionListener((evt) -> {
            
            if (!this.newfilePath.equals("") && this.newfilePath != null) {
                    
              ServiceExperience sxp = new ServiceExperience();
            sxp.modifTask(expr, desc.getText(),this.newfilePath); 
                        Dialog.show("Modification expérience", "Modification avec succes", "OK",null);
                        new FilClient(theme).show();

        }

        


       
            
          
//            ,preuve.getImage().getImageName()
           
            //fc.getF().showBack();
                
        });
         
         ctx.add(iv);
        ctx.add(btnmod);
        f.add(desc);
        f.add(ctx);
//        f.add(modifPhoto);
        f.add(btnajout);
}
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}