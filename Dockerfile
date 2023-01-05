FROM debian:latest

RUN apt-get update && \
    apt-get -y install  \
    apache2 \
    openjdk-17-jdk

RUN mkdir /data
RUN mkdir /java

# Copie des fichiers
COPY ./start-script.sh /root/
COPY ./lib /data
COPY ./src/client src/client
COPY ./src/server src/server

# Exposer Apache
EXPOSE 3306
EXPOSE 80
EXPOSE 8000

#Configuration de l'environnement CLASSPATH
ADD java/lib/jdom-2.0.6.jar /srv/app/jdom-2.0.6.jar
ENV CLASSPATH=/srv/app/jdom-2.0.6.jar:${CLASSPATH}

#Ajout des droits d'execution pour le script de démarrage
RUN chmod +x /root/start-script.sh 

# Déplacement dans le dossier java
WORKDIR /src

# On trouve puis compile tous les fichiers Java
RUN find -name "*.java" > sources.txt
RUN javac @sources.txt -encoding UTF-8

WORKDIR ../

#Configuration de la commande d'execution
CMD ["/bin/bash", "/root/start-script.sh"]