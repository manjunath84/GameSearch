package com.mistplay.game.utils;

import java.io.File;
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
		
		int startIndex = 0;
		
		if(result>0) {
			startIndex = binarySearch(0, result, startsWith);
		}		
				
	    int endIndex = binarySearch(result, games.size()-1, startsWith);

	    System.out.println(result+" startIndex - "+startIndex+" end Index - "+endIndex);
		return games.subList(startIndex, endIndex);
	}

	private int binarySearch(int l, int r, String startsWith) {
		// int l = 0, r = arr.length - 1;
		// String startsWithKey = startsWithKey.toLowerCase()+".*";
		int keyLength = startsWith.length();

		while (l < r) {
			int m = l + (r - l) / 2;
			
			System.out.println(m);

			int res = games.get(m).getTitle().toLowerCase().substring(0, keyLength - 1)
					.compareToIgnoreCase(startsWith.toLowerCase());

			// Check if x is present at mid
			if (res == 0)
				return m;

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		return l;
	}

}
