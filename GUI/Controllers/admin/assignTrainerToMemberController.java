/**
 * Sample Skeleton for 'assignTrainerToMember.fxml' Controller Class
 */

package GUI.Controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClass.AlertClass;
import mainClass.Trainer;
import mainClass.con1;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class assignTrainerToMemberController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtf_Admin_assignTrainerToMember_MemberName"
    private TextField txtf_Admin_assignTrainerToMember_MemberName; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignTrainerToMember_MemberID"
    private TextField txtf_Admin_assignTrainerToMember_MemberID; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignTrainerToMember_TrainerName"
    private TextField txtf_Admin_assignTrainerToMember_TrainerName; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignTrainerToMember_TrainerID"
    private TextField txtf_Admin_assignTrainerToMember_TrainerID; // Value injected by FXMLLoader

    @FXML // fx:id="btn_Admin_assignTrainerToMember_Confirm"
    private Button btn_Admin_assignTrainerToMember_Confirm; // Value injected by FXMLLoader


    //             methods
    @FXML
    void onAddingText(ActionEvent x) throws SQLException {
        ResultSet rs= Trainer.Display_specific_trainer_basedOn_id(Integer.parseInt(txtf_Admin_assignTrainerToMember_TrainerID.getText()));
        while(rs.next())
        {
            txtf_Admin_assignTrainerToMember_TrainerName.setText(rs.getString("NAME"));
        }
        con1 connect=new con1();
        ResultSet rs2=connect.displayData_db("Select name FROM member where ID = "+ Integer.parseInt(txtf_Admin_assignTrainerToMember_MemberID.getText()) );
        while(rs2.next())
        {
            txtf_Admin_assignTrainerToMember_MemberName.setText(rs2.getString("NAME"));
        }
    }

    @FXML
    void btn_assign_pressed(){
        try {
            Trainer.Assign_trainer_member(Integer.parseInt(txtf_Admin_assignTrainerToMember_TrainerID.getText()),
                    Integer.parseInt(txtf_Admin_assignTrainerToMember_MemberID.getText()));
        }catch(Exception x){

            AlertClass alertt=new AlertClass();
            alertt.WarningAlert("ERROR MESSAGE !",x.getMessage());

        }
    }

    @FXML // This lib_button_clicked is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtf_Admin_assignTrainerToMember_MemberName != null : "fx:id=\"txtf_Admin_assignTrainerToMember_MemberName\" was not injected: check_password your FXML file 'assignTrainerToMember.fxml'.";
        assert txtf_Admin_assignTrainerToMember_MemberID != null : "fx:id=\"txtf_Admin_assignTrainerToMember_MemberID\" was not injected: check_password your FXML file 'assignTrainerToMember.fxml'.";
        assert txtf_Admin_assignTrainerToMember_TrainerName != null : "fx:id=\"txtf_Admin_assignTrainerToMember_TrainerName\" was not injected: check_password your FXML file 'assignTrainerToMember.fxml'.";
        assert txtf_Admin_assignTrainerToMember_TrainerID != null : "fx:id=\"txtf_Admin_assignTrainerToMember_TrainerID\" was not injected: check_password your FXML file 'assignTrainerToMember.fxml'.";
        assert btn_Admin_assignTrainerToMember_Confirm != null : "fx:id=\"btn_Admin_assignTrainerToMember_Confirm\" was not injected: check_password your FXML file 'assignTrainerToMember.fxml'.";

    }
}
