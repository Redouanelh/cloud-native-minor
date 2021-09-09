package nl.minor.clsd.application;

import nl.minor.clsd.domain.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseService <ENTITY extends BaseEntity> implements IBaseService<ENTITY> {
    // Each service has a repository, entity and ENTITY object
    private final JpaRepository<ENTITY, Integer> repository;

    protected BaseService(JpaRepository<ENTITY, Integer> repository) {
        this.repository = repository;

    }

    /************************************** Get Entities **************************************/

    @Transactional
    public List<ENTITY> findAll()  { return repository.findAll(); }

    @Transactional
    public ENTITY findById(Integer id)  {

        // Default return value
        ENTITY returnEntity = null;

        // If entity exists, select
        Optional<ENTITY> entityOptional = repository.findById(id);
        if(entityOptional.isPresent()) returnEntity = entityOptional.get();

        return returnEntity;
    }


    /*********************************** Save, Update & Delete Entities **********************************/

    @Transactional
    public boolean save(ENTITY entity)  {

        // Attempt to save entity
        repository.save(entity);

        // Check if entity is saved and return boolean
        Optional<ENTITY> entitySaved = repository.findById(entity.getId());
        return entitySaved.isPresent();
    }

    @Transactional
    public boolean update(ENTITY entity) {

        // Attempt to find entity
        Optional<ENTITY> entityOptional = repository.findById(entity.getId());

        // Check if entity is present
        if(entityOptional.isPresent()) {
            repository.save(entity);
            return true;

        }else return false;
    }

    @Transactional
    public boolean delete(ENTITY entity) {
        // Attempt to find entity
        Optional<ENTITY> entityOptional = repository.findById(entity.getId());

        // If present, delete
        if (entityOptional.isPresent()) {
            repository.delete(entity);
            return true;

        }else return false;
    }

    @Transactional
    public boolean deleteById(Integer id)  {
        // Attempt to find entity
        Optional<ENTITY> entityOptional = repository.findById(id);

        // If present, delete
        if (entityOptional.isPresent()) {
            repository.delete(entityOptional.get());
            return true;

        } else return false;
    }
}
