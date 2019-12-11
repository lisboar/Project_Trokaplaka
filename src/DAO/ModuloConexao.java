package DAO;

import java.sql.*;

/**
 *
 * @author Thainã
 */
public class ModuloConexao {
    
    public static Connection conector(){
        
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbtrokaplaka";
        String user = "root";
        String password = "";
        
        try{
            Class.forName(driver);
            conexao=DriverManager.getConnection(url, user, password);
            return conexao;
        }catch (Exception e){
            return null;
        }
    }
    
}
