package dal;

import java.util.List;



public interface DAO<T> {
    void insert(T object);
    void delete(int id);
    void update(T object);
    List<T> selectAll();
    T selectById(int id);
    
}
