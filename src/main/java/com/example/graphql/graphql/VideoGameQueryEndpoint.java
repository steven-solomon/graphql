package com.example.graphql.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoGameQueryEndpoint {
    @Autowired
    GraphQL graphQL;

    @PostMapping("/")
    public ResponseEntity<GraphQLResponse> home(@RequestBody() GraphQLQuery body) {
        ExecutionResult execute = graphQL.execute(body.getQuery());
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(new GraphQLResponse(execute.getData()));
    }
}
