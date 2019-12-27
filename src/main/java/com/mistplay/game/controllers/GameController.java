/**
 * 
 */
package com.mistplay.game.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mistplay.game.entities.Game;
import com.mistplay.game.exceptions.ResourceNotFoundException;
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
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	/**
	 * 
	 * This method returns all the game data from the system whose title starts with the given key
	 * 
	 * @Param startsWith The key to search for
	 * @param pageable The pagination query parameters will be automatically mapped to this object.
	 * @return List<Game> List of all Games starting with the given startsWith
	 *         String
	 */
	@GetMapping("/search")
	public Page<Game> getGames(@RequestParam(name = "startsWith") String startsWith, Pageable pageable) {
		List<Game> gameList = gameSearchUtil.searchGames(startsWith);
		if (pageable.getPageNumber() > gameList.size()) {
            throw new ResourceNotFoundException();
        }
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > gameList.size() ? gameList.size()
				  : (start + pageable.getPageSize()));
		
		Page<Game> page 
		  = new PageImpl<Game>(gameList.subList(start, end), pageable, gameList.size());
	
		return page;
	}
}
