package il.ac.shenkar;

public class Coin {
	
	private String name, unit, currencyCode, country, rate, change ;

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getCountry() {
		return country;
	}

	public String getRate() {
		return rate;
	}

	public String getChange() {
		return change;
	}

	public Coin(String name, String unit, String currencyCode, String country,
			String rate, String change) {
		super();
		this.name = name;
		this.unit = unit;
		this.currencyCode = currencyCode;
		this.country = country;
		this.rate = rate;
		this.change = change;
	}

	@Override
	public String toString() {
		return "Coin [name=" + name + ", unit=" + unit + ", currencyCode="
				+ currencyCode + ", country=" + country + ", rate=" + rate
				+ ", change=" + change + "]";
	}
}
