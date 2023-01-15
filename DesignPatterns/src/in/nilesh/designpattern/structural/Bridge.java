package in.nilesh.designpattern.structural;

/*
Bridge is a structural design pattern 
that lets you split a large class or a set of closely related classes into two separate hierarchies
1. abstraction and 2. implementation
which can be developed independently of each other.

It avoids creating n*m classes, by separating n cobinations into 1 hierarchy and m combinations into another.
Then you refer to one hierarchy from another through interface.

The "abstraction" defines the interface for the "control" part of the two class hierarchies. 
It maintains a reference to an object of the "implementation" hierarchy and  "delegates" all of the real work to this object.
*/

abstract class Remote {

	protected EntertainmentDevice device;

	public abstract void fiveKeyPressed();

	public abstract void sixKeyPressed();

	public abstract void sevenKeyPressed();

	public abstract void eightKeyPressed();

	public abstract void nineKeyPressed();
}

class RemoteImpl extends Remote {

	public RemoteImpl(EntertainmentDevice entertainmentDevice) {
		this.device = entertainmentDevice;
	}

	public void fiveKeyPressed() {
		device.fiveKeyPressed();
	}

	public void sixKeyPressed() {
		device.sixKeyPressed();
	}

	public void sevenKeyPressed() {
		device.sevenKeyPressed();
	}

	public void eightKeyPressed() {
		device.eightKeyPressed();
	}

	public void nineKeyPressed() {
		device.nineKeyPressed();
	}
}

abstract class EntertainmentDevice {
	public void fiveKeyPressed() {
		System.out.println("Voulme --");
	}

	public void sixKeyPressed() {
		System.out.println("Voulme ++");
	}

	public abstract void sevenKeyPressed();

	public abstract void eightKeyPressed();

	public abstract void nineKeyPressed();
}

class TVDevice extends EntertainmentDevice {

	public void sevenKeyPressed() {
		System.out.println("Channel --");
	}

	public void eightKeyPressed() {
		System.out.println("Channel ++");
	}

	public void nineKeyPressed() {
		System.out.println("TV Muted");
	}
}

class DVDDevice extends EntertainmentDevice {

	public void sevenKeyPressed() {
		System.out.println("Preious Program");
	}

	public void eightKeyPressed() {
		System.out.println("Next Program");
	}

	public void nineKeyPressed() {
		System.out.println("Program Paused");
	}
}

public class Bridge {

	public static void main(String[] args) {
		System.out.println("*************** REMOTE FOR DVD ******************");
		Remote dvdRemote = new RemoteImpl(new DVDDevice());
		dvdRemote.fiveKeyPressed();
		dvdRemote.sixKeyPressed();
		dvdRemote.sevenKeyPressed();
		dvdRemote.eightKeyPressed();
		dvdRemote.nineKeyPressed();

		System.out.println("*************** REMOTE FOR TV ******************");
		Remote tvRemote = new RemoteImpl(new TVDevice());
		tvRemote.fiveKeyPressed();
		tvRemote.sixKeyPressed();
		tvRemote.sevenKeyPressed();
		tvRemote.eightKeyPressed();
		tvRemote.nineKeyPressed();

	}
}
