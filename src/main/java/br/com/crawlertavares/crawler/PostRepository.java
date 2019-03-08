package br.com.crawlertavares.crawler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);

    Optional<Post> findById(Long id);

    Page<Post> findAll(Pageable pageable);

    @Query(value = "select count(*) from post", nativeQuery = true)
    Integer getTotalNumPosts();


}
