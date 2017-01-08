#Twitter_Stream_Recorder.scala

Ce programme se connecte à l'API de twitter (via twitter4j) et enregistre les événements (ici les tweets) avec spark streaming.
Les tweets sont stockés dans une table csv ayant le schéma suivant :
utilisateur,année,mois,jour,heure,minute,seconde

NB : Le flux de tweets que l'on enregistre est limité à environ 1000tweets/minute.

Cette partie du programme a été copiée puis adaptée à partir du cours "Mastering Spark for Structured Streaming" d'Oreilly dont le code source du use case est disponible ici.

#DAU_MAU.scala

Ce batch spark va ensuite calculer des indicateurs DAU et MAU, à une echelle de temps plus miniature pour faciliter les tests (jour->seconde, mois-> minute)
et les afficher à l'écran.

# Faire marcher le programme
## Configurer les sources
Pour pouvoir faire marcher le programme, il faut créer un dossier "twitter" puis adapter le chemin de lecture et d'ecriture :
-ligne 20 de Twitter_Stream_Recorder.scala
-ligne 18 de DAU_MAU.scala

## Compiler 
sbt assembly à la racine du projet

## Enregistrer les tweets avec Spark Streaming

sbt run et taper "2" pour choisir le main de Twitter_Stream_Recorder
Laisser l'enregistrement tourner pendant une petite minute.
Ctrl+C pour arreter l'enregistrement

## Afficher le DAU et le MAU

sbt run et taper "2" pour choisir le main de DAU_MAU
Le DAU filtré sur la seconde 10 et le MAU filtré sur la minute 4 vont s'afficher.




