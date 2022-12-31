package agentsBuyer;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContainerBuyer extends Application {
    protected BookBuyerAgent bookBuyerAgent;
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
            AgentController agentController=agentContainer.createNewAgent("Buyer", BookBuyerAgent.class.getName(), new Object[] {this});
            agentController.start();
        }
        catch (Exception e)
        { e.printStackTrace(); }



    }

    public void logMessage(ACLMessage aclMessage) {
        Platform.runLater(()->{
            observableList.add(aclMessage.getContent());

        });


    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        primaryStage.setTitle("Buyer");
        BorderPane borderPane=new BorderPane();
        VBox vbox=new VBox();
        observableList= FXCollections.observableArrayList();
        ListView<String> listView=new ListView<String>(observableList);
        vbox.getChildren().add(listView);
        borderPane.setCenter(vbox);
        Scene scene= new Scene(borderPane,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setBookBuyerAgent(BookBuyerAgent bookBuyerAgent) {
        this.bookBuyerAgent = this.bookBuyerAgent;
    }

}
