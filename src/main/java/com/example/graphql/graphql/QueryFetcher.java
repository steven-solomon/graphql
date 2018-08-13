package com.example.graphql.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class QueryFetcher implements DataFetcher<List<Link>> {
  private LinkRepository linkRepository = new LinkRepository();

  @Override
  public List<Link> get(DataFetchingEnvironment dataFetchingEnvironment) {
    return linkRepository.allLinks();
  }
}