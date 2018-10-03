package com.estacionamento.app.persistency;

import com.estacionamento.app.persistency.entity.ColorEntity;
import com.estacionamento.app.persistency.entity.SizeEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    // Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123") ) {
    public List<Long> findIdClientsByPlate(String plate) {
        List<Long> idClientsByPlate = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            String sqlStatement = "SELECT DISTINCT ID_CLIENT FROM VEHICLE WHERE PLATE LIKE ? ";
            PreparedStatement st = connection.prepareStatement(sqlStatement);
            StringBuilder likePlate = new StringBuilder("%" + plate + "%");
            st.setString(1, likePlate.toString());
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                idClientsByPlate.add(resultSet.getLong("ID_CLIENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idClientsByPlate;
    }

    public List<VehicleEntity> findVehicle(String document) {
        List<VehicleEntity> vehicleEntityList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            String sqlStatement = "SELECT C.ID_CLIENT, C.NAME, C.DOCUMENT, C.TELEPHONE, " +
                    " V.ID_VEHICLE, V.PLATE, V.MODEL, " +
                    " CL.ID_COLOR, CL.DESCRIPTION COLOR, " +
                    " S.DESCRIPTION SIZE " +
                    " FROM CLIENT C " +
                    " INNER JOIN VEHICLE V ON C.ID_CLIENT = V.ID_CLIENT " +
                    " INNER JOIN COLOR CL ON V.ID_COLOR = V.ID_COLOR " +
                    " INNER JOIN SIZE S ON S.ID_SIZE = V.ID_SIZE " +
                    " WHERE C.DOCUMENT = ?";
            PreparedStatement st = connection.prepareStatement(sqlStatement);
            st.setString(1, document);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ColorEntity colorEntity = new ColorEntity(rs.getLong("CL.ID_COLOR"), rs.getString("COLOR"));
                SizeEntity sizeEntity = new SizeEntity(rs.getLong("S.ID_SIZE"), rs.getString("S.DES_SIZE"));
                VehicleEntity entity = new VehicleEntity(
                        rs.getLong("V.ID_VEHICLE"),
                        rs.getString("V.PLATE"),
                        rs.getString("V.MODEL"),
                        colorEntity,
                        sizeEntity,
                        rs.getLong("C.ID_CLIENT"));
                vehicleEntityList.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleEntityList;
    }

    public List<ColorEntity> findAllColors() {
        List<ColorEntity> allColors = new ArrayList<>();

        String sqlAllColors = " SELECT * FROM COLOR ";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlAllColors);
            while (rs.next()) {
                allColors.add(
                        new ColorEntity(
                            rs.getLong("ID_COLOR"),
                            rs.getString("DESCRIPTION")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allColors;
    }

    public List<SizeEntity> findAllSizes() {
        List<SizeEntity> allSizes = new ArrayList<>();

        String sqlAllSizes = " SELECT * FROM SIZE ";

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlAllSizes);
            while (rs.next()) {
                allSizes.add(
                        new SizeEntity(
                            rs.getLong("ID_SIZE"),
                            rs.getString("DESCRIPTION")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allSizes;
    }

    public void saveVehicle(VehicleEntity entity) {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {

            String sqlSaveVehicle = " INSERT INTO VEHICLE (ID_VEHICLE, PLATE, MODEL, ID_COLOR, ID_SIZE, ID_CLIENT) " +
                    " VALUES (SEQ_ID_VEHICLE.NEXTVAL,?,?,?,?,?) ";
            PreparedStatement ps = connection.prepareStatement(sqlSaveVehicle);
            ps.setString(1, entity.getPlate());
            ps.setString(2, entity.getModel());
            ps.setLong(3, entity.getColor().getIdColor());
            ps.setLong(4, entity.getSize().getId());
            ps.setLong(5, entity.getIdClient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
