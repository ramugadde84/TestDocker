package test.docker.container.TestDocker.service;

import org.springframework.stereotype.Service;
import test.docker.container.TestDocker.model.Post;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {

    public Map<String, Post> formPostsMap() {
        Map<String,Post> params =new HashMap<>();
        Post post =new Post();
        post.setPostName("Marketing");
        post.setContent("Food marketing");

        params.put("1233444",post);

        post =new Post();
        post.setPostName("Advertisement");
        post.setContent("Advertisement marketing");

        params.put("1233445",post);

        return params;
    }
}
