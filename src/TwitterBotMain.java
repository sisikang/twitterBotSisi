/* 
 * Date: Created Fall 2018, Modified 2019, 2020
 * This class is a template for creating a twitterbot & also demonstrated web-scraping
 */


import processing.core.*;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.jaunt.JauntException;

//This class serves as a template for creating twitterbots and demonstrates string tokenizing and web scraping and the use of the 
//twitter API
public class TwitterBotMain extends PApplet {

	private ArrayList<String> tokens;
	private static String HEYER_TWITTER_URL = "https://twitter.com/Sisik87162233"; //this is mine, you should use yours
	private static int TWITTER_CHAR_LIMIT = 140; //I understand this has changed... but forget limit
	
	//useful constant strings -- for instance if you want to make sure your tweet ends on a space or ending punctuation, etc.
	private static final String fPUNCTUATION = "\",.!?;:()/\\";
	private static final String fENDPUNCTUATION = ".!?;,";
	private static final String fREALENDPUNCTUATION = ".!?";

	private static final String fWHITESPACE = "\t\r\n ";
	
	//example twitter hastag search termx
	private static final String fPASSIVEAGG = "passiveaggressive";
	private static final String fCOMMA = ","; 
	
	//handles twitter api
	TwitterInteraction tweet; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("TwitterBotMain");  //Not really using processing functionality but ya know, you _could_. UI not required.
		
	}

	public void settings() {
		size(300, 300); //dummy window

	};

	public void setup() {
		tweet = new TwitterInteraction(); 
		
//NOTE: everything starts uncommented. Comment out the calls that you would like to try and use.
		
		loadNovel("data/TessExcerpt.txt"); //TODO: must train from another source
		println("Token size:"+tokens.size());

		//TODO: train an AI algorithm (eg, Markov Chain) and generate text for markov chain status
		// MarkovGenerator<String> strGen;
		
		// strGen = new MarkovGenerator("String", 2);
		// strGen.train(tokens);
		

		// String str = " ";
		// for (String s : strGen.generate(20)) {
		// 	str += s + " ";
		// }

		// System.out.println(str);


		
		//can train on twitter statuses -- note: in your code I would put this part in a separate function
		//but anyhow, here is an example of searching twitter hashtag. You have to pay $$ to the man to get more results. :(
		//see TwitterInteraction class
//			ArrayList<String> tweetResults = tweet.searchForTweets("Tess");
//			for (int i = 0; i < tweetResults.size(); i++) {
//			println(tweetResults.get(i)); //just prints out the results for now
//	}
//		
		//Make sure within Twitter limits (used to be 140 but now is more?)
//		String status = "Testing";
//		tweet.updateTwitter(status);
				
		//prints the text content of the sites that come up with the google search of dogs
		//you may use this content to train your AI too
//		Scraper scraper = new Scraper(); 
//		ArrayList<String> results;
//		try {
//			results = scraper.scrapeGoogleResults("dogs");
//			
//			//print your results
//			System.out.println(results); 
			
//			scraper.scrape("http://google.com",  "dogs"); //see class documentation

//		} catch (JauntException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//				
	}

	public void keyPressed() {
		tweet = new TwitterInteraction();

		// NOTE: everything starts uncommented. Comment out the calls that you would
		// like to try and use.

		loadNovel("data/TessExcerpt.txt"); // TODO: must train from another source
		println("Token size:" + tokens.size());

		// TODO: train an AI algorithm (eg, Markov Chain) and generate text for markov
		// chain status
		MarkovGenerator<String> strGen;

		strGen = new MarkovGenerator("String", 2);
		strGen.train(tokens);

		if (key == '1') {
			String str = " ";
			for (String s : strGen.generate(20)) {
				str += s + " ";
			}
			String status = str;
			tweet.updateTwitter(status);

			System.out.println(str);
			
		}
	
	}

	//this loads the novel 'The Grand Sophy' given a path p -- but really will load any file.
	void loadNovel(String p) {
		String filePath = getPath(p);
		Path path = Paths.get(filePath);
		tokens = new ArrayList<String>(); //initializing the tokens

		try {
			List<String> lines = Files.readAllLines(path); //read all lines on file to put it in a list

			for (int i = 0; i < lines.size(); i++) {

				TextTokenizer tokenizer = new TextTokenizer(lines.get(i)); //initializing with 1 line of text
				ArrayList<String> t = tokenizer.parseSearchText(); // return all tokens
				tokens.addAll(t); //ADD ALL of the tokens to the array list
			}

		} catch (Exception e) {
			e.printStackTrace();
			println("Oopsie! We had a problem reading a file!");
		}
	}
	
	void printTokens() {
		for (int i = 0; i < tokens.size(); i++)
			print(tokens.get(i) + " ");
	}

	//get the relative file path 
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	public void draw() {
		//ellipse(width / 2, height / 2, second(), second());
		textSize(12);
		
		fill(0,102, 153);
		text("Press 1 to start", 100, height/2);
	}
	


}
