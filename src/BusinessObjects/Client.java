/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import DTOs.Movie;

/**
 *
 * @author bikunda
 */
public class Client
{

    static Scanner keyboard = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DaoException
    {
        String hostName = "localhost";  // the server (running on local machine)
        int portNumber = 4444;          // port number the server is listening on

        try ( /// try-with-resources - will autoclose resources including sockets
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));)
            
            
        {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String userInput;
            
            System.out.println("COMMANDS:");
            System.out.println("show all");
            System.out.println("search by id \"ID\"");
            System.out.println("search by title \"TITLE\"");
            System.out.println("search by director \"DIRECTOR\"");
            System.out.println("add");
            System.out.println("remove \"ID\"");
            System.out.println("update id \"ID\"");
            System.out.println("watch \"TITLE\"");
            System.out.println("display watched movies");
            System.out.println("recommend \"GENRE\" \"RATING\"");
                    
           
            
      
      
      String output = "Enter command: ";
      

            while ((fromServer = in.readLine()) != null)
            {  // keep reading from stream
                System.out.println(fromServer);

                System.out.println(output);
                userInput = stdIn.readLine();  // ask user for input

                if (userInput != null)
                {
                    out.println(userInput);  // send message to server
                }

                if (userInput.equalsIgnoreCase("exit"))
                {
                    break;
                }

                if (userInput.equalsIgnoreCase("add"))
                {
                    //Serialize -convert from Java Object(s) into JSON String - send to server
                    Gson gson = new Gson();

                    Type type = new TypeToken<Movie>(){}.getType();

                    System.out.println("Enter Title:");
                    String title = stdIn.readLine();

                    System.out.println("Enter Genre:");
                    String genre = stdIn.readLine();

                    System.out.println("Enter director:");
                    String director = stdIn.readLine();

                    System.out.println("Enter runtime:");
                    String runtime = stdIn.readLine();

                    System.out.println("Enter plot:");
                    String plot = stdIn.readLine();

                    System.out.println("Enter location:");
                    String location = stdIn.readLine();

                    System.out.println("Enter rating:");
                    String rating = stdIn.readLine();

                    System.out.println("Enter format:");
                    String format = stdIn.readLine();

                    System.out.println("Enter year:");
                    String year = stdIn.readLine();

                    System.out.println("Enter starring actor:");
                    String starring = stdIn.readLine();

                    System.out.println("Enter barcode:");
                    String barcode = stdIn.readLine();

                    System.out.println("Enter user rating:");
                    String userRating = stdIn.readLine();

                    Movie movie = new Movie();
                    movie.setTitle(title);
                    movie.setGenre(genre);
                    movie.setDirector(director);
                    movie.setRuntime(runtime);
                    movie.setPlot(plot);
                    movie.setLocation(location);
                    movie.setRating(rating);
                    movie.setFormat(format);
                    movie.setYear(year);
                    movie.setStarring(starring);
                    movie.setBarcode(barcode);
                    movie.setUserRating(userRating);

                    String json = gson.toJson(movie, type);
                    out.println(json);
                }
              

                if (userInput.equalsIgnoreCase("update"))
                {
                      Gson gson = new Gson();

                    Type type = new TypeToken<Movie>(){}.getType();
                   
                    
                    System.out.println("Enter id:");
                    String id = stdIn.readLine();
                    
                    int mid = Integer.parseInt(id);
                    
                    System.out.println("Enter Title:");
                    String title = stdIn.readLine();

                    System.out.println("Enter Genre:");
                    String genre = stdIn.readLine();

                    System.out.println("Enter director:");
                    String director = stdIn.readLine();

                    System.out.println("Enter runtime:");
                    String runtime = stdIn.readLine();

                    System.out.println("Enter plot:");
                    String plot = stdIn.readLine();

                    System.out.println("Enter location:");
                    String location = stdIn.readLine();

                    System.out.println("Enter rating:");
                    String rating = stdIn.readLine();

                    System.out.println("Enter format:");
                    String format = stdIn.readLine();

                    System.out.println("Enter year:");
                    String year = stdIn.readLine();

                    System.out.println("Enter starring actor:");
                    String starring = stdIn.readLine();

                    System.out.println("Enter barcode:");
                    String barcode = stdIn.readLine();

                    System.out.println("Enter user rating:");
                    String userRating = stdIn.readLine();
                  
                    Movie m = new Movie();
                    m.setId(mid);
                    m.setTitle(title);
                    m.setGenre(genre);
                    m.setDirector(director);
                    m.setRuntime(runtime);
                    m.setPlot(plot);
                    m.setLocation(location);
                    m.setRating(rating);
                    m.setFormat(format);
                    m.setYear(year);
                    m.setStarring(starring);
                    m.setBarcode(barcode);
                    m.setUserRating(userRating);
                    
                    String json = gson.toJson(m, type);
                    out.println(json);                  
                }
                



            }

        } catch (UnknownHostException e)
        {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}
