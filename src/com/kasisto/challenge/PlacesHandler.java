package com.kasisto.challenge;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * PlacesHandler will carry out requests that use the google Places api (over http)
 * Created by jmdevivo on 8/7/15.
 */
public class PlacesHandler {
    private String apiKey;
    private String location;
    private final String radius = "&radius=10000";
    private String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

    public  PlacesHandler(String apiKey, String latitude, String longitude){
        this.apiKey = "&key="+apiKey;
        this.location  = "location=" + latitude+ "," + longitude;
    }

    /**
     * Given a places name, uses the google Places API to find places based on the name parameter
     * and return information about that place for later use
     *
     * @param name The name of the place
     *
     * @return The toString representation of the JSON object returned from the get
     * request to the Google places API
     */
    public String request(String name){
        name = name.replaceAll(" ", "%20");
        String requestUrl = baseUrl + this.location + this.radius + "&name=" + name + this.apiKey;

        HttpURLConnection connection = null;
        try{
            //create the connection
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();


        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }

    }

    /**
     * Executes the request method on each element of an Array of Strings
     *
     * @param nameArray
     */
    public void request(String[] nameArray){
        for(String s : nameArray){
            System.out.println(request(s));
        }
    }
}
