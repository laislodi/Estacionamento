package com.estacionamento.app.persistency.instanciation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDaoInitializer {

    public void createDbStructure(Connection connection) throws SQLException {
        Statement st = connection.createStatement();

        st.executeUpdate("DROP ALL OBJECTS");
        st.executeUpdate("CREATE TABLE CLIENT (\n" +
                "  ID_CLIENT BIGINT,\n" +
                "  NAME VARCHAR(80) NOT NULL,\n" +
                "  TELEPHONE VARCHAR(12) NOT NULL,\n" +
                "  DOCUMENT VARCHAR(15) NOT NULL,\n" +
                "  PRIMARY KEY (ID_CLIENT)\n" +
                ")");

        st.executeUpdate("CREATE TABLE COLOR (\n" +
                "  ID_COLOR BIGINT,\n" +
                "  DESCRIPTION VARCHAR(20) NOT NULL,\n" +
                "  PRIMARY KEY (ID_COLOR),\n" +
                ")");

        st.executeUpdate("CREATE TABLE SIZE (\n" +
                "  ID_SIZE BIGINT,\n" +
                "  DESCRIPTION VARCHAR(20) NOT NULL,\n" +
                "  PRIMARY KEY (ID_SIZE),\n" +
                ")");

        st.executeUpdate("CREATE TABLE VEHICLE (\n" +
                "  ID_VEHICLE BIGINT,\n" +
                "  PLATE VARCHAR(9) NOT NULL,\n" +
                "  MODEL VARCHAR(50) NOT NULL,\n" +
                "  SIZE VARCHAR(1) NOT NULL,\n" +
                "  COLOR INTEGER NOT NULL,\n" +
                "  ID_CLIENT BIGINT,\n" +
                "  PRIMARY KEY (ID_VEHICLE),\n" +
                "  FOREIGN KEY (ID_CLIENT) REFERENCES CLIENT(ID_CLIENT)\n" +
                "  FOREIGN KEY (ID_COLOR) REFERENCES COLOR(ID_COLOR)\n" +
                "  FOREIGN KEY (ID_SIZE) REFERENCES SIZE(ID_SIZE)\n" +
                ")");
        st.close();
    }

    public void initilizeData(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        // Preto, Prata, Branco, Amarelo, Vermelho, Azul, Verde, Outra
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (1, 'Preto') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (2, 'Prata') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (3, 'Branco') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (4, 'Amarelo') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (5, 'Vermelho') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (6, 'Azul') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (7, 'Verde') ");
        st.executeUpdate("INSERT INTO COLOR(ID_COLOR,DESCRIPTION) VALUES (8, 'Outro') ");

        st.executeUpdate("INSERT INTO CLIENT(ID_CLIENT,NAME,TELEPHONE,DOCUMENT) VALUES (2, 'Lais', '51996584712', '12345678952') ");
        st.executeUpdate("INSERT INTO CLIENT(ID_CLIENT,NAME,TELEPHONE,DOCUMENT) VALUES (3, 'Foguinho', '51996584712', '12345678952') ");
        st.executeUpdate("INSERT INTO CLIENT(ID_CLIENT,NAME,TELEPHONE,DOCUMENT) VALUES (1, 'Maria', '51996584712', '12345678952') ");

        st.executeUpdate("INSERT INTO VEHICLE(ID_VEHICLE,PLATE,MODEL,ID_SIZE,ID_COLOR,ID_CLIENT) VALUES (2, 'OEK 1928', 'BMW', 3, 3, 2) ");
        st.executeUpdate("INSERT INTO VEHICLE(ID_VEHICLE,PLATE,MODEL,ID_SIZE,ID_COLOR,ID_CLIENT) VALUES (3, 'OEK 1928', 'Ford', 1, 2, 1) ");
        st.executeUpdate("INSERT INTO VEHICLE(ID_VEHICLE,PLATE,MODEL,ID_SIZE,ID_COLOR,ID_CLIENT) VALUES (1, 'OEK 1928', 'Toyota', 2, 1, 3) ");

        st.close();
    }
}
