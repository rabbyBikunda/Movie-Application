/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;


import Exceptions.DaoException;
import java.net.*;
import java.io.*;

/**
 *
 * @author bikunda
 */


public class Server
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, DaoException
    {
        int portNumber = 4444;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // connection is now established, using the socket,
                // with input and output streams
                )
        {

            String inputLine, outputLine;

            // Initiate conversation with client
            Protocol p = new Protocol();
            outputLine = "";


            out.println(outputLine);  // will send "MOVIE MANAGEMENT APLLICATION"

            // in.readline() waits for (and gathers) input from the socket. 
            // It returns when a newline ('\n') is encountered, and the
            // while loop is executed using the returned data.  
            // readline() will return 'null' when the stream is ended,
            // which happens when the socket is closed (by the client).
            while ((inputLine = in.readLine()) != "exit")
            {  // get data until socket is closed
                
                outputLine = p.processInput(inputLine);
                
                if (inputLine.equalsIgnoreCase("add"))
                {
                    String json = in.readLine();
                    outputLine =p.add(json);
                }
                
                 if (inputLine.equalsIgnoreCase("update"))
                {
                    String json = in.readLine();
                    outputLine = p.update(json);
                }

                out.println(outputLine);
            }

        } catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
