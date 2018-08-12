package com.example.graphql.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class WorldFetcher implements DataFetcher<String> {
  @Override
  public String get(DataFetchingEnvironment dataFetchingEnvironment) {
    return "world";
  }
}