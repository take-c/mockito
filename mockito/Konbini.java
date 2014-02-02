package mockito;

public class Konbini {

	private BuySake buySake;
	
	public String buyCannedBeer() {
		
		Integer age = 25;
		if (buySake.checkAge(age)) {
			return "OK";
		} else {
			return "NG";
		}
	}
}
