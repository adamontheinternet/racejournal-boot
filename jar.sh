#!/usr/bin/env bash
gradle clean
gradle build
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar build/libs/racejournal-1.0.jar