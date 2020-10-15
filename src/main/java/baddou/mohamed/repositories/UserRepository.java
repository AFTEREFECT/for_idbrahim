package baddou.mohamed.repositories;


import baddou.mohamed.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
UserEntity findByEmail(String email);

}
