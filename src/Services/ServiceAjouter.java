/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Publicite;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.Resources;
import Publicite.AddPublicite;
import Publicite.PubliciteController;
import com.mycompany.myapp.SideMenuBaseForm;
import java.util.Date;

/**
 *
 * @author user
 */
public class ServiceAjouter extends SideMenuBaseForm{
    Form f;
    Resources theme;
    public void Ajouter(Publicite p){
        
    ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/add/publicite/" 
                     + p.getTitre()
                     + '/' + p.getDescription_publicite()
                      + '/' + p.getPhoto_publicite()+ 
                        '/'+p.getEtablissement().getId_etablissement()+'/'+p.getDateDebut());
           
            
            
//            PubliciteController h = new PubliciteController(theme);
//            h.show();
            
            
         NetworkManager.getInstance().addToQueue(req);
    
}

    @Override
    protected void showOtherForm(Resources res) {
         //To change body of generated methods, choose Tools | Templates.
    }
}
