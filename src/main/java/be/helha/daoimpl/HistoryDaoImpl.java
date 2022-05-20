package be.helha.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import be.helha.dao.HistoryDao;
import be.helha.domaine.History;

public class HistoryDaoImpl implements HistoryDao {
	private static final String AJOUT = "INSERT INTO History (cptedonneur, cptereceveur, montant) VALUES (?,?,?)";
	private static final String LISTER = "SELECT * FROM History h WHERE cptedonneur=?";
	
	public HistoryDaoImpl() {
		
	}

	@Override
	public boolean ajouterHistory(History history) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(AJOUT);
			//ps.setString(1, history.getHistoryID()); 
			ps.setString(1, history.getCpteDonneur().trim());
			ps.setString(2, history.getCpteReceveur().trim());
			ps.setDouble(3, history.getMontant());
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				ajoutReussi = true;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, ps, con);
		}
		return ajoutReussi;
	}

	@Override
	public List<History> listerHistory(String cptedonneur) {
		List<History> liste = new ArrayList<History>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			ps.setString(1, cptedonneur);
			rs = ps.executeQuery();
			while (rs.next()) {
				cptedonneur = rs.getString("cptedonneur");
				History history = new History(cptedonneur, rs.getString(2), rs.getDouble(3));
				liste.add(history);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, ps, con);
		}
		return liste;
	}
	
	private void cloturer(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception ex) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
		}
	}

	
}
