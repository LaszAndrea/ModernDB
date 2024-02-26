package hu.domparse.DJ7PNE;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadDJ7PNE {

	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		File xmlFile = new File("XMLDJ7PNE.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("nyomtatogep");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);
			System.out.println("\nCurrent element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String termeknev = elem.getAttribute("termeknev");

				Node node1 = elem.getElementsByTagName("folyometer").item(0);
				String fm = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("tipus").item(0);
				String tip = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("festekfelhasznalas").item(0);
				String ff = node3.getTextContent();

				System.out.println("Termeknev: " + termeknev);
				System.out.println("Felhaszn�lt foly�m�ter: " + fm);
				System.out.println("G�p t�pusa: " + tip);
				System.out.println("Nyomtat�s sor�n felhaszn�lt fest�kmennyis�g: " + ff);

				if (nList.item(i).getChildNodes().getLength() > 5) {
					int db = 0;
					Node node4 = elem.getElementsByTagName("raszterhenger").item(0);
					while (node4 != null) {
						node4 = elem.getElementsByTagName("raszterhenger").item(db);
						if (node4 != null) {
							String rh = node4.getTextContent();
							System.out.println("Nyomtat�shoz felhaszn�lt raszterhenger: " + rh);
						}
						db++;
					}

				}

			}
		}

		NodeList fList = doc.getElementsByTagName("festek");

		for (int i = 0; i < fList.getLength(); i++) {

			Node fNode = fList.item(i);
			System.out.println("\nCurrent element: " + fNode.getNodeName());

			if (fNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) fNode;
				String psz = elem.getAttribute("pantonszam");
				String felh = elem.getAttribute("felhasznalas");

				Node node1 = elem.getElementsByTagName("lejarat").item(0);
				String lej = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("raktaron").item(0);
				String rak = node2.getTextContent();

				System.out.println("Pantonszam: " + psz);
				System.out.println("Melyik term�khez van felhaszn�lva: " + felh);
				System.out.println("Lej�rati d�tum: " + lej);
				System.out.println("Mennyis�g ami a rakt�ron van: " + rak);

				if (fList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("gyarto").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("gyarto").item(db);
						if (node3 != null) {
							String gy = node3.getTextContent();
							System.out.println("A fest�ket gy�rtja: " + gy);
						}
						db++;
					}

				}

			}
		}
		
		NodeList tList = doc.getElementsByTagName("tasakrendeles");

		for (int i = 0; i < tList.getLength(); i++) {

			Node tNode = tList.item(i);
			System.out.println("\nCurrent element: " + tNode.getNodeName());

			if (tNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) tNode;
				String tid = elem.getAttribute("tasakID");

				Node node1 = elem.getElementsByTagName("termeknev").item(0);
				String tnev = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("mennyiseg").item(0);
				String menny = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("papirmin").item(0);
				String pm = node3.getTextContent();

				System.out.println("Tasak egyedi azonos�t�ja: " + tid);
				System.out.println("Rendelt tasak neve: " + tnev);
				System.out.println("Rendelt mennyis�g: " + menny);
				System.out.println("Gy�rt�shoz haszn�lt pap�r min�s�ge: " + pm);

			}
		}
		
		NodeList vList = doc.getElementsByTagName("vevo");

		for (int i = 0; i < vList.getLength(); i++) {

			Node vNode = vList.item(i);
			System.out.println("\nCurrent element: " + vNode.getNodeName());

			if (vNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) vNode;
				String asz = elem.getAttribute("adoszam");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("szarmazas").item(0);
				String szarm = node2.getTextContent();
				
				System.out.println("Vev� ad�sz�ma: " + asz);
				System.out.println("Rendel� neve: " + nev);
				System.out.println("Rendelt mennyis�g: " + szarm);
				
				if (fList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("telephely").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("telephely").item(db);
						if (node3 != null) {
							Node n = elem.getElementsByTagName("isz").item(db);
							String isz = n.getTextContent();
							System.out.println("Telephely ir�ny�t�sz�ma: " + isz);
							Node n2 = elem.getElementsByTagName("utca").item(db);
							String u = n2.getTextContent();
							System.out.println("Telephely utc�ja: " + u);
						}
						db++;
					}

				}
				
			}
		}
		
		NodeList gyList = doc.getElementsByTagName("gyar");

		for (int i = 0; i < gyList.getLength(); i++) {

			Node gyNode = gyList.item(i);
			System.out.println("\nCurrent element: " + gyNode.getNodeName());

			if (gyNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) gyNode;
				String gyasz = elem.getAttribute("gyAdoszam");
				String vgy = elem.getAttribute("vGy");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("telephely").item(0);
				String telep = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("alapitas").item(0);
				String ap = node3.getTextContent();

				System.out.println("Gy�r ad�sz�ma: " + gyasz);
				System.out.println("Vev� ad�sz�ma, akinek a rendel�se itt k�sz�l: " + vgy);
				System.out.println("A gy�r neve: " + nev);
				System.out.println("A telephely v�rosa: " + telep);
				System.out.println("A gy�r alap�t�s�nak az �ve: " + ap);

			}
		}
		
		NodeList lList = doc.getElementsByTagName("logo");

		for (int i = 0; i < lList.getLength(); i++) {

			Node lNode = lList.item(i);
			System.out.println("\nCurrent element: " + lNode.getNodeName());

			if (lNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) lNode;
				String lid = elem.getAttribute("logoId");
				String gyl = elem.getAttribute("gyL");

				Node node1 = elem.getElementsByTagName("elkeszites").item(0);
				String elk = node1.getTextContent();
				
				Node node3 = elem.getElementsByTagName("tervezo").item(0);
				String terv = node3.getTextContent();

				System.out.println("A logo egyedi azonos�t�ja: " + lid);
				System.out.println("A gy�r ad�sz�ma, aminek a log�ja ez: " + gyl);
				System.out.println("A logo elk�sz�l�s�nek id�pontja: " + elk);
				
				if (lList.item(i).getChildNodes().getLength() > 2) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("szinek").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("szinek").item(db);
						if (node2 != null) {
							String szin = node2.getTextContent();
							System.out.println("A logohoz felhaszn�lt sz�n: " + szin);
						}
						db++;
					}

				}
				
				System.out.println("A logo tervez�je: " + terv);

			}
		}
		
		NodeList tnList = doc.getElementsByTagName("tulajdonos");

		for (int i = 0; i < tnList.getLength(); i++) {
			
			Node tnNode = tnList.item(i);
			System.out.println("\nCurrent element: " + tnNode.getNodeName());

			if (tnNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) tnNode;
				String tsz = elem.getAttribute("tajszam");
				String tgy = elem.getAttribute("tGy");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("szuletes").item(0);
				String szul = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("szerzodese").item(0);
				String szer = node3.getTextContent();

				System.out.println("Tulajdonos tajsz�ma: " + tsz);
				System.out.println("A gy�r ad�sz�ma, aminek a tulaja az ember: " + tgy);
				System.out.println("A tulajdonos neve: " + nev);
				System.out.println("A tulajdonos sz�let�se: " + szul);
				System.out.println("Mi�ta szerz�d�tt ide: " + szer);

			}
		}

		NodeList szerList = doc.getElementsByTagName("szerzodes");

		for (int i = 0; i < szerList.getLength(); i++) {
			
			Node szerNode = szerList.item(i);
			System.out.println("\nCurrent element: " + szerNode.getNodeName());

			if (szerNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) szerNode;
								
				String tnev = elem.getAttribute("tNev");
				String tid = elem.getAttribute("tasakID");
				String asz = elem.getAttribute("adoszam");
												
				Node node1 = elem.getElementsByTagName("rendeles").item(0);
				String rend = node1.getTextContent();

				System.out.println("Megrendelt term�k neve: " + tnev);
				System.out.println("A tasak ID-ja, ami meg lett rendelve: " + tid);
				System.out.println("A vev� ad�sz�ma, aki rendelt: " + asz);
				System.out.println("Rendel�s id�pontja: " + rend);
				
			}
		}

	}

}
