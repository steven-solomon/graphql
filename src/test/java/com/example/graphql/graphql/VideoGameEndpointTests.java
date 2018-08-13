package com.example.graphql.graphql;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoGameEndpointTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void allLinks() throws Exception {
		mockMvc
			.perform(post("/").content("{ \"query\": \"{allVideoGames {name, description}}\" }").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.allVideoGames[0].name").value("Metroid"))
			.andExpect(jsonPath("$.data.allVideoGames[0].description").value("Platformer that made its appearance on the Nintendo."))
			.andExpect(jsonPath("$.data.allVideoGames[1].name").value("Scrabble"))
			.andExpect(jsonPath("$.data.allVideoGames[1].description").value("A board game they decided to make into a video game."));
	}
}
