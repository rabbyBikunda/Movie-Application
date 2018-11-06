/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author bikunda
 */
public class MySqlMovieDao extends Daos.MySqlDao implements MovieDaoInterface
{

    @Override
    public List<Movie> findAllMovies() throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Movie> movies = new ArrayList<Movie>();
       
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES";
            ps = con.prepareStatement(query);
            
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                int movieId = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                String year = rs.getString("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String userRating = rs.getString("user_rating");
                
               
                Movie m = new Movie(movieId,title,genre,director,runtime,plot,location,poster,rating,format,year,starring,copies,barcode,userRating);
                movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
        return movies;     // may be empty
    }

    @Override
    public Movie findMovieByTitle(String title) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       Movie m= null;
        
       try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE title= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int movieId = rs.getInt("id");
                String movieTitle = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                String year = rs.getString("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String userRating = rs.getString("user_rating");
                               
                 m = new Movie(movieId,movieTitle,genre,director,runtime,plot,location,poster,rating,format,year,starring,copies,barcode,userRating);
               
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMovieByTitle " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMovieByTitle" + e.getMessage());
            }
        }
        return m;     // m may be null 
    }

    @Override
    public List<Movie> findMovieByDirector(String director) throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Movie> movies = new ArrayList<Movie>();
       
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE director= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, director);
            
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                int movieId = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String movieDirector = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                String year = rs.getString("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String userRating = rs.getString("user_rating");
                
               
                Movie m = new Movie(movieId,title,genre,movieDirector,runtime,plot,location,poster,rating,format,year,starring,copies,barcode,userRating);
                movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMovieByDirector " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMovieByDirector " + e.getMessage());
            }
        }
        return movies;     // may be empty
    }
    
     @Override
    public boolean  addMovie(Movie m) throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
     
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            
            String query = "INSERT INTO MOVIES (title,genre,director,runtime,plot,location,rating,format,year,starring,barcode,user_rating) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);       
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getGenre());         
            ps.setString(3, m.getDirector());
            ps.setString(4, m.getRuntime());
            ps.setString(5, m.getPlot());
            ps.setString(6, m.getLocation());
            ps.setString(7, m.getRating());
            ps.setString(8, m.getFormat());
            ps.setString(9, m.getYear());
            ps.setString(10, m.getStarring());
            ps.setString(11, m.getBarcode());
            ps.setString(12, m.getUserRating());
            ps.executeUpdate();
           
            
          
           
            
         
            
       
        }
        catch (SQLException e) 
        {
            throw new DaoException("addMovie " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("addMovie" + e.getMessage());
            }
        }
       
       return true;
    }
    
     @Override
    public boolean  removeMovie(int id) throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE id= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            //Using a PreparedStatement to execute SQL...
            if(rs.isBeforeFirst())
            {
               String query2 = "DELETE FROM MOVIES WHERE id = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, id);
                ps.executeUpdate();
                
                return true;
            }
            
            
            
            
            
        }
         catch (SQLException e) 
        {
            throw new DaoException("removeMovie " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("addMovie" + e.getMessage());
            }
        }
       
       
       
       return false;
    }
    
    @Override
    public Movie findMovieById(int id) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m= null;
        
        
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE id= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int movieId = rs.getInt("id");
                String movieTitle = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                String year = rs.getString("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String userRating = rs.getString("user_rating");
                               
                 m = new Movie(movieId,movieTitle,genre,director,runtime,plot,location,poster,rating,format,year,starring,copies,barcode,userRating);
                
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMovieById " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMovieById" + e.getMessage());
            }
        }
        return m;     // m may be null 
    }
    
    @Override
    public boolean updateMovie(Movie m) throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE id= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, m.getId());
            
            rs = ps.executeQuery();
            
            
            //Using a PreparedStatement to execute SQL...
            if(rs.isBeforeFirst())
            {
            String query2 = "UPDATE MOVIES SET title = ?,genre = ?,director = ?,runtime = ?,plot = ?,location = ?,rating = ?,format = ?,year = ?,starring = ?,barcode = ?,user_rating = ? WHERE id = ?";
            ps = con.prepareStatement(query2);       
            
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getGenre());         
            ps.setString(3, m.getDirector());
            ps.setString(4, m.getRuntime());
            ps.setString(5, m.getPlot());
            ps.setString(6, m.getLocation());
            ps.setString(7, m.getRating());
            ps.setString(8, m.getFormat());
            ps.setString(9, m.getYear());
            ps.setString(10, m.getStarring());
            ps.setString(11, m.getBarcode());
            ps.setString(12, m.getUserRating());
            ps.setInt(13, m.getId());
            ps.executeUpdate();
               
                return true;
            }
            
            
            
            
            
        }
         catch (SQLException e) 
        {
            throw new DaoException("updateMovie " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("updateMovie" + e.getMessage());
            }
        }
       
       
        return false;
    }
    
    @Override
    public List<Movie> recommend(String genre, String rating) throws DaoException
    {
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Movie> movies = new ArrayList<Movie>();
       
       try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE genre LIKE ? AND rating LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, genre + "%");
            ps.setString(2, rating + "%");
            
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                int movieId = rs.getInt("id");
                String title = rs.getString("title");
                String mgenre = rs.getString("genre");
                String movieDirector = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String mrating = rs.getString("rating");
                String format = rs.getString("format");
                String year = rs.getString("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String muserRating = rs.getString("user_rating");
                
               
                Movie m = new Movie(movieId,title,mgenre,movieDirector,runtime,plot,location,poster,mrating,format,year,starring,copies,barcode,muserRating);
                movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("recommend " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("recommend " + e.getMessage());
            }
        }
        return movies;     // may be empty
    }
    
    
    
    
    
}
