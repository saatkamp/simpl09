# Meine Apache ODE Einarbeitung #

Hab mir gedacht ich hau mal das Zeugs was ich jetzt so mache und woran ich grad arbeite direkt hier in unser Wiki Verzeichnis rein.
Ist denk ich auch gut damit Xi sieht was ich so gemacht habe und das ganze fortsetzen kann wenn ich im Urlaub bin.


# Details #

Momentan lesen des Developer Guides auf der Apache ODE Seite. Ist allerdings bisher keine wirklich große Hilfe, deswegen werd ich heute mal direkt im Apache ODE Dev Forum nachfragen wie das ganze so funktioniert. Weiteres folgt.

Auf dieser Seite befindet sich eine Übersicht dazu, welche BPEL Aktivitäten in ODE unterstützt sind und welche nur teilweise oder gar nicht unterstützt sind:

http://ode.apache.org/ws-bpel-20-specification-compliance.html

Das Monitoring in ODE wird wie ich es bisher verstanden habe über das ProcessManagementInterface und das InstanceManagementInterface durchgeführt. Werd mir dazu allerdings heute und am WE nochmal ein paar Sachen anschauen und überprüfen ob das soweit stimmt. Hier mal die Javacode Pages zu den beiden Interfaces:

http://ode.apache.org/javadoc/org/apache/ode/bpel/pmapi/ProcessManagement.html
http://ode.apache.org/javadoc/org/apache/ode/bpel/pmapi/InstanceManagement.html

So wie ich es gerade sehe, scheint hauptsächlich das Process ManagementInterface für Monitoring Funktionen zuständig zu sein. Mit diesem können alle Prozesse oder ein Teil der Prozesse aufgelistet werden. Weiterhin kann man sich die Details zu allen Prozessen anzeigen lassen. Zum Beispiel wann und von wem sie gestartet wurden usw.

Komplette Liste und Ausarbeitung folgt.

So hier auch noch was interessantes (direkt von der ODE Seite kopiert):

Ode lets you register your own event listeners to analyze all produced events and do whatever you want to do with them. To create a listener you just need to implement the org.apache.ode.bpel.iapi.BpelEventListener interface.

BpelEventListenerInterface: https://svn.apache.org/repos/asf/ode/trunk/bpel-api/src/main/java/org/apache/ode/bpel/iapi/BpelEventListener.java

Then add your implementation in the server's classpath and add a property in ode-axis2.properties giving your fully qualified implementation class name. Something like:
ode-axis2.event.listeners=com.compamy.product.MyOdeEventListener

Start your server and you're done!

Das heißt es ist relativ einfach möglich neue EventListener für ODE zu implementieren. Denk aber das wir das nicht brauchen werden.

Hier noch einmal eine Liste der Erweiterungen die ODE gegenüber dem normalen WS-BPEL enthält:

http://ode.apache.org/bpel-extensions.html

Diese Erweiterungen sind nichts anderes als einige Funktionen die ODE zusätzlich implementiert und die es im normalen BPEL nicht gibt.

Hier ist eine Übersicht über das Monitoring Package von ODE zu sehen:

http://ode.apache.org/javadoc/org/apache/ode/bpel/runtime/monitor/package-tree.html


So nach einem kurzen Gespräch mit Tammo van Lessen hat er mir ein paar Links gegeben in denen Infos über das Monitoring und die Extensions stehen.
Auditing, sprich das Speichern der Informationen, welcher Nutzer, wann was gemacht hat, gibt es in ODE gar nicht. Wenn wir etwas derartiges Nutzen wollen, müssen wir es komplett selbst implementieren.

Hier die Links:
http://ode.apache.org/user-guide.html#UserGuide-ManagementAPI

http://svn.eu.apache.org/viewvc/ode/trunk/extensions/e4x/

http://svn.eu.apache.org/viewvc/ode/trunk/bpel-api/src/main/java/org/apache/ode/bpel/extension/

http://ode.apache.org/extension-activities-extensible-assign-operations.html

Hier direkt mal die Readme zu den E4x Extensions:

http://svn.eu.apache.org/viewvc/ode/trunk/extensions/e4x/README?revision=712885

So hier noch die WSDL der ProzessManagement API, Tammo van Lessen meinte, dass hier alle Operationen die uns für das Monitoring zur Verfügung stehen aufgeführt sind. Außerdem sind hier auch die Links zu den beiden Webinterfaces des Monitorings enthalten (Prozesse und Instanzen), am Ende des Dokuments.

http://svn.apache.org/repos/asf/ode/trunk/axis2/src/main/wsdl/pmapi.wsdl

Auf die Interfaces konnte ich vom Laptop wegen irgend eines Fehlers nicht zugreifen, werd später zu Hause an meinem Rechner nochmal kucken woran das liegt ...