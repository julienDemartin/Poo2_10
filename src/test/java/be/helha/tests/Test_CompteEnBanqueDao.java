package be.helha.tests;




import org.junit.jupiter.api.Test;

import be.helha.dao.UserDao;
import be.helha.daoimpl.DaoFactory;


public class Test_CompteEnBanqueDao {
	private static UserDao user = (UserDao) DaoFactory.getInstance().getDaoImpl(UserDao.class);
	
	
	
	@Test
	
	static void deposer() throws Exception
	{
		
	}
}
