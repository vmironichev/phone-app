FROM openjdk:10-jdk-slim as buildImage
LABEL maintainer=valeriy

ARG SERVICE_NAME
ENV SERVICE_NAME ${SERVICE_NAME}

ARG SERVICE_PORT
ENV SERVICE_PORT ${SERVICE_PORT:-8090}

RUN echo ${SERVICE_NAME}

ADD gradle /appcode/${SERVICE_NAME}/gradle
ADD gradlew* /appcode/${SERVICE_NAME}/
RUN cd /appcode/${SERVICE_NAME} && ./gradlew --no-daemon -version && cd -

ADD *.gradle /appcode/${SERVICE_NAME}/

ADD . /appcode/${SERVICE_NAME}

RUN apt update && apt --assume-yes install apt-utils libncurses5 libaio1 procps tree net-tools less zip; exit 0

WORKDIR /appcode/${SERVICE_NAME}

RUN ./gradlew --info --no-daemon :${SERVICE_NAME}:clean build && cd /appcode/${SERVICE_NAME}/${SERVICE_NAME}/build/libs

FROM openjdk:10-jre-slim as runImage

ARG SERVICE_NAME
ENV SERVICE_NAME ${SERVICE_NAME}
ARG VERSION=0.0.1

ARG SERVICE_PORT
ENV SERVICE_PORT ${SERVICE_PORT:-8090}

RUN apt update && apt --assume-yes install wget curl

EXPOSE $SERVICE_PORT

COPY --from=buildImage /appcode/${SERVICE_NAME}/${SERVICE_NAME}/build/libs/${SERVICE_NAME}-$VERSION.jar /apps/${SERVICE_NAME}/${SERVICE_NAME}.jar
COPY --from=buildImage /appcode/${SERVICE_NAME}/${SERVICE_NAME}/startup.sh /apps/bin/

RUN chmod +x "/apps/bin/startup.sh"

ENTRYPOINT ["/apps/bin/startup.sh"]

