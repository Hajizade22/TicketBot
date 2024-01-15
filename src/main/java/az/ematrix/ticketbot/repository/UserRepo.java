package az.ematrix.ticketbot.repository;


import az.ematrix.ticketbot.entity.security.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findUsersEntityByEmail(String email);
}
