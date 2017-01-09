# Sources du programme
##Twitter_Stream_Recorder.scala

Ce micro-batch se connecte à l'API de twitter (via twitter4j) et enregistre les événements (ici les tweets) avec Spark Streaming.
Les tweets sont stockés dans une table csv ayant le schéma suivant :
utilisateur,année,mois,jour,heure,minute,seconde

NB : Le flux de tweets que l'on enregistre est limité à environ 1000tweets/minute par l'API

Cette partie du programme est inspirée du cours ["Mastering Spark for Structured Streaming" d'Oreilly](https://www.safaribooksonline.com/library/view/mastering-spark-for/9781491974445/video287523.html?autoStart=True) dont le code source du use case est disponible [ici](https://github.com/thedataincubator/spark-structured-streaming/tree/master/twitter).

##DAU_MAU.scala

Ce batch spark va ensuite calculer des indicateurs DAU et MAU, à une échelle de temps miniature pour faciliter les tests.
* jour->seconde 
* mois-> minute

Il affiche ensuite les résultats dans la console

# Faire marcher le programme
## Configurer les sources
Pour pouvoir faire marcher le programme, il faut créer un dossier "twitter" puis adapter le chemin de lecture et d'ecriture :
-ligne 20 de Twitter_Stream_Recorder.scala
-ligne 18 de DAU_MAU.scala

Par exemple "/home/gregoire/twitter" pour moi

## Compiler 
A la racine du projet, lancer :
```shell
sbt assembly
```
## Enregistrer les tweets avec Spark Streaming

```shell
sbt run
```
à la racine du projet puis taper "2" pour choisir le main de Twitter_Stream_Recorder
Laisser l'enregistrement tourner pendant une petite minute.
Ctrl+C pour arreter l'enregistrement

## Afficher le DAU et le MAU


```shell
sbt run
```
puis taper "1" pour choisir le main de DAU_MAU
Le DAU filtré sur la seconde 10 et le MAU filtré sur la minute 4 vont s'afficher.

Il est possible de modifier ces paramètres lignes 19 et 23 respectivement de dans DAU_MAU.scala




