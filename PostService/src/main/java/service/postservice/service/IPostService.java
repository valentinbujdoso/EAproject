package service.postservice.service;



import service.postservice.model.Post;

import java.util.List;

public interface IPostService {
    public void add(Post post);
    public List<Post> getAll();
    public Post get(Long id);
    public void update(Post post);
    public void delete( Long id);
}
