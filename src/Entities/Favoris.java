/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author malek
 */
public class Favoris {
    private String id_favoris;
    private String  id_etablissement;
    private String id_user;

    public Favoris() {
    }

    public Favoris(String id_etablissement, String id_user) {
        this.id_etablissement = id_etablissement;
        this.id_user = id_user;
    }

    public String getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(String id_favoris) {
        this.id_favoris = id_favoris;
    }

    

    public String getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(String id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id_favoris=" + id_favoris + ", id_etablissement=" + id_etablissement + ", id_user=" + id_user + '}';
    }

    
}
