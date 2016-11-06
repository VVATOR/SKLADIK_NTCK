package by.ntck.sten.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import by.ntck.sten.model.Kladovshik;
import by.ntck.sten.model.Sklad;

public interface IKladovshikService extends IService<Kladovshik>{
	public  Kladovshik login(String login, String password);
	
	public List<Kladovshik> kladovshikBySklad(int id_sklad) ;
	
}
