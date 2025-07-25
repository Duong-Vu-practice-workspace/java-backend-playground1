package vn.edu.ptit.duongvct.playground.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.playground.playground1.domain.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUserId(Integer id);
}
