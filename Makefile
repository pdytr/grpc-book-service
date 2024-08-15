# Nombre de la imagen
IMAGE_NAME = pdytr-simple-image 
CONTAINER_NAME = alpine-vnc-container
VNC_PORT = 5900

.PHONY: all clean test run exec

all: clean install test

clean:
	mvn clean

install:
	mvn install

test:
	mvn test

run:
	docker run -it --rm --name $(CONTAINER_NAME) -p $(VNC_PORT):5900 -v ${PWD}:/app $(IMAGE_NAME)

exec:
	docker exec -it $(CONTAINER_NAME) ash