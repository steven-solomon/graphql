package com.example.graphql.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphQLEndpoint {
    @Autowired
    GraphQL graphQL;

    @PostMapping("/")
    public ResponseEntity<String> home(@RequestBody String query) {
        System.out.println("query: " + query);
        ExecutionResult execute = graphQL.execute(query);
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty())
            return ResponseEntity.badRequest().body(errors.toString());

        return ResponseEntity.ok(execute.getData());
    }
}
