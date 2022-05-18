package be.helha.dao;

import java.util.List;
import be.helha.domaine.History;

public interface HistoryDao extends Dao{
	boolean ajouterHistory(History history);
	List<History> listerHistory();
}
