package com.example.graphql.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class QueryFetcher implements DataFetcher<List<VideoGame>> {
  private VideoGameRepository videoGameRepository = new VideoGameRepository();

  @Override
  public List<VideoGame> get(DataFetchingEnvironment dataFetchingEnvironment) {
    return videoGameRepository.allVideoGames();
  }
}