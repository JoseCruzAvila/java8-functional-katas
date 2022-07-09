package katas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata11Test {

    @Test
    public void testExecute() {
        List<Map> expected = List.of(Map.of("name", "New Releases", "videos",
                List.of(Map.of("id", 65432445, "title", "The Chamber",
                        "time", 32432, "boxart", "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"),
                        Map.of("id", 675465, "title", "Fracture",
                                "time", 3534543, "boxart", "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"))
        ), Map.of("name", "Thrillers", "videos",
                List.of(Map.of("id", 70111470, "title", "Die Hard",
                        "time", 645243, "boxart", "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"),
                        Map.of("id", 654356453, "title", "Bad Boys",
                                "time", 984934, "boxart", "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"))));

        Assert.assertThat(Kata11.execute(), equalTo(expected));
    }
}
