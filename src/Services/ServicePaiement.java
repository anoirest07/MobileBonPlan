/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Paiement;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServicePaiement {
     public boolean payer(String numeroCarte,int moisExp,int anneeExp,String cvc,int montant,String description) 
    {
        Token t1= new Token();
        
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();
        Paiement p = new Paiement();
        Stripe.apiKey =p.getApiKey();
        cardParams.put("number", numeroCarte);
        cardParams.put("exp_month", moisExp);
        cardParams.put("exp_year", anneeExp);
        cardParams.put("cvc", cvc);
        int nMontant= montant*100;
        tokenParams.put("card", cardParams);
        Token token= new Token();
        try{
            try { 
                token =Token.create(tokenParams);
            } catch (AuthenticationException ex) {
                
            } catch (APIConnectionException ex) {
               
            } catch (APIException ex) {
                
            }
        }
        catch (InvalidRequestException e){
        }
        catch (CardException ce){
            
        }      
        if (token.getId()!=null){
        params.put("amount", nMontant);
        params.put("currency", "eur");
        params.put("description", description);
        params.put("source", token.getId());
        Charge charge;
            try {
                charge = Charge.create(params);
            } catch (AuthenticationException ex) {
                
            } catch (InvalidRequestException ex) {
             
            } catch (APIConnectionException ex) {
               
            } catch (CardException ex) {
               
            } catch (APIException ex) {
               
            }
            
        }
        else 
            return false;
        return true; 
    }
    
    
}
