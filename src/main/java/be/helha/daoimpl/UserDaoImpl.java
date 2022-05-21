package be.helha.daoimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import be.helha.dao.UserDao;
import be.helha.domaine.User;

public class UserDaoImpl implements UserDao {
	private static final String GET = "SELECT * FROM account WHERE email=? and mdp = crypt(?, mdp)";
	private static final String UPDATE = "UPDATE account SET solde = ? WHERE numero = ? ";
	private static final String GETRECEVEUR = "SELECT * FROM account WHERE numero = ?";

	public UserDaoImpl() {
	}
	
	
	@Override
	public User getUser(String email, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET);
			ps.setString(1, email.trim());
			ps.setString(2, password.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setNom(rs.getString("nom"));
				user.setPassword(rs.getString("mdp"));
				user.setNumero(rs.getString("numero"));
				user.setSolde(rs.getDouble("solde"));
				user.setDecouvert(rs.getDouble("decouvert"));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return user;
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


	@Override
	public boolean ajouterMontant(String cptereceveur, Double montant) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(UPDATE);
			ps.setDouble(1, montant);
			ps.setString(2, cptereceveur);
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
	public boolean retirerMontant(String cptedonneur, Double montant) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(UPDATE);
			ps.setDouble(1, montant);
			ps.setString(2, cptedonneur);
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
	public User getReceveur(String numero) {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GETRECEVEUR);
			ps.setString(1, numero.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setNom(rs.getString("nom"));
				user.setPassword(rs.getString("mdp"));
				user.setNumero(rs.getString("numero"));
				user.setSolde(rs.getDouble("solde"));
				user.setDecouvert(rs.getDouble("decouvert"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return user;
	}
}
