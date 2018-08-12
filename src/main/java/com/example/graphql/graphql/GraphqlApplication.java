package com.example.graphql.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.Arrays;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@SpringBootApplication
public class GraphqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlApplication.class, args);
  }

  @Bean
  public GraphQL graphQL(ResourceLoader resourceLoader) {
    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(getSchema(resourceLoader));

    RuntimeWiring runtimeWiring = newRuntimeWiring()
      .type("Query", builder -> builder.dataFetcher("hello", new WorldFetcher()))
      .build();

    SchemaGenerator schemaGenerator = new SchemaGenerator();
    GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

    return GraphQL.newGraphQL(graphQLSchema).build();
  }

  private static String getSchema(ResourceLoader resourceLoader) {
    try {
      StringBuilder builder = new StringBuilder();
      InputStream inputStream = resourceLoader.getResource("classpath:schema.graphqls").getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      while (bufferedReader.ready()) {
        builder.append(bufferedReader.readLine());
      }
      return builder.toString();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load Schema", e);
    }
  }
}
