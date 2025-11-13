package Repository.user;

import Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select * from Users where Users.email = :email", nativeQuery = true)
    Users FindByEmail(@Param("email") String email);
    @Query(value = "select * from Users where Users.token= :token",nativeQuery = true)
    Optional<Users> FindByToken(@Param("token") String token);
    @Query(value = "select * from Users where Users.user_id = :user_id",nativeQuery = true)
    Users FindById(@Param("user_id") int user_id);
}
