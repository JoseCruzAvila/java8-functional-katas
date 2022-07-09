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
                .reduce(Kata6::getLargestBoxArt)
                .map(BoxArt::getUrl)
                .orElseThrow(() -> new IllegalArgumentException("There are no box art data in the movies"));
    }

    private static BoxArt getLargestBoxArt(BoxArt boxArt1, BoxArt boxArt2) {
        return boxArt1.getWidth().compareTo(boxArt2.getWidth()) > 0
                && boxArt1.getHeight().compareTo(boxArt2.getHeight()) > 0 ? boxArt1 : boxArt2;
    }
}
