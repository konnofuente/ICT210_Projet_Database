package com.example.ict210_frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

/****
 *  the exit function will permit use to exit the program
 * ***/
        public static void exit(){
            System.exit(0);
        }

    /**
     * CHANGEOVER FUNCTION IS THE FUNCTION THAT LEAD TO THE INTERCHANGE OF SCENE(INTERPHASES)
     * FROM WANT TO ANTHER WITH PARAMATER AS FOLLOW
     */

    public static void changeover(ActionEvent event, String fxml, String title,Integer width) throws IOException {
        Integer height;
        if(width==900){
            height=600;
        }
        else{
            height=400;
        }
        Parent root=null;
        FXMLLoader loader=new FXMLLoader(DBUtils.class.getResource(fxml));
        root=loader.load();

        Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,width,height));
        stage.show();

    }

    /**
     * SMALL_WINDOW FUNCTION IS THE FUNCTION THAT LEAD TO THE INTERCHANGE OF SCENE(INTERPHASES)
     * WITHOUD CLOSING THE PREVIOUSE WANTS
     */
    public static void small_window(ActionEvent event, String fxml, String title){
        Parent root=null;
        try{
            FXMLLoader loader= new FXMLLoader(DBUtils.class.getResource(fxml));
            root =loader.load();
            Scene scene=new Scene(root,600,400);
            Stage stage =new Stage();
            stage.setScene(scene);


            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Signup function will permit us to enter and administrator information
     */
    public static  void signup_admin(ActionEvent event,String id,String name,String password) throws SQLException {
        Connection connection=null;
       PreparedStatement psInsert=null;
       PreparedStatement checkUserExist=null;
        ResultSet resultSet=null;

    connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projetresaux", "root" , "1234");
        checkUserExist= connection.prepareStatement("SELECT * FROM administrator WHERE id_admin=?");
        checkUserExist.setString(1,id);
        resultSet=checkUserExist.executeQuery();
        if(resultSet.isBeforeFirst()){
            System.out.println("admin already Exist");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot add this administrator\n An admin already Exist with Same Matriculation!!!!!!!!!");
            alert.show();
        }else
        {
            String query="insert into administrator (id_admin,name_admin,password_admin)"+"values (?,?,?)";
           psInsert=connection.prepareStatement(query);
           psInsert.setString(1,id);
           psInsert.setString(2,name);
           psInsert.setString(3,password);
           psInsert.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("The admin "+name+" has been successfully Created!!!!!!");
            alert.show();

            connection.close();
        }

    }

    public static void login_admin(ActionEvent event, String id, String password) throws SQLException, IOException {
        Connection connection=null;
        PreparedStatement checkUserExist=null;
        ResultSet resultSet=null;

        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetresaux", "root" , "1234");
        checkUserExist=connection.prepareStatement("select * from administrator where id_admin=? and password_admin=?");
        checkUserExist.setString(1,id);
        checkUserExist.setString(2,password);
        resultSet=checkUserExist.executeQuery();
        if(!resultSet.isBeforeFirst()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Password or ID re-enter your credentialle");
            alert.show();
        }else{
            DBUtils.changeover(event,"menu.fxml","MENU",900);
        }

    }


    public static void AddTeacher(ActionEvent event ,Integer id,String name,String sexe)throws SQLException{
        Connection connection=null;
        PreparedStatement checkUserExist=null;
        ResultSet resultSet=null;
        PreparedStatement psInsert=null;
        String query;

        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetresaux", "root" , "1234");
        checkUserExist=connection.prepareStatement("select * from enseignant where IDENSEIGNANT = ?");
        checkUserExist.setInt(1,id);
        resultSet=checkUserExist.executeQuery();

        if(resultSet.isBeforeFirst()){
            System.out.println("Teacher already Exist");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot add this Teacher\n A teacher  already Exist with Same ID!!!!!!!!!");
            alert.show();
        }
        else {
            query="insert into enseignant (IDENSEIGNANT,NOM_ENSEIGNANT,SEXE)" + "values(?,?,?)";
            psInsert=connection.prepareStatement(query);
            psInsert.setInt(1,id);
            psInsert.setString(2,name);
            psInsert.setString(3,sexe);
            psInsert.executeUpdate();
            connection.close();

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Mr "+name+ " \n Was successfully Register as Teacher!!!!!!!!!");
            alert.show();

        }
    }


    public static void AddTable2Column(ActionEvent event ,Integer id,String name,String tableName,String PrimaryKey,String SecondColum)throws SQLException{
        Connection connection=null;
        PreparedStatement checkExisting=null;
        PreparedStatement psInsert=null;
        String query;
        ResultSet resultSet;

        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetresaux", "root" , "1234");
        checkExisting=connection.prepareStatement("select * from "+tableName+" where "+PrimaryKey+" = ?");
        checkExisting.setInt(1,id);
        resultSet=checkExisting.executeQuery();

        if(resultSet.isBeforeFirst()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This Id already exist in our "+tableName+" Database!!!!!!!!!");
            alert.show();
        }
        else {
            query="insert into "+tableName+" ("+PrimaryKey+","+SecondColum+") "+"values(?,?)";
            psInsert=connection.prepareStatement(query);
            psInsert.setInt(1,id);
            psInsert.setString(2,name);
            psInsert.executeUpdate();
            connection.close();

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(" Was successfully Register in the "+tableName+" DATABASE!!!!!!!!!");
            alert.show();


        }
    }
}


