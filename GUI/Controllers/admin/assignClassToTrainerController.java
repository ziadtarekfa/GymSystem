package GUI.Controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClass.AlertClass;
import mainClass.Fitness_class;
import mainClass.Trainer;
import mainClass.con1;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Sample Skeleton for 'assignClassToTrainer.fxml' Controller Class
 */
public class assignClassToTrainerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtf_Admin_assignClassToTrainer_className"
    private TextField txtf_Admin_assignClassToTrainer_className; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignClassToTrainer_classId"
    private TextField txtf_Admin_assignClassToTrainer_classId; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignClassToTrainer_TrainerName"
    private TextField txtf_Admin_assignClassToTrainer_TrainerName; // Value injected by FXMLLoader

    @FXML // fx:id="txtf_Admin_assignClassToTrainer_TrainerID"
    private TextField txtf_Admin_assignClassToTrainer_TrainerID; // Value injected by FXMLLoader

    @FXML
    private Button btn_assgin;

   /* @FXML
    protected SimpleDateFormat date_format = new SimpleDateFormat("dd MMM yy");

    @FXML
    private JFXTimePicker txt_time_class;

    @FXML
    private DatePicker txtf_Admin_assignClassToTrainer_ClassDate;
*/
    @FXML
    void onAddingText(ActionEvent x) throws SQLException {

        try {
            ResultSet rs=Trainer.Display_specific_trainer_basedOn_id(Integer.parseInt(txtf_Admin_assignClassToTrainer_TrainerID.getText()));
            while(rs.next())
            {
                txtf_Admin_assignClassToTrainer_TrainerName.setText(rs.getString("name"));
            }
            con1 connect5=new con1();

            if(txtf_Admin_assignClassToTrainer_classId.getText().isEmpty() || txtf_Admin_assignClassToTrainer_classId.getText().equals("null"))
            {
                throw new Exception();
            }
            else {            System.out.println(txtf_Admin_assignClassToTrainer_classId.getText());
                ResultSet rs5=connect5.displayData_db("select class_name from fitness_class where class_id= " + txtf_Admin_assignClassToTrainer_classId.getText()  );
                while(rs5.next())
                {
                    txtf_Admin_assignClassToTrainer_className.setText(rs5.getString("class_name"));
                }

            }




        }catch (NumberFormatException n)
        {
            System.out.println(n.getMessage());
        }
        catch (Exception e ){
            System.out.println("Note;  class id is empty ");
        }


    }

    @FXML
    void btn_assign_pressed(){

        try {
            boolean isAvailable =Trainer.check_availbitiy(txtf_Admin_assignClassToTrainer_TrainerID.getText(),txtf_Admin_assignClassToTrainer_classId.getText());

            if(isAvailable==true) {
                Fitness_class.Assign_Trainer_to_class(Integer.parseInt(txtf_Admin_assignClassToTrainer_TrainerID.getText()),
                        Integer.parseInt(txtf_Admin_assignClassToTrainer_classId.getText()));
            }
            else{
                AlertClass alert=new AlertClass();
                alert.errorAlert("ERROR MESSAGE !","Trainer cant have two class at the same time");
            }


        }catch(Exception x){

            AlertClass alertt=new AlertClass();
            alertt.errorAlert("ERROR MESSAGE !",x.getMessage());

        }

    }

    @FXML // This lib_button_clicked is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtf_Admin_assignClassToTrainer_className != null : "fx:id=\"txtf_Admin_assignClassToTrainer_className\" was not injected: check_password your FXML file 'assignClassToTrainer.fxml'.";
        assert txtf_Admin_assignClassToTrainer_classId != null : "fx:id=\"txtf_Admin_assignClassToTrainer_classId\" was not injected: check_password your FXML file 'assignClassToTrainer.fxml'.";
        assert txtf_Admin_assignClassToTrainer_TrainerName != null : "fx:id=\"txtf_Admin_assignClassToTrainer_TrainerName\" was not injected: check_password your FXML file 'assignClassToTrainer.fxml'.";
        assert txtf_Admin_assignClassToTrainer_TrainerID != null : "fx:id=\"txtf_Admin_assignClassToTrainer_TrainerID\" was not injected: check_password your FXML file 'assignClassToTrainer.fxml'.";

    }
}


























  /*
            LocalDate date = txtf_Admin_assignClassToTrainer_ClassDate.getValue();
            Date classDate;
            classDate = java.sql.Date.valueOf(date);
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
            String dateToString = date_format.format(classDate);  //s means start date
            System.out.println(dateToString);
            dateToString.toUpperCase();
            System.out.println(txtf_Admin_assignClassToTrainer_ClassDate.getValue());
            System.out.println(txt_time_class.getValue());
*/
// txt_time_class.getValue().toString(), dateToString.toUpperCase()