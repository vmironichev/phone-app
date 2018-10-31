#!/bin/sh
echo $JAVA_HOME
echo $(java -version)
echo java -jar /apps/order-api/order-api.jar
exec java -jar /apps/order-api/order-api.jar
