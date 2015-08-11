package com.kasisto.challenge;
/*
* Uses the google Maps API to request data about places specified in the names array based on the
* coordinates given.
*/

public class Main {

    public static void main(String[] args) {
        //store the api Key for the google Places api
        String apiKey = "AIzaSyCCMmBmVnTPBv3QG2a0iPkUwwNwd3mYEPA";

	    PlacesHandler handler = new PlacesHandler(apiKey,"40.7378811","-73.9887086");
        String[] names = new String[]{"CVS", "Crate & Barrel", "Red Lobster", "Chilis", "Outback Steakhouse"};
        handler.request(names);

    }
}
