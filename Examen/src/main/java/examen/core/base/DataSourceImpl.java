package newstart.core.base;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Data;

@Data
public class DataSourceImpl<T> implements DataSource<T>{

    protected Connection connect;
    protected PreparedStatement ps;
    private final String user  = "postgres";
    private final String password = "seventeen";
    private final String url = "jdbc:postgresql://localhost:5432/projet_fil_rouge";


    @Override
    public void getConnexion() throws SQLException {
        try {
            if (connect == null || connect.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erreur de chargement du driver PostgreSQL", e);
        } catch (SQLException e) {
            throw new SQLException("Erreur de connexion à la base de données : " + url, e);
        }
    }

    

    @Override
    public void closeConnexion() throws ClassNotFoundException, SQLException {
        if (connect != null) {
            try {
                if (!connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            }
        }
}


    @Override
    public ResultSet executeQuery() throws ClassNotFoundException, SQLException{
        return ps.executeQuery();
    }

    @Override
    public int executeUpdate() throws ClassNotFoundException, SQLException{
        return ps.executeUpdate();
    }

    @Override
    public void initStatement(String sql) throws ClassNotFoundException, SQLException {
        if(sql.toUpperCase().trim().startsWith("INSERT")){
            ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }else{
            ps = connect.prepareStatement(sql);
        }
    }

    @Override
    public String generateSql(String sql,String objet) throws ClassNotFoundException, SQLException{
        return String.format(sql,objet);
    }

    @Override
    public T setFiels(T objet, ResultSet resultSet) throws SQLException {
        Field[] fields = objet.getClass().getDeclaredFields();
    
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object value = resultSet.getObject(fieldName);
    
                if (value != null) {
                    if (field.getType().isEnum() && value instanceof String) {
                        field.set(objet, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
                    } else if (field.getType().equals(String.class)) {
                        field.set(objet, value.toString());
                    } else if (field.getType().equals(Integer.TYPE) || field.getType().equals(Integer.class)) {
                        field.set(objet, ((Number) value).intValue());
                    } else if (field.getType().equals(Double.TYPE) || field.getType().equals(Double.class)) {
                        field.set(objet, ((Number) value).doubleValue());
                    } else if (field.getType().equals(Long.TYPE) || field.getType().equals(Long.class)) {
                        field.set(objet, ((Number) value).longValue());
                    } else if (field.getType().equals(java.util.Date.class) && value instanceof java.sql.Timestamp) {
                        field.set(objet, new java.util.Date(((java.sql.Timestamp) value).getTime()));
                    } else {
                        field.set(objet, value);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                throw new SQLException("Erreur lors de l'assignation du champ " + field.getName(), e);
            }
        }
    
        return objet;
    }
}