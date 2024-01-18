package School.dao.impl;

import java.util.List;

public interface Repository<T>{
    boolean create(T o);

    //boolean update(T o);

    boolean delete(Long id);

   boolean findByName(String str);

    T findById(int id);

    List<T> findAll();
}
