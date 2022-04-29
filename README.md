# Kurt Mario av Hackespett

Kurt Mario er et libGDX basert platformspill laget av Hackespett for en prosjektoppgave i INF112.
Spillet er basert på det orginale Super Mario spillet. Målet for spillet er at spilleren skal bekjempe fiender, samle poeng, finne nøkkelen og ta
den med seg til mål for å fullføre nivåene.
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

Man kan velge mellom enspiller eller tospiller på samme pc.
- Spiller 1 beveger seg med piltastene og skyter med ENTER
- Spiler 2 beveger seg med W,A,D, og skyter med SPACE.

For å fullføre et nivå må man finne en nøkkel og den med seg til
målområdet i enden av nivået. På veien kan man plukke opp penger, skudd og ekstra liv.
Hver blokk med ammunisjon har 10 skudd, og pengeblokkene har et vilkårlig antall mynter opp til 8.
For å plukke opp ekstra liv, skudd og penger må spilleren hoppe slik at hodet treffer undersiden
av blokkene.
Gjennom nivået vil man alltid ha en oversikt over poeng, skudd, nøkler og liv samlet, samt en klokke som
teller ned. Dersom man blir truffet av en fiende eller tiden renner ut, er spillet over.

Fiendene kan beseires enten ved å hoppe på hodene deres, eller skyte de om man har skudd tilgjengelig.

På start skjermen kan spilleren også velge hvilket nivå den ønsker å starte på, og når det nivået fullføres flyttes
spilleren automatisk til neste nivå.


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
- [Kevin MacLeod](https://www.chosic.com/download-audio/27248/) for bakgrunsmusikken.
- [ALEXANDER](https://orangefreesounds.com/mario-coin-sound/) for "coin.ogg" og "key.ogg".
- [Moonlightbunny](https://www.myinstants.com/profile/moonlightbnny/) for "extraLife.ogg".
- [Anonym](https://www.myinstants.com/instant/winner-winner-chicken-dinner-9/) for "winner.ogg".
- [CyberMelt](https://www.myinstants.com/profile/CyberMelt/) for "hurt.ogg"
- [kwahmah_02](https://freesound.org/people/kwahmah_02/sounds/262893/) for "jump.ogg"
- [Anonym](https://www.myinstants.com/instant/pokemon-wall-bump-28798/) for "noCoin.ogg"
- [GunGirl](https://www.myinstants.com/profile/GunGirl/) for "ammoPickup.ogg"
- [Anonym](http://plantsvszombies.clan.su/publ/fajly/muzyka_i_zvuki_iz_igry/plants_vs_zombies_sounds_zvuki_iz_igry_chast_3/7-1-0-7) for "hit.ogg"
- [Anders Stene](https://git.app.uib.no/Anders.Stene) for lydfilene "enSpillereValgt.ogg" og "toSpillereValgt.ogg"