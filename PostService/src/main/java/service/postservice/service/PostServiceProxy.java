package service.postservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import service.postservice.model.Post;


import java.util.List;

//@Service
public class PostServiceProxy implements IPostService {

    @Autowired
    private RestTemplate restTemplate;
    private final String postUrl = "http://localhost:8082/posts/{id}";
    private final String postsUrl = "http://localhost:8082/posts";

    @Override
    public List<Post> getAll() {
        ResponseEntity<List<Post>> response = restTemplate.exchange(postsUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    @Override
    public void add(Post post) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Post> httpEntity = new HttpEntity<>(post, headers);

        restTemplate.postForObject(postsUrl, httpEntity, Post.class);
    }

    @Override
    public Post get(Long id) {
        return restTemplate.getForObject(postUrl, Post.class, id);
    }

    @Override
    public void update(Post post) {
        restTemplate.put(postUrl, post, post.getPostId());
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(postUrl, id);
    }
}
