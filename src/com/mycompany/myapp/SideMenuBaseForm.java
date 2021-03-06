/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;



import Etablissement.MesEtabs;
import Evenement.AffichagePropEvenForm;
import Experience.FilProp;
import Produit_Offre.AfficheOffres;
import Produit_Offre.AfficheProduits;
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

//import Wishlist.MyFavoriteProducts;

/**
 * Common code that can setup the side menu
 *
 * @author user
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
//        
//        Image profilePic = res.getImage("logoacc.png");
//        Image mask = res.getImage("round-mask.png");
//       mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
     //   profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", "SideMenuTitle");
       //profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
//          sidemenuTop.getAllStyles().setBgColor(0x990033);
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Accueil", FontImage.MATERIAL_DASHBOARD,  e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_DASHBOARD,  e -> new MonProfilProp(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Etablissement", FontImage.MATERIAL_TRENDING_UP,  e -> new MesEtabs(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Publicite", FontImage.MATERIAL_TRENDING_UP,e -> new PubliciteController(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Evenement", FontImage.MATERIAL_TRENDING_UP,e -> new AffichagePropEvenForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Offre", FontImage.MATERIAL_TRENDING_UP,e -> new AfficheOffres(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Produit", FontImage.MATERIAL_TRENDING_UP,e -> new AfficheProduits(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Experience", FontImage.MATERIAL_TRENDING_UP,e -> new FilProp(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new Authentification(res).getF().show());
        
     
      
    }
    
    protected abstract void showOtherForm(Resources res);
}
