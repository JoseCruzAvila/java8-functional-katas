package katas;

import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie -> {
                    var boxart = movie.getBoxarts()
                            .stream()
                            .reduce((boxart1, boxart2) -> {
                                return boxart1.getWidth()
                                        .compareTo(boxart2.getWidth()) < 0
                                        && boxart1.getHeight()
                                        .compareTo(boxart2.getHeight()) < 0 ? boxart1 : boxart2;
                            })
                            .map(BoxArt::getUrl);

                    var moment = movie.getInterestingMoments()
                            .stream()
                            .filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                            .findFirst()
                            .map(InterestingMoment::getTime);

                    return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "time",  moment,"boxart", boxart);
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
