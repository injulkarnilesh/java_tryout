package in.nilesh.designpattern.structural;

/*
Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object. 
A proxy controls access to the original object, 
allowing you to perform something either before or after the request gets through to the original object.
*/

interface InternetAccess {
	public void grantInternetAddress();
}

class RealInternetAccess implements InternetAccess{

	private String employeeId;
	
	public RealInternetAccess(String empId) {
		this.employeeId = empId;
	}
	
	@Override
	public void grantInternetAddress() {
		System.out.println("Internet access is granted for employee " + employeeId);
	}
}

class ProxyInternetAccess implements InternetAccess{
	private RealInternetAccess realInternetAccess;
	private String employeeId;
	public ProxyInternetAccess(String empId) {
		this.employeeId = empId;		
	}
	
	@Override
	public void grantInternetAddress() {
		if(isEmployeeIdAllowedInternetAccess(employeeId)){
			realInternetAccess = new RealInternetAccess(employeeId);
			realInternetAccess.grantInternetAddress();
		}else{
			System.out.println("Internet access is not granted for " + employeeId);
		}
		
	}
	
	private boolean isEmployeeIdAllowedInternetAccess(String empId){
		if(empId.contains("n") || empId.contains("N")){
			return true;
		}
		return false;
	}
}

public class Proxy {
	public static void main(String[] args) {
		InternetAccess internetAccess = new ProxyInternetAccess("nilesh00135");
		internetAccess.grantInternetAddress();
		
		InternetAccess internetAccess1 = new ProxyInternetAccess("rahul007");
		internetAccess1.grantInternetAddress();
	}
}
