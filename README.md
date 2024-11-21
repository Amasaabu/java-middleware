**PayPal API Clone - Middleware Service**

Welcome to the PayPal API Clone Middleware Service repository! This project replicates core functionalities of PayPal’s API in a distributed system architecture. The middleware serves as the backbone for merchant interactions, enabling seamless modifications, authentication, and transaction tracking.

Overview
The Middleware Service is a critical component of this distributed system, providing merchants with tools to:

Modify transaction routing logic.
Authenticate themselves before interacting with the system.
Generate and manage API keys.
View and track transactions being processed.
Additional Components in the Ecosystem
This repository focuses on the Middleware Service, but the complete system includes:

Routing Service: Routes customer transaction requests to the appropriate services. 
Link to Roting Service (https://github.com/Amasaabu/router/tree/main)
Processor Service: Handles customer requests for processing transactions.
Link to processing service (https://github.com/Amasaabu/processot/tree/main)
Features
Merchant Authentication

Merchants must authenticate themselves using secure mechanisms to access and modify settings.
Transaction Routing

Provides merchants the ability to customize how transactions are routed to the processing service.
API Key Management

Generate, retrieve, and manage API keys for merchant integrations.
Transaction Tracking

Allows merchants to view transactions being processed in real-time or through history logs.
Built With
Java Spring Boot: The backend application framework used to build the middleware service.
Kubernetes: For container orchestration and managing deployments.
Docker: To containerize the application for consistent deployment.
Helm: To manage application upgrades within the Kubernetes cluster.
Deployment Workflow
The deployment of this application follows a streamlined CI/CD process, set up using GitHub Actions. Details of the deployment process can be found in the workflows folder of this repository.

Deployment Flow:
Docker Image Creation

A Docker image of the application is built and published to DockerHub.
Trigger Kubernetes Upgrade

The published Docker image triggers the Kubernetes cluster to upgrade the application.
Helm is used to manage and automate these upgrades.
