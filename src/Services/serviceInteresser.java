/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.stripe.model.Charge;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author amine
 */
public class serviceInteresser {
   ConnectionRequest connectionRequest;
   Map<String, Object> response;
   public static boolean res;
  
     public boolean isInteresse(int idUser, int idEvent){
      //  System.out.println("ey");
         ArrayList<Boolean> list = new ArrayList<Boolean>();
        connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/isAbo?idUser="+idUser+"&idEvent="+idEvent);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                   
                    String res = new String(connectionRequest.getResponseData());
                    JSONParser j = new JSONParser();
                    response = (Map<String, Object>) j.parseJSON(new CharArrayReader(res.toCharArray()));
                    System.out.println(response);
                    
                    System.out.println("true or false ? "+response.containsValue("true"));
                    if(response.containsValue("true"))
                    {
                        list.add(true);
                    }
                    else{
                        list.add(false);
                    }
                    //res = response.containsValue("true");
                } catch (IOException ex) {
                    
                }
                    }
        
                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        
        return list.get(0);
        
        
        
    }

public void supprimerabo(Utilisateur u,Evenement e){
      connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/deleteIN?idUser="+u.getId()+"&idEvent="+e.getId_evenement());
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {                    
                    String res = new String(connectionRequest.getResponseData());
                    JSONParser j = new JSONParser();
                    response = (Map<String, Object>) j.parseJSON(new CharArrayReader(res.toCharArray()));
                    //res = response.containsValue("true");
                } catch (IOException ex) {
                    
                }
                    }
        
                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        
}


public void interesser(Utilisateur u,Evenement e){
      connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/addAbo?idUser="+u.getId()+"&idEvent="+e.getId_evenement());
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                   
                    String res = new String(connectionRequest.getResponseData());
                    JSONParser j = new JSONParser();
                    response = (Map<String, Object>) j.parseJSON(new CharArrayReader(res.toCharArray()));
                   
                    //res = response.containsValue("true");
                } catch (IOException ex) {
                    
                }
                    }
        
                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        
}
}