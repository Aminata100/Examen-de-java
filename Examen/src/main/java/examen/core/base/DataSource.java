package newstart.core.base;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource<T> {
    void getConnexion() throws SQLException;
    void closeConnexion()throws ClassNotFoundException, SQLException;
    ResultSet executeQuery()throws ClassNotFoundException, SQLException;
    int executeUpdate()throws ClassNotFoundException, SQLException;
    void initStatement(String sql)throws ClassNotFoundException, SQLException;
    String generateSql(String sql,String objet)throws ClassNotFoundException, SQLException;
    T setFiels(T objet, ResultSet resultSet) throws SQLException;

}
