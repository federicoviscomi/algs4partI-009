#!/bin/bash

mvn install:install-file \
  -Dfile="/home/fviscomi/workspace/algs4partI-009/algs4.jar" \
  -DgroupId="algs4partI-groupId" \
  -DartifactId="algs4partIjar" \
  -Dversion="1" \
  -Dpackaging="jar" \
  -DgeneratePom=true
