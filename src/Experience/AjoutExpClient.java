/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experience;

import Entities.Categorie;
import Entities.CriteresEvaluation;
import Entities.Etablissement;
import Entities.Evaluation;
import Entities.Experience;
import Services.ServiceCategorie;
import Services.ServiceCritere;
import Services.ServiceEtablissement;
import Services.ServiceEvaluation;
import Services.ServiceExperience;
import com.BonPlan.Utils.UploadFile;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.codename1.ext.filechooser.FileChooser;




/**
 *
 * @author admin
 */
public class AjoutExpClient {
    Form f;
    Picker date = new Picker();
//    DateSpinner date=new DateSpinner();
    
    TextField desc;
    
    Button btnajout;
    Button btnOpen;
    Button btnCapt;

    Rating rat1;
    Rating rat2;
    Rating rat3;
    Rating rat4;
    Rating rat5;
    
    Slider s1;
    Slider s2;
    Slider s3;
    Slider s4;
    Slider s5;

    SpanLabel l1;
    SpanLabel l2;
    SpanLabel l3;
    SpanLabel l4;
    SpanLabel l5;
    
    ComboBox comboCat;
    
    List<Etablissement> etabs2 = new ArrayList<>();
    ListModel<String> etabRech = new DefaultListModel<String>();
    List<CriteresEvaluation> listecrit = new ArrayList<>();

    AutoCompleteTextField ac = new AutoCompleteTextField(etabRech);

    Container ctrat = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ctcrit = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ctev = new Container(new BoxLayout(BoxLayout.X_AXIS));
    
    String newfilePath = "";
    private TextField sendeer;
    
    private Resources theme;
    EncodedImage eimg ;
    Image img;
    ImageViewer imgv;
    


    public AjoutExpClient() throws IOException {
        
        f = new Form("Ajout d'expérience", new BoxLayout(BoxLayout.Y_AXIS));
        
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        FilClient fc = new FilClient();
        f.getToolbar().addCommandToLeftBar("", theme.getImage("back-command.png"), b -> {
                fc.getF().showBack();
            });
        
        btnajout = new Button("Valider");
        btnOpen = new Button("Ajouter une photo");
        btnCapt = new Button("Prendre une photo");

        desc = new TextField();
        desc.setHint("Description");
       
        ImageViewer i = new ImageViewer();
        
//        date = new Picker();
       
        comboCat = new ComboBox();
      
        ServiceCategorie scat = new ServiceCategorie();
        List<Categorie> listecat = new ArrayList<>();
        listecat= scat.getListCat();

        for (Categorie c: listecat)
        {
            comboCat.addItem(c.getNom_categorie());
        }           
        
        ac.setMinimumElementsShownInPopup(5);

        ServiceEtablissement setab = new ServiceEtablissement();
        
        ServiceCritere scrit = new ServiceCritere();
        
//                    f.removeAll();
//ctcrit.removeAll();
//ctrat.removeAll();
//ctev.removeAll();
//        
        comboCat.addActionListener((evt) -> {

            for (int ii=0; ii<=etabRech.getSize();ii++)
            {                
                etabRech.removeItem(ii);
            }
            
//        etabs2 = setab.getListEtab(comboCat.getSelectedItem().toString());
            
            for (Etablissement et : etabs2)
        {
            etabRech.addItem(et.getNom_etablissement());
        }      
            
           listecrit= scrit.getListCrit(comboCat.getSelectedItem().toString());
           
           for ( CriteresEvaluation crit : listecrit)
           {
               System.out.println("CRIIIT: "+crit.getNom_critere_evaluation());
           }

          l1=new SpanLabel();
          l2=new SpanLabel();
          l3=new SpanLabel();
          l4=new SpanLabel();
          l5=new SpanLabel();
          
            ctcrit.add(l1);
            ctcrit.add(l2);
            ctcrit.add(l3);
            ctcrit.add(l4);
            ctcrit.add(l5);

            ctev.add(ctcrit);
            ctev.add(ctrat);
            
            l1.setText(listecrit.get(0).getNom_critere_evaluation());
            rat1 = new Rating();
            s1= new Slider();
            s1=rat1.createStarRankSlider();
            ctrat.add(s1);

             l2.setText(listecrit.get(1).getNom_critere_evaluation());
            rat2 = new Rating();
            s2= new Slider();
            s2=rat2.createStarRankSlider();
            ctrat.add(s2);

             l3.setText(listecrit.get(2).getNom_critere_evaluation());
            rat3 = new Rating();
            s3= new Slider();
            s3=rat3.createStarRankSlider();
            ctrat.add(s3);
            
             l4.setText(listecrit.get(3).getNom_critere_evaluation());
            rat4 = new Rating();
            s4= new Slider();
            s4=rat4.createStarRankSlider();
            ctrat.add(s4);
            
             l5.setText(listecrit.get(4).getNom_critere_evaluation());
            rat5 = new Rating();
            s5= new Slider();
            s5=rat5.createStarRankSlider();
            ctrat.add(s5);              
              
              

        });
        
        
        f.add(comboCat);
        f.add(ac);                    
        f.add(desc);
//        f.add(date);
        f.add(btnOpen);
        f.add(ctev);

        f.add(btnajout);
        
        f.add(btnCapt);


            
        f.add(i);
        
//        Rating rat = new  Rating();
//        f.add(rat.createStarRankSlider());
            ServiceExperience sxp = new ServiceExperience();

        Experience exp = new Experience();
        btnOpen.addActionListener((evt1) -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        try {
                            this.newfilePath = (String) e.getSource();
                            i.setImage(Image.createImage(this.newfilePath));
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
           
            btnCapt.addActionListener((evt1) -> {
                this.newfilePath = Capture.capturePhoto();
                try {
                    i.setImage(Image.createImage(this.newfilePath));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(this.newfilePath);
                try {
                    this.newfilePath = UploadFile.uploadImage(newfilePath, null);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(this.newfilePath);
            });
            
////btnCapt.getAllStyles().setAlignment(4);
                
         btnajout.addActionListener((e) -> {
            
            Etablissement etab = new Etablissement();
            
            

           // etab=setab.getEtab(ac.getText());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
            Date debut = date.getDate();

             if(comboCat.getSelectedItem().equals(null) || ac.getText().isEmpty() || desc.getText().isEmpty() ) 
   { 
                   Dialog.show("Erreur !", "Veuillez remplir tous les champs", "OK",null);

                    
   }
   
   else if (s1.getProgress()== 0|| s2.getProgress()==0 || s3.getProgress()==0|| s4.getProgress() ==0|| s5.getProgress() ==0)
   {                   Dialog.show("Erreur !", "Veuillez évaluez tous les critères", "OK",null);

            
        }
   else {
       
        if (!this.newfilePath.equals("") && this.newfilePath != null) {
                                
            
            sxp.ajoutTask(exp, 2, etab.getId_etablissement(), desc.getText(), this.newfilePath , 1, debut);
        }
            ServiceEvaluation seval = new ServiceEvaluation();

            Evaluation ev1 = new Evaluation();
            ev1.setExperience(exp);
            ev1.setCritere_evaluation(listecrit.get(0));
            ev1.setNote(s1.getProgress());
            seval.ajoutTask(ev1);

            Evaluation ev2 = new Evaluation();
            ev2.setExperience(exp);
            ev2.setCritere_evaluation(listecrit.get(1));
            ev2.setNote(s2.getProgress());
            seval.ajoutTask(ev2);

            Evaluation ev3 = new Evaluation();
            ev3.setExperience(exp);
            ev3.setCritere_evaluation(listecrit.get(2));
            ev3.setNote(s3.getProgress());
            seval.ajoutTask(ev3);

            Evaluation ev4 = new Evaluation();
            ev4.setExperience(exp);
            ev4.setCritere_evaluation(listecrit.get(3));
            ev4.setNote(s4.getProgress());
            seval.ajoutTask(ev4);

            Evaluation ev5 = new Evaluation();
            ev5.setExperience(exp);
            ev5.setCritere_evaluation(listecrit.get(4));
            ev5.setNote(s5.getProgress());
            seval.ajoutTask(ev5);

            int tot = s1.getProgress()+s2.getProgress()+s3.getProgress()+s4.getProgress()+s5.getProgress();
            int moyenne = 5;
            sxp.modifNoteTask(exp, tot/5);


            FilClient a=new FilClient();
            a.getF().show();

  if ((ev1.getNote()<3) || (ev2.getNote()<3)||(ev3.getNote()<3)||(ev4.getNote()<3)||(ev5.getNote()<3))
           
       {
       
     
AuthMethod auth = new TokenAuthMethod("ba86ba3c", "ig9F77jNDgxOQW79");

NexmoClient client = new NexmoClient(auth);
TextMessage message = new TextMessage("BonPlan", "21620004410", "Vous avez une mauvaise évaluation");
            try {
                SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
                             System.out.println("   FFIN");
                             for (SmsSubmissionResult response : responses) {
    System.out.println("////////"+response);
                                 System.out.println("   af");

}
                            System.out.println("   af2");


            } catch (IOException ex) {
                             System.out.println("   FFIN2");

            } catch (NexmoClientException ex) {
                             System.out.println("   FFIN3");

            }
       }}
            
        });


}
                 
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getDesc() {
        return desc;
    }

    public void setDesc(TextField desc) {
        this.desc = desc;
    }
    
}
