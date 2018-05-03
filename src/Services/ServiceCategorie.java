/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.CriteresEvaluation;
import Entities.Etablissement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadia
 */
public class ServiceCategorie {
      
    public ArrayList<Categorie> getListCat() {
        ArrayList<Categorie> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/ListeDesCategoriesMobiles");
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
                        Categorie task = new Categorie();
                        task.setId_categorie((int) (double) obj.get("idCategorie"));
                        task.setNom_categorie(obj.get("nomCategorie").toString());
                        
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                     
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     public ArrayList<Categorie> getCaetgParNom(String nom) {
        ArrayList<Categorie> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/categparnom/"+nom);
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
                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Categorie task = new Categorie();
                        task.setId_categorie((int) (double) obj.get("idCategorie"));
                        task.setNom_categorie(obj.get("nomCategorie").toString());
                        
                       int i = obj.get("enabled").toString().equals("true") ? 1:0 ;
                        task.setEnabled(i);
                     
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
      public ArrayList<CriteresEvaluation> getCritereParCateg(int id) {
        ArrayList<CriteresEvaluation> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/CriteresParCateg/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        CriteresEvaluation task = new CriteresEvaluation();
                        task.setId_critere((int) (double) obj.get("idCritere"));
                        task.setNom_critere_evaluation(obj.get("nomCritereEvaluation").toString());
                        Categorie c = new Categorie();
                        c.setId_categorie(id);
                        task.setCategorie(c);
                     
                  listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listTasks);
        return listTasks;
    }
     
}
