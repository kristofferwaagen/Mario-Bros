# Kurtmario av Hackespett

Kurtmario er et libGDX basert platformspill

## Medlemmer

- [Kristoffer Wågen](https://git.app.uib.no/Kristoffer.Wagen)
- [Anders Stene](https://git.app.uib.no/Anders.Stene)
- [Samuel Berre](https://git.app.uib.no/Samuel.Berre)
- [Johnny Nguyen](https://git.app.uib.no/J.Nguyen)
- [André Normann](https://git.app.uib.no/Andre.Normann)
## Nødvendig programvare

**Bygge-krav:** Java, Maven, JUnit, en IDE

**Oprativsystem:** Windows, MacOS eller Linux

## Hvordan laste ned programmet og starte spillet

Last ned XXX.jar fra:

*EN LINK*

### Windows

Åpne et terminal-vindu og navigere til mappen hvor XXX.jar-filen er, og kjøre:
```bash
java -jar XXX.jar
```
### Linux
Kjør kommandoer i terminalen:
```bash
sudo apt update
sudo apt install default-jdk
```
Naviger til mappen hvor XXX.jar ligger, og kjør:
```bash
java -jar XXX.jar
```
### MacOS

Naviger til mappen hvor XXX.jar ligger i terminalen, og kjør:
```bash
java -XstartOnFirstThread -jar RoboRallyVersion4.jar
```

### Dersom du vil kjøre spillet fra en Java-IDE:

* Laste ned Git-repo fra: https://git.app.uib.no/hackespett/plattformspill-temp som en zip-fil eller klon prosjektet.
* Åpne prosjektet i din IDE. Eksempelvis Eclipse eller IntelliJ.
* Finn main under directory: plattformspill-temp-master/src/main/java/main/Main.java
* Kjør main. Da skal spillet starte.

## Hvordan spille spillet

#### En spiller
* Velg 1 player i startmenyen 
* Bruk piltastene for å bevege spilleren.
* Bruk mellomromstasten for å skyte. 

#### To spillere i splitscreen 
* Velg 2 player i startmenyen 
* Spiller 1: bruk piltastene for bevegelse og mellomromstasten for å skyte.
* Spiller 2. bruk bokstavene A, S, D og W for bevegelse.

## Kjøre tester

For å kjøre tester

```bash
  run  GamePlayerTest.java 
```

For å teste det grafiske i spillet går man inn i PlayScreen.java, kommenterer ut den første private String mapLocation, og kommenterer inn den andre som er laget for testing. Så kjører man spillet som beskrevet fra før. Man beveger karakterene med WASD og piltaster. Det som skal testes er at figurene beveger seg smooth, og ikke går gjennom blokker. Dette gjøres ved å bevege karakterene inn i blokker, hoppe oppå eller inn i fra undersiden.

## Kjente bugs og problemer

På Mac OS X:

* Programmet vil ikke starte uten å gi JVM `-XstartOnFirstThread` muligheten. I Eclipse, kan du gjøre det ved å trykke *Run → Run Configurations...*, så velg *Arguments* fanen ag legg til `-XstartOnFirstThread` til *VM argument*.

* På Macer med M1 prosessor er en nyere versjon av libGDX nødvendig. Maven [`pom.xml`](pom.xml) filen har blitt satt opp til å bruke denne `1.10.1-SNAPSHOT` automatisk.

For spillet:

* Man ikke kan hoppe og skyte samtidig. 

* Når den ene spilleren dør kan det komme en bug som gjør at den andre spilleren vil skyte ut den døde spilleren.

* Om man går over et spesifikt objektet av typen ExprBlock på et av spillbrettene to ganger, så får man en feilmelding.

* Når man dreper en fiende blir den drepte fienden den nærmeste spilleren for AdvancedEnemy å følge etter. Dermed stopper den å bevege seg.

* Når en spiller dør i multiplayer kan spilleren styre "retningen" (øynene) til den andre gjenværende spilleren.

* Noen ganger ved gjentatte raske skudd kan spille kræsje.

## Brukerstøtte

For brukerstøtte, ta kontakt i vår Discord gruppe https://discord.gg/Zqsp3k7Q.

## Trello
Link til Trello:
https://trello.com/invite/b/RknwjnfS/3604c83a56a6f5bdf2cc1b11ffdcaf95/kurt-mario

## Acknowledgements

- En stor takk til [bee-m](https://bee-m.itch.io) for [Simple Platformer Premium 8x8 and 16x16](https://bee-m.itch.io/simple-platformer-premium-8x8-and-16x16) tilesettet
- [Johnny Nguyen](https://git.app.uib.no/J.Nguyen) for png filene Elias16Transp.png og Steffen16Transp.png
- [Kevin MacLeod](https://www.chosic.com/download-audio/27248/) for bakgrunsmusikken.
- [ALEXANDER](https://orangefreesounds.com/mario-coin-sound/) for coin lyd og key lyd.
- [Moonlightbunny](https://www.myinstants.com/profile/moonlightbnny/) for lyd når man plukker opp liv.
- [Anonym](https://www.myinstants.com/instant/winner-winner-chicken-dinner-9/) for winner sound.
