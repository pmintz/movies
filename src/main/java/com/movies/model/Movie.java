package com.movies.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.movies.utilities.ArrayToStringDeserializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    private Long id;
    private Long rank;
    private String title;
    @JsonDeserialize(using = ArrayToStringDeserializer.class)
    private String genre;
    private String description;
    private String director;
    @JsonDeserialize(using = ArrayToStringDeserializer.class)
    private String actors;
    @Column(name = "years")
    private int year;
    private int runtime;
    private float rating;
    private int votes;
    private float revenue;
    @Column(name = "metascore")
    private int MetaScore;

}
