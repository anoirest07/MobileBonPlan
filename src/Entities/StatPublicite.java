/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class StatPublicite {
     private Utilisateur user;
    private Publicite publicite;
    private Date date;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Publicite getPublicite() {
        return publicite;
    }

    public void setPublicite(Publicite publicite) {
        this.publicite = publicite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StatPublicite(Utilisateur user, Publicite publicite, Date date) {
        this.user = user;
        this.publicite = publicite;
        this.date = date;
    }
    
    
    public StatPublicite(){
    
    
    }

    @Override
    public String toString() {
        return "StatPublicite{" + "user=" + user + ", publicite=" + publicite + ", date=" + date + '}';
    }
    
}
    

