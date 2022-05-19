package be.helha.daoimpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import be.helha.dao.Dao;
import be.helha.daoimpl.HistoryDaoImpl;
/*
 * Classe chargée de fournir les instances des daos
 * et les connexions à la db (si spécifiée dans le fichier de configuration)
 *
 */
// pattern singloton: une classe ou on peut créer un seul objet unique
public class DaoFactory
{
	private static final String FICHIER_CONFIGURATION = "src/main/resources/config.xml";
	//qu'une seul instance en ram
	private static final DaoFactory INSTANCE = new DaoFactory();

	private Persistance persistance;

	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	//constructeur pouvant être appeler que depuis cette classe, parserconfig, classe qui permet de lire le fichier de configuration
	private DaoFactory() {
		try {
			this.persistance = ParserConfig.lireConfiguration(FICHIER_CONFIGURATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// renvoie une connexion créée en utilisant les données du fichier de
	// configuration
	public Connection getConnexion() {
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(persistance.getUrl(), persistance.getUser(),
					persistance.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connexion;
	}

	// renvoie l'instance du dao dont on spécifie l'interface
	//recoit une interface et attend un descripteur de dao
	public Dao getDaoImpl(Class<? extends Dao> interfaceDao) {
		return this.persistance.getDaoImpl(interfaceDao);
	}
}
