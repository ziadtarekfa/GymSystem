package mainClass;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class AlertClass {

    public void Alert(){

    }


    public void WarningAlert(String ContentText){

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning Message");
        alert.setHeaderText(null);
        alert.setContentText(ContentText);
        alert.showAndWait();

    }
    public void WarningAlert(String HeaderText, String ContentText){

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning Message");
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContentText);
        alert.showAndWait();

    }

    public int WarningAlertForDelete(String Title,String HeaderText)
    {

        ButtonType buttonTypeOne=new ButtonType("Confirm");
        ButtonType buttonTypeCancel=new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeCancel );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return 1;
        } else if (result.get() == buttonTypeCancel) {
            return 2;
        }
        else return 0;


    }


    public void infoAlert(String headerText){


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(headerText);
        alert.showAndWait();

    }
    public void infoAlert(String headerText,String contentText){


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }

    public void errorAlert(String headerText , String e){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error Message");
        alert.setHeaderText(headerText);
        alert.showAndWait();
        alert.setContentText(e);

    }
    public void errorAlert(String headerText ){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error Message");
        alert.setHeaderText(headerText);
        alert.showAndWait();


    }


}
