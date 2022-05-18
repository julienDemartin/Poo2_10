package be.helha.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import be.helha.dao.HistoryDao;
import be.helha.domaine.History;

public class HistoryDaoImpl implements HistoryDao {
	private static final String AJOUT = "INSERT INTO History (id, cpteDonneur, cpteReceveur, montant) VALUES (?,?,?,?)";
	private static final String LISTER = "SELECT * FROM History h ORDER BY h.";
	
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
			ps.setInt(1, history.getHistoryID()); 
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
			// comment cloturer?
		}
		return ajoutReussi;
	}

	@Override
	public List<History> listerHistory() {
		List<History> liste = new ArrayList<History>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			rs = ps.executeQuery();
			String cpteDonneur = "";
			while (rs.next()) {
				cpteDonneur = rs.getString("cpteDonneur");
				History history = new History(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
				liste.add(history);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// comment cloturer?
		}
		return liste;
	}

	
}
