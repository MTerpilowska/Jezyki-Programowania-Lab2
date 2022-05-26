/* Autor: Maja Terpi³owska, 259176
 * Grupa: PN/TN 15:15
 * Data utworzenia: pazdziernik 2021
 * Lab1  
 * Plik: MapConsoleApp.java
 */

package ui;

import java.util.Arrays;

import data.Map;
import data.MapException;
import data.MapType;

public class MapConsoleApp {
	private static final String GREETING_MESSAGE = 
			"Program Lab1 - wersja konsolowa\n" + 
	        "Autor: Maja Terpi³owska, 259176\n" +
			"Grupa: PN/TN 15:15\n" +
			"Data:  paŸdziernik 2021 r\n";

	private static final String MENU = 
			"    M E N U   G £ Ó W N E  \n" +
			"1 - Podaj dane nowej mapy \n" +
			"2 - Modyfikuj dane mapy      \n" +
			"3 - Usuñ dane mapy     \n" +
			"4 - Zapisz dane do pliku   \n" +
			"5 - Wczytaj dane z pliku   \n" +
			"0 - Zakoñcz program        \n";	
	
	private static final String CHANGE_MENU = 
			"   Co zmieniæ?     \n" + 
	        "1 - Nazwê mapy           \n" + 
			"2 - Autora mapy      \n" + 
	        "3 - Skalê mapy  \n" + 
			"4 - Szerokoœæ mapy    \n" +
			"5 - Wysokoœæ mapy    \n" +
			"6 - Rodzaj mapy    \n" +
	        "0 - Powrót do menu g³ównego\n";
	
	private static ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		MapConsoleApp application = new MapConsoleApp();
		application.runMainLoop();
	} 

	private Map currentMap = null;
	
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentMap();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					currentMap = createNewMap();
					break;
				case 2:
					if (currentMap == null) throw new MapException("¯adna mapa nie zosta³a utworzona.");
					changeMapData(currentMap);
					break;
				case 3:
					currentMap = null;
					UI.printInfoMessage("Dane aktualnej mapy zosta³y usuniête");
					break;
				case 4: { 
					String file_name = UI.enterString("Podaj nazwê pliku: ");
					Map.printToFile(file_name, currentMap);
					UI.printInfoMessage("Dane aktualnej mapy zosta³y zapisane do pliku " + file_name);
				}
					break;
				case 5: {
					String file_name = UI.enterString("Podaj nazwê pliku: ");
					currentMap = Map.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej mapy zosta³y wczytane z pliku " + file_name);
				}
					break;
				case 0:
					UI.printInfoMessage("\nProgram zakoñczy³ dzia³anie!");
					System.exit(0);
				}
			} catch (MapException e) { 
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	void showCurrentMap() {
		showMap(currentMap);
	} 
	
	static void showMap(Map mapa) {
		StringBuilder sb = new StringBuilder();
		
		if (mapa != null) {
			sb.append("Aktualna mapa: \n");
			sb.append( "      Nazwa: " + mapa.getNazwa() + "\n" );
			sb.append( "   Autor: " + mapa.getAutor() + "\n" );
			sb.append( "  Skala: 1:" + mapa.getSkala() + "\n" );
			sb.append( "   Wymiary: " + mapa.getWymiarX() + "x" + mapa.getWymiarY() + "\n" );
			sb.append( "Rodzaj: " + mapa.getRodzaj() + "\n");
		} else
			sb.append( "Brak danych mapy" + "\n" );
		UI.printMessage( sb.toString() );
	}
	
	static Map createNewMap(){
		String nazwa_mapy = UI.enterString("Podaj nazwê: ");
		String autor_mapy = UI.enterString("Podaj autora: ");
		String skala_mapy = UI.enterString("Podaj skalê: 1:");
		String wymiar_x_mapy = UI.enterString("Podaj szerokoœæ: ");
		String wymiar_y_mapy = UI.enterString("Podaj wysokoœæ: ");
		UI.printMessage("Istniej¹ce rodzaje map:" + Arrays.deepToString(MapType.values()));
		String rodzaj_mapy = UI.enterString("Podaj rodzaj mapy: ");
		Map mapa;
		try { 
			mapa = new Map(nazwa_mapy, autor_mapy);
			mapa.setSkala(skala_mapy);
			mapa.setWymiarX(wymiar_x_mapy);
			mapa.setWymiarY(wymiar_y_mapy);
			mapa.setRodzaj(rodzaj_mapy);
		} catch (MapException e) {    
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return mapa;
	}
	
	static void changeMapData(Map mapa)
	{
		while (true) {
			UI.clearConsole();
			showMap(mapa);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					mapa.setNazwa(UI.enterString("Podaj nazwê: "));
					break;
				case 2:
					mapa.setAutor(UI.enterString("Podaj autora: "));
					break;
				case 3:
					mapa.setSkala(UI.enterString("Podaj skalê: 1:"));
					break;
				case 4:
					mapa.setWymiarX(UI.enterString("Podaj szerokoœæ: "));
					break;
				case 5:
					mapa.setWymiarY(UI.enterString("Podaj wysokoœæ: "));
					break;
				case 6:
					UI.printMessage("Istniej¹ce rodzaje map:" + Arrays.deepToString(MapType.values()));
					mapa.setRodzaj(UI.enterString("Podaj rodzaj mapy: "));
					break;
				case 0: return;
				}
			} catch (MapException e) {     
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	

}
