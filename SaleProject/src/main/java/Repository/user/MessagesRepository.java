package Repository.user;

import Model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    @Query(value = "select * from Messages m where m.sender_id= :id", nativeQuery = true)
    List<Messages> findByUserId(@RequestParam("id") int id);

}
