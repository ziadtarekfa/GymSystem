/**
 * Sample Skeleton for 'addEditDeleteTrainer.fxml' Controller Class
 */

package GUI.Controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mainClass.AlertClass;
import mainClass.Trainer;
import mainClass.con1;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class trainerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextField txtf_f3_emp_editTrainer_tName; // Value injected by FXMLLoader

    @FXML
    private TextField txtf_f3_emp_editTrainer_tID; // Value injected by FXMLLoader

    @FXML
    private TextField txtf_f3_emp_editTrainer_tEmail; // Value injected by FXMLLoader

    @FXML
    private TextField txtf_f3_emp_editTrainer_tPhone; // Value injected by FXMLLoader

    @FXML
    private Button btn_f3_admin_editTrainer_add; // Value injected by FXMLLoader

    @FXML
    private Button btn_f3_admin_editTrainer_edit; // Value injected by FXMLLoader

    @FXML
    private Button btn_f3_admin_editTrainer_delete; // Value injected by FXMLLoader

    @FXML 
    private Button btn_f3_admin_editTrainer_confirm; // Value injected by FXMLLoader

    @FXML 
    private RadioButton Radio_boxtxtf_f3_emp_Trainer_Male; // Value injected by FXMLLoader

    @FXML 
    private RadioButton Radio_boxtxtf_f3_emp_Trainer_Female; // Value injected by FXMLLoader

    @FXML 
    private Label lbl_trainerid3; // Value injected by FXMLLoader

    @FXML 
    private Label lbl_n_trainer_age; // Value injected by FXMLLoader

    @FXML 
    private TextField txtf_f3_emp_Trainer_Age; // Value injected by FXMLLoader



    @FXML
    private TableColumn<Trainer,String> tableColumn_admin_trainer_name;

    @FXML
    private TableColumn<Trainer, Integer> tableColumn_admin_trainer_id;

    @FXML
    private TableColumn<Trainer, Integer> tableColumn_admin_trainer_age;

    @FXML
    private TableColumn<Trainer, String> tableColumn_admin_trainer_phoneNo;

    @FXML
    private TableColumn<Trainer, String> tableColumn_admin_trainer_email;

    @FXML
    private TableColumn<Trainer, String> tableColumn_admin_trainer_gender;


    @FXML 
    private TableView<Trainer> tableView_f3_admin_editTrainer; // Value injected by FXMLLoader
    @FXML
    private ToggleGroup Gender;


    @FXML
    ObservableList<Trainer> listview = FXCollections.observableArrayList();


    @FXML
    void btn_confirm_pressed(ActionEvent event)throws Exception{

        System.out.println("Confirm btn is pressed");

        AlertClass obj=new AlertClass();
        obj.infoAlert("Confirm btn is pressed. ");

        setNull();
        Gender.setUserData(null);




    }
    @FXML
    void btn_add_pressed(ActionEvent event){

        try {
            System.out.println("ADD btn is pressed");

            boolean repeatedId;
            repeatedId= Trainer.Check_id(Integer.parseInt(txtf_f3_emp_editTrainer_tID.getText()));
            if(repeatedId ){


                AlertClass warningM=new AlertClass();
                warningM.WarningAlert("ID is found in another Trainee","Cant add the same id to new trainee !" );
                txtf_f3_emp_editTrainer_tID.setText(null);
                return;
            }

            Trainer newTrainer = new Trainer();
            newTrainer.setName(txtf_f3_emp_editTrainer_tName.getText());
            newTrainer.setPhone_number(txtf_f3_emp_editTrainer_tPhone.getText());
            newTrainer.setId(Integer.parseInt(txtf_f3_emp_editTrainer_tID.getText()));
            newTrainer.setAge(Integer.parseInt(txtf_f3_emp_Trainer_Age.getText()));
            newTrainer.setEmail(txtf_f3_emp_editTrainer_tEmail.getText());
            newTrainer.setGender(((RadioButton) Gender.getSelectedToggle()).getText());
            newTrainer.Add_trainer();
            tableView_f3_admin_editTrainer.getItems().clear();
            initialize();

            AlertClass obj=new AlertClass();
            obj.infoAlert("Trainer is added successively. ");



        } catch (NullPointerException e) {

            AlertClass obj=new AlertClass();
            obj.WarningAlert("Please insert data in all Text fields !");

            System.out.println(e + "there is sth that  is empty");

        } catch (Exception e) {

            AlertClass obj=new AlertClass();
            obj.errorAlert("Please Check Entered data ! \n" , e.getMessage());
            System.out.println(e);

        }
    }

    int index=-1;

    @FXML
    void btn_delete_pressed(ActionEvent event){

        System.out.println("delete btn is pressed");

        try{
            ButtonType buttonTypeOne=new ButtonType("Confirm");
            ButtonType buttonTypeCancel=new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            AlertClass obj=new AlertClass();
            int result=obj.WarningAlertForDelete("Delete data","Are you sure you want to delete data , press ok to continue");

            if (result == 1){
                Trainer.Delete_trainer(Integer.parseInt(txtf_f3_emp_editTrainer_tID.getText()));
                index = tableView_f3_admin_editTrainer.getSelectionModel().getSelectedIndex();
                tableView_f3_admin_editTrainer.getItems().remove(index);
                setNull();

                AlertClass obj1=new AlertClass();
                obj1.infoAlert("Trainer is successively deleted . ");

            } else if (result == 2) {
                return;
            }

        }catch (Exception e){

            //Alert  object ;; warning
            AlertClass obj=new AlertClass();
            obj.WarningAlert("Please insert/check_password Trainee Name and id ! \n" );
            System.out.println(e);
        }

    }

    @FXML
    void btn_edit_pressed(ActionEvent event){

        System.out.println("Edit btn is pressed");
        try {
            con1 connect = new con1();
            index = tableView_f3_admin_editTrainer.getSelectionModel().getSelectedIndex();
            while(tableColumn_admin_trainer_id.getCellData(index).toString().equals( txtf_f3_emp_editTrainer_tID.getText()) !=true )
            {
                AlertClass obj1=new AlertClass();
                obj1.errorAlert("ERROR MESSAGE ! You arenot obligated to change ID of an existing user .");
                txtf_f3_emp_editTrainer_tID.setText(tableColumn_admin_trainer_id.getCellData(index).toString());
            }

            String editedName = txtf_f3_emp_editTrainer_tName.getText();
            String editedEmail = txtf_f3_emp_editTrainer_tEmail.getText();
            String editedGender = ((RadioButton) Gender.getSelectedToggle()).getText();
            String editedPhone_number = txtf_f3_emp_editTrainer_tPhone.getText();
            int editedAge = Integer.parseInt(txtf_f3_emp_Trainer_Age.getText());

            String query;
            query = "UPDATE trainer set name='" + editedName + "',age='" + editedAge
                    + "',email='" + editedEmail + "',phone_number='" + editedPhone_number
                    + "',gender='" + editedGender + "'"
                    + "WHERE ID=" + (tableColumn_admin_trainer_id.getCellData(index).toString());
            connect.alterData_db(query);
            tableView_f3_admin_editTrainer.getItems().clear();
            initialize();

        }catch (Exception e){

            AlertClass obj2=new AlertClass();
            obj2.errorAlert("ERROR MESSAGE ! \n"  , e.getMessage());
            System.out.println(e);
        }
    }

    @FXML
    void btn_row_pressed(MouseEvent x) throws Exception {
        try{
            index=tableView_f3_admin_editTrainer.getSelectionModel().getSelectedIndex();

            if(index>-1) {
                txtf_f3_emp_editTrainer_tName.setText(tableColumn_admin_trainer_name.getCellData(index));
                txtf_f3_emp_editTrainer_tEmail.setText(tableColumn_admin_trainer_email.getCellData(index));
                txtf_f3_emp_editTrainer_tID.setText(String.valueOf(tableColumn_admin_trainer_id.getCellData(index)));
                txtf_f3_emp_Trainer_Age.setText(String.valueOf(tableColumn_admin_trainer_age.getCellData(index)));
                txtf_f3_emp_editTrainer_tPhone.setText(tableColumn_admin_trainer_phoneNo.getCellData(index));


                String editedGender = toString();
                String getGender = null;

                if (tableColumn_admin_trainer_gender.getCellData(index).equals("Male")) {
                    Radio_boxtxtf_f3_emp_Trainer_Male.setSelected(true);
                } else if (tableColumn_admin_trainer_gender.getCellData(index).equals("Female")) {
                    Radio_boxtxtf_f3_emp_Trainer_Female.setSelected(true);
                }

            }
        }catch (Exception e){

            AlertClass obj2=new AlertClass();
            obj2.errorAlert("ERROR MESSAGE ! \n"  , e.getMessage());
            System.out.println(e);

            }
    }


    @FXML // This lib_button_clicked is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtf_f3_emp_editTrainer_tName != null : "fx:id=\"txtf_f3_emp_editTrainer_tName\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert txtf_f3_emp_editTrainer_tID != null : "fx:id=\"txtf_f3_emp_editTrainer_tID\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert txtf_f3_emp_editTrainer_tEmail != null : "fx:id=\"txtf_f3_emp_editTrainer_tEmail\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert txtf_f3_emp_editTrainer_tPhone != null : "fx:id=\"txtf_f3_emp_editTrainer_tPhone\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert btn_f3_admin_editTrainer_add != null : "fx:id=\"btn_f3_emp_editTrainer_tName\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert btn_f3_admin_editTrainer_edit != null : "fx:id=\"btn_f3_emp_editTrainer_tName1\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert btn_f3_admin_editTrainer_delete != null : "fx:id=\"btn_f3_emp_editTrainer_tName11\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert btn_f3_admin_editTrainer_confirm != null : "fx:id=\"btn_f3_emp_editTrainer_tName111\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert Radio_boxtxtf_f3_emp_Trainer_Male != null : "fx:id=\"Radio_boxtxtf_f3_emp_Trainer_Male\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert Radio_boxtxtf_f3_emp_Trainer_Female != null : "fx:id=\"Radio_boxtxtf_f3_emp_Trainer_Female\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert lbl_trainerid3 != null : "fx:id=\"lbl_trainerid3\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert lbl_n_trainer_age != null : "fx:id=\"lbl_n_trainer_age\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert txtf_f3_emp_Trainer_Age != null : "fx:id=\"txtf_f3_emp_Trainer_Age\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        assert tableView_f3_admin_editTrainer != null : "fx:id=\"tableView_f3_emp_editTrainer\" was not injected: check_password your FXML file 'addEditDeleteTrainer.fxml'.";
        Gender=new ToggleGroup();
        this.Radio_boxtxtf_f3_emp_Trainer_Female.setToggleGroup(Gender);
        this.Radio_boxtxtf_f3_emp_Trainer_Male.setToggleGroup(Gender);

        tableView_f3_admin_editTrainer.setEditable(true);
        try {

            System.out.println("Functions displays trainees  data in table in a class ");
            con1 connect=new con1();
            String query="SELECT * FROM trainer ";//execute query to display
            ResultSet rs=connect.displayData_db(query);
            while (rs.next()) {
                listview.add(new Trainer(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("gender"), rs.getString("phone_number")
                        , rs.getInt("age") ));
            }
            tableView_f3_admin_editTrainer.setItems(listview);
        } catch (Exception e) {
            AlertClass obj2=new AlertClass();
            obj2.errorAlert("ERROR MESSAGE ! \n"  , e.getMessage());

            System.out.println(e);
        }

        tableColumn_admin_trainer_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn_admin_trainer_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn_admin_trainer_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumn_admin_trainer_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableColumn_admin_trainer_phoneNo.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        tableColumn_admin_trainer_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));



    }


    @FXML
    void setNull(){
        txtf_f3_emp_editTrainer_tName.setText(null);
        txtf_f3_emp_editTrainer_tEmail.setText (null);
        txtf_f3_emp_editTrainer_tID.setText(null);
        txtf_f3_emp_Trainer_Age.setText(null);
        txtf_f3_emp_editTrainer_tPhone.setText(null);
        Radio_boxtxtf_f3_emp_Trainer_Male.setSelected(true);
        Radio_boxtxtf_f3_emp_Trainer_Female.setSelected(false);
    }
}
