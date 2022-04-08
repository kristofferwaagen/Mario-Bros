###Referat fra møte 22.03.2022

####Deltakere
- Kristoffer
- Johnny
- Anders

#####Oppsummering
Vi diskuterte deloppgave 1, hvordan teamet fungerer og om rollene burde endres. Vi tenkte grafisk designer (Andrè) kunne få, i tillegg til å designet banene, lage reglene for spillet. Med regler tenker vi da hvordan spilleren interagerer med objekter og hvordan poengsystemet skal struktures og hvordan man vinner et nivå.
Uten direkte kobling til prosjektet og diskusjon, ble det også sett på hvordan man kan oppdatere enkelt fra git for å holde sin fork oppdatert med endringer som er sendt til development branchen til prosjektet.
Johnny viste oss implementasjon av start skjerm til spillet og hvordan han videre skal utvikle en game over skjerm og skjerm når spilleren når et nytt nivå. Han ønsker også å ha en knapp i hjørnet av skjermen så man alltid kan avslutte / returnere til menyskjermen selvom man er i et spill.

###Referat framøte 23.03.20222

####Deltakere
- Kristoffer
- Johnny
- Anders
- Samuel
- André

####Oppsummering
Anders oppdaterte gruppen på hvordan vi skal lage klassediagrammet for
deloppgave 3. Tanken var først å lage det fra plugin i intelliJ, men
siden han ikke fant ut av hvordan han kunne legge til egne piler for importerte
klasser, bruker han klassediagrammet fra intelliJ som mal, men lager selve
klassediagrammet i Lucidcharts og legger det inn som en png fil.

Samuel har jobbet med collision mellom spillere og objekter. Så videre må vi jobbe med
å få oppdatert  GamePLayer og GameEnemy til å følge denne koden.
Han informerer oss mer om hvordan det er vanskelig å teste spillerklassen når
de direkte bruker sprite. Ved å flytte det grafiske ut av disse klassene
blir det enklere å teste de.

Siden vi fikk tilbakemelding på innlevering 1, diskurer vi også hva vi må
være tydeligere på innleveringene.

###Referat fra møte 25.03.2022

####Deltakere
- Kristoffer
- Johnny
- Anders
- Samuel
- André

####Oppsummering
Vi bestemte oss for å sette opp et møte før innleveringen av oblig2 for å høre hvordan folk ligger an med arbeidsoppgavene sine
og om alle var klare for innlevering. Vi diskuterte hva vi hadde gjort og det hjelper med et par ekstra øyne for å se over
sine egne oppgaver. Vi har prøvd å få laget en abstract class framfor et interface av GameFigures fordi GamePlayer og GameEnemy
har flere like metoder (f.eks. setposition()). Fordelen med en abstract class er at vi kan ha ikke abstrakte metoder også. Interface tillater
jo ikke metode bodies.
Siden André har hatt problemer med innlogging på git har han sendt README.md filen i discord også laster Kristoffer den opp til git.
Johnny har fått ryddet i koden fra sist møte, Anders har fått laget klassediagram, og Kristoffer har ryddet i project boarded på trello.
Samuel oppdaterte oss også på tester han hadde skrevet - både automatiske og manuelle.

###Referat fra møte 29.03.2022
####Deltakere
- Kristoffer
- Johnny
- Samuel
- André
- Anders

####Oppsummering
Johnny og Kristoffer oppdaterer oss på hvordan kameraet fungere og hvordan det må bli riktig konfigurert, slik at når man dør vil bakgrunnen stemme
overens med hvor spilleren døde, og "Game over" kommer på det stedet.
Samuel jobber med å se på hvordan det er best å implementere en spiller i spillet. Skal det være
et eget objekt som man kan legge til i Tiled og definere som et objekt, eller skal det
være en figur som legges på kartet i koden. Videre skal André lage nivåene spilleren skal bevege seg gjennom.
Anders fikk problemer med SDK-en som Intellij bestemte seg for å bruke, så
det gikk mye tid til å få fikset opp i dette. Til slutt ble det å klone forken på nytt igjen.
Vi bestemte oss også for å forkaste abstrakt klasse for player og enemy, da dette
kom til å gjøre det vanskeligere å lage tester. Det ble derfor enklere å bruke interface.


###Referat fra møte 05.04.2022
####Deltakere
- Kristoffer
- André
- Anders
- Johnny
- Samuel

####Oppsummering
André har fått fikset innlogging til GitLab, så endelig har alle full tilgang til prosjektet.
Kristoffer som har jobbet med kollisjon har implementert
spilleren som et layer i kartet vårt. Da er det enklere for spilleren å interagere med objectlayers
som er definert i level.tmx filen. Vi har valgt å gå videre med box2d, for å gjøre
det enklere for oss med kollidering mellom spiller og objekter på kartet.
Dette fører til at vi må gjøre endringer i tester, men det skal gå fint.
