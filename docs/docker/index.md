# Getting Started

You are probably asking yourself why there is an entire chapter about docker in this documentation. And the question is
to a large part fully justified. To make a complicated answer simple we need docker to build, test and deploy our application.
This is also the case if you use [Kubernetes](/kubernetes/) to do the deployment of this application. 

On this page you will find a clear description as well as a list of where docker is used in livius and how we work with it.

## Usage

Docker is used for the following parts of our application. First of we have the documentation you are reading right now,
this is build with MkDocs build with Docker as an image and deployed with Kubernetes in the livius.io cluster.
The documentation should only be deployed on your own servers if your users do not have a stable or constant internet 
connection to access the global documentation. 

The second place we use docker is in the building and delivering of the frontend application. The final and most interesting 
part where we use docker is in the backend of livius with the building, delivering of the services necessary to run livius.
The services can be found under there respected documentation sites.

## Prerequisites

The following is a list of things you should keep in mind while working with the livius in docker. Please note that these 
requirements are only exemplary and depending on your system and or the tools you use alterations or further steps might
be required.

- Docker 22 or newer
- Java 21 
- Maven 3.9 or newer
- npm
- Node.js

As long as you have these prerequisites you should be ready to start your work with or on this project. If you have any issues
or problems please let us know so we might fix them for you.

## Why Docker and not Podman or ...

If you are a bit more familiar with docker or other container tools you might ask yourself why we specifically describe,
use and recommend docker for this project. This is for two reasons. The first one is fairly simple as it is the most
widely known and used container tool. And secondly because most of the examples and tools we use are directly linked to it.

Furthermore, it does not really make a difference as we mostly are interested in the image that is produced by docker for the 
varies application parts. So as long as your container tool does follow the OCI standard you should not run into any 
problems. 

If you like you can also open a pull request on the git repo and add your desired deployment and container tool parts to
the application.