/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Etablissement;
import Entities.Publicite;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServicePublicite {
    public ArrayList<Publicite> getList2(int id) {
        ArrayList<Publicite> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/Publicite/"+id+"/");
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
                        Publicite P = new Publicite();
                        float id = Float.parseFloat(obj.get("idPublicite").toString());
                        P.setId_publicite((int) id);
                        P.setPhoto_publicite(obj.get("photoPublicite").toString()); 
                        P.setTitre(obj.get("titre").toString()); 
                        P.setDescription_publicite(obj.get("descriptionPublicite").toString());


                        listTasks.add(P);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Etablissement> getListEtab(int id) {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/etabM");
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
    public ArrayList<Publicite> getList3() {
        ArrayList<Publicite> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/Publicite1");
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
                        Publicite P = new Publicite();
                        float id = Float.parseFloat(obj.get("idPublicite").toString());
                        P.setId_publicite((int) id);
                        P.setPhoto_publicite(obj.get("photoPublicite").toString()); 
                        P.setTitre(obj.get("titre").toString()); 
                        P.setDescription_publicite(obj.get("descriptionPublicite").toString());


                        listTasks.add(P);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
}
