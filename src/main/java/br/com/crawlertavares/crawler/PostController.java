package br.com.crawlertavares.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

//    @GetMapping
//    public ModelAndView allPosts(Pageable pageable) {
//
//        Pageable myPageable = PageRequest.of(pageable.getPageNumber(), 12);
//        Page<Post> all = postRepository.findAll(myPageable);
//        ModelAndView model = new ModelAndView("posts");
//        model.addObject("posts", all.getContent());
//        return model;
//
//    }

    @GetMapping
    public ModelAndView allPosts(Pageable pageable) {

        Post allea = postService.getAllea();
        ModelAndView model = new ModelAndView("post");
        model.addObject("post", allea);
        return model;

    }



}
