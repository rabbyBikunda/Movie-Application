/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import DAOs.MovieDaoInterface;
import DAOs.MySqlMovieDao;
import DTOs.Movie;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bikunda
 */
public class Protocol 
{
    MovieDaoInterface movieDao;
    Gson gson = new Gson();
    Type type = new TypeToken<List<Movie>>(){}.getType();
    Type type2 = new TypeToken<Movie>(){}.getType();
    List<Movie> watched = new ArrayList<>();
    List<Movie> moviesList;
    Movie movie;

   
    public Protocol()
    {
        movieDao = new MySqlMovieDao();
    }

    public String processInput(String input) throws DaoException
    {
        String output = "command does not exist";

        if (input.startsWith("search by director"))
        {
            String segments[] = input.split("director");
            String director = segments[1].trim();

            moviesList = movieDao.findMovieByDirector(director);
            if (!moviesList.isEmpty())
            {
                output = gson.toJson(moviesList, type);
            } 
            else
            {
                output = "movie(s) not found";
            }
        } 
        else if (input.equalsIgnoreCase("show all"))
        {
            moviesList = movieDao.findAllMovies();
            output = gson.toJson(moviesList, type);
        } 
        else if (input.startsWith("search by title"))
        {
            String segments[] = input.split("title");
            String title = segments[1].trim();//trim removes space

            movie = movieDao.findMovieByTitle(title);

            if (movie != null)
            {
                output = gson.toJson(movie, type2);  
            } 
            else
            {
                output = "movie not found";
            }

        }
        else if (input.startsWith("search by id"))
        {
            String segments[] = input.split("id");
            int id = Integer.parseInt(segments[1].trim());

            movie = movieDao.findMovieById(id);

            if (movie != null)
            {
                output = gson.toJson(movie, type2);
            } 
            else
            {
                output = "movie not found";
            }
        }
        else if (input.startsWith("remove"))
        {
            String segments[] = input.split("remove");
            int id = Integer.parseInt(segments[1].trim());

            if (movieDao.removeMovie(id))
            {
                output = "Movie removed";
            } 
            else
            {
                output = "ID does not exist";
            }
        }
        else if (input.startsWith("watch"))
        {
            String segments[] = input.split("watch");
            String title = segments[1].trim();//trim removes space

           movie = movieDao.findMovieByTitle(title);

            if (movie != null)
            {
                  watched.add(movie);
                output = "movie watched";
            } 
            else
            {
                output = "movie not found";
            }
        }
          else if (input.equalsIgnoreCase("display watched movies"))
        {
            //moviesList = movieDao.findAllMovies();
            if(!watched.isEmpty())
            {
                output = gson.toJson(watched, type);
            }
            else
            {
                output = "no movies watched";
            }
            
        }
        else if (input.startsWith("recommend"))
        {
           String[] segments = input.split(" ");
           String genre = segments[1];
           
           String rating = segments[2];
           
           moviesList = movieDao.recommend(genre, rating);
           
           if(!moviesList.isEmpty())
           {
               output = gson.toJson(moviesList, type);
           }
           else
           {
               output = "no movies recommended";
           }
            
        } 
        
       
        return output;
    }

    public String update(String json) throws DaoException
    {
        String output = null;

        Movie m = gson.fromJson(json, type2);
        movie = movieDao.findMovieById(m.getId());
         if(movie== null)
         {
             output ="update could not occur - id does not exist"; 
         }
         else
         {
             movieDao.updateMovie(m);
             output = "movie updated";
        }
         
         return output;
    }

    public String add(String json) throws DaoException
    {
        String output;
        //  String json = in.readLine();
        Movie m = gson.fromJson(json, type2);

        movieDao.addMovie(m);
        output = "Movie added";

        return output;
    }
}
