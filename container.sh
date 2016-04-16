#!/usr/bin/env bash
echo BE SURE TO RUN WITHIN DOCKER QUICKSTART TERMINAL!

echo Kill existing containers
docker kill $(docker ps -a -q)

echo Run MySQL container
docker run -p 3306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:latest
echo Build RaceJournal
docker build -t adamontheinternet/racejournal-boot .
echo Run RaceJournal container
docker run -p 8080:8080 -t adamontheinternet/racejournal-boot