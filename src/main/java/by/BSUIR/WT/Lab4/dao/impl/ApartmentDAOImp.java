package by.BSUIR.WT.Lab4.dao.impl;

import java.util.List;

import by.BSUIR.WT.Lab4.dao.AbstractDAO;
import by.BSUIR.WT.Lab4.dao.Table;
import by.BSUIR.WT.Lab4.dao.exception.DAOException;
import by.BSUIR.WT.Lab4.dao.intrfs.ApartmentDAO;
import by.BSUIR.WT.Lab4.dao.mapper.RowMapperFactory;
import by.BSUIR.WT.Lab4.entity.Apartment;

public class ApartmentDAOImp extends AbstractDAO<Apartment> implements ApartmentDAO{
	
    private static final String FIND_APPARTMENTS_BY_STATUS 		= "SELECT * FROM " + Table.APARTMENTS + " WHERE status=? ";
    private static final String FIND_APPARTMENTS_BY_PRICE 		= "SELECT * FROM " + Table.APARTMENTS + " WHERE price BETWEEN ? AND ? ";
    private static final String SAVE_APARTMENT_QUERY 			= "INSERT INTO " + Table.APARTMENTS + " (status, number, price) VALUES (?, ?, ?)";
    private static final String UPDATE_APARTMENT_BY_ID			= "UPDATE " + Table.APARTMENTS + " SET status=? price=? WHERE id=?";
    private static final String UPDATE_APARTMENT_STATUS_BY_ID	= "UPDATE " + Table.APARTMENTS + " SET status=?  WHERE id=?";

    public ApartmentDAOImp() {
        super(RowMapperFactory.getInstance().getApartmentRowMapper(), Table.APARTMENTS);
    }

    @Override
    public int save(Apartment apartment) throws DAOException {
        return executeInsertQuery(SAVE_APARTMENT_QUERY, apartment.getStatus(), apartment.getNumber(), apartment.getPrice());
    }

    @Override
    public List<Apartment> findByStatus(String status) throws DAOException {
        return executeQuery(FIND_APPARTMENTS_BY_STATUS,status);
    }

    @Override
    public List<Apartment> findByPrice(double from, double to) throws DAOException {
        return executeQuery(FIND_APPARTMENTS_BY_PRICE,from,to);
    }

    @Override
    public void updateApartmentStatusById(int id,String status) throws DAOException {
        executeUpdateQuery(UPDATE_APARTMENT_STATUS_BY_ID,status,id);
    }

    @Override
    public void updateApartmentById(int id, Apartment apartment) throws DAOException {
        executeUpdateQuery(UPDATE_APARTMENT_BY_ID,apartment.getStatus(),
        		apartment.getPrice(),apartment.getId());
    }
}
