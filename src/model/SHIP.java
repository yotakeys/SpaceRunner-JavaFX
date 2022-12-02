package model;

public enum SHIP {
	BLUE("view/resources/shipchooser/blue_ship.png", "view/resources/shipchooser/blue_life.png"),
	GREEN("view/resources/shipchooser/green_ship.png", "view/resources/shipchooser/green_life.png" ),
	ORANGE("view/resources/shipchooser/orange_ship.png", "view/resources/shipchooser/orange_life.png"),
	RED("view/resources/shipchooser/red_ship.png", "view/resources/shipchooser/red_life.png");
	
	private String urlShip;
	private String urlLife;
	
	private SHIP(String urlShip, String urlLife) {
		this.urlShip = urlShip;
		this.urlLife = urlLife;
	}
	
	public String getUrl() {
		return urlShip;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
}
