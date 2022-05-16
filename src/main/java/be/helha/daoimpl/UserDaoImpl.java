package be.helha.daoimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import be.helha.dao.UserDao;
import be.helha.domaine.User;

public class UserDaoImpl implements UserDao {
	private static final String GET = "SELECT * FROM account WHERE email=? and mdp = crypt(?, mdp)";

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
				user.setNom(rs.getString("nom"));
				user.setEmail(rs.getString("email"));
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
	
}
