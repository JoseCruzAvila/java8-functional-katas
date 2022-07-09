package katas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata10Test {

    @Test
    public void testExecute() {
        List<Map>  expected = List.of(Map.of("name", "New Releases", "videos",
                List.of(Map.of("id", 65432445, "title", "The Chamber"),
                        Map.of("id", 675465, "title", "Fracture"))
        ), Map.of("name", "Thrillers", "videos",
                List.of(Map.of("id", 70111470, "title", "Die Hard"),
                        Map.of("id", 654356453, "title", "Bad Boys"))));

        Assert.assertThat(Kata10.execute(), equalTo(expected));
    }
}
