package agentsConsumer;


import agentsConsumer.AgentConsumer;
import jade.core.ProfileImpl;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jade.core.Runtime;



public class ConsumerContainer extends Application {
	
	protected AgentConsumer agentConsumer;
	protected ObservableList<String> observableList;
	
public static void main(String[] args) { 
	launch(args);
}

public void startContainer() {
	try {
         Runtime runtime=Runtime.instance();  
         ProfileImpl profileImpl=new ProfileImpl(false);
         profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost"); 
         AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);  
         AgentController consumerController=agentContainer.createNewAgent("Consumer",
        		 "agentsConsumer.AgentConsumer", new Object[]{this});
		consumerController.start();

	     }
         catch (Exception e)
	    { e.printStackTrace(); }
}



public void start(Stage primaryStage) throws Exception {

	startContainer();
	primaryStage.setTitle("Consumer container");
	BorderPane borderpane = new BorderPane();

	HBox hbox=new HBox();
	hbox.setPadding(new Insets(10));
	hbox.setSpacing(10);

	Label label = new Label("Book name :");
	TextField textFieldLivre=new TextField();
	Button buttonAcheter=new Button("OK");

	hbox.getChildren().addAll(label,textFieldLivre,buttonAcheter);
	borderpane.setTop(hbox);
	observableList=FXCollections.observableArrayList();
	ListView<String> listViewMessages= new ListView<String>(observableList);

	HBox hBox2 = new HBox();
	hBox2.setPadding(new Insets(10));
	hBox2.setSpacing(10);
	hBox2.getChildren().add(listViewMessages);
	borderpane.setCenter(hBox2);
	Scene scene=new Scene(borderpane,600,400);
	primaryStage.setScene(scene);
	primaryStage.show();


	buttonAcheter.setOnAction(evt->{
		String bookName=textFieldLivre.getText();

		GuiEvent guiEvent = new GuiEvent(this,1);
		guiEvent.addParameter(bookName);
		agentConsumer.onGuiEvent(guiEvent);

	});





	//VBox vbox=new VBox();







	//vbox.setPadding(new Insets(10));
	//vbox.getChildren().add(listViewMessages);
	//borderpane.setCenter(vbox);


	
}

public void setAgentConsumer(AgentConsumer agentConsumer) {
	this.agentConsumer = agentConsumer;
}

public void logMessage(ACLMessage aclMessage) {
	Platform.runLater(()->{
		observableList.add(aclMessage.getContent());
		
	});
	
}

}

