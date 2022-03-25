##Obligatorisk innlevering 2

###Deloppgave 1

#####Møtereferater
Møtereferater er lagt til som en egen .md fil kalt Møtereferater under deliverables.

#####Roller
Rollene i laget er vi relativt fornøyd med. Teamleader Kristoffer gjør en veldig god jobb og har veldig god oversikt over prosjektet og hvilke deloppgaver som må gjøres. Han passer også på at gruppemedlemmene forstår hvilke arbeidsoppgaver de har i henhold til rollene sine. Johnny som utvikler har fått lagt inn flere funksjoner, nå nylig menyskjerm. Han har flere arbeidsoppgaver gitt fra project boarded som han jobber med. Samuel har jobbet med tester, men også gått utenfor hovedansvaret sitt og hjulpet til med kollisjon av spiller og objekter. Andrè har designet nivået vi foreløpig er på som grafisk designer. Han må se at nivåene er gjennomførbare i henhold til spillmekanikken og hva spilleren er tillat å gjøre. Anders har stått for innleveringene og i tillegg til det ønsker han å være mer aktiv på spillfunksjonene også. Av endringer i roller tenker vi det kan være en ide at Andrè sitt ansvarsområde går fra map design til map design og definere funksjonene og reglene til spillet. Ellers er vi veldig fornøyd med rollefordelingen.

#####Prosjektmetodikk og gruppedynamikk
Som sist så ønsker vi fortsatt å øke antall møter hvor vi kan kode sammen og oppdatere hverandre på hvordan hver person ligger an. Uten at vi har møtt på noen uenigheter tenker vi gruppedynamikken kan forbedres ved å kommunisere oftere og bedre. På gruppetimene har det sjeldent vært 100% oppmøte på grunn av sykdom. På grunn av pandemien har noen vært i karantene og andre har hatt corona. Dersom noen ikke har hatt muligheten til å være der på selve møte pleier vi å ha en oppsummering på discord for at alle skal være oppdatert.
De siste ukene har dette vært vanskelig med obligatoriske innleveringer i andre fag. Erfaringer gjort den siste tiden viser at project board på trello er til veldig stor hjelp og gir en god oversikt over hva som må gjøres. Det er selvfølgelig viktig at denne oppdateres og det blir lagt til nye punkter etterhver som man gjør framgang for å få utbytte av project boarded. Vi er derfor veldig fornøyd med kanban som prosjektmetodikk. Som nevnt i oblig1 ønsket vi å implementere parprogrammering, men dette har ikke blitt noe av da det har vært vanskelig å sette opp passende tider for medlemmene oppå obligatoriske innleveringer i andre fag. Når vi kommer videre i prosjektet der arbeidsoppgavene kan kreve mer av oss vil pargrommaring være en prosjektmetodikk vi bruker.


#####Retrospektiv diskusjon
I starten av prosjektet var det mye usikkerhet på hvordan vi skulle angripe oppgaven. 
For å få til MVP kravene bryter vi de ned i mindre arbeidsoppgaver på Trello 
hvor Kristoffer også matcher arbeidsoppgavene med riktig gruppemedlem mtp 
deres ansvarsområde. Dette gjør prosjektet mye mindre kaotisk og hjelper oss med å 
få en mer strukturert tilnærming.
Vi har foreløpig fått til MVP kravene 1-3, Johnny har fått satt 
opp en start skjerm og Samuel har fått til kollisjon av klosser for å lage 
hindringer til spillerene. Vi byttet over til trello (linket i README.md) som project 
board da det er enklere å dele med gruppelederene. Generelt ønsker vi å ha en jevn innsats 
i prosjektet som vil gjøre det enklere å følge med for hvert av gruppemedlemmene. 
Fra sist innlevering har det vært vanskelig med den jevne innsatsen når andre obliger 
kommer i tillegg med en mer fastsatt tidsfrist. I tillegg til en god og oversiktlig project board, 
ser vi at det er viktig med god dokumentasjon av koden også. Dette skal hjelpe gruppemedlemmene 
med å enklere forstå hverandres kode og hva metodene gjør. For testansvarlig vil dette være 
viktig når kode som ikke skrevet av han skal testes. Det vil også oppstå situasjoner hvor man 
implementerer metoder man selv ikke har skrevet, og for at man ikke skal duplisere kode dersom man ikke 
er klar over at en annen har skrevet samme metode, vil dokumentasjon av kode redusere duplisering.
Vi ønsker også å få til flere tester av koden.

#####Forbedringspunkter
Generelt sett ønsker vi bedre kommunikasjon, kodedokumentasjon og jevn jobbing. Dette vil bedre gruppedynamikken, samarbeidet og gjøre det enklere å få et godt ferdigstilt produkt.

#####Forklaring av commits
- Anders med hovedansvar for logging og innleveringene har commits under deliverables
- Samuel som har ansvar for testing hadde også færre commits første innlevering da all testingen var visuell for å sjekke at spiller og spillbrett vises.
- Johnny commitet til et prosjektet før omkonstruering av git oppsettet, som Kristoffer gjorde. Og derfor er det flere commits på Kristoffer.
- André hadde personlige ting som kom i veien for full jobbing

#####Problemer
Testansvarlig har støtt på problemer når det kommer til å teste koden for klasser som bruker SpriteBatch, Gdx, Texture og lignende. Han tenker derfor at unit-testing må bare teste objekter hvor man lager instanser uten å være avhengig av å vise spillet grafisk.


###Deloppgave 2

######Stretch goal
Vi har bestemt oss for å fortsette med multiplayer som den mer avanserte tingen i spillet. 
Vi har allerede lagt inn to figurer hvor den ene spiller med WAD og den andre spiller med piltastene.

######MVP og annet
Brukerhistorier ligger i linken til trello under README.md.

Hittill har vi fått laget et spillbrett, vist spiller 1 og spiller 2 på spillbrettet, hvor vi også kan flytte spillerene med taster. 
I tillegg interagerer spilleren med terrenget, som bakke og hindringer i veien. Videre skal vi få implementert et poengsystem hvor spesifikke 
klosser kan inneholde bonuselementer. Vi må også få designet fiender for spillerene våres og utvikle en måte for spilleren å dø eller bekjempe fienden. 
Som kjent fra standard Super Mario spill, vil dette kunne skje ved at spilleren hopper på fienden, eller dersom fienden treffer spilleren på en 
annen måte vil spilleren dø. Vi må også designe et mål på spillbrettet. I vårt tilfelle vil dette være en sted spilleren når fram til for å så komme videre til neste nivå. (MVP 8 - 9). 
Siden sist gang har vi fått delvis implementert MVP 10 med start skjerm, og MVP 11 ved å ha flere spillere som kan spille samtidig. Vi har fikset interagering mellom
spillere og terreng, og interagering mellom fiender og terreng (MVP 4 og MVP 6). Her mangler det at vi også får fienden og spilleren til å interagere med hverandre.

Vi har ikke endret på noen av MVP kravene hittil. De passer godt til Kurt Mario og vi bruker
de som en mal for å se hva vi må jobbe med. 

Gjøremål, brukerhistorier og akeptansekrav ligger i project boarded i Trello som er linket i README.md.

######Arbeidsoppgaver rundt MVP punktene

Anders skal til innlevering 2 ferdigstille klassediagrammet og 
ObligatoriskOppgave2.md filen, i tillegg jobber han å lage fiender til spillet. Samuel skal jobbe med kollisjon mellom
spiller, fiende og objekter. Han har som sagt også fokusert mer på tester, da dette var noe
vi fikk lav score på sist oblig. Johnny skal jobbe med startskjerm og hvordan man kan
avslutte spillet eller returnere til main menu. Han skal også rydde kode, sjekke kodekvaliteten
og se om det er metoder som kan abstraheres. André skal jobbe med spillbrettene
så vi har flere nivåer vi kan teste ut og la spillerene bevege seg mellom ved fullført
nivå. Kristoffer har også ansvaret for hvordan skjermen fungerer og designe en HUD til spillet.

I henhold til MVP punktene gitt i oblig1, vil vi fokusere på

- spiller har poeng og interagerer med poenggjenstander (MVP 5)
- fiender og spillere interagerer med hverandre (MVP 6)
- spiller kan dø (MVP 7)
- mål for spillbrett (MVP 8)

I tillegg vil vi også lage et ferdigstilt interface for gameplayer, gamenemy og bevegelige objekter.

Vi ser for oss at når vi har funnet ut av hvordan fiender og spillere 
interagerer med hverandre, vil det å fullføre MVP 7 være relativt lett. 
Siden dette er Kurt Mario vil spilleren i utgangspunktet bare ha
ett liv, så om de er nær hverandre vil spilleren dø. Når kollisjon er på plass
mellom spiller, objekter og fiender, kan vi også få MVP 8 på plass ved å laste
nytt nivå / ta spiller tilbake til menyen når de er nær målet.

###Deloppgave 3
######Dette har vi fikset siden sist
- tester
- interface for objekter som skal brukes for fiender, bevegelig gjenstander og player
- tydeligjort hva arbeidsoppgavene videre er
- oppdaterer README.md for å vise hvordan man bygger, kjører og tester programmet.

######Klassediagram
Ligger som en egen bildefil under ObligatoriskOppgave2 mappen.




