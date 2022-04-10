##Obligatorisk innlevering 3
###Deloppgave 1

#####Møtereferater
Møtereferater er lagt til som en egen .md fil kalt Møtereferater under deliverables.

#####Roller
Vi er fortsatt fornøyd med rollene. André har fått tilgang til GitLab endelig, som
gjør hans rolle viktigere. Anders som har hovedansvaret for rapporter og innleveringsfilene
har jobbet mer med koding, da det å skrive innleveringsoppgavene går veldig greit. Ellers fortsetter
Samuel med testene og Johnny jobber med skjermene som utviklerrolle.

#####Prosjektmetodikk og gruppedynamikk
Det vi har innsett er at det er vanskelig å sette opp møter. Flere av oss har forskjellige fag
og tider vi er ledige. I tillegg kommer andre obligatoriske oppgaver som har en mer definert
innleveringsfrist og oppgaver som skal gjennomføres, som gjør det vanskeligere å sette av tid til 
dette prosjektet. Det blir derfor til at man ofte jobber med dette i mindre tidsrom hvor man finner tid.
Projectboarded på trello hjelper oss uansett med å holde oversikten over arbeidsoppgaver som må jobbes med.

#####Retrospektiv diskusjon
Vi ønsker fortsatt en mer jevn innsats, men når en innlevering leveres, kommer
det en ny en i et annet fag. Det gjør det vanskeligere å opprettholde jevn framgang i prosjektet.
Vi må bli flinkere her til å sette av tid og prioritere prosjektet.
I tillegg må vi oppdatere hverandre når større endringer i prosjektet blir gjort, som f.eks. nå som vi har 
implementert box2D, som fører til at oppsettet av prosjektet blir annerledes og mange klasser blir påvirket.
Derfor har vi erfart at når sånt skjer så blir vi satt tilbake i prosjektet, men målt opp mot hvordan det kan gjøre
det enklere for oss senere, så vil det være verdt det. 
Klassene må også bli mer delt opp og være beskrivende for hver funksjon i spillet. Dette har vi nå gjort med mappen resources
da det ble veldig rotete med alle bilder, tilesets og tsx filer om hverandre. 

#####Forbedringspunkter
Som sist gang og som sagt i retrospektiv diskusjon må vi forberdre den jevne innsatsen og prioritering
av prosjektet. I tillegg kan vi bli flinkere til å stykke opp klassene og bruke interfaces / abstrakte klasser for å
gjemme kode. 

#####Problemer
Vi har hatt problemer der kollisjonen ikke følger kartet når spilleren beveger seg
ved at collision referte til posisjoner på skjermen. Vi håper dette løser seg 
når vi bruker box2d. Dit vi har kommet til nå med implementeringen av box2d så går det rett vei. 
Vi har derimot fått problemer med de tidligere testene som er laget, og de må omkonstrueres for å passe til
med den nye logikken i spillet. 
Dersom spilleren dør så skal man kunne trykke retry, men akkurat nå er ikke det lov fordi spillerene ikke eksisterer 
etter at de har dødd. 

###Deloppgave 2

######Stretch goal
Som beskrevet i ObligatoriskOppgave2.md ønsker vi å få implementert multiplayer mellom
to spillere lokalt på en PC. Hittil så er man to spillere på brettet og dersom begge dør får man muligheten til 
å starte på nytt igjen. Kameraet er låst på spiller 1 tradisjonen tro, helt til spiller 1 dør. Da bytter det over til 
spiller 2. Det samme gjelder andre vegen. Litt mer spesifikt om stretch goal så er planen å implementere KryoNet slik at man kan spille spillet med flere
terminaler på samme pc. Planen er da at i terminal 1 følger kameraet spiller 1 og i terminal 2 følger kameraet spiller 2.

#####MVP og arbeidsoppgaver
Anders skal lage klassediagram for ObligatoriskOppgave3 og få skrevet
ferdig innleveringsfilen. Når det er møter skal han også legge inn møtereferater
for møtene. Kristoffer jobber fortsatt med kollisjon mellom spillere, objekter og fiender. Dette 
viste seg å være en større oppgave enn forventet da vi nylig har måttet gjøre
endringer i hvordan det skal implementeres. André jobber med å lage nivåer og hvordan man skal 
kunne vinne et nivå. Foreløpig er en tanke at man kan lete etter en nøkkel som plukkes opp av
spilleren, og når man kommer til målobjektet så sjekker man om spilleren har nøkkelen. Ved å bruke en
slik regel tvinger man spilleren til å utforske hele nivået. Johnny jobber videre med meny og gameover skjermen. 
Dersom spillerene dør, så vil man få valget mellom å restarte eller exit spillet. Han har også lagt inn at "esc" er
pause i spillet. Samuel har i oppgave å fikse testene som følge av bruken av box2d. Vi må også få implementert en sprite for
spillere og fiender. Per nå er de bare definert som sirkler.


###Deloppgave 3
######Dette har vi fikset siden sist
- vi har endret hvordan kollidering fungere
- vi kan legge inn flere fiender
- spiller og fiende kolliderer med hverandre
- spiller kan dø


######Klassediagram
Ligger som en egen bildefil under ObligatoriskOppgave3 mappen.
