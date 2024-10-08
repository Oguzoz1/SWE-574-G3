package com.communitter.api.post;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PreAuthorize("@authorizer.checkSubscription(#root,#id)")
    @PostMapping("/community/{id}")
    public ResponseEntity<Post> createPost(@P("id") @PathVariable Long id, @RequestBody  Post post){
       return ResponseEntity.ok(postService.createPost(id,post));
    }

    @PreAuthorize("@authorizer.checkAuthor(#root,#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

}
