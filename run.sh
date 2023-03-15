#!/bin/bash
docker compose -f /var/tmp/docker-compose.yml down
docker compose -f /var/tmp/docker-compose.yml pull myapp
docker compose -f /var/tmp/docker-compose.yml up &
