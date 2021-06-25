package mainClass.Membership;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import mainClass.Trainee;
import mainClass.con1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Term_package extends  Membership{


    public Term_package() throws SQLException {
        super();
        setType("Term_package");
        setPrice(4000);
        Date currentDate=new Date();
        setStart_date(S_date_toString(currentDate));
        setEnd_date(E_Date_toString(currentDate));
        setM_description("This package fees is paid year by year ");

    }


    @Override
    public  void Display_Trainees_in_membership(ObservableList x , TableView Y) {
        System.out.println("Members have open package membership" );

        try {
            System.out.println("Functions displays trainees  data in table ");
            con1 connect=new con1();
            String query="SELECT  MEMBER_ID FROM  term_package" ;
            ResultSet rs=connect.displayData_db(query);                                               //execute query to display

            query= "SELECT  name FROM member WHERE id in ( select member_id from term_package ) " ;
            ResultSet rs1=connect.displayData_db(query);
            while (rs.next() && rs1.next()) {
                x.add(new Trainee(rs.getInt("member_id"),rs1.getString("name")));
                System.out.println(rs.getInt("member_id"));
            }
            Y.setItems(x);
        } catch (Exception e) { System.out.println(e); }
    }


    @Override
    public void Display_membership_information() {


    }
}
