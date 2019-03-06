#!/bin/bash

docker-compose -f ./docker/docker-compose.yml down
docker-compose -f ./docker/docker-compose.yml up -d --build

alive_postgresql=0
while [ $alive_postgresql -lt 1 ]
do
    psql -U sampledbuser -d sampledb -h localhost -p 5432 -c "select 1"  > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        alive_postgresql=1
    fi
    sleep 2
done

sh ./gradlew clean build -p pj-db
sh ./gradlew flywayMigrate -Dflyway.locations=classpath:testdata

