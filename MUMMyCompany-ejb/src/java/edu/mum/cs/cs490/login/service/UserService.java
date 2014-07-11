package edu.mum.cs.cs490.login.service;

import edu.mum.cs.cs490.login.service.entity.MyComapnyUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TalakB
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public boolean saveUser(MyComapnyUser user) {
        //encrypt users password before persist 
        boolean saved = false;
        try {
            em.persist(user);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }

    public boolean authenticateUser(MyComapnyUser user) {
        boolean userAuthenticated = false;
        String userName = user.getUserName();
        String passw = user.getPassword();

        Query query = em.createNamedQuery("checkUser");
        query.setParameter("uname", userName);
        query.setParameter("upass", passw);

        if(!query.getResultList().isEmpty())
            userAuthenticated = true;
        return userAuthenticated;
    }

}
