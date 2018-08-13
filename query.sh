#!/bin/bash
curl http://localhost:3000/ -X POST -H "Content-Type: application/json" -d "{ \"query\": \"{allVideoGames {name, description}}\" }"