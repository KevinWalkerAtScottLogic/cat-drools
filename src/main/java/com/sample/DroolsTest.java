package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
    	int numberOfRulesFired=0; 
    	
        try {
            // load up the knowledge is everything framework
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            
            //You insert facts into the session
            kSession.insert(new CatFact("Mittens","Hello World - Kitten 1",9));
            kSession.insert(new CatFact("Ratty cat","Hello World - Kitten 1",4));
            kSession.insert(new CatFact("Methusala","Hello World - Kitten 2",40));
            
            //run the rules until a coinclusion is reached
            numberOfRulesFired=kSession.fireAllRules();
            System.out.println( "Rules fired: " + numberOfRulesFired );
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class CatFact {

        public static final int SAFE = 0;
        public static final int ATRISK = 1;
        public static final int DEAD = 2;

        private String message;

        private int status;
        
        private int catLifeCount=0;
        
        private String humanName;
        
        public CatFact (String birthName, String initialMessage, int lifeCountYouHaveAtBirth) {
        status=CatFact.SAFE;
        humanName=birthName;
        message=initialMessage;
        catLifeCount=lifeCountYouHaveAtBirth;
        System.out.println(birthName +" is born!");
        }
        
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

		public int getCatLifeCount() {
			return catLifeCount;
		}

		public void setCatLifeCount(int messageGen) {
			this.catLifeCount = messageGen;
		}

		public void anotherLifeGone() {
			catLifeCount--;
		}

		public String getHumanName() {
			return humanName;
		}

		public void setHumanName(String humanName) {
			this.humanName = humanName;
		}

    }

}
