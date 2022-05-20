package be.helha.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import be.helha.dao.HistoryDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.History;

public class Test_HistoryDao {
	private static List<History> histories;
	private static HistoryDao historyDao = (HistoryDao) DaoFactory.getInstance().getDaoImpl(HistoryDao.class);

	@BeforeAll
	static void initialiserListeHistory() {
		histories = new ArrayList<History>(6);
		histories.add(new History("2525", "1111", 50.25));
		histories.add(new History("2626", "2222", 100.00));
		histories.add(new History("2727", "3333", 200.50));
		histories.add(new History("2828", "4444", 320.78));
		histories.add(new History("2525", "5555", 75.45));
		histories.add(new History("2525", "6666", 163.95));
	}
	
	@Test
	@Order(1)
	public void testAjouter() {
		for (History h : histories) {
			assertTrue(historyDao.ajouterHistory(h));
		}
	}
	// Il faut que la table soit vide pour les tests Lister 1 et 2
	@Test
	@Order(2)
	public void testLister1() {
		List<History> historyObtenus = historyDao.listerHistory("2525");
			assertEquals(3,historyObtenus.size());
	}
	
	@Test
	@Order(3)
	public void testLister2() {
		List<History> historyObtenus = historyDao.listerHistory("2626");
			assertEquals(1,historyObtenus.size());
			assertNotEquals(2,historyObtenus.size());
	}



}
