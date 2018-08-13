package com.example.graphql.graphql;

import java.util.Arrays;
import java.util.List;

public class VideoGameRepository {
  List<VideoGame> allVideoGames() {
    return Arrays.asList(
      new VideoGame("Metroid", "Platformer that made its appearance on the Nintendo."),
      new VideoGame("Scrabble", "A board game they decided to make into a video game.")
    );
  }
}
