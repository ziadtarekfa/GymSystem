package GUI.Controllers.emp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClass.AlertClass;
import mainClass.Fitness_class;
import mainClass.Trainee;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddDeleteMemberClassController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtf_emp_addMemInClass_classID; // Value injected by FXMLLoader
    @FXML
    private TextField txtf_emp_addMemInClass_className; // Value injected by FXMLLoader
    @FXML
    private TextField txtf_emp_addMemInClass_TraineeName; // Value injected by FXMLLoader
    @FXML
    private TextField txtf_emp_addMemInClass_TraineeID; // Value injected by FXMLLoader
    @FXML
    private Button btn_emp_addMemInClass_confirm; // Value injected by FXMLLoader
    @FXML
    private Button btn_emp_addMemInClass_delete; // Value injected by FXMLLoader
    @FXML
    private Button btn_emp_addMemInClass_add; // Value injected by FXMLLoader
    @FXML
    private TableColumn<Trainee, String> tableColumn_emp_addMemberInClass_memberName;
    @FXML
    private TableColumn<Trainee, Integer> tableColumn_emp_addMemberInClass_memberID;
    @FXML
    private TableView<Trainee> tableMemberInClass;
    @FXML
    ObservableList<Trainee> tableViewTrainee = FXCollections.observableArrayList();
    @FXML
    private TextField txt_view_class_id;

    // methods

    @FXML
    void view(){                                                   //this function fill data in table by using filling observablelist
        int classId=0;

        clearTable();
        try {
            classId=Integer.parseInt(txt_view_class_id.getText());
            if(classId==0)
            {
                classId=Integer.parseInt(txtf_emp_addMemInClass_classID.getText());
            }else {
                System.out.println("This Function displays trainees data  in class whose ID= " + classId + " in table in a class ");
                ResultSet rs = Fitness_class.Display_members_in_class(classId);
                while (rs.next()) {
                    tableViewTrainee.add(new Trainee(rs.getString("m.name"), rs.getInt("member_id")));
                    System.out.println(rs.getString("m.name") + rs.getInt("member_id"));
                }
                tableColumn_emp_addMemberInClass_memberName.setCellValueFactory(new PropertyValueFactory<>("name"));
                tableColumn_emp_addMemberInClass_memberID.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableMemberInClass.setItems(tableViewTrainee);
                initialize();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void btn_add_pressed(){
        try {
            System.out.println("ADD btn is pressed");
            boolean isAdded = Fitness_class.Add_members_in_class(Integer.parseInt(txtf_emp_addMemInClass_TraineeID.getText().toString()),
                    Integer.parseInt(txtf_emp_addMemInClass_classID.getText().toString()),txtf_emp_addMemInClass_TraineeName.getText().toString());
            if (isAdded==true )
            {                                                           //alert shows that member is succcessively added .
                AlertClass newAlertInfo=new AlertClass();
                newAlertInfo.infoAlert("Information Dialog",
                        "Adding member in classid= "+ txtf_emp_addMemInClass_classID.getText()+  " process is successful ");
            }
            else if (txtf_emp_addMemInClass_classID.getText().isEmpty() || txtf_emp_addMemInClass_TraineeID.getText().isEmpty() ||
                    txtf_emp_addMemInClass_TraineeName.getText().isEmpty())
            {
                AlertClass newAlertWarning1 =new AlertClass();
                newAlertWarning1.WarningAlert("Warning Message"," Check data in all fields , some fields are empty  ");
            }
            else{
                AlertClass newAlert=new AlertClass();
                newAlert.errorAlert("Error Message","Please Check Entered data ( hint: you cant add a member in a class with same id 2 times ! ) ");
            }
            view();  //this function refreshes the table with the  new data if the enetered class id in which the member is added to  is the same as the class id value above table ;

        } catch (NullPointerException e) {
            AlertClass newAlert=new AlertClass();
            newAlert.WarningAlert("Warning Message","Please enter data in all the required text fields ! ");
            System.out.println(e + "there is sth that  is empty");
        } catch (Exception e) {
            AlertClass newAlert=new AlertClass();
            newAlert.errorAlert("Error Message",e.getMessage());
            System.out.println(e);
        }
        view();  //this function refreshes the table with the  new data if the enetered class id in which the member is added to  is the same as the class id value above table ;

    }

    @FXML
    void btn_delete_pressed(){
            try {

                System.out.println("ADD btn is pressed");
                boolean isRemoved= Fitness_class.Remove_members_from_class(Integer.parseInt(txtf_emp_addMemInClass_TraineeID.getText()),
                        Integer.parseInt(txtf_emp_addMemInClass_classID.getText()));
                if(isRemoved==true) {
                    AlertClass infoAlert = new AlertClass();
                    infoAlert.infoAlert("Information Dialog", "Deleting member from classid= " + txtf_emp_addMemInClass_classID.getText() + "process is successful ");
                }
                else if (txtf_emp_addMemInClass_classID.getText().isEmpty() || txtf_emp_addMemInClass_TraineeID.getText().isEmpty() || txtf_emp_addMemInClass_TraineeName.getText().isEmpty())
                {
                    AlertClass newAlertWarning1 =new AlertClass();
                    newAlertWarning1.WarningAlert("Warning Message"," Check data in all fields , some fields are empty  ");
                }
                else{
                    throw new Exception();
                }
            } catch (NullPointerException e) {

                AlertClass alert = new AlertClass();
                alert.WarningAlert("Warning Message","Please enter data in all the required text fields ! ");
                System.out.println(e + "there is sth that  is empty");

            } catch (Exception e) {

                AlertClass newAlert=new AlertClass();
                newAlert.errorAlert("Error Message","Please Check Entered data ! ");
                System.out.println(e);
            }
            view();
    }

    @FXML
    void clearTable(){
        tableMemberInClass.getItems().clear();
    }


    @FXML
    void btn_ConfirmBtn_pressed() throws Exception   {
        try {
                              // if fields donot have values in gui then throw an error (alert to user )
            if(txtf_emp_addMemInClass_classID.getText().isEmpty() || txtf_emp_addMemInClass_TraineeID.getText().isEmpty() || txtf_emp_addMemInClass_TraineeName.getText().isEmpty() )
            {
                AlertClass newAlertWarning1 =new AlertClass();
                newAlertWarning1.WarningAlert("Warning Message"," Check data in all fields , some fields are empty  ");
                throw new Exception() ;       // this code runs catch block
            }
            else{
                              //Alert user that traine has been added
                AlertClass newAlert=new AlertClass();
                newAlert.infoAlert("Information Message"," Changes are committed  ");
            }
        }
        catch (Exception x){
                            //Alert  object of type , warning
            AlertClass newAlertWarning =new AlertClass();
            newAlertWarning.WarningAlert("Warning Message","No data is changed  ");
        }
    }


    @FXML                      // This lib_button_clicked is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtf_emp_addMemInClass_classID != null : "fx:id=\"txtf_emp_addMemInClass_classID\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert txtf_emp_addMemInClass_className != null : "fx:id=\"txtf_emp_addMemInClass_className\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert txtf_emp_addMemInClass_TraineeName != null : "fx:id=\"txtf_emp_addMemInClass_TraineeName\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert txtf_emp_addMemInClass_TraineeID != null : "fx:id=\"txtf_emp_addMemInClass_TraineeID\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert btn_emp_addMemInClass_confirm != null : "fx:id=\"btn_emp_addMemInClass_confirm\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert btn_emp_addMemInClass_delete != null : "fx:id=\"btn_emp_addMemInClass_delete\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
        assert btn_emp_addMemInClass_add != null : "fx:id=\"btn_emp_addMemInClass_add\" was not injected: check_password your FXML file '3-Add.delete Member in class.fxml'.";
    }

}
