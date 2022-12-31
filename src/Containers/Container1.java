package Containers;

import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.core.Runtime;




public class Container1 {
public static void main(String[] args) { 
	try {
         Runtime runtime=Runtime.instance();  
         ProfileImpl profileImpl=new ProfileImpl(false);
         profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost"); 
         AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);  
         agentContainer.start();
	     }
         catch (Exception e)
	    { e.printStackTrace(); }
}
}

