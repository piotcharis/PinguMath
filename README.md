**PinguMath**

Unsere Freunde von der Pingu-Uni kennen wir bereits aus der P-Aufgabe über die Verhaltensforschung an Hunden. Nun möchte die Universität ihr Forschungsgebiet im Bereich der Mathematik ausweiten. Dazu benötigen sie eine Erweiterung ihrer Forschungs-Libraries.

WICHTIG: In der Gesamten Aufgabe sind die Signaturen der Methoden im Template vorgegeben und dürfen nicht verändert werden!

WICHTIG: In der Gesamten Aufgabe ist es nur erlaubt Libraries aus java.util und java.lang zu importieren exklusive der java.lang.Math und java.lang.StrictMath Library. Sollten andere Libraries oder die Math Library genutzt werden, wird die Aufgabe mit 0P bewertet.

**Number Base Conversion**

Seit die Pinguine mit uns Menschen in Kontakt getreten sind, haben sie von uns das Dezimal-System beigebracht bekommen. Die Pinguine selbst zählen normalerweise in Pingu-Zahlen. Da diese aber für uns zu schwer zu verstehen sind, benötigen die Pinguine nun ein Programm, das Pingu-Zahlen in Dezimal-Zahlen und umgekehrt umrechnen kann. Deine Aufgabe ist es nun diese zwei Algorithmen zu implementieren. Unten findest du eine Beschreibung, wie sich Pingu-Zahlen in Dezimal-Zahlen umrechnen lassen:

**Pingu-Zahlen**

- Im Gegensatz zu Dezimal-Zahlen (Basis: 10) sind Pingu-Zahlen ein Ternärsystem (d.h. Basis: 3)
- Der einzige Unterschied zwischen herkömmlichen Ternär-Zahlen und unseren Pingu-Zahlen besteht darin, dass Pinguine keine arabischen Ziffern (0-2) kennen. Daher nutzen sie die Silben des Wortes ‚Pinguin‘ die Übersetzung gestaltet sich nach der einfachen Abbildung: pin <-> 2, gu <-> 1, in <-> 0
- Die Pinguine haben sich jedoch einen kleinen Twist einfallen lassen. Jede Pingu-Zahl beginnt nämlich mit einem Großbuchstaben und alle folgenden Buchstaben werden klein geschrieben. (Die zuvor erwähnte Abbildung verzichtet auf die Unterscheidung von Groß- und Kleinbuchstaben, die Silben sind nämlich klein geschrieben und daher keine echten Pingu-Zahlen.)

**Nun deine Aufgaben für den Converter**

**intToPinguNum**
 
In der Klasse NumberConverter findest du die Methode intToPinguNum. Diese soll die übergebene Dezimal-Zahl in eine Pingu-Zahl umrechnen und diese als String zurückgeben. Beachte dabei genau die zuvor beschriebenen Kriterien. Wichtig: Achte auch darauf, dass unsere Pinguine keine negativen Zahlen kennen, d.h. wird der Methode eine strikt negative Zahl übergeben, wird der String „N.D.“ (steht für: Nicht Definiert) zurückgegeben.

Hinweis: Natürlich suchen die Pinguine nach der kürzesten Darstellungsform, d.h. die Pinguzahl soll keine führenden Nullen enthalten um potenziell endlose Strings zu vermeiden.

**pinguNumToIn**

In der Klasse NumberConverter findest du die Methode pinguNumToInt. Diese soll die übergebene Pingu-Zahl in eine Dezimal-Zahl umrechnen und diese als Ganzzahl zurückgeben. Du kannst davon ausgehen, dass die eingegebene Pingu-Zahl immer in einem Integer gespeichert werden kann. Mache dir also keine Gedanken über Overflows. Wichtig ist aber, dass bei einer Eingabe, die KEINE echte Pingu-Zahl ist (z.B. „123“, „Abc“, „pingu“, etc.), immer der Default-Wert -1 zurückgegeben wird. Hier ist ein erneuter Blick auf die Beschreibung zu den Pingu-Zahlen hilfreich. Findest du noch weitere fehlerhafte Eingaben?

Hinweis: Für diese Aufgabe können dir folgende Methoden der Klasse String helfen, noch mehr dazu kannst du in den JavaDocs nachschlagen (String):

```
 String str = "Some String Here";
 int l = str.length(); // length() gibt die Länge des Strings zurück -> l = 16
 char c = str.charAt(2); // charAt(i) gibt den char an Index i (wobei bei 0 begonnen wird) zurück -> c = 'm'
 String subStr = str.substring(1, 4) // substring(beginIndex, endIndex) gibt den Substring beginnend mit dem char an Stelle 'beginIndex' bis zum char an Stelle 'endIndex zurück' ('beginIndex' inklusive, aber 'endIndex' exklusive) -> subStr = "ome"
```

**Sequence Analysis Tool (SAT)**

Dank unseres Zahlen-Converters können die Pinguine nun gemeinsam mit uns über die faszinierende Welt der Mathematik philosophieren. Bei einem Kaffee-/Fisch-Kränzchen sind sie auf Zahlenfolgen aufmerksam geworden. Sie interessieren sich nun für ein Programm, das ihnen dabei hilft, eine Zahl auf Zugehörigkeit zu bestimmten Zahlenfolgen zu überprüfen. Hilfsbereit wie du bist, bietest du ihnen an, solch ein Tool zu implementieren.

Hinweis 1: In dieser Aufgabe können auch kleinere Eingaben schnell zu Overflows führen. Du musst dir keine Gedanken darüber machen außer wir weisen direkt darauf hin. Die Tests sind so gestaltet, dass keine Overflows eintreten bzw. das Programm erwartungsgemäß terminiert, wenn alle Edge Cases beachtet wurden und du den Anweisungen in der Angabe folgst. Um dir die Sicherheit zu geben, dass deine Implementierung effizient genug ist, findest du unter jeder Aufgabe einen Wertebereich für die Eingabe(n) die korrekt und effizient (Δt&lt;1s\Delta t &lt; 1sΔt<1s) berechnet werden können müssen. Die Referenz hierfür sind die Artemisserver, diese sind in der Regel etwas langsamer als dein PC/Laptop. Mit den richtigen Tests werden die Methoden aber auch auf High-End PCs merkbar länger brauchen als gefordert.

Hinweis 2: In der Klasse SAT findest du die Methode checkSpecial. Diese wendet alle von dir implementierten Methoden für die Eingabe an und gibt dir einen formatierten String der Ergebnisse zurück. Die Methode soll eine Hilfestellung bei der Implementierung und dem Testen sein.

Hinweis 3: In der Gesamten Aufgabe ist es nur erlaubt Libraries aus java.util und java.lang zu importieren exklusive der java.lang.Math und java.lang.StrictMath Library. Sollten andere Libraries oder die Math Library genutzt werden, wird die Aufgabe mit 0P bewertet.

**isPow**

Die Methode isPow soll untersuchen, ob es für die Parameter n und i eine strikt positive Basis gibt, so dass gilt: x^i=n. Existiert solch ein x, geben wir true zurück, ansonsten false. Die Methode behandelt nur nicht negative i, d.h. sollte i dieses Kriterium nicht erfüllen, wird der Default-Wert false zurückgegeben. 

Für diese Aufgabe reicht eine (naive) iterative Implementierung aus. Damit du diese umsetzen kannst, ohne die Potenzfunktion aus der Java eigenen Bibliothek importieren zu müssen, findest du im Template die Funktion pow. Nutze diese Methode, um die Potenz zu berechnen : pow(a,b)=a^b.

**isCentralBin**

Die Methode isCentralBin soll untersuchen, ob die Eingabe n eine Zahl aus der Folge der mittleren Binomialkoeffizienten ist. Ist dies der Fall, gibt die Methode true zurück, ansonsten false.

Als kleine Hilfe: Die ersten mittleren Binomialkoeffizienten (und damit die Eingaben, für die die Methode true zurück geben muss) sind: 1,2,6,20,70,252,924,…

**isJacobsthal**

In der Methode isJacobsthal untersuchen wir, ob die Eingabe n eine Jacobsthal-Zahl ist. Ist dies der Fall, gibt die Methode true zurück, ansonsten false.

Die ersten Jacobsthal Zahlen sind: 0,1,1,3,5,11,21,…

Mit deiner Implementierung zu den Jacobsthal Zahlen hast du das Interesse der Pinguine geweckt. Du klärst sie darüber auf, dass die Jacobsthal Zahlen im Allgemeinen als Lucas-Folge betrachtet werden können. Wir haben jedoch gelernt, dass Pinguine keine negativen Zahlen kennen. Daher diskutierst du mit ihnen nur über eine Teilmenge der Lucas-Folgen. Wir beschränken uns nämlich nur auf monoton steigende Folgen (im Folgenden genannt Lucas-Like-Folge).

**isLucasLikeSequence**

Implementiere nun die Methode isLucasLikeSequence. Die übergebenen Parameter (x0, x1, a, b, n) sind die notwendigen Variablen entsprechend der Definition der Lucas-Like-Folge. n ist die Zahl die untersucht werden soll ob sie Teil der Folge L(x0,x1,a,b) ist. Ist dies der Fall, gibt die Methode true zurück, ansonsten false.
