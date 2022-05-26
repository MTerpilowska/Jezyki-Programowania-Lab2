/* Autor: Maja Terpi³owska, 259176
 * Grupa: PN/TN 15:15
 * Data utworzenia: pazdziernik 2021
 * Lab1  
 * Plik: MapType.java
 */

package data;

public enum MapType {
	NIEZNANA("-------"), 
	TURYSTYCZNA("Turystyczna"), 
	TOPOGRAFICZNA("Topograficzna"), 
	GOSPODARCZA("Gospodarcza"), 
	POLITYCZNA("Polityczna");
	
	String rodzajMapy;

	private MapType(String rodzaj_mapy) {
		rodzajMapy = rodzaj_mapy;
	}
	
	@Override
	public String toString() {
		return rodzajMapy;
	}
}