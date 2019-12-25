package com.mistplay.game;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mistplay.game.entities.Game;
import com.mistplay.game.utils.GameSearchUtil;


@SpringBootTest
class GameSearchApplicationTests {
	
	@Autowired
	GameSearchUtil gameSearchUtil;

	@Test
	void contextLoads() {
	}
	

	@Test
	public void testFindAllNovelInfo() {
		List<Game> games = gameSearchUtil.searchGames("So");
		assertNotNull(games);
	}

}
