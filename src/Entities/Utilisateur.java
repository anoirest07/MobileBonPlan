 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author admin
 */
public class Utilisateur {
      public int id;
    public String nom ;
 public String prenom ; 
 public String mot_de_passe ;
 public String username ;
 public String roles;
 public String email;
 public String PhotoUser;
 

     public Utilisateur( String nom, String prenom,String username, String mot_de_passe, String roles, String email,String PhotoUser) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.username=username;
        this.mot_de_passe = mot_de_passe;
        this.roles = roles;
        this.email=email;
        this.PhotoUser=PhotoUser;
              
    }


     public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getUsername() {
        return username;
    }

    public void setUsername(String nom_user) {
        this.username = nom_user;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUser() {
        return PhotoUser;
    }

    public void setPhotoUser(String PhotoUser) {
        this.PhotoUser = PhotoUser;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mot_de_passe=" + mot_de_passe + ", username=" + username + ", roles=" + roles + ", email=" + email + ", PhotoUser=" + PhotoUser + '}';
    }
    

    
    

   

   
            
            public void setUtilisateur(Utilisateur u){
   
 }   
    
    
}
