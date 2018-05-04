/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Etablissement;
import Entities.budget;
import Etablissement.MesEtabs;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Nadia
 */
public class ServiceEtablissement {
    Resources theme;
     public int nb =0;
    public ArrayList<Etablissement> getList2() {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/listedesetablissementsClients");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
//                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
//                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Etablissement task = new Etablissement();
                        task.setId_etablissement((int) (double) obj.get("idEtablissement"));
                        task.setNom_etablissement(obj.get("nomEtablissement").toString());
                        task.setAdresse_etablissement(obj.get("adresseEtablissement").toString());
                        Map<String,Object> e = (Map<String,Object>) obj.get("idCategorie"); 
                         Categorie c = new Categorie();
                         float idCat = Float.parseFloat(e.get("idCategorie").toString());
                         c.setId_categorie((int)idCat);
                         c.setNom_categorie(e.get("nomCategorie").toString());
                         int i1=e.get("enabled").toString().equals("true") ? 1:0 ;
                         c.setEnabled(i1);
                 
                    
                    task.setCategorie((Categorie) c);    
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                    System.out.println(obj.get("budget"));
                      task.setBudget( obj.get("budget").toString());
                    
//                        task.setLat((double)obj.get("latitude"));
//                        task.setLong((double)obj.get("longitude"));
//                        task.setTelephone_etablissement((int) obj.get("telephoneEtablissement"));
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setDescription_etablissement(obj.get("descriptionEtablissement").toString());
//                         new Date((long) Float.parseFloat(obj.get("ouverture").toString()));
//                        //double t = (double) ouverture.get("timestamp");
//                        //long x = (long) (t * 1000L); 
//                        //String format = new SimpleDateFormat("HH:mm:ss").format(new Date(x));
//                        //Date tdate= new SimpleDateFormat("HH:mm:ss").parse(format);
//                       Map<String, Object> fermeture = (Map<String, Object>) obj.get("fermeture");
//                        double t1 = (double) fermeture.get("timestamp");
//                        long x1 = (long) (t1 * 1000L); 
////                        String format1 = new SimpleDateFormat("HH:mm:ss").format(new Date(x1)); 
////                        Date tdate1= new SimpleDateFormat("HH:mm:ss").parse(format1);
//                     //    new Date((long) Float.parseFloat(x1.toString()));
//                        task.setOuverture(new Date((long) Float.parseFloat(obj.get("ouverture").toString())));  
//                        task.setFermeture(new Date((long) Float.parseFloat(obj.get("fermeture").toString())));
             task.setCode_postal((int)(double) obj.get("codePostal"));
             task.setSite_web(obj.get("siteWeb").toString());
             task.setTelephone_etablissement((int)(double) obj.get("telephoneEtablissement"));
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
      public ArrayList<Etablissement> RechercheEtabParNom(String nom) {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/EtabParNom/"+nom);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
//                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
//                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Etablissement task = new Etablissement();
                        task.setId_etablissement((int) (double) obj.get("idEtablissement"));
                        task.setNom_etablissement(obj.get("nomEtablissement").toString());
                        task.setAdresse_etablissement(obj.get("adresseEtablissement").toString());
                     Map<String,Object> e = (Map<String,Object>) obj.get("idCategorie"); 
                         Categorie c = new Categorie();
                         float idCat = Float.parseFloat(e.get("idCategorie").toString());
                         c.setId_categorie((int)idCat);
                         c.setNom_categorie(e.get("nomCategorie").toString());
                         int i1=e.get("enabled").toString().equals("true") ? 1:0 ;
                         c.setEnabled(i1);
                 
                    
                    task.setCategorie((Categorie) c);    
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                    System.out.println(obj.get("budget"));
                      task.setBudget( obj.get("budget").toString());
                    
//                        task.setLat((double)obj.get("latitude"));
//                        task.setLong((double)obj.get("longitude"));
//                        task.setTelephone_etablissement((int) obj.get("telephoneEtablissement"));
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setDescription_etablissement(obj.get("descriptionEtablissement").toString());
                         Map<String, Object> ouverture = (Map<String, Object>) obj.get("ouverture");
                        double t = (double) ouverture.get("timestamp");
                        long x = (long) (t * 1000L); 
                        String format = new SimpleDateFormat("HH:mm:ss").format(new Date(x));
                        Date tdate= new SimpleDateFormat("HH:mm:ss").parse(format);
                        Map<String, Object> fermeture = (Map<String, Object>) obj.get("fermeture");
                        double t1 = (double) fermeture.get("timestamp");
                        long x1 = (long) (t1 * 1000L); 
                        String format1 = new SimpleDateFormat("HH:mm:ss").format(new Date(x1)); 
                        Date tdate1= new SimpleDateFormat("HH:mm:ss").parse(format1);

                        task.setOuverture(tdate);
                        task.setFermeture(tdate1);
             task.setCode_postal((int)(double) obj.get("codePostal"));
             task.setSite_web(obj.get("siteWeb").toString());
             task.setTelephone_etablissement((int)(double) obj.get("telephoneEtablissement"));
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Etablissement> MesEtabs(int id) {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/MesEtabsMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
//                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
//                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Etablissement task = new Etablissement();
                        task.setId_etablissement((int) (double) obj.get("idEtablissement"));
                        task.setNom_etablissement(obj.get("nomEtablissement").toString());
                        task.setAdresse_etablissement(obj.get("adresseEtablissement").toString());
                         Map<String,Object> e = (Map<String,Object>) obj.get("idCategorie"); 
                         Categorie c = new Categorie();
                         float idCateg = Float.parseFloat(e.get("idCategorie").toString());
                         c.setId_categorie((int)idCateg);
                         c.setNom_categorie(e.get("nomCategorie").toString());
                         int i1=e.get("enabled").toString().equals("true") ? 1:0 ;
                         c.setEnabled(i1);
                         task.setCategorie((Categorie) c);   
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                    System.out.println(obj.get("budget"));
                      task.setBudget( obj.get("budget").toString());
                    
                        task.setLat((double)obj.get("latitude"));
                        task.setLong((double)obj.get("longitude"));
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setDescription_etablissement(obj.get("descriptionEtablissement").toString());
                        task.setPhoto_patente(obj.get("photoPatente").toString());
                       
         //     task.setOuverture((TimeZone) format);
             //     task.setOuverture((TimeZone) obj.get("fermeture").toString());
             task.setCode_postal((int)(double) obj.get("codePostal"));
             task.setSite_web(obj.get("siteWeb").toString());
             task.setTelephone_etablissement((int)(double) obj.get("telephoneEtablissement"));
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
   
    public ArrayList<String> getEtablissementClient(int id) {
        ArrayList<String> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/UnEtablissement/"+id);
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

//    public void ajouterEtablissement(Etablissement ta) {
//        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://192.168.1.12/BonPlan/web/app_dev.php/BonPlan/AjouterEtablissementMobile/" + ta.getNom_etablissement() + "/" + ta.getAdresse_etablissement()+"/"
//        +ta.getDescription_etablissement()+"/"+ta.getBudget()+"/"+ta.getPhoto_etablissement()+"/"+ta.getPhoto_patente()+"/"
//        +ta.getSite_web()+"/"+ta.getCode_postal()+"/"+"/"+ta.getLat()+"/"+ta.getLong()+"/"+ta.getOuverture()+"/"
//        +ta.getFermeture()+"/"+ta.getTelephone_etablissement();
//        con.setUrl(Url);
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
////            if (str.trim().equalsIgnoreCase("OK")) {
////                f2.setTitle(tlogin.getText());
////             f2.show();
////            }
////            else{
////            Dialog.show("error", "login ou pwd invalid", "ok", null);
////            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    }
    
   

    public void ajouterEtablissement(Etablissement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/AjouterEtablissementMobile/" + ta.getNom_etablissement() + "/" + ta.getAdresse_etablissement()+"/"
        +ta.getDescription_etablissement()+"/"+ta.getBudget()+"/"+ta.getPhoto_etablissement()+"/"+ta.getPhoto_patente()+"/"
        +ta.getSite_web()+"/"+ta.getCode_postal()+"/"+"/"+ta.getLat()+"/"+ta.getLong()+"/"+ta.getOuverture()+"/"
        +ta.getFermeture()+"/"+ta.getTelephone_etablissement();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        
     public void suppTask (int id) {
        try {
        ConnectionRequest con = new ConnectionRequest();
            con.setPost(true);
            con.setContentType("application/json");
            con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/SupprimerEtablissement/" + id);
            con.addResponseListener((e) -> {
                String str = new String(con.getResponseData());
                System.out.println(str);
                    
                            

            });
            NetworkManager.getInstance().addToQueueAndWait(con);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
      public int  nbEtab(int id) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/StatistiqueMobile/"+id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            ServiceEtablissement ws = new ServiceEtablissement();
            String jsonj = new String(con.getResponseData());

            try {

                JSONParser jp = new JSONParser();

                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(jsonj.toCharArray()));
                System.out.println("hhhhhhhhh"+events);

                java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) events.get("root");

                for (Map<String, Object> obj : list) {//
                    this.nb = (int) Float.parseFloat(obj.get("nb").toString());

                
}

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(nb);
          NetworkManager.getInstance().addToQueueAndWait(con);
        return nb;

    }
      public ArrayList<Etablissement> getListEtab() {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/etabproduits");
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
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
                        Etablissement E = new Etablissement();
                        E.setNom_etablissement(obj.get("nomEtablissement").toString()); 
                        float id = Float.parseFloat(obj.get("idEtablissement").toString());
                        E.setId_etablissement((int)id);
                            


                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
      
    public ArrayList<Etablissement> getListEtab(String nomCat) {
        ArrayList<Etablissement> listEtab = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/etabs/allEtabCat/"+ nomCat );
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
                        Etablissement etab = new Etablissement();

                        float id = Float.parseFloat(obj.get("idEtablissement").toString());
                        etab.setId_etablissement((int)id);                        

                        etab.setNom_etablissement(obj.get("nomEtablissement").toString());
                        
                         Map<String,Object> e = (Map<String,Object>) obj.get("idCategorie"); 
                        Categorie cat = new Categorie();

                        float idcat = Float.parseFloat(e.get("idCategorie").toString());
                        cat.setId_categorie((int)idcat);  
                        cat.setNom_categorie(e.get("nomCategorie").toString());

                        etab.setCategorie(cat);  
                        
                        listEtab.add(etab);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtab;
    }
    
    
    public Etablissement getEtab(String nomEtab) {
        Etablissement etab = new Etablissement();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/etabs/oneEtabNom/"+ nomEtab );
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

                        float id = Float.parseFloat(obj.get("idEtablissement").toString());
                        etab.setId_etablissement((int)id);                        

                        etab.setNom_etablissement(obj.get("nomEtablissement").toString());
                        
                        Map<String,Object> e = (Map<String,Object>) obj.get("idCategorie"); 
                        Categorie cat = new Categorie();

                        float idcat = Float.parseFloat(e.get("idCategorie").toString());
                        cat.setId_categorie((int)idcat);  
                        cat.setNom_categorie(e.get("nomCategorie").toString());

                        etab.setCategorie(cat);  
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return etab;
    }


}
