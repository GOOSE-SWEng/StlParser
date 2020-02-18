
public class Facet {
	CartesianCoordinate normal;
	CartesianCoordinate v1;
	CartesianCoordinate v2;
	CartesianCoordinate v3;
	
	public Facet(CartesianCoordinate normal, CartesianCoordinate v1, CartesianCoordinate v2, CartesianCoordinate v3) {
		this.normal = normal;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;	
	}

	public CartesianCoordinate getNormal() {
		return normal;
	}

	public void setNormal(CartesianCoordinate normal) {
		this.normal = normal;
	}

	public CartesianCoordinate getV1() {
		return v1;
	}

	public void setV1(CartesianCoordinate v1) {
		this.v1 = v1;
	}

	public CartesianCoordinate getV2() {
		return v2;
	}

	public void setV2(CartesianCoordinate v2) {
		this.v2 = v2;
	}

	public CartesianCoordinate getV3() {
		return v3;
	}

	public void setV3(CartesianCoordinate v3) {
		this.v3 = v3;
	}
}
