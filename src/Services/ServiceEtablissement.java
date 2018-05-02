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
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Nadia
 */
public class ServiceEtablissement {
    
    public ArrayList<Etablissement> getList2() {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://192.168.1.12/BonPlan/web/app_dev.php/BonPlan/listedesetablissementsClients");
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
                       // Categorie c = new Categorie();
             
                 
                    
              //        task.setCategorie((Categorie) c);    
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                    System.out.println(obj.get("budget"));
//                      task.setBudget( obj.get("budget").toString());
                    
//                        task.setLat((double)obj.get("latitude"));
//                        task.setLong((double)obj.get("longitude"));
//                        task.setTelephone_etablissement((int) obj.get("telephoneEtablissement"));
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setDescription_etablissement(obj.get("descriptionEtablissement").toString());
     //             task.setOuverture((TimeZone) obj.get("ouverture"));
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
    public ArrayList<Etablissement> MesEtabs(int id) {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://192.168.1.12/BonPlan/web/app_dev.php/BonPlan/MesEtabsMobile/"+id);
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
                       // Categorie c = new Categorie();
                 
                 
                    
              //        task.setCategorie((Categorie) c);    
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                    System.out.println(obj.get("budget"));
//                      task.setBudget( obj.get("budget").toString());
                    
//                        task.setLat((double)obj.get("latitude"));
//                        task.setLong((double)obj.get("longitude"));
//                        task.setTelephone_etablissement((int) obj.get("telephoneEtablissement"));
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setDescription_etablissement(obj.get("descriptionEtablissement").toString());
     //             task.setOuverture((TimeZone) obj.get("ouverture"));
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
        con.setUrl("http://192.168.1.12/BonPlan/web/app_dev.php/BonPlan/UnEtablissement/"+id);
     
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
}
