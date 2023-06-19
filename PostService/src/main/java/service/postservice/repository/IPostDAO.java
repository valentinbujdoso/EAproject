package service.postservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.postservice.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPostDAO extends JpaRepository<Post, Long> {
            @Query("select  p from  Post p where p.userId = ''")
            List<Post> findByUser(Long userId);

            @Query("SELECT p FROM Post p WHERE p.postId=:postId and p.userId=:userId")
            Optional<Post> findByIdAndUserId(@Param("postId") Long id, @Param("userId")String userId);

}
