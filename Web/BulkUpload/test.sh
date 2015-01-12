
#!bin/bash
    FILES=“//path of file//”
    for f in $FILES
    do
        mongoimport -d meteor -c articlesNew --type json --file "$f" --jsonArray -h 127.0.0.1:3001
    done




