package coins;

public class coin {
	private int coinID;
	private String name;

	public coin(int coinID, String name) {
		this.setCoinID(coinID);
		this.setName(name);

	}

	public int getCoinID() {
		return coinID;
	}

	public void setCoinID(int coinID) {
		this.coinID = coinID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
