package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.Comparator;
import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute()  {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .flatMap(movie -> movie.getBoxarts().stream())
                .reduce((boxart1, boxart2) -> {
                    return boxart1.getWidth()
                            .compareTo(boxart2.getWidth()) > 0
                            && boxart1.getHeight()
                            .compareTo(boxart2.getHeight()) > 0 ? boxart1 : boxart2;
                })
                .map(BoxArt::getUrl)
                .orElseThrow(() -> new IllegalArgumentException("There are no box art data in the movies"));

    }
}
