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


###Referat fra møte 08.04.2022
####Deltakere
- Kristoffer
- André
- Johnny
- Samuel
- Anders

####Oppsummering
Vi har fått implementert box2d som skal gjøre det enklere for senere implementasjon av kollisjon og interaksjon mellom
spiller og objekter. Nå som vi har lagt inn box2d så må spilltestene endres.
Vi snakker om poengdeling i spillet, og en ide kan være at man får x antall poeng for mynter, x antall poeng for fiender
og x antall poeng for hvor mye tid som er igjen på klokken. Vi tenker også at for å fullføre nivået så 
må man plukke opp en nøkkel og ta den med til mål. 
Gjennom møtet har Samuel fått oppdatert et par av testene så det går riktig vei. Vi har også fjernet overflødige klasser
som ikke lenger brukes etter box2d ble innført.

###Referat fra møte 08.04.2022
####Deltakere
- Kristoffer
- André
- Anders
- Samuel og Johnny kunne ikke møte på grunn av sykdom og påskeferie, men vi fyller de inn på hva vi har snakket om.
  
####Oppsummering
Fra forrige møte har Anders laget flere nivåer og skjermer avhengig av hvilken state befinner seg i.
Man starter på en menyskjerm, hvor man kan velge mellom single- two-player, hvilket nivå man vil starte på
og en victoryskjerm når man har fullført siste nivå. På slutten av hvert nivå blir man automatisk flyttet til neste.
Når man dør vil man kunne restarte eller gå tilbake til menyen. Anders har også lagt inn lydeffekter til hopping, penger, nøkkel og
når man treffer en fiende. I tillegg har han lagt til musikk for spillet og når man dør.
Vi burde få til en måte å legge til pauseskjerm som ikke restarter spillet men lar spilleren fortsette der han slapp da det ble
pauset. Kristoffer har sett mer på multiplayer, men vi har ikke fått satt av tid til å implementere det i spillet vårt.
Derfor må vi vurdere andre "stretch goal" som vi kan prøve å få til - dette kan være en "boss", en database hvor highscore lagres, animasjoner til bevegelser og
når man treffer f.eks. myntblokker og når spilleren beveger seg. 
Med boss så kan vi gjøre det slik at når den dør, så spawner nøkkelen for å kunne fullføre nivået. Da må man drepe bossen for å komme seg videre.
Anders skal videre legge inn musikk til victoryskjerm, når fiende treffer spiller, og lage en pause skjerm.
André skal legge inn i README.md hvordan man kjører spillet uten IDE. Kristoffer skal jobbe med 
hvordan fiender blir lagt inn i spillet.
Vi må også fikse hopping, hvor vi ønsker at spilleren bare skal kunne hoppe en gang.
I tillegg burde vi fikse hvor kameraet starter.

###Referat fra møte 26.04.2022
####Deltakere
- Kristoffer
- Samuel
- André
- Anders
- Johnny

####Oppsummering
Samuel har fått implementert at spiller bare kan dobbelthoppe ved at en boolean endrer seg 
om man er i luften eller toucher en ground bit eller andre liknende blokker.
Anders har fått lagt inn animasjoner til fiendene og spillere. Samuel har
også lagt inn flere tester av spillet.
Til siste innlevering må vi fokusere på bugs, lage en executable fil via terminal, stretch goal og finpussing generelt
av spillet.
Dersom vi ikke får til multiplayer så endrer vi stretch goal til å lage en database med high scores.