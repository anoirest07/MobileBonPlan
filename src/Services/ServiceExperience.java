/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Etablissement;
import Entities.Experience;
import Entities.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServiceExperience {

    public ArrayList<Experience> getList2() {
        ArrayList<Experience> listExps = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/expeval/experiences/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Experience exp = new Experience();

                        float id = Float.parseFloat(obj.get("idExp").toString());
                        exp.setId_exp((int)id);                        

//                        exp.setDate_exp((Date)obj.get("dateExp"));
                        exp.setDescription_experience(obj.get("descriptionExperience").toString());
                     
                        exp.setPreuve(obj.get("preuve").toString());
                        
                        
                        float note = Float.parseFloat(obj.get("noteExp").toString());
                        exp.setNoteExp((int)note);
                        
                        Map<String,Object> e = (Map<String,Object>) obj.get("id"); 
                        Utilisateur user = new Utilisateur();

                        float iduser = Float.parseFloat(e.get("id").toString());
                        user.setId((int)iduser);  
                        user.setNom(e.get("nom").toString());
                        user.setPrenom(e.get("prenom").toString());

                        exp.setUtilisateur(user);  
                        
                        Map<String,Object> b = (Map<String,Object>) obj.get("idEtablissement"); 
                        Etablissement etb = new Etablissement();
                        
                        float idetb = Float.parseFloat(b.get("idEtablissement").toString());
                        
                        etb.setId_etablissement((int)idetb);
                        
                        Map<String,Object> u = (Map<String,Object>) b.get("id"); 
                        Utilisateur usr = new Utilisateur();

                        float idusr = Float.parseFloat(u.get("id").toString());
                        
                        usr.setId((int)idusr);
                        etb.setUtilisateur(usr);
                        
                        etb.setNom_etablissement(b.get("nomEtablissement").toString());
                        
                        Map<String, Object> c = (Map<String, Object>)b.get("idCategorie");
                        Categorie cat = new Categorie();
                         float idcat = Float.parseFloat(c.get("idCategorie").toString());
                        cat.setId_categorie((int)idcat);
//                        cat.setId_categorie(c.get("idCategorie"));
                        
                        cat.setNom_categorie(c.get("nomCategorie").toString());
                        
                        etb.setCategorie(cat);

                        exp.setEtablissement(etb);  
                        
                        
                        Map<String, Object> dateexp = (Map<String, Object>) obj.get("dateExp");
                        
                        float t = Float.parseFloat(dateexp.get("timestamp").toString()) ;
                        long x = (long) (t * 1000L);
                      
                        String format = new SimpleDateFormat("dd/MM/yyyy").format(new Date(x));
//                        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(format);
                        System.out.println("format"+format);
                        
                        exp.setDate_exp(format);
                        
                        listExps.add(exp);
                    }
                }
                catch (IOException ex)
                {
                } 
            }        
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return listExps;
    }
    
    
    
     public int ajoutTask(Experience exp, int idUser, int idEtab, String desc, String preuve, int note, Date debut) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/expeval/experiences/AjoutExp/" + idUser + "/" + idEtab + "?descriptionExperience=" + desc + "&preuve=" + preuve + "&dateExp=" + debut+ "&noteExp=" + note;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            
              JSONParser jsonp = new JSONParser();
                
               
        Map<String, Object> tasks;
            try {
                tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                

                        float id = Float.parseFloat(tasks.get("idExp").toString());
                        exp.setId_exp((int)id);
                        
                     

            } catch (IOException ex) {
            }
            
            
            
            

//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         System.out.println("ID EXP "+exp.getId_exp());
        return exp.getId_exp();
    }
     
         
     public int modifTask(Experience exp, String desc, String preuve) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/expeval/experiences/modifExp/" + exp.getId_exp() + "?descriptionExperience=" + desc + "&preuve=" + preuve;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            
              JSONParser jsonp = new JSONParser();
                
               
        Map<String, Object> tasks;
            try {
                tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                

                        float id = Float.parseFloat(tasks.get("idExp").toString());
                        exp.setId_exp((int)id);
                        
                     

            } 
            catch (IOException ex) {
            }
            
            
            
            

//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return exp.getId_exp();
    }
     
      public int modifNoteTask(Experience exp, int note) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/expeval/experiences/modifNoteExp/" + exp.getId_exp() + "?noteExp=" + note;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            
              JSONParser jsonp = new JSONParser();
                
               
        Map<String, Object> tasks;
            try {
                tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                

                        float id = Float.parseFloat(tasks.get("idExp").toString());
                        exp.setId_exp((int)id);
                        
                     

            } 
            catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return exp.getId_exp();
    }
     
     
     public void suppTask (int idExp) {
        try {
        ConnectionRequest con = new ConnectionRequest();
            con.setPost(true);
            con.setContentType("application/json");
            con.setUrl("http://localhost/symfony/web/app_dev.php/expeval/experiences/suppExp/" + idExp);
            con.addResponseListener((e) -> {
                String str = new String(con.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
     
//      public Experience getExp() {
//        ArrayList<Experience> listExps = new ArrayList<>();
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/BonPlan/web/app_dev.php/expeval/experiences/all");
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                //listTasks = getListTask(new String(con.getResponseData()));
//                JSONParser jsonp = new JSONParser();
//                
//                try {
//                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks);
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    for (Map<String, Object> obj : list) {
//                        Experience exp = new Experience();
//
//                        float id = Float.parseFloat(obj.get("idExp").toString());
//                        exp.setId_exp((int)id);                        
//
////                        exp.setDate_exp((Date)obj.get("dateExp"));
//                        exp.setDescription_experience(obj.get("descriptionExperience").toString());
//                     
//                        exp.setPreuve(obj.get("preuve").toString());
//                        
//                        
//                        float note = Float.parseFloat(obj.get("noteExp").toString());
//                        exp.setNoteExp((int)note);
//                        
//                        Map<String,Object> e = (Map<String,Object>) obj.get("id"); 
//                        Utilisateur user = new Utilisateur();
//
//                        float iduser = Float.parseFloat(e.get("id").toString());
//                        user.setId_user((int)iduser);  
//                        user.setNom(e.get("nom").toString());
//                        user.setPrenom(e.get("prenom").toString());
//
//                        exp.setUtilisateur(user);  
//                        
//                        Map<String,Object> b = (Map<String,Object>) obj.get("idEtablissement"); 
//                        Etablissement etb = new Etablissement();
//                        
//                        float idetb = Float.parseFloat(b.get("idEtablissement").toString());
//                        
//                        etb.setId_etablissement((int)idetb);
//                        etb.setNom_etablissement(b.get("nomEtablissement").toString());
//
//                        exp.setEtablissement(etb);  
//                        
//                        
//                        Map<String, Object> dateexp = (Map<String, Object>) obj.get("dateExp");
//                        
//                        float t = Float.parseFloat(dateexp.get("timestamp").toString()) ;
//                        long x = (long) (t * 1000L);
//                      
//                        String format = new SimpleDateFormat("MM/dd/yyyy").format(new Date(x));
////                        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(format);
//                        System.out.println("format"+format);
//                        
//                        exp.setDate_exp(format);
//                        
//                        listExps.add(exp);
//                    }
//                }
//                catch (IOException ex)
//                {
//                } 
//            }        
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(con);
//      
//        return listExps;
//    }
    
    
    
}
