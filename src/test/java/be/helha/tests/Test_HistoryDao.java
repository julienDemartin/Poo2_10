package be.helha.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	static void initialiserListeBieres() {
		histories = new ArrayList<History>(4);
		histories.add(new History("2525", "258", 50.00));
		histories.add(new History("2626", "752", 100.00));
		histories.add(new History("2525", "645", 200.50));
		histories.add(new History("2525", "125", 320.78));
	}
	
	@Test
	@Order(1)
	public void testAjouter() {
		for (History h : histories) {
			assertTrue(historyDao.ajouterHistory(h));
		}
	}
	
	@Test
	@Order(2)
	public void testLister() {
		List<History> historyObtenus = historyDao.listerHistory("2525");
		for (int i = 0; i < historyObtenus.size(); i++) {
			assertEquals(historyObtenus.get(i), histories.get(i));
		}
	}



}
