package be.helha.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			ps.setInt(1, 1); // setter l'id (auto?)
			ps.setString(2, history.getCpteReceveur()); // prendre le compte donneur
			ps.setString(3, history.getCpteReceveur());
			ps.setDouble(4, history.getMontant());
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				ajoutReussi = true;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return ajoutReussi;
	}

	@Override
	public List<History> listerHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
