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
		histories.add(new History("BE12 1567 1887 5555", "BE12 1567 1887 4444", 50.25));
		histories.add(new History("BE12 1567 1887 4444", "BE12 1567 1887 8888", 100.00));
		histories.add(new History("BE12 1567 1887 8888", "BE12 1567 1887 4444", 200.50));
		histories.add(new History("BE12 1567 1887 8888", "BE12 1567 1887 9999", 320.78));
		histories.add(new History("BE12 1567 1887 5555", "BE12 1567 1887 9999", 75.45));
		histories.add(new History("BE12 1567 1887 5555", "BE12 1567 1887 8888", 163.95));
	}
	
	@Test
	@Order(1)
	public void testAjouter() {
		for (History h : histories) {
			assertTrue(historyDao.ajouterHistory(h));
		}
	}
	
	// Il faut que la table soit vide pour les tests Lister 1 et 2
	/*
	@Test
	@Order(2)
	public void testLister1() {
		List<History> historyObtenus = historyDao.listerHistory("BE12 1567 1887 5555");
			assertEquals(3,historyObtenus.size());
	}
	
	@Test
	@Order(3)
	public void testLister2() {
		List<History> historyObtenus = historyDao.listerHistory("BE12 1567 1887 4444");
			assertEquals(1,historyObtenus.size());
			assertNotEquals(2,historyObtenus.size());
	}
	*/
}
