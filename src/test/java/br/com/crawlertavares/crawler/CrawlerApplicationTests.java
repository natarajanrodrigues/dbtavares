package br.com.crawlertavares.crawler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private PostRepository postRepository;

    public void contextLoads() {
    }

    @Test
    public void test1() throws IOException {

        Set<Post> posts = crawlerService.readPage("http://mundofantasmo.blogspot.com/2013/07/3232-10-cancoes-772013.html");

        for (Post p : posts) {
            postRepository.save(p);
        }

        Assert.assertNotNull(new Object());

    }

}
