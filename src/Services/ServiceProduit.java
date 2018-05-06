/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Produit_Offre.AfficheProduits;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassine
 */
public class ServiceProduit {
    Resources res;
       
    public ArrayList<Produit> getList2(int id) {
        ArrayList<Produit> listProduits = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/affichageprodprop/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                JSONParser jsonp = new JSONParser();
                System.out.println(jsonp.toString());
                                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> prods = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
                    System.out.println(prods.toString());
                                        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");

//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
//                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
//                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) prods.get("root");
                    System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
                    System.out.println(list.toString());
                                        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");

                    for (Map<String, Object> obj : list) {
                        System.out.println(".........................");
                        System.out.println(obj.toString());
                                                System.out.println(".........................");

                        Produit prod = new Produit();

                        prod.setId_produit((int) (double) obj.get("idProduit"));
                        prod.setNom_produit(obj.get("nomProduit").toString());
                        prod.setPrix_produit(Double.valueOf((obj.get("prixProduit").toString())));
                        prod.setPhoto_produit(obj.get("photoProduit").toString());
                        listProduits.add(prod);

//                         Etablissement etab = new Etablissement();
//                         prod.setEtablissement((Etablissement) etab);    
 


                    }
                } catch (IOException ex) {
                }

            }
        });
        

  try{                    NetworkManager.getInstance().addToQueueAndWait(con);

}
  catch (Exception e){
      System.out.println("EXCEPTION ADD TO QUEUE AND WAIT: "+e.getMessage());}      
  
  return listProduits;
    }
    
    public ArrayList<Produit> getListClient(int id) {
        ArrayList<Produit> listProduits = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/findproduitidetab/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                JSONParser jsonp = new JSONParser();
                System.out.println(jsonp.toString());
                                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> prods = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
                    System.out.println(prods.toString());
                                        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");

//                    System.out.println(tasks);
//                    //System.out.println(tasks.size) sa taille =1;
//                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
//                    System.out.println("+++"+tasks.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) prods.get("root");
                    System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
                    System.out.println(list.toString());
                                        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");

                    for (Map<String, Object> obj : list) {
                        System.out.println(".........................");
                        System.out.println(obj.toString());
                                                System.out.println(".........................");

                        Produit prod = new Produit();

                        prod.setId_produit((int) (double) obj.get("idProduit"));
                        prod.setNom_produit(obj.get("nomProduit").toString());
                        prod.setPrix_produit(Double.valueOf((obj.get("prixProduit").toString())));
                        prod.setPhoto_produit(obj.get("photoProduit").toString());
                        listProduits.add(prod);

//                         Etablissement etab = new Etablissement();
//                         prod.setEtablissement((Etablissement) etab);    
 


                    }
                } catch (IOException ex) {
                }

            }
        });
        

  try{                    NetworkManager.getInstance().addToQueueAndWait(con);

}
  catch (Exception e){
      System.out.println("EXCEPTION ADD TO QUEUE AND WAIT: "+e.getMessage());}      
  
  return listProduits;
    }
    
    public void ajouterProduit(String nom_prod, String photo_prod, Double prix_prod, int etab) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/createproduit/"
                + nom_prod + "/" + photo_prod + "/"
                + prix_prod + "/" + etab;
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println(s);
                // if (s.equals("success")) {
                Dialog.show("Confirmation", "Produit Ajouté", "Ok", null);
                new AfficheProduits(res).show();

                //}
            }
        });
    }
    
      public void supprimerProduit(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
      

        if (Dialog.show("Confirmation", "Supprimer ce Produit ?", "Confirmer", "Annuler")) {
      
            con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/deleteprod/" + p.getId_produit());
            con.addResponseListener((e) -> {
                String str = new String(con.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
        }
    }
    }
