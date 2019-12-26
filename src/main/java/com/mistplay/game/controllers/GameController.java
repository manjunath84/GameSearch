/**
 * 
 */
package com.mistplay.game.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	 * This method returns all the game data from the system
	 * 
	 * @return List<Game> List of all Games starting with the given startsWith
	 *         String
	 */
	@GetMapping("/search")
	public Page<Game> getGames(@RequestParam(name = "startsWith") String startsWith, Pageable pageable,
			final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
		List<Game> gameList = gameSearchUtil.searchGames(startsWith);
		if (pageable.getPageNumber() > gameList.size()) {
            throw new ResourceNotFoundException();
        }
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > gameList.size() ? gameList.size()
				  : (start + pageable.getPageSize()));
		
		Page<Game> page 
		  = new PageImpl<Game>(gameList.subList(start, end), pageable, gameList.size());
		
		String resourceName = Game.class.getSimpleName().toString().toLowerCase();
		 uriBuilder.path( "/search/" + resourceName );

		return page;
	}
}
