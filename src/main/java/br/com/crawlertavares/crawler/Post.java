package br.com.crawlertavares.crawler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String link;

    public Post() {
    }

    public Post(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        return this.title.compareToIgnoreCase(o.getTitle());
    }
}
