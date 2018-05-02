/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Evenement;

import Entities.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;


/**
 *
 * @author Mohamed
 */
public class MapForm 
{
    Form hi;

   
    public MapForm(double lat, double lon, String name) {
        
        hi = new Form(name);
        hi.setLayout(new BorderLayout());
         MapContainer cnt = new MapContainer();
        hi.addComponent(BorderLayout.CENTER, cnt);
        cnt.setCameraPosition(new Coord(lat, lon));
        try {
            EncodedImage ic = EncodedImage.create("/star.png");
            ic.scale(32, 37);
            cnt.setCameraPosition(new Coord(lat, lon));
                    cnt.addMarker(ic, new Coord(lat, lon), "Hi marker", "Optional long description", new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Détails", name, "OK", null);
                        }
                    });
                    
        } catch (IOException ex) {
           
        }
        
        
        hi.show();
    }
    
    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = f;
    }

   
    
}
