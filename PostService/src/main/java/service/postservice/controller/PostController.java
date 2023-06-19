package service.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.postservice.model.Post;
import service.postservice.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/v1/{userId}/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    OAuth2RestTemplate restTemplate;

    public String getUserName(){
        return restTemplate.getForObject("http://localhost:8088/user/id", String.class);
    }
    @GetMapping
    public List<Post> getAllPosts() {
       return postService.getAll();
    }

    @PostMapping("")
    public void addPost(@RequestBody Post post) {
        String userId = this.getUserName();
        post.setUserId(userId);
        postService.add(post);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable  Long postId, @PathVariable  String userId ) {
        return postService.get(postId);
    }

    @DeleteMapping ("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable  Long postId, @PathVariable  String userId) {
        postService.checkUser(postId, userId);
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public  void updatePost(@PathVariable Long postId, @RequestBody Post post) {
       if( postService.get(postId) != null ){
           postService.update(post);
       }
    }

}
