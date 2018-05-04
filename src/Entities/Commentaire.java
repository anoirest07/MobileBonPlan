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
public class Commentaire {
    
    private String id_commentaire;
    private String commentaire;
    private String idUcomm;
    private String idExp;
    
    public Commentaire(){
        
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getIdUcomm() {
        return idUcomm;
    }

    public void setIdUcomm(String idUcomm) {
        this.idUcomm = idUcomm;
    }

    public String getIdExp() {
        return idExp;
    }

    public void setIdExp(String idExp) {
        this.idExp = idExp;
    }

    public String getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(String id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    
    
    public Commentaire(String commentaire, String idUcomm, String idExp) {
        this.commentaire = commentaire;
        this.idUcomm = idUcomm;
        this.idExp = idExp;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", commentaire=" + commentaire + ", idUcomm=" + idUcomm + ", idExp=" + idExp + '}';
    }
    
    
}
