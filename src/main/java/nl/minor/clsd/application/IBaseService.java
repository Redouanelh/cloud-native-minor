package nl.minor.clsd.application;

import java.util.List;

public interface IBaseService<ENTITY> {
    // Select
    List<ENTITY> findAll();
    ENTITY findById(Integer id);

    // Create, update delete
    boolean save (ENTITY ENTITY);
    boolean update (ENTITY ENTITY);
    boolean delete(ENTITY ENTITY);
    boolean deleteById(Integer id);
}
