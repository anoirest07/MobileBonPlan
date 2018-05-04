/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etablissement;

import Entities.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author amine
 */
public class GoogleMaps {
     Coord LL;
    final MapContainer cnt;
    String[] Adds;
    Resources theme;
   // Session SS;
    
    public String[] searchLocations(String text) 
    {
    try 
    {
        if(text.length() > 0) 
        {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key", "AIzaSyAAxYLLxmNPFJLibEfU5szcn1HCIp38HJI");
            r.addArgument("input", text);
            r.addArgument("components", "country:tn");
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result;
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            //System.out.println(result);
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            String[] resu = Result.fromContent(result).getAsStringArray("//place_id");
            LL = LongLat(resu[0]);
            cnt.setCameraPosition(LL);
            cnt.zoom(LL, 18);
            return res;
        }
    } 
    catch(Exception err) 
    {
        System.out.println(err);
    }
    return null;
    }
    
    public Coord LongLat(String PI)
    {
        try
        {
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        r.setUrl("https://maps.googleapis.com/maps/api/place/details/json");
        r.addArgument("key", "AIzaSyAAxYLLxmNPFJLibEfU5szcn1HCIp38HJI");
        r.addArgument("placeid", PI);
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String,Object> result;
        result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
        double[] resa = Result.fromContent(result).getAsDoubleArray("//geometry/location/lat");
        System.out.println(Result.fromContent(result).get("//geometry"));
        double[] reso = Result.fromContent(result).getAsDoubleArray("//geometry/location/lng");
        Adds = Result.fromContent(result).getAsStringArray("//formatted_address");
        return new Coord(resa[0], reso[0]);
        }
        catch (IOException E)
        {
            System.out.println(E);
        }
        return null;
    }
    
    public GoogleMaps()
    {
       // this.SS = SS;
        Form hi = new Form("Google Maps");
        hi.setLayout(new BorderLayout());
        cnt = new MapContainer("AIzaSyCeIRbinQ5bJ2h_Qk9i558DRTg9PranZQ0");
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField ac = new AutoCompleteTextField(options)
        {
        @Override
        protected boolean filter(String text) 
        {
         if(text.length() == 0) 
         {
//             cnt.zoom(LL, 10);
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) 
         {
             return false;
         }

         options.removeAll();
         int i = 1;
         for(String s : l) 
         {
//             if (i == 2)
//             {
//                 break;
//             }
             options.addItem(s);
             i++;
         }
         return true;
        }
    };
    ac.setMinimumElementsShownInPopup(5);
    ac.setHint("Précisez Votre Adresse ...");
    Style S = new Style();
    S.setBgTransparency(0xff);
    S.setBorder(Border.createLineBorder(1));
    S.setPaddingBottom(15);
    ac.setUnselectedStyle(S); ac.setSelectedStyle(S);
    ac.addPointerPressedListener(new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                Coord T = new Coord(36.8386651, 10.0304474);
                cnt.zoom(T, 10);
            }

          

           
        });
    Button BO = new Button("OK");
    
    
    
    BO.addActionListener(new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                AjouterEtab UD = new AjouterEtab(theme);
                if (Adds != null)
                {
                  UD.getTadr().setText(Adds[0]);
                  UD.tlong.setText(Double.toString(LL.getLongitude()));
                  UD.tlat.setText(Double.toString(LL.getLatitude()));

                }
                else
                {
                    UD.getTadr().setText("");
                }
                UD.getF().show();
            }
        });
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
    hi.getToolbar().addCommandToLeftBar("", icon, new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
//                UneDemande UD = new UneDemande(SS);
//                UD.getF().show();
            }
        });
    Container CN = new Container(new BoxLayout(BoxLayout.X_AXIS));
    CN.add(ac).add(BO);
   
    Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.north(CN));
    hi.add(BorderLayout.CENTER, root);
    hi.show();
    }
}
