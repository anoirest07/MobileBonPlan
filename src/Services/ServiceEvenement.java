/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Entities.Etablissement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Evenement;
import Entities.Interesser;
import Entities.Utilisateur;
import com.codename1.components.ImageViewer;
import com.codename1.io.File;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceEvenement {

    public ArrayList<Evenement> getList2(int id) {
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/even/" + id);
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
                        Evenement E = new Evenement();
                        float id = Float.parseFloat(obj.get("idEvenement").toString());

                        Map<String, Object> debutsession = (Map<String, Object>) obj.get("dateEvenement");
                        double td = (double) debutsession.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd-MM-yyyy ").format(new Date(xd));
                        Date datedebut = new SimpleDateFormat("dd-MM-yyyy").parse(formatd);
                        System.out.println(formatd);
                        System.out.println(datedebut);
                        E.setDate_evenement(datedebut);
                        Map<String, Object> etab = (Map<String, Object>) obj.get("idEtablissement");
                        E.setId_evenement((int) id);
                        E.setNom_evenement(obj.get("nomEvenement").toString());
                        E.setDescription(obj.get("descriptionEvenement").toString());
                        E.setPhoto(obj.get("photoEvenement").toString());
                        E.getEtab().setNom_etablissement(etab.get("nomEtablissement").toString());
                        E.getEtab().setLat((Double) etab.get("latitude"));
                        E.getEtab().setLong((Double) etab.get("longitude"));
                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<Evenement> getList() {
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/evenClient");
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
                        Evenement E = new Evenement();
                        float id = Float.parseFloat(obj.get("idEvenement").toString());

                        Map<String, Object> debutsession = (Map<String, Object>) obj.get("dateEvenement");
                        double td = (double) debutsession.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd-MM-yyyy ").format(new Date(xd));
                        Date datedebut = new SimpleDateFormat("dd-MM-yyyy").parse(formatd);
                        System.out.println(formatd);
                        System.out.println(datedebut);
                        E.setDate_evenement(datedebut);
                        Map<String, Object> etab = (Map<String, Object>) obj.get("idEtablissement");

                        E.setId_evenement((int) id);

                        E.setNom_evenement(obj.get("nomEvenement").toString());
                        E.setDescription(obj.get("descriptionEvenement").toString());
                        E.setPhoto(obj.get("photoEvenement").toString());
                        E.getEtab().setNom_etablissement(etab.get("nomEtablissement").toString());
                        E.getEtab().setLat((Double) etab.get("latitude"));
                        E.getEtab().setLong((Double) etab.get("longitude"));
                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<Evenement> recherchEvenements(String nom) {
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/rechJson/" + nom);
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
                        Evenement E = new Evenement();
                        float id = Float.parseFloat(obj.get("idEvenement").toString());

                        Map<String, Object> debutsession = (Map<String, Object>) obj.get("dateEvenement");
                        double td = (double) debutsession.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd-MM-yyyy ").format(new Date(xd));
                        Date datedebut = new SimpleDateFormat("dd-MM-yyyy").parse(formatd);
                        System.out.println(formatd);
                        System.out.println(datedebut);
                        E.setDate_evenement(datedebut);
                        Map<String, Object> etab = (Map<String, Object>) obj.get("idEtablissement");

                        E.setId_evenement((int) id);

                        E.setNom_evenement(obj.get("nomEvenement").toString());
                        E.setDescription(obj.get("descriptionEvenement").toString());
                        E.setPhoto(obj.get("photoEvenement").toString());
                        E.getEtab().setNom_etablissement(etab.get("nomEtablissement").toString());
                        E.getEtab().setLat((Double) etab.get("latitude"));
                        E.getEtab().setLong((Double) etab.get("longitude"));
                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<Etablissement> getListEtab(int id) {
        ArrayList<Etablissement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/etabM/" + id);
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
                        Etablissement E = new Etablissement();
                        E.setNom_etablissement(obj.get("nomEtablissement").toString());
                        float id = Float.parseFloat(obj.get("idEtablissement").toString());

                        E.setId_etablissement((int) id);

                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<Client> getListinteresser(int id) {
        ArrayList<Client> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/listinteresser/" + id);
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
                        Client E = new Client();

                        float id = Float.parseFloat(obj.get("id").toString());
                        E.setId((int) id);
                        E.setNom(obj.get("nom").toString());
                        E.setPhoto_user(obj.get("photoUser").toString());

                        listTasks.add(E);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void ajouteven(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/new/"
                + ta.getNom_evenement() + "/" + ta.getDescription() + "/"
                + ta.getDate_evenement() + "/" + ta.getEtab().getId_etablissement() + "/" + ta.getPhoto();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

            System.out.println("ajout ok");

        });
        System.out.println("before");
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("after");

    }

    public void Updateeven(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/BonPlan/updateJson/"
                + ta.getNom_evenement() + "/" + ta.getDescription() + "/"
                + ta.getDate_evenement() + "/" + ta.getEtab().getId_etablissement() + "/" + ta.getId_evenement();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("modif ok");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void setImage(String filePath, ImageViewer iv) {
        try {
            //creation d'image apartir du filepath
            Image i1 = Image.createImage(filePath).scaled(400, 400);
            iv.setImage(i1);
            if (i1 != null) {
                //FileSystemStorage  
                //trodek tnajm testoki l image en binaire
                ImageIO imgIO = ImageIO.getImageIO();
                //stocker l'inage dans le flux
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                imgIO.save(i1, out, File.separator, 1);
                //recuperer l image du flux dans un tab binaire
                byte[] ba = out.toByteArray();
                //cryptage de l image binaire
                String Imagecode = Base64.encode(ba);
                ConnectionRequest request = new ConnectionRequest();
                request.addResponseListener((NetworkEvent evt) -> {
                    byte[] data = (byte[]) evt.getMetaData();
                    String imageName = new String(data);
                    System.out.println("metadata " + imageName);
                    iv.getImage().setImageName(imageName);
                });
                request.setPost(true);
                request.setHttpMethod("POST");
                // imagecode sequence binaire de l image coder
                request.addArgument("Image", Imagecode);
                request.setUrl("http://localhost:80/Upload.php");
                NetworkManager.getInstance().addToQueueAndWait(request);
            } else {
                System.out.println("Unable to upload");
            }
            iv.getParent().revalidate();

        } catch (Exception ex) {

        }

    }

    public void browseImage(ImageViewer im) {
        //open gallery
        Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {

            if (ev != null && ev.getSource() != null) {
                String filePath = (String) ev.getSource();
                // retenue de nom d'image
//             int fileNameIndex = filePath.lastIndexOf("/") + 1;
//             String fileName = filePath.substring(fileNameIndex);
                // Do something

                setImage(filePath, im);
            }
        }, Display.GALLERY_IMAGE);

    }
}
