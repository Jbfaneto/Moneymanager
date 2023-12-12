
FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root

ENV MYSQL_DATABASE=moneymanager

ENV MYSQL_USER=moneymanager_user
ENV MYSQL_PASSWORD=12345678

EXPOSE 3306

COPY ./db/schema.sql /docker-entrypoint-initdb.d/