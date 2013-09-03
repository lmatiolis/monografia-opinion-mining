/*Classe: ConnectionFactory
  Pacote: prototipominop.dao
  Funcionalidade: Essa classe é responsável pelas conexão com o banco de dados.
  Autor: Leandro Matioli Santos
*/

package protototipominop.dao;

/**
 *
 * @author Leandro
 */
import java.sql.*;

public class ConnectionFactory {

    static private Connection conn = null;

    static private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static private String DATABASE_URL = "jdbc:mysql://localhost:3306/dbPrototipo";

    static private String user = "";
    static private String password = "";

    //Metodo para definir o usuario e senha do banco
    public static void defineBanco(String usuario, String senha){

        user = usuario;
        password = senha;

    }

    //Realiza a conexão
    public static Connection connect(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DATABASE_URL, user, password);
            return conn;
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.toString());
            sqlException.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException classNotFound){
            System.out.println(classNotFound.toString());
            classNotFound.printStackTrace();
            return null;
        }
    }

    //Disconecta-se do banco
    public static void disconnect(){
        try{
            conn.close();
        }
        catch(Exception exception){
            System.out.println(exception.toString());
            exception.printStackTrace();
        }
        finally{
            try{
            conn.close();
            }
            catch(Exception exception){
                System.out.println(exception.toString());
                exception.printStackTrace();
            }
        }
    }

}
