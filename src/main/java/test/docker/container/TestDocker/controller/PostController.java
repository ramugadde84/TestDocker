package test.docker.container.TestDocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.docker.container.TestDocker.model.Post;
import test.docker.container.TestDocker.service.PostService;

import java.util.Map;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public Map<String, Post> fetchPosts() {
        return postService.formPostsMap();
    }
}
