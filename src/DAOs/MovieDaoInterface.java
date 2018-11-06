/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.util.List;

/**
 *
 * @author bikunda
 */
public interface MovieDaoInterface
{
    public List<Movie> findAllMovies() throws DaoException;
    public Movie findMovieByTitle(String title) throws DaoException;
    public List<Movie> findMovieByDirector(String director) throws DaoException;
    public Movie findMovieById(int id) throws DaoException;
    public boolean addMovie(Movie movie) throws DaoException;
    public boolean removeMovie(int id) throws DaoException;
    public boolean updateMovie(Movie movie) throws DaoException;
    public List<Movie> recommend(String genre, String rating) throws DaoException;
  
}
