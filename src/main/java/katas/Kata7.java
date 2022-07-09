package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
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

                    return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", boxart);
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
