/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement;

import Entities.Evenement;
import Entities.Produit;
import Services.ServiceEvenement;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import static com.codename1.io.Log.p;
import com.codename1.media.MediaManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.SideMenuBaseForm1;
import java.io.IOException;

import java.util.ArrayList;

/**
 *
 * @author tanga
 */
public class AffichageclientEvenement extends SideMenuBaseForm1 {

    ConnectionRequest connectionRequest;
    Container f;
    String url2 = "/imggd";
    String url = "http://localhost/symfony/web/uploads/images/";
    EncodedImage enc;

     public AffichageclientEvenement(Resources theme) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        String file = "C:/Users/amine/Desktop/jeuvoiture.mp4";
        try {
            com.codename1.media.Media video = MediaManager.createMedia(file, false);
            video.setVolume(150);
            video.play();
        } catch (IOException err) {

        }
        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Liste Evenements", "Title")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(theme);
        ServiceEvenement sp = new ServiceEvenement();
        ArrayList<Evenement> listE = sp.getList();
        ListModel<String> autoP = new DefaultListModel<>();
        ListModel<URLImage> pictures = new DefaultListModel<>();
        final int size = Display.getInstance().convertToPixels(7);
        final EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xffcccccc), true);
        for (Evenement pr : listE) {
            autoP.addItem(pr.getNom_evenement());
            URLImage urli = URLImage.createToStorage(placeholder,
                    "http://localhost/symfony/web/uploads/images/" + pr.getPhoto(),
                    "http://localhost/symfony/web/uploads/images/" + pr.getPhoto());
            pictures.addItem(urli);
        }
        AutoCompleteTextField ac = new AutoCompleteTextField(autoP);
        ac.setCompletionRenderer(new ListCellRenderer() {
            private final Label focus = new Label();
            private final Label line1 = new Label();
            private final Label icon = new Label();
            private final Container selection = BorderLayout.center(
                    BoxLayout.encloseY(line1)).add(BorderLayout.EAST, icon);

            @Override
            public Component getListCellRendererComponent(com.codename1.ui.List list, Object value, int index, boolean isSelected) {
                for (int iter = 0; iter < autoP.getSize(); iter++) {
                    if (autoP.getItemAt(iter).equals(value)) {
                        line1.setText(autoP.getItemAt(iter));
                        //System.out.println(autoP.getItemAt(iter));
                        icon.setIcon(pictures.getItemAt(iter));
                        break;
                    }

                }
                return selection;
            }

            @Override
            public Component getListFocusComponent(com.codename1.ui.List list) {
                return focus;
            }
        });
        add(ac);
        add(fillContainer(listE));
         theme = UIManager.initFirstTheme("/theme");
        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Resources theme = UIManager.initFirstTheme("/theme");
                if (!"".equals(ac.getText())) {
                    AffichageclientEvenement pl = new AffichageclientEvenement(theme , ac.getText());
                } else {
                    AffichageclientEvenement pl = new AffichageclientEvenement(theme);
                }

            }
        });
        show();
    }

    public AffichageclientEvenement(Resources theme, String name) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        String file = "C:/Users/amine/Desktop/jeuvoiture.mp4";
        try {
            com.codename1.media.Media video = MediaManager.createMedia(file, false);
            video.setVolume(150);
            video.play();
        } catch (IOException err) {

        }
        f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Liste Evenements", "Title")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        setupSideMenu1(theme);
        ServiceEvenement sp = new ServiceEvenement();
        ArrayList<Evenement> listE = sp.recherchEvenements(name);
        ListModel<String> autoP = new DefaultListModel<>();
        ListModel<URLImage> pictures = new DefaultListModel<>();
        final int size = Display.getInstance().convertToPixels(7);
        final EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xffcccccc), true);
        for (Evenement pr : listE) {
            autoP.addItem(pr.getNom_evenement());
            URLImage urli = URLImage.createToStorage(placeholder,
                    "http://localhost/symfony/web/uploads/images/" + pr.getPhoto(),
                    "http://localhost/symfony/web/uploads/images/" + pr.getPhoto());
            pictures.addItem(urli);
        }
        AutoCompleteTextField ac = new AutoCompleteTextField(autoP);
        ac.setHint("Chercher un evenement");
        ac.setMinimumElementsShownInPopup(5);
        ac.setCompletionRenderer(new ListCellRenderer() {
            private final Label focus = new Label();
            private final Label line1 = new Label();
            private final Label icon = new Label();
            private final Container selection = BorderLayout.center(
                    BoxLayout.encloseY(line1)).add(BorderLayout.EAST, icon);

            @Override
            public Component getListCellRendererComponent(com.codename1.ui.List list, Object value, int index, boolean isSelected) {
                for (int iter = 0; iter < autoP.getSize(); iter++) {
                    if (autoP.getItemAt(iter).equals(value)) {
                        line1.setText(autoP.getItemAt(iter));
                        //System.out.println(autoP.getItemAt(iter));
                        icon.setIcon(pictures.getItemAt(iter));
                        break;
                    }

                }
                return selection;
            }

            @Override
            public Component getListFocusComponent(com.codename1.ui.List list) {
                return focus;
            }
        });
        add(ac);
        add(fillContainer(listE));
         theme = UIManager.initFirstTheme("/theme");
        ac.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Resources theme = UIManager.initFirstTheme("/theme");
                if (!"".equals(ac.getText())) {
                    AffichageclientEvenement pl = new AffichageclientEvenement(theme , ac.getText());
                } else {
                    AffichageclientEvenement pl = new AffichageclientEvenement(theme);
                }

            }
        });
        show();
    }

    public static Image getImageFromTheme(String name) {
        try {
            Resources resFile = Resources.openLayered("/theme");
            Image image = resFile.getImage(name);
            return image;
        } catch (IOException ioe) {
            System.out.println("Image " + name + " not found: " + ioe);
        }
        return null;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
        //To change body of generated methods, choose Tools | Templates.
    }

    public Container fillContainer(ArrayList<Evenement> listE) {
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Evenement pr : listE) {
            Container root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label Photo2 = new Label();
            int deviceWidth4 = Display.getInstance().getDisplayWidth() / 11;
            Image placeholder15 = Image.createImage(deviceWidth4, deviceWidth4); //square image set to 10% of screen width
            EncodedImage encImage11 = EncodedImage.createFromImage(placeholder15, false);
            Photo2.setIcon(URLImage.createToStorage(encImage11,
                    "Large_" + "http://localhost/symfony/web/images/explore.png"
                    + "", "http://localhost/symfony/web/images/explore.png"
                    + "", URLImage.RESIZE_SCALE_TO_FILL));
            Button btmap = new Button("Carte");
            btmap.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MAP, btmap.getUnselectedStyle()));
            btmap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double latitude = pr.getEtab().getLat();
                    double longitude = pr.getEtab().getLong();
                    System.out.println("coordinates: " + latitude + " " + longitude);
                    Form f7;
                    MapForm a = new MapForm(latitude, longitude, pr.getNom_evenement());
                    a.getF().show();
                    f7 = a.getF();
                    Toolbar fg = f7.getToolbar();
                    fg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g
                            -> {
                        Resources theme = UIManager.initFirstTheme("/theme");
                         new AffichageclientEvenement(theme).show();
                    });
                }
            });
            Button details = new Button("Détails");
            details.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, details.getUnselectedStyle()));
            details.setWidth(15);
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    DetailEvenement se = new DetailEvenement(pr);
                }
            });
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label label = new Label();
            System.out.println(pr.getPhoto());
            int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            label.setIcon(URLImage.createToStorage(encImage,
                    "Large_" + url2 + pr.getPhoto()
                    + "", url + pr.getPhoto()
                    + ""));

            c.add(label);
            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cnt.add(pr.getNom_evenement());
            SpanLabel date = new SpanLabel(pr.getDate_evenement().toString());
            cnt.add(date);
            cnt.add(pr.getEtab().getNom_etablissement());
            c.add(cnt);
            Container buttons = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttons.add(details);
            buttons.add(btmap);
            root.add(c);
            root.add(buttons);
            ctnlistProduct.add(root);
        }
        return ctnlistProduct;

    }
}
