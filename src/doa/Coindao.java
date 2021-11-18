package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coins.coin;

public class Coindao {
	private Connection connection;
	private final String GET_COINS_QUERY = "SELECT * FROM coins";
	private final String GET_COIN_BY_ID_QUERY = "SELECT * FROM coins WHERE id = ?";
	private final String CREATE_NEW_TEAM_QUERY = "INSERT INTO coins(name) VALUES(?)";
	private final String DELETE_COIN_BY_ID_QUERY = "DELETE FROM coins WHERE id = ?";
	private final String UPDATE_COIN_BY_ID_QUERY = "UPDATE coins set name = ? WHERE id = ?";

	public Coindao() {
		connection = DBConnection.getConnection();
	}

	public List<coin> getcoins() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_COINS_QUERY).executeQuery();
		List<coin> coins = new ArrayList<coin>();

		while (rs.next()) {
			coins.add(populatecoin(rs.getInt(1), rs.getString(2)));
		}
		return coins;

	}

	public coin getcoinById(int coinID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_COIN_BY_ID_QUERY);
		ps.setInt(1, coinID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populatecoin(rs.getInt(1), rs.getString(2));
	}

	public void createNewCoin(String coinName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TEAM_QUERY);
		ps.setString(1, coinName);
		ps.executeUpdate();
	}

	public void deleteCoinById(int coinID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_COIN_BY_ID_QUERY);
		ps.setInt(1, coinID);
		ps.executeUpdate();
	}

	public void UpdateCoinById(int coinID, String coinName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_COIN_BY_ID_QUERY);
		ps.setString(1, coinName);
		ps.setInt(2, coinID);
		ps.executeUpdate();
	}

	public coin populatecoin(int coinID, String name) throws SQLException {
		return new coin(coinID, name);

	}
}
