package hu.roothema.homework.repository;

import hu.roothema.homework.repository.entity.DataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Long> {

    @Override
    DataEntity save(DataEntity dataEntity);

}
