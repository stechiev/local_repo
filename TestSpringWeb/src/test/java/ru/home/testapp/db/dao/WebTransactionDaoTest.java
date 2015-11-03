package ru.home.testapp.db.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.home.testapp.db.model.WebTransaction;

public class WebTransactionDaoTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	
	//@Test
    public void testNewUser() {

        EntityManager em = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();
        
        String transid ="";

        //User user = new User();
        WebTransaction trans = em.find(WebTransaction.class, transid); 

       
        // see that the ID of the user was set by Hibernate
        log.debug("trans={}", trans.getId() + " - " + trans.getCreated());
        //eassertEquals(trans.getId(), transid);

        em.close();
    }

}
