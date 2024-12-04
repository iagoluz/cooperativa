package cooperativa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
 
    private static final String URL = "jdbc:postgresql://localhost:5432/cooperativa"; // URL do BD
    private static final String USER = "postgres"; // Substitua pelo nome do usuário
    private static final String PASSWORD = "123"; // Substitua pela senha do usuário

    private static Connection connection = null;

    // Método para obter a conexão
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver"); // Carregar o driver
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC não encontrado!");
                e.printStackTrace();
                throw new SQLException(e);
            }
        }
        return connection;
    }

    // Método para fechar a conexão
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
