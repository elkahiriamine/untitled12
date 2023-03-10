package agents;


import Containers.ContainerVendeur;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentVendeur extends GuiAgent {
	
	protected ContainerVendeur gui;
	@Override
	protected void setup() {
		
		if(getArguments().length==1) {
			gui=(ContainerVendeur) getArguments()[0];
			gui.setAgentVendeur(this);
		}
		
		ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage aclMessage=receive();
				if(aclMessage!=null) {
					gui.logMessage(aclMessage);
					
				}
				
				else block();
				
			}
		});
	}
	@Override
	protected void onGuiEvent(GuiEvent event) {
		// TODO Auto-generated method stub
		
	}
	


}
