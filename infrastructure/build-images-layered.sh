#!/bin/bash

function unpack() {
  FOLDER=$1
  NAME=$2
  VERSION=$3

  CURRENT=$(pwd)

  cd "$FOLDER"/target || exit
  java -jar -Djarmode=layertools "${NAME}"-"${VERSION}".jar extract

  cd "$CURRENT" || exit
}

function build() {
  FOLDER=$1
  NAME=$2

  docker build -f ./infrastructure/Dockerfile \
    --build-arg JAR_FOLDER="${FOLDER}"/target \
    -t "${NAME}":latest \
    -t "${NAME}":layered .
}

APP_VERSION=1.0-SNAPSHOT

# Building the app
cd ..

echo "Building JAR files"
mvn clean package -DskipTests

echo "Unpacking JARs"
unpack config-server config-server ${APP_VERSION}
unpack eureka-server eureka-server ${APP_VERSION}
unpack api-gateway api-gateway ${APP_VERSION}
unpack user-service user-service ${APP_VERSION}
unpack delivery-service delivery-service ${APP_VERSION}
unpack parcel-service parcel-service ${APP_VERSION}

echo "Building Docker image"
build config-server application/config-server
build eureka-server application/eureka-server
build api-gateway application/api-gateway
build user-service application/user-service
build delivery-service application/delivery-service
build parcel-service application/parcel-service