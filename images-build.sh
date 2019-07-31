#!/usr/bin/env bash

docker build -f Dockerfile-tomcat85-jre8 -t informaticsmatters/tomcat:8.5-jre8 .
docker build -f Dockerfile-tomcat85-jre11 -t informaticsmatters/tomcat:8.5-jre11 .
docker build -f Dockerfile-tomcat85-jre8-with-content -t informaticsmatters/tomcat-with-content:8.5-jre8 .
docker build -f Dockerfile-tomcat85-jre8-keycloak -t informaticsmatters/tomcat-keycloak:8.5-jre8-3.4.3 .
