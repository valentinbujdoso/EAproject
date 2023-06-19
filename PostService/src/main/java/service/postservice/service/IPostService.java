package service.postservice.service;



import service.postservice.model.Post;

import java.util.List;

public interface IPostService {
    public Post add(Post post);
    public List<Post> getAll();
    public Post get(Long id);
    public Post update(Post post);
    public void delete( Long id);
}
