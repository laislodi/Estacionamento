package com.estacionamento.app.helper;

import com.estacionamento.app.persistency.entity.ClientEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class H2Helper {

    public void createDbStructure(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();

        statement.executeUpdate("DROP ALL OBJECTS");
        statement.executeUpdate("CREATE TABLE COLOR (\n" +
                "ID_COLOR BIGINT,\n" +
                "COLOR VARCHAR(20)\n" +
                "PRIMARY  KEY(ID_COLOR)\n" +
                ")");
        statement.executeUpdate("CREATE TABLE ");

    }

    public void initilizeData(Connection connection) {

    }

    public void updateData(Connection connection) {

    }

    public void deleteData(Connection connection) {

    }

    public List<ClientEntity> selectAll(Connection connection) {
        return null;
    }
}
