package katas;

import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
                .map(movie -> ImmutableMap.of("id", movie.getId(),
                                                            "title", movie.getTitle(),
                                                            "time",  getInterestingMomentMiddleTime(movie.getInterestingMoments()),
                                                            "boxart", getSmallestBoxArtUrl(movie.getBoxarts())))
                .collect(Collectors.toUnmodifiableList());
    }

    private static Optional<String> getSmallestBoxArtUrl(List<BoxArt> boxArts) {
        return boxArts.stream()
                .reduce(Kata9::getSmallestBoxArt)
                .map(BoxArt::getUrl);
    }

    private static BoxArt getSmallestBoxArt(BoxArt boxArt1, BoxArt boxArt2) {
        return boxArt1.getWidth() < boxArt2.getWidth() && boxArt1.getHeight() < boxArt2.getHeight() ? boxArt1 : boxArt2;
    }

    private static Optional<Date> getInterestingMomentMiddleTime(List<InterestingMoment> interestingMoments) {
        return interestingMoments.stream()
                .filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                .findFirst()
                .map(InterestingMoment::getTime);
    }
}
