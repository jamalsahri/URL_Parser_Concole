/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url_parser02;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jamal
 */
public class URL_Parser02 extends Application {
    
    final WebView browser = new WebView();
    final WebEngine webengine = browser.getEngine();
    
    @Override
    public void start(Stage primaryStage) {
        try{
                webengine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            Document doc = webengine.getDocument();
                            
                            if(doc!=null){
                                NodeList nl = doc.getElementsByTagName("a");
                                System.out.println("Total : "+nl.getLength()+"\n\n");
                                double progressVal = (double)nl.getLength()/100.0;
                                double tmp = 0;
                                for(int i=0; i<nl.getLength(); i++){
                                    Element node = (Element) nl.item(i);
                                    addElement(node);
                                    tmp +=progressVal;
                                }
                            }else{
                                System.out.println("NULL DOC");
                            }
                        }
                    }
                });
        
                webengine.load("https://ceri.univ-avignon.fr/");
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void addElement(Element element) {
        try{
            NamedNodeMap attributes = element.getAttributes();

            Attr href = (Attr) attributes.getNamedItem("href");
            String textVal = element.getTextContent();
            String linkVal = href.getNodeValue();

            if(!textVal.trim().isEmpty()){
                System.out.println("Text : "+textVal.trim());
            }
            if(!linkVal.trim().isEmpty()){
                System.out.println("Link : "+linkVal.trim());
            }

            System.out.println(" = = = = = = = = = ");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
