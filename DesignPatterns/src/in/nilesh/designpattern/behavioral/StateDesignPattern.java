package in.nilesh.designpattern.behavioral;

/*
State is a behavioral design pattern that lets an object 
alter its behavior when its internal state changes. It appears as if the object changed its class.
*/
enum FAN_SPEED {
	OFF, SLOW, MEDIUM, HURRICANE
};

/*
 * State Context
 */
class CeilingFanRegulator {
	private FAN_SPEED fanSpeed;
	private CeilingFanState state;

	public CeilingFanRegulator() {
		fanSpeed = FAN_SPEED.OFF;
		state = FanOff.getInstance();
	}

	public void rotateClockWise() {
		this.state.rotateClockWise(this);
	}

	public void rotateAntiClockWise() {
		this.state.rotateAntiClockWise(this);
	}

	public void setFanSpeed(FAN_SPEED newSpeed) {
		fanSpeed = newSpeed;
	}

	public void showFanSpeed() {
		System.out.println("***** Fan is @ " + fanSpeed + " *******");
	}

	public void setState(CeilingFanState newState) {
		this.state = newState;
	}

}

/*
 * State Interface
 */
interface CeilingFanState {
	public void rotateClockWise(CeilingFanRegulator regulator);

	public void rotateAntiClockWise(CeilingFanRegulator regulator);
}

/*
 * Concrete State
 */
class FanOff implements CeilingFanState {

	static CeilingFanState fanOff = new FanOff();

	private FanOff() {
	}

	static CeilingFanState getInstance() {
		return fanOff;
	}

	public void rotateClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ OFF to SLOW");
		regulator.setFanSpeed(FAN_SPEED.SLOW);
		regulator.setState(SlowFan.getInstance());
	}

	public void rotateAntiClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ OFF to HURRICAN");
		regulator.setFanSpeed(FAN_SPEED.HURRICANE);
		regulator.setState(HurricaneFan.getInstance());
	}

}

/*
 * Concrete State
 */
class SlowFan implements CeilingFanState {

	static CeilingFanState slowFan = new SlowFan();

	private SlowFan() {
	}

	static CeilingFanState getInstance() {
		return slowFan;
	}

	public void rotateClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ SLOW to MEDIUM");
		regulator.setFanSpeed(FAN_SPEED.MEDIUM);
		regulator.setState(MediumFan.getInstance());
	}

	public void rotateAntiClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ SLOW to OFF");
		regulator.setFanSpeed(FAN_SPEED.OFF);
		regulator.setState(FanOff.getInstance());
	}

}

/*
 * Concrete State
 */
class MediumFan implements CeilingFanState {

	static CeilingFanState mediumFan = new MediumFan();

	private MediumFan() {
	}

	static CeilingFanState getInstance() {
		return mediumFan;
	}

	public void rotateClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ MEDIUM to HARRICANE");
		regulator.setFanSpeed(FAN_SPEED.HURRICANE);
		regulator.setState(HurricaneFan.getInstance());
	}

	public void rotateAntiClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ MEDIUM to SLOW");
		regulator.setFanSpeed(FAN_SPEED.SLOW);
		regulator.setState(SlowFan.getInstance());
	}

}

/*
 * Concrete State
 */
class HurricaneFan implements CeilingFanState {

	static CeilingFanState harricaneFan = new HurricaneFan();

	private HurricaneFan() {
	}

	static CeilingFanState getInstance() {
		return harricaneFan;
	}

	public void rotateClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ HARRICANE to OFF");
		regulator.setFanSpeed(FAN_SPEED.OFF);
		regulator.setState(FanOff.getInstance());
	}

	public void rotateAntiClockWise(CeilingFanRegulator regulator) {
		System.out.println("Fan @ HARRICANE to MEDIUM");
		regulator.setFanSpeed(FAN_SPEED.MEDIUM);
		regulator.setState(MediumFan.getInstance());
	}

}

public class StateDesignPattern {

	public static void main(String[] args) {
		CeilingFanRegulator regulator = new CeilingFanRegulator();
		regulator.rotateClockWise();
		regulator.showFanSpeed();

		regulator.rotateClockWise();
		regulator.showFanSpeed();

		regulator.rotateAntiClockWise();
		regulator.showFanSpeed();

		regulator.rotateClockWise();
		regulator.showFanSpeed();

		regulator.rotateClockWise();
		regulator.showFanSpeed();

		regulator.rotateClockWise();
		regulator.showFanSpeed();
	}

}
