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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .map(movie -> ImmutableMap.of("id", movie.getId(),
                                                            "title", movie.getTitle(),
                                                            "boxart", getSmallestBoxArtUrl(movie.getBoxarts())))
                .collect(Collectors.toUnmodifiableList());
    }

    private static Optional<String> getSmallestBoxArtUrl(List<BoxArt> boxArts) {
        return boxArts.stream()
                    .reduce(Kata7::getSmallestBoxart)
                    .map(BoxArt::getUrl);
    }

    private static BoxArt getSmallestBoxart(BoxArt boxArt1, BoxArt boxArt2) {
        return boxArt1.getWidth().compareTo(boxArt2.getWidth()) < 0
                && boxArt1.getHeight().compareTo(boxArt2.getHeight()) < 0 ? boxArt1 : boxArt2;
    }
}
