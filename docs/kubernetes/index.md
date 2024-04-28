# General

To understand why we use Kubernetes for livius is not difficult. As this software is trying to explain how to integrate 
a variety of different tools and technologies it is only fitting that I also try to showcase the power and use a kubernetes
deployment can have for a microservice based application. 

All kubernetes configs can be found under the deployment directory in the root of the project. Each part of the deployment
of livius has its own file. In each file are included all necessary configs. This is different from other deployment 
configurations that you might have seen. But as we have quite a lot of deployments. And as this project is designed to be
deployed using [ArgoCD]() we think it is only fitting to put them there. For ease of use we also provide a kustomization.yaml
which is only needed to be changed to fit your needs to deploy livius in your own infrastructure. 