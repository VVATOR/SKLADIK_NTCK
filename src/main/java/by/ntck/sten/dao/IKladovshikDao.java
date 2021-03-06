package by.ntck.sten.dao;

import java.util.List;

import by.ntck.sten.model.Kladovshik;
import by.ntck.sten.model.Sklad;

public interface IKladovshikDao extends IDAO<Kladovshik>{
	
	public  Kladovshik login(String login, String password);
		
	public List<Sklad> SkladBykladovshik(int id_kladovshok);
	
	public List<Sklad> SkladBykladovshikSearch(int id_kladovshok, int index, String naim);
	
	public String getRole(int id);
		
}
