#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
mkdir -p out
find src tests -name '*.java' > fuentes.txt
javac -encoding UTF-8 -d out @fuentes.txt
java -cp out nomina.app.Main
