package com.bigdata.spark.A_examples_rdd.B_maptransformation;

import com.bigdata.spark.model.Movie;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;

public class Map {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "D:\\Libs\\hadoop-common-2.2.0-bin-master");

        JavaSparkContext javaSparkContext = new JavaSparkContext("local", "Map Spark");

        JavaRDD<String> rawData = javaSparkContext.textFile("D:\\eclipse-workspace\\spark-core\\src\\movies.csv");

        JavaRDD<Movie> loadMovie = rawData.map(new Function<String, Movie>() {
            public Movie call(String line) throws Exception {
                String[] data = line.split(",");
                Movie m = new Movie();
                m.setMovieId(Integer.parseInt(data[0]));
                m.setTitle(data[1]);
                m.setGenres(data[2]);
                return m;
            }
        });

        loadMovie.foreach(new VoidFunction<Movie>() {
            public void call(Movie movie) throws Exception {
                System.out.println(movie.getTitle());
            }
        });

    }
}
