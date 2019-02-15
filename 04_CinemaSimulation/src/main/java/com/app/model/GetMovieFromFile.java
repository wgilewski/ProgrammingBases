package com.app.model;

import com.app.converters.MoviesJsonConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetMovieFromFile implements IMovie
{
    private Movies movies;

    public GetMovieFromFile(String moviesFile)
    {
        MoviesJsonConverter moviesJsonConverter = new MoviesJsonConverter(moviesFile);
        movies = moviesJsonConverter.fromJson().get();
    }

    @Override
    public String toString() {
        return "GetMovieFromFile{" +
                "movies=" + movies +
                '}';
    }

    @Override
    public Movie getMovie()
    {
        Random rnd = new Random();
        int max = movies.getMovies().size();
        int randomInt = rnd.nextInt(max);
        List<Movie> movieList = new ArrayList<>(movies.getMovies());
        return movieList.get(randomInt);
    }

}
