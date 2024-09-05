package practice;

public class canadaLights implements lights {

	public static void main(String[] args) {

		lights Li = new canadaLights();
		Li.redlight();
		Li.yellowLight();
		 Li =new australianLights();
		 Li.yellowLight();
		 Li.
}

	@Override
	public void redlight() {
		System.out.println("red light");
	}

	@Override
	public void yellowLight() {
		System.out.println("yellow light");
		
	}

}
