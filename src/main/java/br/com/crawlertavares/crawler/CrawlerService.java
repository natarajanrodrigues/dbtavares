package br.com.crawlertavares.crawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CrawlerService {

    private TreeSet<String> links;


    public CrawlerService() {
        this.links = new TreeSet<>();
    }

    public void getPageLinks(String URL) {

        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }

        links.forEach(s -> System.out.println("link: " + s));
    }

    public Set<Post> readPage(String url) throws IOException {
        Set<Post> result = new TreeSet<>();

        Document document = Jsoup.connect(url).get();

        Elements elements = document.select("a[href]");

//        Iterator<Element> iterator = elements.iterator();
//        while (iterator.hasNext() && result.size() < 10){
//            Element e = iterator.next();
//            if(e.hasClass("post-count-link")) {
//                Set<String> ulLinks = getLinks(e);
//                for (String aUrl: ulLinks) {
//                    result.addAll(readPosts(aUrl));
//                }
//            }
//
//        }

        for(Element e : elements){
            if(e.hasClass("post-count-link")) {
                Set<String> ulLinks = getLinks(e);
                for (String aUrl: ulLinks) {
                    result.addAll(readPosts(aUrl));
                    System.out.println("Total Results: " + result.size());
                }
            }
        }

        return result;

    }

    private Set<Post> getPosts(Element e) {
        Set<Post> result = new TreeSet<>();
        Elements select = e.select("a[href]");
        select.forEach(s -> {
            String link = s.attr("abs:href");
            String title = s.childNode(0).toString();
            Post post = new Post(title, link);
            result.add(post);
        });

        return result;
    }

    private Set<String> getLinks(Element e) {
        Set<String> result = new TreeSet<>();
        Elements select = e.select("a[href]");
        select.forEach(s -> {
            String link = s.attr("abs:href");
//            System.out.println("Link: " + link);
            result.add(link);
        });

        return result;
    }

    private Set<Element> getRealLinksElements(Element e) {
        Set<Element> result = new HashSet<>();
        Elements select = e.select("a[href]");
        select.forEach(s -> {
            String link = s.attr("abs:href");
            if (!link.endsWith("archive.html"))
                System.out.println("Real link: " + link);
            result.add(s);
        });

        return result;
    }


    public Set<Post> readPosts(String url) throws IOException {

        Set<Post> result = new TreeSet<>();

        Document document = Jsoup.connect(url).get();

        Elements elements = document.select("ul");

        for(Element e : elements){
            if(e.hasClass("posts")) {

                Set<Element> realLinksElements = getRealLinksElements(e);

                for (Element eLink : realLinksElements) {
                    result.addAll(getPosts(eLink));
                }


            }
        }

        return result;

//        Iterator<Element> iterator = elements.iterator();
//        while (iterator.hasNext() && resultRealPosts.size() < 10){
//            Element e = iterator.next();
//
//            if(e.hasClass("posts")) {
//
//                Set<Element> realLinksElements = getRealLinksElements(e);
//
//                for (Element eLink : realLinksElements) {
//                    Set<Post> posts = getPosts(eLink);
//                    if (posts != null && posts.size() > 0)
//                        resultRealPosts.addAll(posts);
//                }
//
//
//            }
//        }
//
//
//
//        return resultRealPosts;

    }



}
