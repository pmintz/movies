package com.movies.service;

import com.movies.model.Movie;
import com.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository rewardsRepository){
        this.movieRepository = rewardsRepository;
    }

    public Movie save(Movie movie){
        //transaction.setAmountOfRewards(calculate(transaction.getAmountOfTransaction()));
        return movieRepository.save(movie);

    }

    public List<Movie> findAll(){
        List<Movie> list = (List<Movie>) movieRepository.findAll();
        return list;
    }

    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

    public Optional<Movie> findById(Long id){
        //Movie movie = null;
        return movieRepository.findById(id);
    }

    public List<Movie> findByGenre(String genre){
        return movieRepository.findByGenre(genre);
    }


}
