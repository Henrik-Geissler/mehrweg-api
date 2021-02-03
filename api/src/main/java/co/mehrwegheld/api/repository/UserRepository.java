package co.mehrwegheld.api.repository;

import co.mehrwegheld.api.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
