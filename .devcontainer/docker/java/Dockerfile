FROM maven:3-eclipse-temurin-24-noble

ENV TZ=Asia/Tokyo

RUN apt-get update && \
    apt-get install -y --no-install-recommends git tree curl &&\
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

CMD [ "sleep", "infinity" ]
