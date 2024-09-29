#!/bin/bash

echo 'Stop RabbitMQ'
docker stop rabbitmq

echo 'Remove RabbitMQ'
docker rm rabbitmq

echo 'Start RabbitMQ'
docker run -d -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -e RABBITMQ_MAX_MESSAGE_SIZE=536870912 -p 15777:15777 -p 5677:5677 --hostname rabbitmq --name rabbitmq rabbitmq:3-management