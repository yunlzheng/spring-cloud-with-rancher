FROM java:8

VOLUME /tmp

ADD entrypoint.sh entrypoint.sh
ADD client-1.0-SNAPSHOT.jar client.jar
RUN bash -c 'touch /client.jar'
RUN chmod +x entrypoint.sh
EXPOSE 7211
CMD ["./entrypoint.sh"]