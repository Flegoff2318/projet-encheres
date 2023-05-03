package dal;

import java.util.List;

import dal.jdbc.ArticleVenduImpl;

public interface DAO<T> {
    void insert(T object);
    void delete(int id);
    void update(T object);
    List<T> selectAll();
    T selectById(int id);
    
	public static ArticleVenduDAO getArticleVenduDao() {
		return  new ArticleVenduImpl();
	} 
	
	
}
