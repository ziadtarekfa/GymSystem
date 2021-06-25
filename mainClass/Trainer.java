package mainClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static mainClass.con1.close_connection;

public class Trainer {

    private String name;
    private int age;
    private int id;
    private String phone_number;
    private String email;
    private String gender;

    public Trainer(int id, String name, String email, String gender, String phone_number, int age) {

        this.name = name;
        this.age = age;
        this.id = id;
        this.phone_number = phone_number;
        this.email = email;
        this.gender = gender;

    }

    public Trainer() {
    }

    //                getters and setters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //                  methods

    public void Add_trainer() {
        try {

            con1 connectNow= new con1();
            System.out.println("function adds the new trainer data to database IS RUNNING ...... ");

            String query = "INSERT INTO trainer (id, name, age,email,phone_number , gender) " +
                    "VALUES (?,?,?,?,?,?)";

            PreparedStatement pre_statement;
            pre_statement = connectNow.getCon1().prepareStatement(query);
            pre_statement.setInt(1, getId());
            pre_statement.setString(2, getName());
            pre_statement.setInt(3, getAge());
            pre_statement.setString(4, getEmail());
            pre_statement.setString(5, getPhone_number());
            pre_statement.setString(6, getGender());
            int result = pre_statement.executeUpdate();
            System.out.println(result + "records inserted");


        } catch (Exception x) {
            System.out.println(x);



        }


    }

    public void Edit_trainer() {

    }

    public static void Delete_trainer(int trainerId) {
        try {

            con1 connection = new con1();
            int rs = connection.alterData_db("DELETE FROM trainer WHERE id=" + trainerId);


            AlertClass obj=new AlertClass();
            obj.infoAlert("Deleting Trainer from database process is successful ");


        } catch (SQLException e) {

            AlertClass errorAlert=new AlertClass();
            errorAlert.errorAlert("ERROR MESSAGE !" , e.getMessage());

        } finally {
            try {
                close_connection();
            } catch (SQLException e) {

                AlertClass errorAlert=new AlertClass();
                errorAlert.errorAlert("Error in closing connection ! " , e.getMessage());
                System.out.println(e);

            }
        }
    }

    public static ResultSet Display_specific_trainer_basedOn_id(int trainerid) throws SQLException {

        con1 connectNow;
        connectNow = new con1();
        System.out.println("function displays  trainer name to database IS RUNNING ...... ");
        String query;
        PreparedStatement pre_statement;
        query = "Select name From trainer where id="+trainerid;
        ResultSet rs = connectNow.displayData_db(query);
        return rs;

    }

    public  static void Assign_trainer_member(int trainer_id,int member_id ) throws SQLException {

        try {
            con1 connect=new con1();
            String query="insert into member_trainer(trainer_id,member_id)" +
                    "values("+ trainer_id+","+member_id+")";
            int rs=connect.alterData_db(query);

            System.out.println(rs + "records inserted");
            AlertClass obj=new AlertClass();
            obj.infoAlert("Assigning  member to Trainer  process is successful ! ");

        }
        catch (SQLException e ){
            AlertClass error=new AlertClass();
            error.errorAlert("ERROR MESSAGE !", e.getMessage());


        }
        finally {
            try {
                con1.close_connection();
            }
            catch (SQLException e){
                AlertClass error=new AlertClass();
                error.errorAlert("ERROR MESSAGE !", e.getMessage());

            }
        }
    };

    public static boolean Check_id(int checkId) throws SQLException {

        boolean isValid = false;
        con1 connect = new con1();
        String query;
        query = "Select count(*) from trainer WHERE id='" + checkId + "'";
        ResultSet rs = connect.displayData_db(query);
        while (rs.next()) {
            if (rs.getInt(1) == 1) {
                isValid = true;
            } else {
                isValid = false;
            }
        }
        return isValid;
    }

    public static boolean check_availbitiy(String trainerId ,String req_toBe_Assigned_classId )throws SQLException {

        String  req_to_check_date="0";
        String req_to_check_time="0";



        con1 connect0 = new con1();
        String query0="Select class_date , class_time from fitness_class where class_id=" + Integer.parseInt(req_toBe_Assigned_classId);
        ResultSet rs0=connect0.displayData_db(query0);
        while (rs0.next()) {

            req_to_check_date= rs0.getString("class_date");
            System.out.println("assigned class date " + req_to_check_date);
            req_to_check_time=rs0.getString("class_time");
            System.out.println("assigned class time " + req_to_check_time);
        }

        System.out.println("assigned class date " + req_to_check_date);
        System.out.println("assigned class time " + req_to_check_time);

        boolean flagToEnd=false,isAvailable=false;
        con1 connect1 = new con1();
        String query1;
        query1 = "Select class_id from class_trainer where trainer_id=" + Integer.parseInt(trainerId);
        ResultSet classResult = connect1.displayData_db(query1);

        if (classResult.next() == false) {
            return isAvailable=true;
        } else {
            do  {
                String query2;
                query2 = "Select class_date , class_time from fitness_class where class_id=" + Integer.parseInt(classResult.getString("class_id"));
                ResultSet r = connect1.displayData_db(query2);
                while (r.next()) {
                    String foundDate = r.getString("class_date");
                    if (foundDate.equals(req_to_check_date)) {
                        String foundTime = r.getString("class_time");
                        if (foundTime.equals(req_to_check_time)) {
                            System.out.println("trainer is not avaible at that time !");
                           flagToEnd=true;
                           return isAvailable=false;
                        } else {
                           /* LocalTime target = LocalTime.parse( foundTime );
                            Boolean targetInZone=false;
                            if( targetInZone = ( target.isAfter( LocalTime.parse( req_to_check_time ) )
                                            &&
                                            target.isBefore( LocalTime.parse(  ) )
                            )
                            ; )*/
                            return isAvailable=true;
                        }

                    } else if(flagToEnd==true) {
                        System.out.println(isAvailable);
                        break;
                    }else {
                        return isAvailable=true;
                    }

                }


            }while ((classResult.next()));
        }

        return isAvailable;
    }

}










