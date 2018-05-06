/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Com_Fav.AffichageCom.n;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Client;
import Entities.Commentaire;
import Entities.Experience;
import Entities.Utilisateur;
import com.mycompany.myapp.Authentification;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malek
 */
public class ServiceCommentaire {
    
    public void ajoutCommentaire(Commentaire ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "localhost/symfony/web/app_dev.php/BonPlan/comjsonnew2" + ta.getCommentaire() + "/" + ta.getIdExp() + "/" + ta.getIdUcomm();
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
    
    public ArrayList<Commentaire> getListCom(int id) {
        ArrayList<Commentaire> listCom = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        Experience e = new Experience();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/comjsonid/"+id);
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
                        Commentaire c = new Commentaire();
                         c.setId_commentaire(obj.get("idCommentaire").toString());
                         c.setCommentaire(obj.get("commentaire").toString());
                         c.setIdExp(obj.get("idExp").toString());
                         //c.setIdUcomm(obj.get("idUcomm").toString());
                         String user_id ="";
                user_id = obj.get("idUcomm").toString();
                String id_user=user_id.substring(user_id.indexOf("id=")+3, user_id.indexOf("nom")-4);
                System.out.println("7achty   "+id_user);
            // c.setId_u(id_user);
                         
                        c.setIdUcomm(id_user);
                      
                         if(id_user.equals(String.valueOf(Authentification.connectedUser.getId()))){
                             n++;
                         }
                        
                        //ArrayList<Etablissement> listEtab=getListEtab("http://localhost/BonPlan3/web/app_dev.php/BonPlan/etabjsonid/"+P.getId_etablissement());
                                
                         
                        listCom.add(c);
                        System.out.println(c);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCom;
    }
    
    public ArrayList<Client> getListClient(String stringurl) {
           
           System.out.println(stringurl);
        ArrayList<Client> listCl = new ArrayList<>();
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
                        Client cl = new Client();
                        cl.setPhoto_user(obj.get("photoUser").toString());
                        cl.setNom(obj.get("nom").toString());
                        cl.setPrenom(obj.get("prenom").toString());
                        
                        
                        
                      
                        
                        
                        listCl.add(cl);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCl;
    }
     
}
