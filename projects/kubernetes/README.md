# kubernetes

This example app demonstrates reading configuration keys from a `ConfigMap`. 

## Prepare a Cluster

Create a cluster for local testing, for examle using [k3d](https://k3d.io/). Make sure to create a [local registry](https://k3d.io/v5.0.0/usage/registries/#using-a-local-registry).

    k3d registry create my-registry --port 0.0.0.0:5000
    k3d cluster create my-cluster --registry-use k3d-my-registry: 

## Build Spring Boot App Image 

Build a Docker image of the Spring Boot application.

    ./gradlew :projects:kubernetes:bootBuildImage

Assign a tag in order to push the image to the local registry. If the host cannot resolve the hostname of the registry, use `localhost:5000` as the registry prefix.

    docker tag unmacaque/spring-cloud-kubernetes localhost:5000/unmacaque/spring-cloud-kubernetes:latest
    docker push localhost:5000/unmacaque/spring-cloud-kubernetes:latest

## Assign Roles to Service Account

The serivce account used to run a Spring Cloud Kubernetes application needs to be given permissions to access the requested resources from the Kubernetes API.

For testing purposes, one can use the provided `permissions.yaml` to give the default service account permissions to read `ConfigMap` and other resources in the `default` namespace.

    kubectl apply -f permissions.yaml

## Create ConfigMap

Create the `ConfigMap` for the Spring Boot application. Make sure `metadata.name` matches the `spring.application.name` property. Alternatively, [configure the application](https://docs.spring.io/spring-cloud-kubernetes/reference/property-source-config/configmap-propertysource.html) to explicity reference the desired resources.

    kubectl create configmap spring-cloud-k8s --from-literal=app.keys.foo1=bar1 --from-literal=app.keys.foo2=bar2

## Run App in Cluster

Finally, start the application in the cluster.

    kubectl run spring-cloud-k8s --image=k3d-my-registry:5000/unmacaque/spring-cloud-kubernetes:latest --port 8080

Verify successful loading of the `ConfigMap` by creating a port forward and accessing the app via http://localhost:8080.

    kubectl port-forward spring-cloud-k8s 8080:8080
