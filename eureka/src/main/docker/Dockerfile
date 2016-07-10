FROM java:8

VOLUME /tmp

ADD entrypoint.sh entrypoint.sh
ADD eureka-1.0-SNAPSHOT.jar eureka.jar
RUN bash -c 'touch /eureka.jar'
RUN chmod +x entrypoint.sh
EXPOSE 8761
CMD ["./entrypoint.sh"]