FROM openjdk:22-jdk
LABEL authors="siame"

ENTRYPOINT ["top", "-b"]