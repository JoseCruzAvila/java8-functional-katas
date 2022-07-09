package katas;

import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() throws NullPointerException {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .map(Movie::getRating)
                .reduce(Math::max)
                .orElseThrow(() -> new NullPointerException("There are no ratings to evaluate"));
    }
}
