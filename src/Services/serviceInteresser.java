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
import java.io.IOException;
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
    public void isinteresser(Utilisateur ta ,Evenement e) {
        ConnectionRequest con = new ConnectionRequest();
     //   con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/isinteresserJson/"+ta.getId()+"/"+ta.getU());
        
    
    }
     public boolean isInteresse(int idUser, int idEvent){
      //  System.out.println("ey");
        connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/isAbo?idUser="+idUser+"&idEvent="+idEvent);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                   
                    String res = new String(connectionRequest.getResponseData());
                    JSONParser j = new JSONParser();
                    response = (Map) j.parseJSON(new CharArrayReader(res.toCharArray()));
                    System.out.println("true or false ? "+response.containsValue("true"));
                    //res = response.containsValue("true");
                } catch (IOException ex) {
                    
                }
                    }
        
                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        
        return true;
        
        
        
    }



}