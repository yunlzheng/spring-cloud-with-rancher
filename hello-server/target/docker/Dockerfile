FROM java:8

VOLUME /tmp

ADD entrypoint.sh entrypoint.sh
ADD hello-server-1.0-SNAPSHOT.jar server.jar
RUN bash -c 'touch /server.jar'
RUN chmod +x entrypoint.sh
EXPOSE 7111
CMD ["./entrypoint.sh"]