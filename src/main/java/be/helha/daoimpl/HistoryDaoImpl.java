package be.helha.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import be.helha.dao.HistoryDao;
import be.helha.domaine.History;

public class HistoryDaoImpl implements HistoryDao {
	private static final String AJOUT = "INSERT INTO History (cpteDonneur, cpteReceveur, montant) VALUES (?,?,?)";
	private static final String LISTER = "SELECT * FROM History h WHERE cpteDonneur=?";
	
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
			ps.setString(2, history.getCpteDonneur().trim());
			ps.setString(3, history.getCpteReceveur().trim());
			ps.setDouble(4, history.getMontant());
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
	public List<History> listerHistory(String cpteDonneur) {
		List<History> liste = new ArrayList<History>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			ps.setString(1, cpteDonneur);
			rs = ps.executeQuery();
			while (rs.next()) {
				cpteDonneur = rs.getString("cpteDonneur");
				History history = new History(cpteDonneur, rs.getString(2), rs.getDouble(3));
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
