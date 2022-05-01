package ru.rsreu.FindingTravelCrappo.datalayer.db;

import java.sql.SQLException;

import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleDAOFactory;

public enum DBType {
    ORACLE {
        @Override
        public DAOFactory getDAOFactory() {
            return new OracleDAOFactory();
        }
    };

    public static DBType getTypeByName(String dbType) {
        try {
            return DBType.valueOf(dbType.toUpperCase());
        } catch (IllegalStateException ex) {
            throw new DBTypeException(ex.getMessage(), ex);
        }
    }

    public abstract DAOFactory getDAOFactory();
}
