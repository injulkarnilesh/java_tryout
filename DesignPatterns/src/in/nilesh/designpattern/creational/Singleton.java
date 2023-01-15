package in.nilesh.designpattern.creational;

/*
Singleton is a creational design pattern that lets you ensure 
that a class has only one instance, while providing a global access point to this instance.
*/
class SingletonClassClassic {
	private static SingletonClassClassic instance;
	
	private SingletonClassClassic(){}
	
	public static SingletonClassClassic getInstance(){
		if(instance == null){
			instance = new SingletonClassClassic();
		}
		return instance;
	}
}

class SingletonClassNew {
	private static SingletonClassNew instance = new SingletonClassNew();
	
	private SingletonClassNew(){}

	public static SingletonClassNew getInstance(){
		return instance;
	}
	
}

public class Singleton {
		public static void main(String[] args) {
			SingletonClassClassic sc1 = SingletonClassClassic.getInstance();
			SingletonClassClassic sc2 = SingletonClassClassic.getInstance();
			SingletonClassClassic sc3 = SingletonClassClassic.getInstance();
			System.out.println("sc1 == sc2 : " + (sc1 == sc2));
			System.out.println("sc2 == sc3 : " + (sc2 == sc3));
			
			SingletonClassNew scn1 = SingletonClassNew.getInstance();
			SingletonClassNew scn2 = SingletonClassNew.getInstance();
			SingletonClassNew scn3 = SingletonClassNew.getInstance();
			System.out.println("scn1 == scn2 : " + (scn1 == scn2));
			System.out.println("scn2 == scn3 : " + (scn2 == scn3));
		}
}
