/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amine
 */
public class MapContainerTest {
    
    public MapContainerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initComponent method, of class MapContainer.
     */
    @Test
    public void testInitComponent() {
        System.out.println("initComponent");
        MapContainer instance = new MapContainer();
        instance.initComponent();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deinitialize method, of class MapContainer.
     */
    @Test
    public void testDeinitialize() {
        System.out.println("deinitialize");
        MapContainer instance = new MapContainer();
        instance.deinitialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mapUpdated method, of class MapContainer.
     */
    @Test
    public void testMapUpdated() {
        System.out.println("mapUpdated");
        int mapId = 0;
        MapContainer.mapUpdated(mapId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNativeMaps method, of class MapContainer.
     */
    @Test
    public void testIsNativeMaps() {
        System.out.println("isNativeMaps");
        MapContainer instance = new MapContainer();
        boolean expResult = false;
        boolean result = instance.isNativeMaps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireMarkerEvent method, of class MapContainer.
     */
    @Test
    public void testFireMarkerEvent_int_long() {
        System.out.println("fireMarkerEvent");
        int mapId = 0;
        long markerId = 0L;
        MapContainer.fireMarkerEvent(mapId, markerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireMarkerEvent method, of class MapContainer.
     */
    @Test
    public void testFireMarkerEvent_long() {
        System.out.println("fireMarkerEvent");
        long markerId = 0L;
        MapContainer instance = new MapContainer();
        instance.fireMarkerEvent(markerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMarker method, of class MapContainer.
     */
    @Test
    public void testAddMarker() {
        System.out.println("addMarker");
        EncodedImage icon = null;
        Coord location = null;
        String text = "";
        String longText = "";
        ActionListener onClick = null;
        MapContainer instance = new MapContainer();
        MapContainer.MapObject expResult = null;
        MapContainer.MapObject result = instance.addMarker(icon, location, text, longText, onClick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPath method, of class MapContainer.
     */
    @Test
    public void testAddPath() {
        System.out.println("addPath");
        Coord[] path = null;
        MapContainer instance = new MapContainer();
        MapContainer.MapObject expResult = null;
        MapContainer.MapObject result = instance.addPath(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxZoom method, of class MapContainer.
     */
    @Test
    public void testGetMaxZoom() {
        System.out.println("getMaxZoom");
        MapContainer instance = new MapContainer();
        int expResult = 0;
        int result = instance.getMaxZoom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinZoom method, of class MapContainer.
     */
    @Test
    public void testGetMinZoom() {
        System.out.println("getMinZoom");
        MapContainer instance = new MapContainer();
        int expResult = 0;
        int result = instance.getMinZoom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMapObject method, of class MapContainer.
     */
    @Test
    public void testRemoveMapObject() {
        System.out.println("removeMapObject");
        MapContainer.MapObject obj = null;
        MapContainer instance = new MapContainer();
        instance.removeMapObject(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMapLayers method, of class MapContainer.
     */
    @Test
    public void testClearMapLayers() {
        System.out.println("clearMapLayers");
        MapContainer instance = new MapContainer();
        instance.clearMapLayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of zoom method, of class MapContainer.
     */
    @Test
    public void testZoom() {
        System.out.println("zoom");
        Coord crd = null;
        int zoom = 0;
        MapContainer instance = new MapContainer();
        instance.zoom(crd, zoom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZoom method, of class MapContainer.
     */
    @Test
    public void testGetZoom() {
        System.out.println("getZoom");
        MapContainer instance = new MapContainer();
        float expResult = 0.0F;
        float result = instance.getZoom();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoundingBox method, of class MapContainer.
     */
    @Test
    public void testGetBoundingBox() {
        System.out.println("getBoundingBox");
        MapContainer instance = new MapContainer();
        BoundingBox expResult = null;
        BoundingBox result = instance.getBoundingBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMapType method, of class MapContainer.
     */
    @Test
    public void testSetMapType() {
        System.out.println("setMapType");
        int type = 0;
        MapContainer instance = new MapContainer();
        instance.setMapType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMapType method, of class MapContainer.
     */
    @Test
    public void testGetMapType() {
        System.out.println("getMapType");
        MapContainer instance = new MapContainer();
        int expResult = 0;
        int result = instance.getMapType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCameraPosition method, of class MapContainer.
     */
    @Test
    public void testSetCameraPosition() {
        System.out.println("setCameraPosition");
        Coord crd = null;
        MapContainer instance = new MapContainer();
        instance.setCameraPosition(crd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCameraPosition method, of class MapContainer.
     */
    @Test
    public void testGetCameraPosition() {
        System.out.println("getCameraPosition");
        MapContainer instance = new MapContainer();
        Coord expResult = null;
        Coord result = instance.getCameraPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordAtPosition method, of class MapContainer.
     */
    @Test
    public void testGetCoordAtPosition() {
        System.out.println("getCoordAtPosition");
        int x = 0;
        int y = 0;
        MapContainer instance = new MapContainer();
        Coord expResult = null;
        Coord result = instance.getCoordAtPosition(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScreenCoordinate method, of class MapContainer.
     */
    @Test
    public void testGetScreenCoordinate_double_double() {
        System.out.println("getScreenCoordinate");
        double lat = 0.0;
        double lon = 0.0;
        MapContainer instance = new MapContainer();
        Point expResult = null;
        Point result = instance.getScreenCoordinate(lat, lon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScreenCoordinate method, of class MapContainer.
     */
    @Test
    public void testGetScreenCoordinate_Coord() {
        System.out.println("getScreenCoordinate");
        Coord c = null;
        MapContainer instance = new MapContainer();
        Point expResult = null;
        Point result = instance.getScreenCoordinate(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireMapChangeEvent method, of class MapContainer.
     */
    @Test
    public void testFireMapChangeEvent() {
        System.out.println("fireMapChangeEvent");
        int mapId = 0;
        int zoom = 0;
        double lat = 0.0;
        double lon = 0.0;
        MapContainer.fireMapChangeEvent(mapId, zoom, lat, lon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTapListener method, of class MapContainer.
     */
    @Test
    public void testAddTapListener() {
        System.out.println("addTapListener");
        ActionListener e = null;
        MapContainer instance = new MapContainer();
        instance.addTapListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTapListener method, of class MapContainer.
     */
    @Test
    public void testRemoveTapListener() {
        System.out.println("removeTapListener");
        ActionListener e = null;
        MapContainer instance = new MapContainer();
        instance.removeTapListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireTapEventStatic method, of class MapContainer.
     */
    @Test
    public void testFireTapEventStatic() {
        System.out.println("fireTapEventStatic");
        int mapId = 0;
        int x = 0;
        int y = 0;
        MapContainer.fireTapEventStatic(mapId, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLongPressListener method, of class MapContainer.
     */
    @Test
    public void testAddLongPressListener() {
        System.out.println("addLongPressListener");
        ActionListener e = null;
        MapContainer instance = new MapContainer();
        instance.addLongPressListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLongPressListener method, of class MapContainer.
     */
    @Test
    public void testRemoveLongPressListener() {
        System.out.println("removeLongPressListener");
        ActionListener e = null;
        MapContainer instance = new MapContainer();
        instance.removeLongPressListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireLongPressEventStatic method, of class MapContainer.
     */
    @Test
    public void testFireLongPressEventStatic() {
        System.out.println("fireLongPressEventStatic");
        int mapId = 0;
        int x = 0;
        int y = 0;
        MapContainer.fireLongPressEventStatic(mapId, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireMapListenerEvent method, of class MapContainer.
     */
    @Test
    public void testFireMapListenerEvent() {
        System.out.println("fireMapListenerEvent");
        int zoom = 0;
        double lat = 0.0;
        double lon = 0.0;
        MapContainer instance = new MapContainer();
        instance.fireMapListenerEvent(zoom, lat, lon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMapListener method, of class MapContainer.
     */
    @Test
    public void testAddMapListener() {
        System.out.println("addMapListener");
        MapListener listener = null;
        MapContainer instance = new MapContainer();
        instance.addMapListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMapListener method, of class MapContainer.
     */
    @Test
    public void testRemoveMapListener() {
        System.out.println("removeMapListener");
        MapListener listener = null;
        MapContainer instance = new MapContainer();
        instance.removeMapListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isShowMyLocation method, of class MapContainer.
     */
    @Test
    public void testIsShowMyLocation() {
        System.out.println("isShowMyLocation");
        MapContainer instance = new MapContainer();
        boolean expResult = false;
        boolean result = instance.isShowMyLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShowMyLocation method, of class MapContainer.
     */
    @Test
    public void testSetShowMyLocation() {
        System.out.println("setShowMyLocation");
        boolean showMyLocation = false;
        MapContainer instance = new MapContainer();
        instance.setShowMyLocation(showMyLocation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRotateGestureEnabled method, of class MapContainer.
     */
    @Test
    public void testIsRotateGestureEnabled() {
        System.out.println("isRotateGestureEnabled");
        MapContainer instance = new MapContainer();
        boolean expResult = false;
        boolean result = instance.isRotateGestureEnabled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRotateGestureEnabled method, of class MapContainer.
     */
    @Test
    public void testSetRotateGestureEnabled() {
        System.out.println("setRotateGestureEnabled");
        boolean rotateGestureEnabled = false;
        MapContainer instance = new MapContainer();
        instance.setRotateGestureEnabled(rotateGestureEnabled);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
