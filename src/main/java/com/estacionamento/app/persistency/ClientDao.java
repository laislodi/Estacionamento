package com.estacionamento.app.persistency;

import com.estacionamento.app.persistency.entity.ClientEntity;
import com.estacionamento.app.persistency.entity.ColorEntity;
import com.estacionamento.app.persistency.entity.SizeEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {

    private static final String SELECT_CLIENT_AND_VEHICLES =
            " SELECT C.ID_CLIENT, C.NAME, C.DOCUMENT, C.TELEPHONE, " +
            " V.ID_VEHICLE, V.PLATE, V.MODEL, " +
            " CL.ID_COLOR, CL.DESCRIPTION C_DES, " +
            " S.ID_SIZE, S.DESCRIPTION S_DES " +
            " FROM CLIENT C " +
            " INNER JOIN VEHICLE V ON C.ID_CLIENT = V.ID_CLIENT " +
            " INNER JOIN COLOR CL ON V.ID_COLOR = CL.ID_COLOR " +
            " INNER JOIN SIZE S ON S.ID_SIZE = V.ID_SIZE " +
            " WHERE C.ID_CLIENT = ? ";
    private static final String SELECT_CLIENTS = "SELECT C FROM CLIENT ";
    private static final String SELECT_VEHICLES =
            "SELECT V.ID_VEHICLE, V.PLATE , V.MODEL, S.ID_SIZE, S.DESCRIPTION S_DES, C.ID_COLOR , C.DESCRIPTION C_DES " +
            " FROM VEHICLE V " +
            " INNER JOIN COLOR C ON V.ID_COLOR = C.ID_COLOR " +
            " INNER JOIN SIZE S ON S.ID_SIZE = V.ID_SIZE ";
    private static final String SELECT_VEHICLES_WHERE =
            "SELECT C.ID_CLIENT, V.ID_VEHICLE, V.PLATE , V.MODEL, S.ID_SIZE, S.DESCRIPTION S_DES, CL.ID_COLOR , CL.DESCRIPTION C_DES " +
            " FROM CLIENT C " +
            " INNER JOIN VEHICLE V ON C.ID_CLIENT = V.ID_CLIENT " +
            " INNER JOIN COLOR CL ON CL.ID_COLOR = V.ID_COLOR " +
            " INNER JOIN SIZE S ON S.ID_SIZE = V.ID_SIZE" +
            " WHERE C.DOCUMENT = ? ";

    public ClientDao() {
//        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
//            ClientDaoInitializer clientDaoInitializer = new ClientDaoInitializer();
//            //clientDaoInitializer.createDbStructure(connection);
//            //clientDaoInitializer.initilizeData(connection);
//            loadClientAndVehicles(connection);
//            //clientDaoInitializer.deleteData(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    private List<ClientEntity> loadClients() {
        List<ClientEntity> clientList = findClientByName("%");

        return clientList;
    }

    public ClientEntity loadClientAndVehicles(Long idClient) {
        ClientEntity clientEntity = new ClientEntity();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            PreparedStatement st = connection.prepareStatement(SELECT_CLIENT_AND_VEHICLES);
            st.setLong(1, idClient);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (rs.isFirst()) {
                    clientEntity.setIdClient(rs.getLong("ID_CLIENT"));
                    clientEntity.setName(rs.getString("NAME"));
                    clientEntity.setDocument(rs.getString("DOCUMENT"));
                    clientEntity.setTelephone(rs.getString("TELEPHONE"));
                }
                ColorEntity colorEntity = new ColorEntity(rs.getLong("ID_COLOR"), rs.getString("C_DES"));
                SizeEntity sizeEntity = new SizeEntity(rs.getLong("ID_SIZE"), rs.getString("S_DES"));

                VehicleEntity vehicleEntity = new VehicleEntity(
                        rs.getLong("ID_VEHICLE"),
                        rs.getString("PLATE"),
                        rs.getString("MODEL"),
                        colorEntity,
                        sizeEntity,
                        rs.getLong("ID_CLIENT")
                );

                clientEntity.getVehicles().add(vehicleEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientEntity;
    }

    public List<VehicleEntity> findVehicles(String document) {
        List<VehicleEntity> vehicleEntities = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            PreparedStatement st = connection.prepareStatement(SELECT_VEHICLES_WHERE);
            st.setString(1, document);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                VehicleEntity vehicle = new VehicleEntity(rs.getLong("ID_VEHICLE"),
                        rs.getString("PLATE"),
                        rs.getString("MODEL"),
                        new ColorEntity(rs.getLong("ID_COLOR"), rs.getString("C_DES")),
                        new SizeEntity(rs.getLong("ID_SIZE"),rs.getString("S_DES")),
                        rs.getLong("ID_CLIENT")
                );
                vehicleEntities.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleEntities;
    }

    public List<VehicleEntity> findAllVehicles() {
        List<VehicleEntity> vehicleEntities = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_VEHICLES);
            while (rs.next()) {
                VehicleEntity vehicle = new VehicleEntity(rs.getLong("ID_VEHICLE"),
                        rs.getString("PLATE"),
                        rs.getString("MODEL"),
                        new ColorEntity(rs.getLong("ID_COLOR"), rs.getString("C_DES")),
                        new SizeEntity(rs.getLong("ID_SIZE"),rs.getString("S_DES")),
                        rs.getLong("ID_CLIENT")
                );
                vehicleEntities.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleEntities;
    }

    public void saveClient(ClientEntity entity) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            PreparedStatement st = connection.prepareStatement(" UPDATE CLIENT SET NAME = ?, TELEPHONE = ?, DOCUMENT= ? WHERE ID_CLIENT = ?; ");
            st.setString(1, entity.getName());
            st.setString(2, entity.getTelephone());
            st.setString(3, entity.getDocument());
            st.setLong(4, entity.getIdClient());

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerClient (ClientEntity entity) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            PreparedStatement st = connection.prepareStatement(" INSERT INTO CLIENT (ID_CLIENT, NAME, TELEPHONE, DOCUMENT) VALUES (SEQ_ID_CLIENT.NEXTvAL,?,?,?); ");
            st.setString(1, entity.getName());
            st.setString(2, entity.getTelephone());
            st.setString(3, entity.getDocument());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ClientEntity findClientByDocument(String document) {
        ClientEntity clientEntity = new ClientEntity();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ) {
            String sqlfindClientByDocument = " SELECT * FROM CLIENT WHERE DOCUMENT = ? ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlfindClientByDocument);
            clientEntity.setIdClient(rs.getLong("ID_CLIENT"));
            clientEntity.setName(rs.getString("NAME"));
            clientEntity.setTelephone(rs.getString("TELEPHONE"));
            clientEntity.setDocument(document);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientEntity;
    }

    public List<ClientEntity> findAllClients() {
        List<ClientEntity> clientEntities = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ){
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(" SELECT * FROM CLIENT ");
            while (resultSet.next()) {
                ClientEntity entity = new ClientEntity();
                entity.setIdClient(resultSet.getLong("ID_CLIENT"));
                entity.setName(resultSet.getString("NAME"));
                entity.setTelephone(resultSet.getString("TELEPHONE"));
                entity.setDocument(resultSet.getString("DOCUMENT"));

                clientEntities.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientEntities;
    }


    public ClientEntity findClientById(Long id) {
        ClientEntity entity = new ClientEntity();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ){
            String sqlStatement = " SELECT * FROM CLIENT WHERE ID_CLIENT = ? ";
            PreparedStatement st = connection.prepareStatement(sqlStatement);
            st.setString(1, id.toString());
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                entity = loadClientEntity(resultSet);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public List<ClientEntity> findClientByName(String name) {
        List<ClientEntity> clientEntityList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ) {

            String sqlStatement = "SELECT * FROM CLIENT WHERE NAME LIKE ? ";
            PreparedStatement st = connection.prepareStatement(sqlStatement);
            StringBuilder nameLike = new StringBuilder("%" + name+ "%");
            st.setString(1, nameLike.toString());
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                clientEntityList.add(loadClientEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientEntityList;
    }

    private ClientEntity loadClientEntity(ResultSet resultSet) throws SQLException {
        ClientEntity entity = new ClientEntity();

        entity.setIdClient(resultSet.getLong("ID_CLIENT"));
        entity.setName(resultSet.getString("NAME"));
        entity.setTelephone(resultSet.getString("TELEPHONE"));
        entity.setDocument(resultSet.getString("DOCUMENT"));
        entity.setVehicles(new ArrayList<>());

        return entity;
    }

    public void updateClient(ClientEntity entity) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ) {
            String sqlUpdate = "UPDATE CLIENT SET NAME = ?, DOCUMENT = ?, TELEPHONE = ? WHERE ID_CLIENT = ?";
            PreparedStatement st = connection.prepareStatement(sqlUpdate);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDocument());
            st.setString(3, entity.getTelephone());
            st.setLong(4, entity.getIdClient());
            int numberOfLinesModified = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(Long idClient) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ) {
            String sqlDeleteVehicles = " DELETE FROM VEHICLE WHERE ID_CLIENT = ? ";
            PreparedStatement stVehicle = connection.prepareStatement(sqlDeleteVehicles);
            stVehicle.setLong(1, idClient);
            stVehicle.executeUpdate();

            String sqlDeleteClient = " DELETE FROM CLIENT WHERE ID_CLIENT = ? ";
            PreparedStatement stClient = connection.prepareStatement(sqlDeleteClient);
            stClient.setLong(1, idClient);
            stClient.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
