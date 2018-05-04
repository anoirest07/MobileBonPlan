/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Etablissement;
import Entities.Favoris;
import com.mycompany.myapp.Authentification;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malek
 */
public class ServiceFavoris {
    
     public void ajoutFavoris(Favoris ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "localhost/symfony/web/app_dev.php/BonPlan/favjsonnew" + ta.getId_etablissement() + "/" +ta.getId_user();
        con.setUrl(Url);

        System.out.println("tt");

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
    
     public ArrayList<Favoris> getList2() {
        ArrayList<Favoris> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/favjson");
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
                        Favoris P = new Favoris();
                         
                         P.setId_etablissement(obj.get("idEtablissement").toString());
                         P.setId_favoris(obj.get("idFavoris").toString());
                       
                        
                        //ArrayList<Etablissement> listEtab=getListEtab("http://localhost/BonPlan3/web/app_dev.php/BonPlan/etabjsonid/"+P.getId_etablissement());
                                
                         
                        listTasks.add(P);
                        System.out.println(P);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     
       public ArrayList<Etablissement> getListEtab(String stringurl) {
           
           System.out.println(stringurl);
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(stringurl);
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
                        Etablissement task = new Etablissement();
                        float id = Float.parseFloat(obj.get("idEtablissement").toString());
                        task.setId_etablissement((int) id);
                        task.setNom_etablissement(obj.get("nomEtablissement").toString());
                        task.setPhoto_etablissement(obj.get("photoEtablissement").toString());
                        task.setAdresse_etablissement(obj.get("adresseEtablissement").toString());
                        task.setSite_web(obj.get("siteWeb").toString());
                        
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}
