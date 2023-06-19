package service.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.postservice.model.Post;
import service.postservice.model.PostDTO;
import service.postservice.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
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
    public Post addPost(@RequestBody PostDTO post) {
        String userId = this.getUserName();
        return postService.add(new Post(post.getTitle(), post.getDescription(), userId));
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable  Long postId) {
        return postService.get(postId);
    }

    @DeleteMapping ("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable  Long postId) {
        String userId = this.getUserName();

        postService.checkUser(postId, userId);
        postService.delete(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public  Post updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        String userId = this.getUserName();

        postService.checkUser(postId, userId);

        Post post = postService.get(postId);
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());

        return postService.update(post);
    }

}
