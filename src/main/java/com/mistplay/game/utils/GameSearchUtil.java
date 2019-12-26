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

	public GameSearchUtil() {

		ObjectMapper mapper = new ObjectMapper();

		try {

			File file = new ClassPathResource("games.json").getFile();

			games = mapper.readValue(file, new TypeReference<List<Game>>() {
			});
			Comparator<Game> byTile = (Game o1, Game o2) -> o1.getTitle().compareTo(o2.getTitle());
			games.sort(byTile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Game> searchGames(String startsWith) {

		int result = binarySearch(0, games.size()-1, startsWith);		
		
		if(result>=0) {			
			return games.subList(getStartIndex(result, startsWith), getEndIndex(result, startsWith)+1);
		} else {
			return new ArrayList<Game>();
		}		
	}
	
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

    private int compareGameWithKey(int gameIndex, String startsWith){
    	int keyLength = startsWith.length();
		
		return startsWith.compareToIgnoreCase(games.get(gameIndex).getTitle().substring(0, keyLength));
    }
    
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
