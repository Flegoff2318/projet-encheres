package dal;

import java.util.List;

import bo.ArticleVendu;


public interface ArticleVenduDAO extends DAO<ArticleVendu> {	
	
	// crud
		public List<ArticleVendu> selectAll();
		public ArticleVendu selectById(int id);
}
