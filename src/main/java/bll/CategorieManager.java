package bll;

import java.util.List;

import bo.Categorie;
import dal.DAOFactory;

public class CategorieManager {
	
	private static CategorieManager instance;
	
	private CategorieManager() {
	}
	
	public static CategorieManager getInstance() {
		if(instance==null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public List<Categorie> selectionnerCategories(){
		return DAOFactory.getCategorieDAO().selectAll();
	}
	
	public Categorie selectionnerCategorieParId(int id){
		return DAOFactory.getCategorieDAO().selectById(id);
	}
}
