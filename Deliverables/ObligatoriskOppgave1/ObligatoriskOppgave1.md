DELOPPGAVE 1 - ORGANISER TEAMET

ERFARING
Erfaringen vår kommer i første omgang fra fag på Universitet i Bergen (UiB). Av relevante fag for dette prosjektet er fagene INF100, INF101, INF102, INF122. I tillegg har Kristoffer hatt en relevant sommerjobb.

ROLLER 
Som teamleder ble det naturlig å utnevne Kristoffer, da han har mer erfaring enn resten av teamet fra sommerjobb. Der jobbet han i team, noe som kan gjøre det enklere å for han å vite hvordan man får det beste samarbeidet. Samuel ble satt til ansvarlig for testing av koden for å forsikre at alle de viktige delene får solide tester. André er satt til grafisk designer og har hovedansvaret for levlene til spillet. Vi ser på dette som en viktig rolle da for mange kokker på kjøkkenet kan gi mye søl, og med en i ansvar kan det gi et bedre visuelt inntrykk for brukeren. 
Ved siden av sine tilegnede roller skal vi hjelpe hverandre og man er ikke låst til sitt ansvarsomeråde. Dette er nytt for hvert av medlemmene, og vi må ha et stort fokus på å hjelpe hverandre og kommunisere om hva neste steget i prosjektet er. Anders har hovedansvaret for å dokumentere fremgangen og skrive de obligatoriske oppgavedelene. Vi har valgt å ha en i hovedansvar for skrivingen av samme grunn som valg av grafisk sjef - mindre rot og et ryddig produkt. Johnny har ansvar for å ha en oversikt over kodene, redusere duplisering av kode og forbedre den der det kan. Dette skal gi et ryddigere uttrykk og bedre lesbarhet for teamet.

DELOPPGAVE 2 - PROSESS FOR LAGET

POSJEKTMETODIKK
Vi har satt opp et prosjekt på Jira. En nettside designet for teamarbeid. Her kan vi sette inn større mål for prosjektet og dele de videre opp i mindre delmål. På denne måten ser vi hva som må gjøres for å nå de forskjellige målene på en oversiktlig måte. Når vi krysser av for fullførte delmål hjelper det oss også med å se hva som har blitt gjort. Slik slipper man duplsisert arbeid. En naturlig metodikk er Scrum, sammen med Jira. Scrum fokuserer på å kutte ned arbeidet i delmål som skal fullføres innen tidsfrister, noe som passer perfekt for dette prosjektet der vi har satte frister for innlevering. I tillegg vil vi implementere kanban hvor kommunikasjon og åpenhet i arbeidet er i fokus. Igjen er dette noe Jira vil hjelpe oss med å oppnå. Til slutt vil det også bli tatt i bruk parprogrammering. Dette prosjektet er nytt for oss alle så endten om en står fast eller om det er større mål som skal nåes, så vil det hjelpe å samarbeidet for å komme seg fram.

ORGANISERING
I skrivende stund har vi ikke koordinert faste møter da vi fortsatt er usikre på størrelsen av prosjektet. Vi innser at vi må være fleksible framover og sette opp møter etter hvor mye som må gjøres. Utover disse møtene skal vi også sette opp noen tidspunkt i uken hvor hver har "daily stand up" hvor medlemmene informerer hverandre om hva de har jobbet med, hva som er gjort, og hva deres neste oppgave er. I tillegg har vi discord hvor vi alle er lett tilgjengelige. Fordelen med en større hyppighet av "daily stand up" er at man enklere får kartlagt hvor i prosjektet det går mye tid, så man fortere kan angripe det og sette opp f.eks. to medlemmer framfor en på en oppgave som krever mer.

ARBEIDSFORDELING
Først og fremst er alle enige om at arbeidsfordelingen skal være rettferdig. Det vil være Kristoffer, i sin rolle som teamleader, som har ansvaret for at dette blir oppfylt. Dersom et medlem ønsker en spesifikk oppgave eller ønsker å ta på seg mer arbeid tenker vi dette er helt opp til enkeltpersonen. Til syvende og sist er det viktig at vi kommuniserer slik at ingen føler de ikke bidrar og at ingen føler de er begravd i arbeid.

OPPFØLGELIG AV ARBEID
Når det kommer til oppfølgelig av arbeid vil Kristoffer sine "daily stand ups" være til god hjelp. I tillegg kan man følge med på hva som er gjort på Jira. Dette gjør at medlemmene er oppmerksom dersom en skulle havne bakpå med arbeidet sitt og kan hjelpe til med tips eller fullføre delmål satt på Jira.

DELING OG OPPBEVARING AV FELLES DOKUMENTER etc.
Vi kommer til å bruke git som kodebase, et google docs dokument for ekstra notater og hjelpende linker og Jira for prosjektplan.

DELOPPGAVE 3 - OVERSIKT OVER DET FORVENTETE PRODUKTET.

MÅL FOR APPLIKASJONEN
Vi ønsker å lage et Super Mario inspirert spill der spilleren beveger seg gjennom nivåer og må unngå fiender og hindringer for å komme dit. På det siste nivået ønsker vi å implementere en større fiende (boss) som spilleren må beseire for å vinne. Vi vil at spilleren skal kunne spille med minst en annen gjennom nivåene. De skal kunne se hverandre og interagere med hverandre. Dersom det er flere spillere kunne det være en ide å øke antall fiender for å gjøre det vanskeligere. På veien skal spilleren kunne plukke opp objekter (power ups) for å enklere beseire fiender mot mål. I tillegg skal spilleren ha en health bar slik at man kan ta skade av fienden og dø. Dersom spilleren faller utenfor spillbrettet må man også starte på nytt igjen. 

BRUKERHISTORIER - tatt fra MVP kravene i oppgaven

MVP 1 	- som spiller ønsker jeg tydelig kontrast mellom bakgrunn og spillbrett for å kunne se spillbrettet.
		- som spiller ønsker jeg å kunne se spillbrettet for å vite hvordan man kan bevege seg
		- som spiller ønsker jeg ulik visuell representasjon av ulike plattformer/blokker/gjenstander for å vite hva som er hva.
		
MVP 2	- som spiller ønsker jeg å kunne se spilleren min for å avgjøre hvordan jeg skal bevege meg videre.
		- som utvikler ønsker jeg å se spilleren for å se hvordan den interagerer med omgivelsene.
		
MVP 3	- som spiller ønsker jeg å kunne flytte karakteren min for å gjennomføre nivåene
		- som spiller ønsker jeg å flytte karakteren min for å unngå fiender og hente power ups.
		- som utvikler ønsker jeg å flytte karakteren for å teste spillet og nivåendringer.



Akseptansekriterier
MVP 1 	- spillet burde åpnes i et eget vindu når man kjører applikasjonen
		- spillbrettet vises i vinduet slik det er designet
		- design ulike blokker/gjenstander/plattformer for spillbrettet

MVP 2	- man må kunne se spilleren tydelig på brettet

MVP 3	- dersom man presser spesifikke taster skal spilleren sin posisjon bli oppdatert i koden.
		- dersom man presser spesifikke taster skal man kunne se at spilleren beveger seg på brettet.
		
OPPSUMMERING
Sammarbeidet har startet veldig greit. Vi var kjapt ute med å fordele roller og arbeidsoppgaver. Det første problemet vi støtte på var hvordan vi ønsket å arbeide med prosjektet (branches / forks) fra GitHub. Kristoffer fikk satt det opp slik at vi forket prosjektet og deretter pushet til hver vår fork. Så legger vi inn en merge request til ulike branches i prosjektet. Dette virker som en foreløpig god løsning. Testing av koden har til nå skjedd ved å kjøre programmet og sett om vi oppfyller de første MVP kravene. Det er visuelle krav, så det er lett å få brekrefelse ved å kjøre applikasjonen. 

Siden dette er den første innleveringen har det selvfølgelig vært mye usikkerhet i hvordan man skal jobbe sammen og hvordan vi skal angripe oppgaven. Vi kunne vært flinkere på å ha daily standups oftere for å gjøre sammarbeidet og kommunikasjonen bedre. Vi kunne også vært mer tilgjengelige på discord for å skape mer framgang i prosjektet og raskere avgjøre spørsmål som f.eks. hvordan vi skal jobbe med prosjektet fra git. Når kommunikasjonen går treigere vil oppdatering av arbeidsoppgaver gå saktere, og framgangen sakker ned. Til neste gang ønsker vi gjøre kommunikasjonen bedre og sørge for at alle er mer aktive på discord. For discord kan vi tagge hverandre for å få tydeligere varsler når noe viktig skjer i prosjektet, og for å øke oppdateringer mellom gruppemedlemmene kan vi ha faste tidspunkt for daily standups.
