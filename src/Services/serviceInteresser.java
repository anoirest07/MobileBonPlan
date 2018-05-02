/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Interesser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Evenement.AffichagePropEvenForm;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class serviceInteresser {
   
    public void isinteresser(Interesser ta ,Evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/isinteresserJson/"+ta.getE()+"/"+ta.getU());
        
    
    }



}