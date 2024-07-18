package keepit.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class UnorderListsService {

    public String getLastElementFromLargestUnorderedList(){
        String result;
        String url = getInputURL();

        //Actual logic of the method
        try {
            Document document = Jsoup.connect(url).get();
            Elements ulLists = document.select("ul");
            Element mostChildrenList = null;
            int maxChildren = 0;

            for(Element unorderedList: ulLists){
                Elements children = unorderedList.children();
                if(children.size() > maxChildren){
                    maxChildren = children.size();
                    mostChildrenList = unorderedList;
                }
            }

            if(mostChildrenList != null){
                System.out.println("The last item from the largest UL: ");
                result = mostChildrenList.children().last().toString();
            }else{
                result = "No UL found.";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public String getInputURL() {
        Scanner scanner = new Scanner(System.in);
        String inputURL;
        inputURL = scanner.nextLine();

        while (!isValidURL(inputURL)){
            System.out.println("Please provide valid URL");
            inputURL = scanner.nextLine();
        }

        return inputURL;
    }

    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public void startService(){
        System.out.println("Please provide URL:");
        String result = getLastElementFromLargestUnorderedList();
        System.out.println(result);
    }
}
