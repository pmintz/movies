package com.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.model.Movie;
import com.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
public class MoviesController {

    MovieService movieService;

    HttpStatus httpStatus;

    public MoviesController(MovieService rewardService){
        this.movieService = rewardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        if(movie.isEmpty()){
            return ResponseEntity.notFound().build();
        }else
        return ResponseEntity.ok(movie.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> getMovies() {

       return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam(value="genre") String genre) {
        return new ResponseEntity<>(movieService.findByGenre(genre), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        Optional<Movie> dbMovieLookup = movieService.findById(movie.getId());
        if(dbMovieLookup.isPresent()){
            return new ResponseEntity<Movie>(movie,HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<Movie>(movieService.save(movie),HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Movie> updateMovieById(@RequestParam(value="id") Long id, @RequestBody Movie movie) {
        Optional<Movie> dbMovieLookup = movieService.findById(id);

        if (dbMovieLookup.isPresent()) {

            return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        Optional<Movie> dbMovieLookup = movieService.findById(id);
        if (dbMovieLookup.isPresent()) {
            movieService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
