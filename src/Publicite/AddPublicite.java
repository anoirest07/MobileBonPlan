/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicite;

import Entities.Etablissement;
import Entities.Publicite;
import Services.ServiceAjouter;
import Services.ServiceEtablissement;
import Services.ServiceEvenement;
import Services.ServicePaiement;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import com.mycompany.myapp.SideMenuBaseForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class AddPublicite extends SideMenuBaseForm{

    Container AjoutPublcite;
    DateSpinner date = new DateSpinner();
TextField ref;
TextField desc;

TextField num;
TextField mexp;
TextField aexp;
TextField cvc;
   // Form f;
Container f;
Etablissement etabli  ;
ImageViewer image;
  
    Button choose ; 

    public AddPublicite(Resources res) {
         super(BoxLayout.y());
         Toolbar tb = getToolbar();
       
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_BACKSPACE);
        fab.addActionListener(e -> {
            new PubliciteController(res).show();
//            PubliciteController pc = new PubliciteController(res);
//            pc.getForm().show();

        });
         f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        AjoutPublcite = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        ServicePublicite serviceTask = new ServicePublicite();
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Ajouter Publicité", "Title")
                        )
                )
        );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu(res);
        /**
         * ******************formulaire dajout****************
         */
        
        ref = new TextField("", "titre", 20, TextField.NUMERIC);
        f.add(ref);
        desc = new TextField("", "descriptionPublicite", 20, TextField.NUMERIC);
        f.add(desc);
        choose=new Button("choisir image");

                 f.add(choose);
        image = new ImageViewer();
        
                  f.add(image);

        choose.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServicePublicite s=new ServicePublicite();
           s.browseImage(image);
          
            }
        });

//        ServicePublicite sp = new ServicePublicite();
   ServiceEtablissement se=new ServiceEtablissement();
                ArrayList<Etablissement> lsevent= se.MesEtabs(Authentification.connectedUser.getId());
                System.out.println("+++++"+se.MesEtabs(Authentification.connectedUser.getId()));
                ComboBox combo = new ComboBox();
                                for (Etablissement etablissement : lsevent) {
                                    combo.addItem(etablissement.getNom_etablissement());
                                    System.out.println(" *****"+etablissement.getNom_etablissement());
                                }
//        Button imgBtn = new Button("image");
//        imgBtn.addActionListener(e -> {
//       Display.getInstance().openGallery(new ActionListener() {
//       @Override
//       public void actionPerformed(ActionEvent ev) {
//       if (ev != null && ev.getSource() != null) {
//       String path = (String) ev.getSource();
//       Image img = null;
//       try {
//       img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
//       } catch (IOException e) {
//       System.out.println(e.getMessage());
//      }
//      }
//       }
//        }, Display.GALLERY_IMAGE);
//        });
//        f.add(imgBtn);
f.add(combo);
        Label d = new Label("Date Debut:", "TitlePictureSpace");
        f.add(d);
        
        f.add(date);

        Container p = new Container();
        num = new TextField("", "Numero Carte", 16, TextField.NUMERIC);
        p.add(num);
        mexp = new TextField("", "Mois d'expiration", 16, TextField.NUMERIC);
        p.add(mexp);
        aexp = new TextField("", "Année d'expiration", 16, TextField.NUMERIC);
        p.add(aexp);
        cvc = new TextField("", "CVC Code", 16, TextField.NUMERIC);
        p.add(cvc);
        Label amount = new Label("Amount: 5 EUR", "TitlePictureSpace");
        p.add(amount);
        Container ct = new Container();
        Button bt = new Button("Payer");
                

        
       
        bt.addActionListener(e -> {   
            Publicite pub = new Publicite();
            if (isInputValid()) 
        {
            pub.setDescription_publicite(desc.getText());
            pub.setTitre(ref.getText());
            pub.setPhoto_publicite(image.getImage().getImageName());
            etabli=lsevent.get(combo.getSelectedIndex());
            pub.setEtablissement(etabli);
            int day=date.getCurrentDay();
            int month=date.getCurrentMonth();
            int year=date.getCurrentYear();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String datee = day + "/" +month + "/"+year;
            Date debut = new Date();
            try {
                debut = format.parse(datee);
            } catch (ParseException ex) {
                
            }
            
            pub.setDateDebut(debut);
            ServiceAjouter SA = new ServiceAjouter();
            ServicePaiement SP = new ServicePaiement();
            SA.Ajouter(pub);
            SP.payer("4242424242424242", 12, 18, "458", 5, "testpaimenet");
            
            Dialog.show("Ajout Publicite", "ajout avec succes", "OK",null);
            new PubliciteController(res).show();
        }
        });
        ct.add(bt);
        p.add(ct);
        p.setVisible(false);
        Button valider = new Button("Ajouter");
        
        f.add(valider);
//        Label lab = new Label("Il faut payer d'abord ");
//        lab.setVisible(false);
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
//                lab.setVisible(true);
                p.setVisible(true);
                valider.setVisible(false);
                Dialog.show("Alert", "Il faut payer d'abord", "Ok", null);
            }
        });

       f.add(AjoutPublcite);
        //f.add(lab);
        f.add(p);
        add(f);
    }

//    public Form getForm() {
//        return f;
//    }
    
    private boolean isInputValid() 
    {
        String errorMessage = "";

        if (ref.getText() == null || ref.getText().length() == 0) 
        {
            errorMessage += "Champ titre invalide !\n"; 
        }
        if (desc.getText() == null || desc.getText().length() == 0) 
        {
            errorMessage += "Champ description invalide !\n"; 
        }
      
        if (num.getText() == null || num.getText().length() == 0) 
        {
            errorMessage += "Champ n° invalide !\n"; 
        }
        if (mexp.getText() == null || mexp.getText().length() == 0) 
        {
            errorMessage += "Champ mois invalide !\n"; 
        }
        if (aexp.getText() == null || aexp.getText().length() == 0) 
        {
            errorMessage += "Champ année invalide !\n"; 
        }
        if (cvc.getText() == null || cvc.getText().length() == 0) 
        {
            errorMessage += "Champ cvc invalide !\n"; 
        }
        if(image.isEnabled() == false)
        {
            errorMessage += "Image !\n"; 
        }
        if (errorMessage.length() == 0) 
        {
            return true;
        }
         else 
        {
            Dialog.show("Champs invalides", errorMessage, "OK", null);
            return false;
        }
    }

    @Override
    protected void showOtherForm(Resources res) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
