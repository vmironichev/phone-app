#!/bin/sh
echo $JAVA_HOME
echo $(java -version)
echo java -jar /apps/phone-catalog-api/phone-catalog-api.jar
exec java -jar /apps/phone-catalog-api/phone-catalog-api.jar
