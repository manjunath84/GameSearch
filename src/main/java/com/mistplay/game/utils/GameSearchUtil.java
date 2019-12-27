package com.mistplay.game.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mistplay.game.entities.Game;

/**
 * @author ms17
 *
 */
@Component
public class GameSearchUtil {

	private List<Game> games;
	
	private static final String FILE_NAME="games.json";

	/**
	 * Constructor to load and initialize games data from games.json file
	 */
	public GameSearchUtil() {

		ObjectMapper mapper = new ObjectMapper();

		try {

			File file = new ClassPathResource(FILE_NAME).getFile();

			games = mapper.readValue(file, new TypeReference<List<Game>>() {
			});
			Comparator<Game> byTile = (Game o1, Game o2) -> o1.getTitle().compareTo(o2.getTitle());
			games.sort(byTile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param startsWith The key to search for
	 * @return List<Game> The list of games starting with the given key
	 */
	public List<Game> searchGames(String startsWith) {

		int result = binarySearch(0, games.size()-1, startsWith);		
		
		if(result>=0) {			
			return games.subList(getStartIndex(result, startsWith), getEndIndex(result, startsWith)+1);
		} else {
			return new ArrayList<Game>();
		}		
	}
	
	/**
	 * Method to get the start Index from where games Title starts with the given key
	 * 
	 * @param middleIndex The middle index from games list whose game title starts with the given key
	 * @param startsWith The key to search for
	 * @return int The start index of the games list
	 */
	private int getStartIndex(int middleIndex, String startsWith) {
		int tempIndex = middleIndex;
		int tempEndIndex = middleIndex;
		while(tempIndex!=-1) {
			tempEndIndex = tempIndex;
			tempIndex = binarySearch(0, tempEndIndex, startsWith);
			if(tempIndex==middleIndex || tempIndex == tempEndIndex) {
				tempEndIndex = tempIndex;
				tempIndex = -1;
			}
		}
		return tempEndIndex;
		
	}
	
	/**
	 * Method to get the end Index until which games Title starts with the given key
	 * 
	 * @param middleIndex The middle index from games list whose game title starts with the given key
	 * @param startsWith The key to search for
	 * @return int The end index
	 */
	private int getEndIndex(int middleIndex, String startsWith) {
		int tempStartIndex = middleIndex;
		int tempIndex = tempStartIndex+1;
		int endIndex = middleIndex;
		while(true) {
			tempStartIndex = tempIndex;
			tempIndex = binarySearch(tempStartIndex, games.size()-1, startsWith);
			if(tempIndex==games.size()-1) {
				tempStartIndex = tempIndex;
				endIndex = tempIndex;
				tempIndex = -1;
			}
			
			if(tempIndex==-1) {
				break;
			}
			endIndex = tempIndex;
			if(tempIndex == tempStartIndex) {
				tempIndex++;
			}
			
		}
		return endIndex;
	}

    /**
     * This method compares the game title at gameIndex position with startsWith key
     * 
     * @param gameIndex The index of the game list which needs to be compared
     * @param startsWith The key to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *          specified String is greater than, equal to, or less
     *          than this String, ignoring case considerations.
     */
    private int compareGameWithKey(int gameIndex, String startsWith){
    	int keyLength = startsWith.length();
		
		return startsWith.compareToIgnoreCase(games.get(gameIndex).getTitle().substring(0, keyLength));
    }
    
	/**
	 * This method will do binary search on the games list based on given startsWith key
	 * @param l The left index of the games list
	 * @param r The right index of the games list
	 * @param startsWith The key to search for
	 * @return int The index where the startsWith key matches
	 */
	private int binarySearch(int l, int r, String startsWith) {
		int keyLength = startsWith.length();

		while (l <= r) {
			int m = l + (r - l) / 2;
			

			int res = compareGameWithKey(m,startsWith);

			// Check if startsWith key is present at mid
			if (res == 0) {
				return m;
			}
				

			// If startsWith key is greater than current game title, ignore left half
			if (res > 0)
				l = m + 1;

			// If startsWith key is lesser than current game title, ignore right half
			else
				r = m - 1;
		}

		return -1;
	}

}
