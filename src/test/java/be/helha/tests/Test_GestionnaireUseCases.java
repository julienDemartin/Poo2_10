package be.helha.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;
import be.helha.controleurs.GestionnaireUseCases;
import be.helha.domaine.Bundle;
import be.helha.domaine.History;
import be.helha.domaine.User;


@TestMethodOrder(OrderAnnotation.class)
public class Test_GestionnaireUseCases {
	private static final String EMAIL_TOTO = "toto@gmail.com";
	private static final String NOM_TOTO = "toto";
	private static final String PASSWORD_TOTO = "1234";

	private static GestionnaireUseCases gestionnaire;
	private static Bundle bundle;
	private User user;

	@BeforeAll
	static void initialiser() {
		bundle = new Bundle();
		//viderLaTable();
		gestionnaire = GestionnaireUseCases.getInstance();
	}

	@AfterAll
	static void terminer() {
		//viderLaTable();
	}

	@Test
	@Order(1)
	public void testDeconnexion() {
		gestionnaire.deconnecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(2)
	public void testAjouterHistory() {
		gestionnaire.ajouterHistory(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(3)
	public void testListerHistory() {
		gestionnaire.lister(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(4)
	public void ajouterMontant() {
		gestionnaire.ajouterMontant(bundle);
		assertFalse((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(5)
	public void retirerMontant() {
		gestionnaire.retirerMontant(bundle);
		assertFalse((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(6)
	public void testConnexion() {
		this.user = new User();
		this.user.setEmail(EMAIL_TOTO);
		this.user.setPassword(PASSWORD_TOTO);
		bundle.put(Bundle.USER, this.user);
		gestionnaire.connecterUser(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		User userConnecte = (User) bundle.get(Bundle.USER);
		assertNotNull(userConnecte);
		assertEquals(NOM_TOTO, userConnecte.getNom());
		assertEquals(EMAIL_TOTO, userConnecte.getEmail());
	}

	@Test
	@Order(7)
	public void testReconnexion() {
		gestionnaire.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(8)
	public void testAjouterHistoryConnecte() {
		History history = new History("BE12 1567 1887 8888","BE12 1567 1887 9999",250.00);
		bundle.put(Bundle.HISTORY, history);
		gestionnaire.ajouterHistory(bundle);
		assertTrue((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(9)
	public void testListeHistoryConnecte() {
		gestionnaire.lister(bundle);
		assertTrue((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(10)
	public void testAjouterMontantConnecte() {
		gestionnaire.ajouterMontant(bundle);
		assertTrue((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(11)
	public void testRetirerMontantConnecte() {
		gestionnaire.retirerMontant(bundle);
		History history = (History)bundle.get(Bundle.HISTORY);
		System.out.println(history);
		assertTrue((Boolean)bundle.get(Bundle.OPERATION_REUSSIE));
	}	
}
