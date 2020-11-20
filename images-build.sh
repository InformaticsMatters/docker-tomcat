#!/usr/bin/env bash

docker build -f Dockerfile-tomcat85-jre11 -t informaticsmatters/tomcat:8.5-jre11 .
docker build -f Dockerfile-tomcat85-jre11-with-content -t informaticsmatters/tomcat-with-content:8.5-jre11 .
docker build -f Dockerfile-tomcat85-jre11-keycloak -t informaticsmatters/tomcat-keycloak:8.5-jre11-10.0.2 .
