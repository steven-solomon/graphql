package com.example.graphql.graphql;

import java.util.Arrays;
import java.util.List;

public class LinkRepository {
  List<Link> allLinks() {
    return Arrays.asList(
      new Link("google.com", "A site to search"),
      new Link("linkedin.com", "A site to remember birthdays")
    );
  }
}
