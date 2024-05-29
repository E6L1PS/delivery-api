#!/bin/bash

while true
do
  curl --location 'http://localhost:8080/api/v1/account/search?firstName=ad&lastName=a&page=0&size=10'
  echo ""
done
