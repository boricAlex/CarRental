package kurs.carrental.services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kurs.carrental.beans.Vehicle;

public class VehicleService extends Service {

	public VehicleService(String url, String usr, String psw) throws SQLException {
		super(url, usr, psw);
	}

	public List<Vehicle> searchVehicles(Integer officeId, Integer statusId, Integer categoryId)
			throws SQLException {

		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		String sql = "{call SearchVehicles(?,?,?)}";

		try (CallableStatement stmt = conn.prepareCall(sql)) {
			if (officeId == null)
				stmt.setNull(1, Types.INTEGER);
			else
				stmt.setInt(1, officeId);
			if (statusId == null)
				stmt.setNull(2, Types.INTEGER);
			else
				stmt.setInt(2, statusId);
			if (categoryId == null)
				stmt.setNull(3, Types.INTEGER);
			else
				stmt.setInt(3, categoryId);
			

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Vehicle bk = new Vehicle();
				bk.setVehicleId(rs.getInt(1));
				bk.setModelId(rs.getInt(2));
				bk.setOfficeId(rs.getInt(3));
				bk.setStatusId(rs.getInt(4));
				bk.setCategoryId(rs.getInt(5));
				bk.setFuelTypeId(rs.getInt(6));
				bk.setTransmissionTypeId(rs.getInt(7));
				bk.setRegNumber(rs.getString(8));
				bk.setDoors(rs.getInt(9));
				bk.setSeats(rs.getInt(10));
				bk.setVolume(rs.getInt(11));
				bk.setPower(rs.getInt(12));

				vehicles.add(bk);
			}

			return vehicles;

		} catch (SQLException ex) {
			throw ex;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
