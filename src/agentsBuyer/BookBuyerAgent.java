package agentsBuyer;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class BookBuyerAgent extends GuiAgent {
    protected ContainerBuyer containerBuyer;
    @Override
    protected void setup() {

        if(getArguments().length==1) {
            containerBuyer =(ContainerBuyer) getArguments()[0];
            containerBuyer.bookBuyerAgent = this;
            containerBuyer.setBookBuyerAgent(this);
        }

        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage aclMessage=receive();
                if(aclMessage!=null) {
                    containerBuyer.logMessage(aclMessage);

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
