#!/bin/bash

java -Djava.library.path=build/natives/linux -cp "build/libs/asteroid-1.0-SNAPSHOT.jar:../../build/libs/Ents-1.0-SNAPSHOT.jar" asteroids.ogl.OpenGLAsteroids

