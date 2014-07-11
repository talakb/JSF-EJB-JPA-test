package edu.mum.cs.cs490.mbean;

import edu.mum.cs.cs490.login.service.CreditCardService;
import edu.mum.cs.cs490.login.service.entity.CreditCard;
import java.io.Serializable;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author TalakB
 */
@Named
@SessionScoped
public class CreditCardBean implements Serializable {

    @EJB
    CreditCardService creaditCardService;
    CreditCard userCreditCard = new CreditCard();

    public CreditCardBean() {
    }

    public CreditCard getUserCreditCard() {
        return userCreditCard;
    }

    public void setUserCreditCard(CreditCard userCreditCard) {
        this.userCreditCard = userCreditCard;
    }

    public String generateCreditCardNo() {
        Random rand = new Random();
        long accumulator = 1 + rand.nextInt(9); 
        for (int i = 0; i < 15; i++) {
            accumulator *= 10L;
            accumulator += rand.nextInt(10);
        }
        
        return String.valueOf(accumulator);
    }
    
    
 //   public STring generate

}
