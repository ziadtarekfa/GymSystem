package GUI.Controllers.admin;
/**
 * Sample Skeleton for 'deleteclass.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mainClass.AlertClass;
import mainClass.Fitness_class;

import java.net.URL;
import java.util.ResourceBundle;

public class deleteClassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_classid; // Value injected by FXMLLoader

    @FXML
    private Label lbl_classname; // Value injected by FXMLLoader

    @FXML
    private TextField txtf_deleteClassid; // Value injected by FXMLLoader

    @FXML
    private TextField txtf_deleteClassname; // Value injected by FXMLLoader

    @FXML
    private Button btn_delete_class; // Value injected by FXMLLoader





    @FXML
    void btn_delete_pressed(ActionEvent x) throws Exception {
        try{

            String A_title="Delete data";
            String A_HeaderText="Are you sure you want to delete data , press ok to continue ";
            AlertClass obj=new AlertClass();
            int choice =obj.WarningAlertForDelete(A_title,A_HeaderText);

            if (choice==1){
                Fitness_class.class_delete(Integer.parseInt(txtf_deleteClassid.getText()));
            } else if (choice==2) {
                return;
            }

        }catch (Exception e){
            //Alert warning
            AlertClass obj=new AlertClass();
            obj.WarningAlert("Please insert/check_password Class Name and id !" );
            System.out.println(e);
        }
    }

    @FXML // This lib_button_clicked is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbl_classid != null : "fx:id=\"lbl_classid\" was not injected: check_password your FXML file 'deleteclass.fxml'.";
        assert lbl_classname != null : "fx:id=\"lbl_classname\" was not injected: check_password your FXML file 'deleteclass.fxml'.";
        assert txtf_deleteClassid != null : "fx:id=\"txtf_deleteClassid\" was not injected: check_password your FXML file 'deleteclass.fxml'.";
        assert txtf_deleteClassname != null : "fx:id=\"txtf_deleteClassname\" was not injected: check_password your FXML file 'deleteclass.fxml'.";
        assert btn_delete_class != null : "fx:id=\"btn_delete_class\" was not injected: check_password your FXML file 'deleteclass.fxml'.";

    }

}
