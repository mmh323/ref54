package com.example.referat54;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.text.Html;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InstructionActivity extends AppCompatActivity {
    private TextView instructionsTextView; // TextView zur Anzeige der Anleitungen
    private Spinner typeSpinner; // Spinner zur Auswahl des Gerätetyps
    private Spinner modelSpinner; // Spinner zur Auswahl des Gerätemodells
    private Button commentButton; // Button zum Hinzufügen von Kommentaren
    private ImageView deviceImageView;  // ImageView zur Anzeige des Gerätebildes
    private Map<String, String[]> modelsMap = new LinkedHashMap<>(); // Karte für Gerätetypen und -modelle
    private Map<String, String> instructionsMap = new HashMap<>(); // Karte für Anleitungen zu Gerätetypen
    private Map<String, Integer> imagesMap = new HashMap<>(); // Karte für Bilder der Gerätetypen
    private Map<String, String> reviewsMap = new HashMap<>(); // Karte für Bewertungen der Gerätemodelle
    private String selectedType = ""; // Variable zur Speicherung des ausgewählten Gerätetyps
    private String selectedModel = ""; // Variable zur Speicherung des ausgewählten Gerätemodells

    // Feld zur Speicherung des aktuellen HTML-Inhalts
    private String currentHtmlContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction); // Layout für die Aktivität festlegen

        // Initialisierung der UI-Komponenten
        instructionsTextView = findViewById(R.id.instructionsTextView);
        typeSpinner = findViewById(R.id.typeSpinner);
        modelSpinner = findViewById(R.id.modelSpinner);
        commentButton = findViewById(R.id.commentButton);
        deviceImageView = findViewById(R.id.deviceImageView);

        // Kommentar-Button initial verstecken
        commentButton.setVisibility(View.GONE);

        setupModelsAndInstructions(); // Gerätetypen, Modelle und Anleitungen einrichten
        setupTypeSpinner(); // Typ-Spinner einrichten
        setupModelSpinner(); // Modell-Spinner einrichten
        setupButtons(); // Schaltflächen einrichten
    }

    private void setupModelsAndInstructions() {
        // Beispielhafte Daten für Gerätetypen und Modelle
        modelsMap.put("Bitte Gerät auswählen", new String[]{"Bitte Modell auswählen"});
        modelsMap.put("Router", new String[]{"Bitte Modell auswählen", "TP-Link Archer AX50", "Netgear Nighthawk AC1900 (R7000)"});
        modelsMap.put("Switch", new String[]{"Bitte Modell auswählen", "Cisco SG350-28", "Netgear GS108"});
        modelsMap.put("Server", new String[]{"Bitte Modell auswählen", "Dell PowerEdge R540", "HPE ProLiant DL380"});
        modelsMap.put("Drucker", new String[]{"Bitte Modell auswählen", "HP LaserJet Pro MFP M428fdw", "Canon imageCLASS MF644Cdw"});
        modelsMap.put("VPN", new String[]{"Bitte Modell auswählen", "Netgear Nighthawk AC1900 (R7000)", "Asus RT-AC86U"});
        modelsMap.put("WLAN Access Point", new String[]{"Bitte Modell auswählen", "Ubiquiti UniFi AP AC Pro", "TP-Link EAP245"});

        // Anleitungen für verschiedene Gerätetypen hinzufügen
        instructionsMap.put("Router", "Anleitung zur Installation und Konfiguration von Routern:<br><br>" +
                "1. Verbinden Sie den Router mit Ihrem Modem über ein Ethernet-Kabel.<br><br>" +
                "2. Schalten Sie den Router ein und warten Sie, bis die Power-LED konstant leuchtet.<br><br>" +
                "3. Öffnen Sie einen Webbrowser und geben Sie die IP-Adresse des Routers ein (normalerweise 192.168.0.1 oder 192.168.1.1).<br><br>" +
                "4. Melden Sie sich mit den Standardanmeldedaten (meistens Benutzername: admin, Passwort: admin) an.<br><br>" +
                "5. Ändern Sie das Standardpasswort zu einem sicheren Passwort.<br><br>" +
                "6. Konfigurieren Sie die Netzwerkeinstellungen: Wählen Sie die WAN-Verbindung (z.B. DHCP, PPPoE) entsprechend Ihrem Internetanbieter.<br><br>" +
                "7. Richten Sie das WLAN ein: Geben Sie den SSID-Namen und ein sicheres Passwort ein.<br><br>" +
                "8. Speichern Sie die Einstellungen und starten Sie den Router neu.");

        instructionsMap.put("Switch", "Anleitung zur Installation und Konfiguration von Switches:<br><br>" +
                "1. Verbinden Sie den Switch mit dem Router oder einem anderen Netzwerkgerät.<br><br>" +
                "2. Schalten Sie den Switch ein und warten Sie, bis alle Status-LEDs stabil leuchten.<br><br>" +
                "3. Verbinden Sie Ihre Geräte (z.B. PCs, Drucker) mit dem Switch über Ethernet-Kabel.<br><br>" +
                "4. Optional: Melden Sie sich bei der Management-Oberfläche des Switches an, um erweiterte Einstellungen vorzunehmen (VLANs, QoS etc.).<br><br>" +
                "5. Speichern Sie alle vorgenommenen Einstellungen und starten Sie den Switch gegebenenfalls neu.");

        instructionsMap.put("Server", "Anleitung zur Installation und Konfiguration von Servern:<br><br>" +
                "1. Installieren Sie die Hardware in einem geeigneten Serverrack oder auf einem stabilen Tisch.<br><br>" +
                "2. Verbinden Sie den Server mit Strom und Netzwerk.<br><br>" +
                "3. Schalten Sie den Server ein und greifen Sie auf die BIOS/UEFI-Einstellungen zu, um die Boot-Reihenfolge festzulegen.<br><br>" +
                "4. Installieren Sie ein Betriebssystem (z.B. Windows Server, Linux) von einem USB-Stick oder einer CD/DVD.<br><br>" +
                "5. Konfigurieren Sie die Netzwerkeinstellungen (IP-Adresse, Gateway, DNS-Server).<br><br>" +
                "6. Installieren Sie notwendige Server-Rollen und Features (z.B. Webserver, Datenbankserver).<br><br>" +
                "7. Richten Sie Sicherheitsmaßnahmen ein (Firewall, Antivirus, Benutzerkonten).<br><br>" +
                "8. Überprüfen Sie die Serverleistung und nehmen Sie ggf. Optimierungen vor.");

        instructionsMap.put("Drucker", "Anleitung zur Installation und Konfiguration von Druckern:<br><br>" +
                "1. Schließen Sie den Drucker an das Stromnetz und den Computer oder das Netzwerk an.<br><br>" +
                "2. Installieren Sie die Treiber von der mitgelieferten CD oder laden Sie sie von der Hersteller-Website herunter.<br><br>" +
                "3. Schalten Sie den Drucker ein und folgen Sie den Anweisungen auf dem Display zur Erstkonfiguration.<br><br>" +
                "4. Richten Sie die Netzwerkeinstellungen ein, falls der Drucker netzwerkfähig ist (z.B. WLAN-Verbindung einrichten).<br><br>" +
                "5. Testen Sie den Drucker, indem Sie eine Testseite drucken.<br><br>" +
                "6. Stellen Sie sicher, dass der Drucker in den Druckeinstellungen des Computers als Standarddrucker ausgewählt ist.");

        instructionsMap.put("VPN", "Anleitung zur Installation und Konfiguration von VPNs:<br><br>" +
                "1. Entscheiden Sie sich für einen VPN-Server-Typ (z.B. PPTP, L2TP, OpenVPN) und installieren Sie die entsprechende Software auf dem Server.<br><br>" +
                "2. Konfigurieren Sie den VPN-Server: Legen Sie IP-Bereich, Authentifizierungsmethoden und Verschlüsselung fest.<br><br>" +
                "3. Erstellen Sie Benutzerkonten für VPN-Zugriff und legen Sie Berechtigungen fest.<br><br>" +
                "4. Installieren Sie VPN-Client-Software auf den Geräten der Benutzer und konfigurieren Sie die Verbindung zum VPN-Server.<br><br>" +
                "5. Testen Sie die VPN-Verbindung von einem externen Netzwerk aus, um sicherzustellen, dass alles richtig konfiguriert ist.<br><br>" +
                "6. Überwachen Sie den VPN-Traffic und passen Sie bei Bedarf die Sicherheitsrichtlinien an.");

        instructionsMap.put("WLAN Access Point", "Anleitung zur Installation und Konfiguration von WLAN Access Points:<br><br>" +
                "1. Platzieren Sie den Access Point an einem zentralen Ort, um eine optimale WLAN-Abdeckung zu gewährleisten.<br><br>" +
                "2. Verbinden Sie den Access Point über ein Ethernet-Kabel mit dem Router oder einem Netzwerk-Switch.<br><br>" +
                "3. Schalten Sie den Access Point ein und warten Sie, bis die Power-LED konstant leuchtet.<br><br>" +
                "4. Greifen Sie über einen Webbrowser auf die Management-Oberfläche des Access Points zu (normalerweise über die IP-Adresse des Access Points).<br><br>" +
                "5. Konfigurieren Sie die SSID und ein sicheres Passwort für das WLAN.<br><br>" +
                "6. Stellen Sie die Verschlüsselung auf WPA2 oder höher ein, um die Sicherheit des Netzwerks zu gewährleisten.<br><br>" +
                "7. Optimieren Sie die Kanaleinstellungen, um Interferenzen zu minimieren und die WLAN-Leistung zu maximieren.<br><br>" +
                "8. Speichern Sie die Einstellungen und testen Sie die WLAN-Verbindung mit verschiedenen Geräten.");

        // Bilder für die verschiedenen Gerätetypen hinzufügen
        imagesMap.put("Router", R.drawable.router_image);
        imagesMap.put("Switch", R.drawable.switch_image);
        imagesMap.put("Server", R.drawable.server_image);
        imagesMap.put("Drucker", R.drawable.printer_image);
        imagesMap.put("VPN", R.drawable.vpn_image);
        imagesMap.put("WLAN Access Point", R.drawable.access_point_image);

        // Bewertungen für spezifische Modelle hinzufügen
        reviewsMap.put("TP-Link Archer AX50",
                "<br><b>Überblick:</b><br>Der TP-Link Archer AX50 bietet Wi-Fi 6 mit Geschwindigkeiten von bis zu 2402 Mbit/s auf 5 GHz und 574 Mbit/s auf 2,4 GHz. " +
                        "Unterstützt MU-MIMO und OFDMA, ideal für Haushalte mit vielen Geräten.<br>" +
                        "<br><b>Setup:</b><br>Verbinden Sie den Router mit Ihrem Modem und nutzen Sie die TP-Link Tether App für die Ersteinrichtung. " +
                        "<br><br>Gehen Sie auf <a href='https://www.tp-link.com/de/support/download/archer-ax50/'>die TP-Link Website</a> für aktuelle Firmware-Updates und Treiber.");

        reviewsMap.put("Netgear Nighthawk AC1900 (R7000)",
                "<br><b>Überblick:</b><br>Ein starker Router mit bis zu 1900 Mbit/s. Unterstützt OpenVPN und ist perfekt für sicheres Arbeiten von zu Hause.<br><br>" +
                        "<b>Setup:</b><br>Verwenden Sie die Nighthawk App oder den Webbrowser für die Einrichtung. <br><br>Besuchen Sie <a href='https://www.netgear.com/support/product/r7000.aspx'>die Netgear Support-Seite</a> für Firmware-Updates.");

        reviewsMap.put("Cisco SG350-28",
                "<br><b>Überblick:</b> <br>Ein verwaltbarer Switch mit 28 Gigabit-Ports. Unterstützt Layer 3, VLANs und QoS für Flexibilität.<br><br>" +
                        "<b>Setup:</b><br>Verwenden Sie die Cisco Web Interface für die Einrichtung und Verwaltung. <br><br>Treiber und Dokumentation finden Sie auf der <a href='https://www.cisco.com/c/en/us/support/switches/sg350-28-28-port-gigabit-managed-switch/model.html'>Cisco Website</a>.");

        reviewsMap.put("Netgear GS108",
                "<br><b>Überblick:</b> <br>Ein einfacher unmanaged Switch mit 8 Gigabit-Ports. Ideal für kleine Netzwerke.<br>" +
                        "<br><b>Setup:</b> <br>Plug-and-Play, keine zusätzliche Software erforderlich. <br><br>Weitere Informationen auf <a href='https://www.netgear.com/support/product/gs108v4.aspx'>Netgear Support-Seite</a>.");

        reviewsMap.put("Dell PowerEdge R540",
                "<br><b>Überblick:</b> <br>Leistungsstarker Server mit Unterstützung für Intel Xeon Prozessoren. Bietet hohe Leistung und Skalierbarkeit.<br>" +
                        "<br><b>Setup:</b> <br>Nutzen Sie das Dell Lifecycle Controller für die Ersteinrichtung. <br><br>Weitere Details und Treiber finden Sie auf der <a href='https://www.dell.com/support/home/de-de/product-support/product/poweredge-r540/overview'>Dell Support-Seite</a>.");

        reviewsMap.put("HPE ProLiant DL380",
                "<br><b>Überblick:</b> <br>Ein flexibler Server mit umfangreichen Speicheroptionen und hoher Zuverlässigkeit. Perfekt für Virtualisierung.<br>" +
                        "<br><b>Setup:</b> <br>Konfigurieren Sie den Server über das Intelligent Provisioning Interface. <br><br>Besuchen Sie die <a href='https://support.hpe.com/hpesc/public/home'>HPE Support-Seite</a> für Software und Treiber.");

        reviewsMap.put("HP LaserJet Pro MFP M428fdw",
                "<br><b>Überblick:</b> <br>Ein vielseitiger Laserdrucker für kleine und mittlere Büros. Unterstützt schnelles, doppelseitiges Drucken.<br>" +
                        "<br><b>Setup:</b> <br>Laden Sie die HP Smart App herunter für eine einfache Einrichtung. <br><br>Treiber und Support auf der <a href='https://support.hp.com/de-de/drivers/selfservice/hp-laserjet-pro-mfp-m428-m429-series/19202542/model/19202543'>HP Support-Seite</a>.");

        reviewsMap.put("Canon imageCLASS MF644Cdw",
                "<br><b>Überblick:</b> <br>Ein All-in-One Farblaserdrucker mit hoher Druckqualität und Geschwindigkeit. Ideal für den Einsatz im Büro.<br>" +
                        "<br><b>Setup:</b> <br>Verwenden Sie das Canon Print Business App für Mobilgeräte. <br><br>Treiber verfügbar auf der <a href='https://www.usa.canon.com/support/p/imageclass-mf644cdw'>Canon Support-Seite</a>.");

        reviewsMap.put("Asus RT-AC86U",
                "<br><b>Überblick:</b> Ein Gaming-Router mit VPN-Unterstützung und hoher Leistung. Perfekt für Streaming und Online-Spiele.<br>" +
                        "<br><b>Setup:</b> <br>Nutzen Sie die ASUS Router App oder den Webbrowser. <br><br>Weitere Informationen finden Sie auf der <a href='https://www.asus.com/de/Networking-IoT-Servers/Whole-Home-Mesh-WiFi-System/All-series/RT-AC86U/HelpDesk/'>ASUS Support-Seite</a>.");

        reviewsMap.put("Ubiquiti UniFi AP AC Pro",
                "<br><b>Überblick:</b> <br>Ein leistungsstarker Access Point für Unternehmen mit Wi-Fi 5 Unterstützung. Ideal für stark frequentierte Bereiche.<br>" +
                        "<br><b>Setup:</b> <br>Verwenden Sie die UniFi Network Controller Software für die Einrichtung. <br><br>Besuchen Sie die <a href='https://www.ui.com/download/unifi/'>Ubiquiti Support-Seite</a> für Downloads.");

        reviewsMap.put("TP-Link EAP245",
                "<br><b>Überblick:</b> <br>Ein erschwinglicher Access Point mit hoher Geschwindigkeit und einfacher Verwaltung. Perfekt für kleine Unternehmen.<br>" +
                        "<br><b>Setup:</b> <br>Nutzen Sie die TP-Link Omada Controller Software. <br><br>Details finden Sie auf der <a href='https://www.tp-link.com/de/support/download/eap245/'>TP-Link Support-Seite</a>.");
    }

    private void setupTypeSpinner() {
        // Adapter für den Typ-Spinner erstellen
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modelsMap.keySet().toArray(new String[0]));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = (String) parent.getItemAtPosition(position);

                // Modell-Spinner verstecken, wenn kein Gerät ausgewählt ist
                if (selectedType.equals("Bitte Gerät auswählen")) {
                    modelSpinner.setVisibility(View.GONE);  // Modell-Spinner verstecken
                } else {
                    modelSpinner.setVisibility(View.VISIBLE);  // Modell-Spinner anzeigen
                    updateModelSpinner(selectedType); // Modell-Spinner mit den entsprechenden Modellen aktualisieren
                }

                String instruction = instructionsMap.get(selectedType);
                Integer imageResId = imagesMap.get(selectedType);

                // Anweisungen und Links für den ausgewählten Gerätetyp anzeigen
                if (instruction != null) {
                    currentHtmlContent = instruction;
                    instructionsTextView.setText(Html.fromHtml(currentHtmlContent, Html.FROM_HTML_MODE_LEGACY));
                    instructionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
                } else {
                    currentHtmlContent = "Bitte wählen Sie ein Gerät aus, um Anweisungen zu erhalten.";
                    instructionsTextView.setText(Html.fromHtml(currentHtmlContent, Html.FROM_HTML_MODE_LEGACY));
                    instructionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
                }

                // Bild für den ausgewählten Gerätetyp anzeigen
                if (imageResId != null) {
                    deviceImageView.setImageResource(imageResId);
                    deviceImageView.setVisibility(View.VISIBLE);
                } else {
                    deviceImageView.setImageResource(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Keine Aktion erforderlich, wenn nichts ausgewählt ist
            }
        });
    }

    private void updateModelSpinner(String type) {
        // Adapter für den Modell-Spinner erstellen
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modelsMap.get(type));
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(modelAdapter);
    }

    private void setupModelSpinner() {
        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedModel = (String) parent.getItemAtPosition(position);
                if (!selectedModel.equals("Bitte Modell auswählen")) {
                    // Kommentar-Button anzeigen, wenn ein gültiges Modell ausgewählt ist
                    commentButton.setVisibility(View.VISIBLE);

                    // Nur modellbezogene Bewertung anzeigen, Basisanweisungen ausblenden
                    String modelReview = reviewsMap.get(selectedModel);
                    if (modelReview != null) {
                        currentHtmlContent = "<b>Informationen zur Modell:</b><br>" + modelReview;
                        instructionsTextView.setText(Html.fromHtml(currentHtmlContent, Html.FROM_HTML_MODE_LEGACY));
                    }

                    // Bild des Geräts ausblenden, wenn ein spezifisches Modell ausgewählt ist
                    deviceImageView.setVisibility(View.GONE);
                } else {
                    // Kommentar-Button ausblenden, wenn ein ungültiges Modell ausgewählt ist
                    commentButton.setVisibility(View.GONE);

                    // Basisanweisungen und Bild anzeigen, wenn kein spezifisches Modell ausgewählt ist
                    String baseInstruction = instructionsMap.get(selectedType);
                    if (baseInstruction != null) {
                        currentHtmlContent = baseInstruction;
                        instructionsTextView.setText(Html.fromHtml(currentHtmlContent, Html.FROM_HTML_MODE_LEGACY));
                    }
                    deviceImageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Keine Aktion erforderlich, wenn nichts ausgewählt ist
            }
        });
    }

    private void setupButtons() {
        // Kommentar-Button konfigurieren
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog(); // Kommentar-Dialog anzeigen
            }
        });
    }

    private void showCommentDialog() {
        // Benutzerdefinierte Ansicht für den Dialog erstellen
        final View dialogView = getLayoutInflater().inflate(R.layout.activity_comment_dialog, null);
        final EditText commentInput = dialogView.findViewById(R.id.commentInput);

        // Dialog erstellen und konfigurieren
        new AlertDialog.Builder(this)
                .setTitle("Ihre Empfehlungen zur Klärung")
                .setView(dialogView)
                .setPositiveButton("Senden", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String comment = commentInput.getText().toString();
                        // Kommentarverarbeitung hier hinzufügen
                        instructionsTextView.setText(Html.fromHtml(currentHtmlContent, Html.FROM_HTML_MODE_LEGACY));
                    }
                })
                .setNegativeButton("Abbrechen", null)
                .show();
    }

    public void goBack(View v) {
        // Verwenden Sie finish(), um die aktuelle Aktivität zu schließen
        finish();
    }
}
