package be.helha.tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import be.helha.daoimpl.ParserConfig;
import be.helha.daoimpl.Persistance;
import be.helha.dao.*;

/*
 * Tests unitaires de ParserConfig
 */
public class Test_Parser {
	private Persistance persistance;

	

	@Test
	public void testPostgres1() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres1.xml");
			assertEquals("DB", persistance.getType());
			String toStringDao = persistance.getDaoImpl(UserDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.helha.daoimpl.UserDaoImpl"));
			assertEquals("jdbc:postgresql://localhost:5432/postgres",
					persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	
/*
	@Test
	public void testPostgres2() { // doit fonctionner avec les 2 daos
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres2.xml");
			assertEquals("DB", persistance.getType());
			String toStringDao = persistance.getDaoImpl(UserDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.helha.daoimpl.UserDaoImpl"));
			toStringDao = persistance.getDaoImpl(UserDao.class).toString();
			assertTrue(toStringDao.contains("be.helha.daoimpl.CompteEnBanqueDaoImpl"));
			assertEquals("jdbc:postgresql://localhost:5432/postgres",
					persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
*/
	@Test
	public void testPostgres3() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres3.xml");
			fail("Il aurait du générer une exception car il manque la balise <url>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testPostgres4() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres4.xml");
			fail("Il aurait du générer une exception car il manque la balise <user>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testPostgres5() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres5.xml");
			fail("Il aurait du générer une exception car il manque la balise <password>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	
}