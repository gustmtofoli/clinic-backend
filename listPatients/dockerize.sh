#!/bin/bash
set -e

SYSTEM_NAME="list-patient"
IMAGE_NAME="$SYSTEM_NAME-image"
CONTAINER_NAME="$SYSTEM_NAME-container"

sudo docker build -t "$IMAGE_NAME" .

echo

sudo docker run -it -p 5001:5001 --name "$CONTAINER_NAME" "$IMAGE_NAME"