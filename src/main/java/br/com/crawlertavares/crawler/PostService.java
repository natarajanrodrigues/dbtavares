package br.com.crawlertavares.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post getAllea() {
        Integer totalNumPosts = postRepository.getTotalNumPosts();

        double v = Math.random() * totalNumPosts;

        int idpost = (int) v + 1;
        Optional<Post> byId = postRepository.findById(new Long(idpost));

        return byId.get();

    }
}
