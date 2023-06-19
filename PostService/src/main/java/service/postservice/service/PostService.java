package service.postservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.postservice.exception.UserNotFoundException;
import service.postservice.model.Post;
import service.postservice.repository.IPostDAO;

import java.util.List;

@Service
@Transactional
public class PostService implements IPostService {
    @Autowired
    private IPostDAO postDao;

    public List<Post> getAll() {
         return postDao.findAll();
    }

    public Post add( Post post) {
        return postDao.save(post);
    }

    public Post get(Long id) {
        return postDao.findById(id).get();
    }

    public Post update(Post post) {
       return postDao.save(post);
    }

    public void delete(Long id) {
        postDao.delete(this.get(id));
    }
    public void checkUser( Long postId, String userId){
        postDao.findByIdAndUserId(postId, userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
    }
}
