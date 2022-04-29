## Obligatorisk innlevering 4

### Deloppgave 1

##### Møtereferater
Møtereferater er lagt til som en egen .md fil kalt Møtereferater under deliverables.

##### Roller
Vi har ikke endret spesielt på rollene. Hvis folk har tid til overs jobber de 
utenfor sine spesifikke arbeidsoppgaver. Anders har for eksempel jobbet med 
å legge inn flere nivåer, objekttyper, oppdatert hud og lagt inn musikk til spillet.
Nå som det er siste innlevering merker man at man må hjelpe til alle steder det trengs.
Samuel har også hjulpet til med koding utenfor å lage spilltester, hvor han har fikset en del bugs i spillet.


##### Prosjektmetodikk og gruppedynamikk
Til tross for at det er vanskelig å sette av tid til møter, har vi blitt flinkere til
å oppdatere hverandre på discord med hva man har gjort. I tillegg lar vi alltid
de andre se over koden før man merger det man har gjort til development branchen.
Dette gjør at man blir oppdatert på hva som er gjort og hva de trenger hjelp med.
Trello har vært til stor hjelp gjennom hele prosjektet. Det gjør at man slipper å gruble over hva 
som må gjøres videre og hva som mangler, og heller kan sette igang med neste arbeidsoppgave på prosjekt boarded.
Etterhvert som coronaen forsvant, ble det bedre oppmøte på møtene og vi fikk hørt med alle før
valg ble tatt når det kommer til nye delmål og fordeling av arbeidsoppgaver. Vi nevnte
jo i starten at vi ønsket å bruke parprogrammering som en prosjektmetodikk, men det har det blitt
veldig lite av. På møtene har vi jobbet med kode og diskutert, og hvis man har trengt hjelp med noe som en annen har fått til
har vi delt kunnskap her, men utover gruppetimene har vi jobbet individuelt på hver våre oppgaver i spillet.
Strect goal multiplayer over LAN kunne trengt parprogrammering (høyst sannsynlig), men vi ser at det ikke ble tid til dette, og valgte
heller å fokusere på en bedre lokal to spiller.

##### Retrospektiv diskusjon
Nå som vi har kommet oss gjennom prosjektet er det lett å se tilbake å peke på hva vi burde
ha gjort annerledes/bedre. Vi ser at vi burde skapt et tydeligere på bilde over akkurat hva spillet
skal inneholde, hvordan man vinner og hvordan spilleren kan bevege seg. Heldigvis for oss har disse brikkene
falt på plass etterhvert som man jobber seg gjennom arbeidsoppgavene og ved at vi har kunne diskutert på møtene våres.
Dette har vært utrolig lærerikt og vi ser at det er vanskelig å bli 100% ferdig til en fastsatt deadline.
Orginalt ønsket vi jo å implementere LAN med to spillere, men tiden rant ut og vi må nøye oss med lokalt tospiller. 
I tillegg må man adaptere seg til hvordan gruppemedlemmene har ulik kodestil. I starten manglet det en del dokumentasjon
på metoder og logikken, men vi har blitt flinkere til dette, i tilegg til at vi forklarer kode på gruppetimene.
Siden vi var dårlige til å kommunisere i starten og veldig usikre på prosjektet, gikk det sakte, og det var vanskelig å se hvor
man skulle begynne. I en perfekt verden skulle vi hatt flere fysiske møter, men mot slutten har vi blitt flinke til 
å oppdatere hverandre på Discord og sørge for at alle har noe å jobbe med. Det har gjort det enklere å vite hva man selv skal gjøre
og hva man kan gjøre når disse arbeidsoppgavene er ferdige. Trello gir muligheten til å "joine" arbeidsoppgaver, så man ser hvem som jobber med hva.
Dette har også vært fint, og man vet hvem man kan spørre angående klasser og kodeseksjoner som tilhører disse arbeidsoppgavene.
Kort oppsummert, til neste gang hadde vi satt opp flere møter, en tydeligere plan og startet tidligere med kodedelen av oppgaven.

##### Forbedringspunkter
Når man ser tilbake på oppgaven, så er det en trend at det forbedringspunktet som
har størst potensiale er møter og kommunikasjon. Dette har plukket seg veldig opp på siste innleveringen, men 
man kan alltid ha flere møter og flere oppdateringer.

##### Forklaring av commits

I starten la man inn en merge request fra sin fork til "development" branchen, så godkjente 
den som så over requesten mergen. Nå har vi gjort det slik at den som legger inn merge requesten
venter på godkjenning, og så merger selv. Da får vi mer riktig fordeling på merge requestes i forhold
til hvem som har gjort hva. Til slutt merger teamleader Kristoffer fra development til master branchen som leveres.
#####Ting som er gjort siden sist
Vi har lagt inn flere bonusblokker og muligheten for spilleren å skyte kuler dersom de har interagert med ammo blokk. 
I tillegg har vi lagt inn flere tester av spillogikken. Vi har lagt inn totalt fem nivåer som må fullføres for at spilleren skal
få victory skjermen. Man kan også velge hvilket nivå man ønsker å spille i menyen. Vi har også lagt inn animasjoner for spillkarakter og fiender.
##### Problemer
### Deloppgave 2

###### Stretch goal

Vi fikk jo beskjed om at multiplayer lokalt ikke var godt nok som stretch goal, men det var
godkjent av Anja, selv om LAN ville gitt mer poeng om det hadde blitt perfekt. Dette ble en for
stor oppgave å implementere, så vi har derfor fokusert på å gjøre lokal multiplayer bedre og bugfri, framfor 
LAN med masse bugs. Det vil alt i alt gi brukeren en bedre opplevelse.

##### MVP og annet
Vi har fått implementert alle MVP kravene gitt i oblig 1.

###### Krav fra MVP:
- Vise et spillebrett
  - Som bruker vil jeg kunne se spillbrettet for å vite hvordan jeg skal bevege
    meg gjennom nivået.
- Vise spiller på spillebrett
  - Som bruker vil jeg kunne se spilleren for å vite hvordan den beveger seg og hvor jeg er på nivået for å kunne fullføre det.
  - Som utvikler vil jeg kunne se spilleren på spillbrettet for å se hvordan spilleren interagerer med nivået.  
- Flytte spiller (vha taster e.l.)
  - Som bruker vil jeg kunne flytte spilleren for å kunne fullføre spillet og få en bedre spillopplevelse.
  - Som utvikler vil jeg kunne flytte spilleren for å se at den interagerer riktig med nivået når den beveger seg
    og at man går/hopper riktig etter hvilke taster som trykkes.
- Spiller interagerer med terreng
  - Som bruker vil jeg at spilleren interagerer med terrenget for at spillerens posisjon oppdateres avhengig av hvor jeg beveger meg.
- Spiller har poeng og interagerer med poenggjenstander
  - Som bruker vil jeg at man skal kunne samle poeng for å kunne forbedre scoren og få lyst til å prøve på nytt.
  - Som bruker vil jeg kunne interagere med bonusgjenstander for å gjøre nivået enklere/morsommere, og interagere med et mål
    for å fullføre nivået.
- Vise fiender/monstre; de skal interagere med terreng og spiller
  - Som spiller ønsker jeg å kunne se fiender for å kunne unngå/drepe de
  - Som utvikler ønsker jeg å kunne se fiender for å se at de beveger seg riktig på terrenget og at de interagerer med hverandre og spiller.  
- Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen)
  - Som bruker ønsker jeg at spilleren kan dø for å gjøre nivået vanskeligere og morsommere
- Mål for spillbrett (enten et sted, en mengde poeng, drepe alle fiender e.l.)
  - Som spiller ønsker jeg å kunne komme til et mål for å kunne gjennomføre nivået og fullføre spillet.
- Nytt spillbrett når forrige er ferdig
  - Som spiller ønsker jeg å bli flyttet til neste nivå når jeg er ferdig med forrige for å kunne fortsette å spillet
- Start-skjerm ved oppstart / game over
  - Som spiller ønsker jeg en tydelig beskjed dersom jeg dør (game over skjerm) og en meny skjerm for å 
    starte spillet.
- Støtte flere spillere (enten på samme maskin eller over nettverk)
  - Som spiller ønsker jeg flerspiller for å kunne spille med en venn.
###### Ekstra krav    
- Animasjoner for spiller og fiender
  - Som spiller ønsker jeg å kunne se spillere og fiender bevege seg med animasjoner for å se hvilken retning man er vendt
- Hud som viser poeng, liv ++
  - Som spiller ønsker jeg å kunne se hvor mange skudd, poeng samlet og om nøkkel er plukket opp for å vite om jeg kan interagere med mal, bruke skytefunksjonalitet og for å vite hvor mange poeng jeg har.
- Kunne velge nivå
  - Som spiller ønsker jeg å kunne velge nivå fritt for å kunne teste ut alle nivåene selv om tidligere nivå ikke er passert.
- Lydeffekter til spillet
  - Som spiller vil jeg kunne høre når man treffer bonusblokker/fiender for å vite at man treffer blokkene, plukker opp gjenstander
    og få en bedre spillopplevelse.

### Deloppgave 3

#### Dette har vi fikset siden sist
- riktig skjerm vises når man vinner, dør eller starter spillet
- fiender har fått en større topp som gjør den enklere å drepe.
- vi har lagt inn flere tester av spillet.
- blokker som faller når man går på de fungerer etter å ha gitt spilleren en "bottomBit"
- Når man hadde plukket opp alle pengene i Coin block kunne man få "null" error, men det er fikset nå.
- lagt inn bedre forklaring på hvorfor vi har valgt å implementere ekstra punkter utenfor MVP kravene.

##### Klassediagram
Klassediagram liger som en egen bildefil under ObligatoriskOppgave4 sin mappe.
Vi har laget relations mellom worldgenerator og InteractiveObjects og Enemy selv om de ikke direkte
importeres, men siden worldgenerator lager objektene tenkte vi det var passende. Dersom dere har muligheten til å åpne det i NClass er det best, men 
har også lagt det til som en png fil.