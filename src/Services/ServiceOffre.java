/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassine
 */
public class ServiceOffre {

    public ArrayList<Offre> getList2(int id) {
        ArrayList<Offre> listOffres = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/offres");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                JSONParser jsonp = new JSONParser();
                System.out.println(jsonp.toString());
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
                //Résponse rapide
                try {
                    Map<String, Object> ofrs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
                    System.out.println(ofrs.toString());
                    System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");

                    List<Map<String, Object>> list = (List<Map<String, Object>>) ofrs.get("root");
                    System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");
                    System.out.println(list.toString());
                    System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_");

                    for (Map<String, Object> obj : list) {
                        System.out.println(".........................");
                        System.out.println(obj.toString());
                        System.out.println(".........................");

                        Offre offre = new Offre();
                        offre.setId_offre((int) (double) obj.get("idOffre"));
                        offre.setTitre_offre(obj.get("titreOffre").toString());
                        offre.setDescription(obj.get("descriptionOffre").toString());
                        offre.setPhoto(obj.get("photoOffre").toString());

                        Map<String, Object> datedebut = (Map<String, Object>) obj.get("dateDebut");
                        double td = (double) datedebut.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd-mm-yyyy ").format(new Date(xd));
                        Date datedebutoff = new SimpleDateFormat("dd-mm-yyyy").parse(formatd);
                        offre.setDate_debut(datedebutoff);
                        System.out.println("///-------//////--------///////--------///////-------///////");
                        System.out.println(datedebutoff);
                        System.out.println("///-------//////--------///////--------///////-------///////");

                        Map<String, Object> datefin = (Map<String, Object>) obj.get("dateFin");
                        double tdf = (double) datefin.get("timestamp");
                        long xdf = (long) (tdf * 1000L);
                        String formatdf = new SimpleDateFormat("dd-mm-yyyy ").format(new Date(xdf));
                        Date datefinoff = new SimpleDateFormat("dd-mm-yyyy").parse(formatdf);
                        offre.setDate_fin(datefinoff);
                        System.out.println("///-------//////--------///////--------///////-------///////");
                        System.out.println(datefinoff);
                        System.out.println("///-------//////--------///////--------///////-------///////");

                        listOffres.add(offre);

//                         Etablissement etab = new Etablissement();
//                         prod.setEtablissement((Etablissement) etab);    
                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }

            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(con);

        } catch (Exception e) {
            System.out.println("EXCEPTION ADD TO QUEUE AND WAIT: " + e.getMessage());
        }

        return listOffres;
    }

//    public ArrayList<Offre> OffreEtab(int idetab) {
//        ArrayList<Offre> listTasks = new ArrayList<>();
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/MesEtabsMobile/"+idetab);
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                //listTasks = getListTask(new String(con.getResponseData()));
//                JSONParser jsonp = new JSONParser();
//                //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation; 
//                //Résponse rapide
//                try {
//                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
////                    System.out.println(tasks);
////                    //System.out.println(tasks.size) sa taille =1;
////                    System.out.println(tasks.keySet());//root c'est l'élément parent du JSON
////                    System.out.println("+++"+tasks.values());
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    for (Map<String, Object> obj : list) {
//
//                        Offre offre = new Offre();
//
//                        offre.setId_offre((int) (double) obj.get("idOffre"));
//                        offre.setTitre_offre(obj.get("titreOffre").toString());
//                        offre.setDescription(obj.get("descriptionOffre").toString());
//                        offre.setPhoto(obj.get("photoOffre").toString());
//
//                        Map<String, Object> datedebut = (Map<String, Object>) obj.get("dateDebut");
//                        double td = (double) datedebut.get("timestamp");
//                        long xd = (long) (td * 1000L);
//                        String formatd = new SimpleDateFormat("DD/MM/yyyy ").format(new Date(xd));
//                        Date datedebutoff = new SimpleDateFormat("DD/MM/yyyy").parse(formatd);
//                        offre.setDate_debut(datedebutoff);
//                        System.out.println("///-------//////--------///////--------///////-------///////");
//                        System.out.println(datedebutoff);
//                        System.out.println("///-------//////--------///////--------///////-------///////");
//
//                        listTasks.add(offre);
//
//                    }
//                } catch (Exception ex) {
//                }
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listTasks;
//    }

    

    public void ajouterOffre(Offre o) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/createoffre"
                + "/"+ o.getTitre_offre() 
                +"/"+ o.getPhoto()
                + "/" + o.getDescription() 
                +"/"+o.getDate_debut()
                +"/"+o.getDate_fin()
                 
                + "/"+o.getEtablissement().getId_etablissement();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void supprimerOffre(Offre o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/deleteoffre/" + o.getId_offre();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
