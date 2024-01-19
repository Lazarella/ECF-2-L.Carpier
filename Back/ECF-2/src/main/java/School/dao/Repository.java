package School.dao;

import java.util.List;

public interface Repository<T>{
    boolean create(T o);

    //boolean update(T o);

    boolean delete(Long id);

  T findByName(String str);

    T findById(int id);

    List<T> findAll();
}
