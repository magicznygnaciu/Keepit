package com.keepit.serviceTest;


import keepit.service.UnorderListsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnorderListsServiceTest {

    UnorderListsService unorderListsService = new UnorderListsService();

    @BeforeEach
    public void setup(){
        //cleans system.in
        System.gc();
    }

    @Test
    public void getLastElementFromUnorderedListTest1(){
        String input = "https://try.jsoup.org/";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String result = unorderListsService.getLastElementFromLargestUnorderedList();

        assertEquals(result, "<li><a href=\"//jsoup.org/apidocs/\">API Reference</a></li>");
    }

    @Test
    public void getLastElementFromUnorderedListTest2(){
        String input = "https://www.w3schools.com/html/";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String result = unorderListsService.getLastElementFromLargestUnorderedList();

        assertEquals(result, "No UL found.");
    }

    @Test
    public void isValidURLTest(){
        Boolean result = unorderListsService.isValidURL("https://www.w3schools.com/html/");

        assertEquals(result, true);
    }
}
