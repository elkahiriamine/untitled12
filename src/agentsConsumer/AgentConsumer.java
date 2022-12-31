package agentsConsumer;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentConsumer extends GuiAgent {
	
protected   ConsumerContainer consumerContainer;
@Override
protected void setup() {

	String bookName = null;

	if(this.getArguments().length == 1){
		consumerContainer= (ConsumerContainer) getArguments()[0];
	    consumerContainer.agentConsumer = this;
	}
	System.out.println("Initialization the agent : "+this.getAID().getName());
	System.out.println("I'm trying to buy the book: "+ bookName);




 
ParallelBehaviour parallelbehaviour= new ParallelBehaviour();
addBehaviour(parallelbehaviour);

parallelbehaviour.addSubBehaviour(new CyclicBehaviour() {
	
	@Override
	public void action() {
		ACLMessage aclmessage = receive();
		if(aclmessage!=null) {

			consumerContainer.logMessage(aclmessage);
			
			
			
			/*System.out.println("****************");
			System.out.println("R�ception du message");
			System.out.println(aclmessage.getContent());
			System.out.println(aclmessage.getSender().getName());
			System.out.println(aclmessage.getPerformative());
			System.out.println(aclmessage.getLanguage());
			System.out.println(aclmessage.getOntology());
			System.out.println("******************");
			
		ACLMessage reply= aclmessage.createReply();
		reply.setContent("bonjour ");
        send(reply);*/
		}
		else block();
		
	}
});
}



@Override
public void onGuiEvent(GuiEvent guiEvent) {
    if(guiEvent.getType()==1) {
		String bookName = guiEvent.getParameter(0).toString();
		ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
		aclMessage.setContent(bookName);
		aclMessage.addReceiver(new AID("BookBuyerAgent",AID.ISLOCALNAME));
		send(aclMessage);
    }
}
}
