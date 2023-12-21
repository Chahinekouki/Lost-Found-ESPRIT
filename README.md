# Projet "LOST & FOUND" - Plateforme de Gestion des Objets Trouvés 
Bienvenue dans le README du projet "LOST & FOUND", fruit d'un stage d'immersion captivant de 6 semaines au sein du groupe SSD Team. Ce projet novateur s'adresse spécifiquement aux étudiants de l'établissement ESPRIT,
offrant une solution dédiée à la recherche et à la récupération d'objets égarés au sein de l'école.
# Objectif du Projet
Le projet se concentre sur le développement d'une application web moderne, utilisant Spring Boot pour le backend, Angular pour le frontend, et une base de données MySQL pour 
assurer la robustesse du stockage des informations. Cette stack technologique promet une expérience utilisateur fluide et sécurisée.
# Déroulement du Stage
Réalisé en collaboration avec deux autres stagiaires au sein de l'équipe SSD, mon rôle passionnant consiste à implémenter le module de gestion des utilisateurs avec authentification. La sécurité est un aspect essentiel de notre approche,
et nous mettrons en valeur cette dimension à l'aide du framework Spring Security.
# Conception 
# 1-Architecture logique 
L'application "LOST & FOUND" adopte une architecture multi-couches pour garantir la modularité et la maintenabilité du code. Voici une vue d'ensemble de l'architecture logique :
![Aperçu de la Plateforme E-sport](./Diagrammevierge(5).png)

controller: Gère les requêtes HTTP et les interactions avec le frontend.
service: Contient la logique métier de l'application.
impl: Implémente les services spécifiques.
repository: Gère l'accès à la base de données.
model: Définit les entités et les objets métier.
security: Gère la sécurité de l'application, utilisant Spring Security.
# 1-Diagramme de classe 

