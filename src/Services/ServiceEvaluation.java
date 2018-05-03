/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Etablissement;
import Entities.Evaluation;
import Entities.Experience;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServiceEvaluation {
    public void ajoutTask(Evaluation eval) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/expeval/evals/ajoutEval/"+eval.getExperience().getId_exp()+"/"+eval.getCritere_evaluation().getId_critere()+"?note="+eval.getNote();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            Dialog.show("Ajout evaluation", "Ajout avec succes", "OK",null);

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
    
    public ArrayList<Evaluation> getListEval(int idExp) {
        ArrayList<Evaluation> listEval = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/expeval/evaluations/affichEval/"+ idExp );
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
                       Evaluation eval = new Evaluation();

                        float id = Float.parseFloat(obj.get("note").toString());
                        eval.setNote((int)id);                        

                        
                        listEval.add(eval);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEval;
    }
    
}
