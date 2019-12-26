/**
 * 
 */
package com.mistplay.game.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mistplay.game.entities.Game;
import com.mistplay.game.utils.GameSearchUtil;

/**
 * @author ms17
 *
 */
@RestController
@RequestMapping("/games")
public class GameController {
	
	@Autowired
	GameSearchUtil gameSearchUtil;
	
	/**
	 * 
	 * This method returns all the game data from the system
	 * 
	 * @return Iterable<Game> List of all Games starting with the given startsWith String
	 */
	@GetMapping("/search")
	public List<Game> getGames(@RequestParam(name="startsWith") String startsWith){
		return gameSearchUtil.searchGames(startsWith);
	}
}
