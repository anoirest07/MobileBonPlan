/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import Etablissement.ListeDesEtab;
import Evenement.AffichagePropEvenForm;
import Evenement.AffichageclientEvenement;
import Publicite.AffichageClient;
import Publicite.PubliciteController;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author user
 */
public abstract class SideMenuBaseForm1 extends Form {
    
     public SideMenuBaseForm1(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm1(String title) {
        super(title);
    }

    public SideMenuBaseForm1() {
    }

    public SideMenuBaseForm1(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu1(Resources res) {
        
        Image profilePic = res.getImage("logo1.png");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "SideMenuTitle");
       profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Accueil", FontImage.MATERIAL_DASHBOARD,  e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Etablissement", FontImage.MATERIAL_TRENDING_UP,  e -> new ListeDesEtab(res).getF().show());
        getToolbar().addMaterialCommandToSideMenu("  Publicite", FontImage.MATERIAL_TRENDING_UP,e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Evenement", FontImage.MATERIAL_TRENDING_UP,e -> new AffichageclientEvenement(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Offre", FontImage.MATERIAL_TRENDING_UP,e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Produit", FontImage.MATERIAL_TRENDING_UP,e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Actualité", FontImage.MATERIAL_TRENDING_UP,e -> new AffichageClient(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new Authentification(res).getF().show());
        
       
      
    }
    
    protected abstract void showOtherForm(Resources res);
}
