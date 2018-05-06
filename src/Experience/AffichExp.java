/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experience;

import Com_Fav.AffichageCom;
import Entities.Client;
import Entities.Commentaire;
import Entities.CriteresEvaluation;
import Entities.Evaluation;
import Entities.Experience;
import Services.ServiceCommentaire;
import Services.ServiceCritere;
import Services.ServiceEvaluation;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Authentification;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AffichExp {
    
    Form f;
    
    SpanLabel usr;
    SpanLabel etab;
    SpanLabel date;
    SpanLabel desc;
    
    Label l1;
    Label l2;
    Label l3;
    Label l4;
    Label l5;
    Label l6;

    Container kbir;
    Container ctx;
    Container ctx2;
    Container ctrat;
    Container ctlab;
    Container ctlab2;
    Container ctmoy;
    Container ctr1;
    Container ctr2;
    Container ctr3;
    Container ctr4;
    Container ctr5;
    Container ajouCom;
    Container affichCom;
    TextField Com;
   

    
    Rating rat1;
    Rating rat2;
    Rating rat3;
    Rating rat4;
    Rating rat5;
    Rating rat6;

    Slider s1;
    Slider s2;
    Slider s3;
    Slider s4;
    Slider s5;
    Slider s6;

    List<CriteresEvaluation> listecrit = new ArrayList<>();

    Resources theme;
        
    public AffichExp(Experience expr) {
        
      
Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
Font smallPlainProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
Font mediumItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
Font largeBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
Font mediumPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

        f = new Form("Détails de l'expérience");
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        FilClient fc = new FilClient(theme);
//        f.getToolbar().addCommandToLeftBar("", theme.getImage("back-command.png"), b -> {
//                fc.showBack();
//                });
Toolbar tg = f.getToolbar();
         tg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            new FilClient(theme).show();
             });
        
        ServiceCritere scrit = new ServiceCritere();

        l1=new Label();
        l2=new Label();
        l3=new Label();
        l4=new Label();
        l5=new Label();
        l6=new Label();

        kbir=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctx=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctx2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctrat=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctlab=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctlab2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctmoy=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctr1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctr2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctr3=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctr4=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ctr5=new Container(new BoxLayout(BoxLayout.X_AXIS));
//        ajouCom = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        affichCom = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
//        ajouCom.add(Com);
//    ajouCom.add(ajouterCom);
        
        


        usr= new SpanLabel(expr.getUtilisateur().getPrenom());
        etab= new SpanLabel(expr.getEtablissement().getNom_etablissement());
        date= new SpanLabel(expr.getDate_exp());
//        desc= new SpanLabel();
//        desc.setText(expr.getDescription_experience());

        desc = new SpanLabel(expr.getDescription_experience());
        desc.getTextAllStyles().setFont(mediumPlainMonospaceFont);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100,130, 0xffff2700), true);
        Image i = URLImage.createToStorage(placeholder, expr.getPreuve(), "http://localhost/symfony/web/uploads/images/"+ expr.getPreuve(), URLImage.RESIZE_SCALE);
        ImageViewer iv = new ImageViewer(i);
        
        
        listecrit= scrit.getListCrit(expr.getEtablissement().getCategorie().getNom_categorie());
        
        l1.setText(listecrit.get(0).getNom_critere_evaluation());
        l2.setText(listecrit.get(1).getNom_critere_evaluation());
        l3.setText(listecrit.get(2).getNom_critere_evaluation());
        l4.setText(listecrit.get(3).getNom_critere_evaluation());
        l5.setText(listecrit.get(4).getNom_critere_evaluation());
        l6.setText("Moyenne  ");
        
         ctmoy.add(createForFont(smallBoldSystemFont, l6.getText()));
//         ctr1.add(createForFont(smallBoldSystemFont, l1.getText()));
//         ctr2.add(createForFont(smallBoldSystemFont, l2.getText()));
//         ctr3.add(createForFont(smallBoldSystemFont, l3.getText()));
//         ctr4.add(createForFont(smallBoldSystemFont, l4.getText()));
//         ctr5.add(createForFont(smallBoldSystemFont, l5.getText()));

         ctr1.add(createForFont(smallPlainProportionalFont, l1.getText()));
         ctr2.add(createForFont(smallPlainProportionalFont, l2.getText()));
         ctr3.add(createForFont(smallPlainProportionalFont, l3.getText()));
         ctr4.add(createForFont(smallPlainProportionalFont, l4.getText()));
         ctr5.add(createForFont(smallPlainProportionalFont, l5.getText()));


//        ctlab2.add(l6);
//        ctlab2.add(l1);
//        ctlab2.add(l2);
//        ctlab2.add(l3);
//        ctlab2.add(l4);
//        ctlab2.add(l5);
        
        ctlab.add(usr);
        ctlab.add(etab);
        ctlab.add(createForFont(smallPlainProportionalFont, date.getText()));
        
        

//        ctlab.add(date);

//        ctlab.add(desc);
        
        ServiceEvaluation seval = new ServiceEvaluation();
        List<Evaluation> listeval = new ArrayList<>();

       listeval= seval.getListEval(expr.getId_exp());
        rat1 = new Rating();
        s1= new Slider();
        s1=rat1.createStarRankSlider();
        s1.setProgress((int)listeval.get(0).getNote());
        s1.setEditable(false);

        rat2 = new Rating();
        s2= new Slider();
        s2=rat2.createStarRankSlider();
        s2.setProgress((int)listeval.get(1).getNote());
        s2.setEditable(false);


        rat3 = new Rating();
        s3= new Slider();
        s3=rat3.createStarRankSlider();
        s3.setProgress((int)listeval.get(2).getNote());
        s3.setEditable(false);


        rat4 = new Rating();
        s4= new Slider();
        s4=rat4.createStarRankSlider();
        s4.setProgress((int)listeval.get(3).getNote());
        s4.setEditable(false);

        rat5 = new Rating();
        s5= new Slider();
        s5=rat5.createStarRankSlider();
        s5.setProgress((int)listeval.get(4).getNote());
        s5.setEditable(false);


        rat6 = new Rating();
        s6= new Slider();
        s6=rat6.createStarRankSlider();
        s6.setProgress(expr.getNoteExp());
        s6.setEditable(false);
       
        ctr1.add(s1);
        ctr2.add(s2);
        ctr3.add(s3);
        ctr4.add(s4);
        ctr5.add(s5);

        ctmoy.add(s6);
        
        ctx.add(iv);
        ctx.add(ctlab);
        ctx2.add(ctlab2);
        ctx2.add(ctrat);

//        
//        ctrat.add(s1);
//        ctrat.add(s2);
//        ctrat.add(s3);
//        ctrat.add(s4);
//        ctrat.add(s5);
        
        
        kbir.add(ctx);
        kbir.add(desc);

        kbir.add(ctmoy);
        kbir.add(ctr1);
        kbir.add(ctr2);
        kbir.add(ctr3);
        kbir.add(ctr4);
        kbir.add(ctr5);
       
        kbir.add(ctx2);
          ServiceCommentaire serviceTask=new ServiceCommentaire();
         ArrayList<Commentaire> listTasks = serviceTask.getListCom(expr.getId_exp());
         Container root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container king = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container Ajout = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          TextField nv =new TextField("","Ajouter commentaire",20, TextField.NUMERIC);
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
       public void actionPerformed(ActionEvent t) {
           Commentaire commentaire= new Commentaire();
        ConnectionRequest con = new ConnectionRequest();
                String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/comjsonnew2?commentaire="+nv.getText()+
                       "&id="+ Authentification.connectedUser.getId()+"&idExp="+expr.getId_exp();
                Dialog.show("Confirmation", "Votre commentaire a été ajouté ", "Ok", null);
                
               // Message m = new Message("yo");
//       Display.getInstance().sendMessage(new String[]{"hot lhné lmail "}, "hey", m);
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
        f.revalidate();
        AffichExp aa=new AffichExp(expr);
                
                aa.getF().show();
        }
       });
            int z =0;
        for(Commentaire pub:listTasks){
            z++;
               // Container C = new Container(new GridLayout(1, 2));
                Container root2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 Container img = new Container(new BoxLayout(BoxLayout.X_AXIS));
          
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
          Dialog.show("Confirmation", "supprimer ce commentaire?? ","oui", "Annuler");
        con.setUrl(Url);
                            System.out.println(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
         f.revalidate();
        AffichExp aa=new AffichExp(expr);
                
                aa.getF().show();
                        }
                        
                        
                        }); 
            ArrayList<Client> listclient=serviceTask.getListClient("http://localhost/symfony/web/app_dev.php/BonPlan/comjsonuser/"+pub.getId_commentaire());
            
               for(Client cl:listclient){
                   
                   
                    Label Message = new Label();
                
                Label Message2 = new Label();
                  Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                   Message.setText(cl.getNom());
                  
                Message2.setText(cl.getPrenom());
                  C1.add(Message);
                
                C1.add(Message2);
                   System.out.println(Message);
                   System.out.println(Message2);
                  // System.out.println(etab);
                   Label label = new Label();
                      int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                      Image placeholder1 = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder1, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + "http://localhost/symfony/web/uploads/images/" + cl.getPhoto_user()
                    + "", "http://localhost/symfony/web/uploads/images/" + cl.getPhoto_user()
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
//                     EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 150), true);
//                            URLImage imgg1 = URLImage.createToStorage(img1, cl.getPhoto_user(),
//                                    "http://localhost/symfony/web/images/"+cl.getPhoto_user());
//                            imgg1.fetch();
//                           // Image bla = imgg1.getImage("bla.jpg");
//                            ImageViewer imgv1 = new ImageViewer(imgg1);
                          //   Container C1 = new Container(BoxLayout.y());
                    img.add(label);
                    img.add(C1);
                  //  img.add(C2);
                  //  root2.add(img);
                   //   root2.add(C1);
                   
                   //  f.add(C1);
                   
               }
                       
                     Container btn = new Container(new BoxLayout(BoxLayout.X_AXIS));
                     System.out.println(pub.getIdUcomm());
                    if(pub.getIdUcomm().equals(String.valueOf(Authentification.connectedUser.getId()))){
                   btn.add(b);
                   }
            
            
              //   root2.add(C2);
                 
                 root.add(img);
                 root2.add(C2);
                 root2.add(btn);
            root.add(root2);
              
                // f.add(C);
         //   System.out.println(Message);
          //  System.out.println(Message2);
        }
        //kbir.add(ajouCom);

     king.add(root);
//       f.add(ajouCom);
//       f.add(affichCom);
        f.add(kbir);
        
        f.add(king);
        f.add(Ajout);
}
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
          public Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }


}