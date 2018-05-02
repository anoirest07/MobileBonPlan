/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author user
 */
public class UserService {
    Utilisateur user;
    ConnectionRequest connectionRequest;
    public Utilisateur GetUserById(int id){
        System.out.println("ey");
        connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/GetUser/"+id);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                    user = new Utilisateur();
                    String res = new String(connectionRequest.getResponseData());
                    JSONParser j = new JSONParser();
                    
                    Map<String, Object> User = j.parseJSON(new CharArrayReader(res.toCharArray()));
                    //Map<String, Object> User = (Map<String, Object>) users.get("root");
                    //System.out.println(User.get("root").toString());
                    System.out.println("user : " + User);
                    String username = User.get("username").toString();
                    int id = (int)Float.parseFloat(User.get("id").toString());
                    String nom = User.get("nom").toString();
                    String prenom = User.get("prenom").toString();
                    String role = User.get("roles").toString();
                    //String adresse= User.get("adresse").toString();
                    
                    user.setUsername(username);
                   // user.setAdresse(adresse);
                    user.setNom(nom);
                    user.setPrenom(prenom);
                    user.setRoles(role);
                    user.setId(id);
                    System.out.println(user.getNom() +"  NAME");
                } catch (IOException ex) {
                    
                }
                    }
        
                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        System.out.println("FINALLY  " + user);
        return user;
        
        
        
    }
    
    
}
