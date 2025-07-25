package vn.edu.ptit.duongvct.playground.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.playground.playground1.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
