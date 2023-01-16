package in.nilesh.designpattern.behavioral;

/*
Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. 
Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
*/

class PurchaseRequest {
	private double cost;
	private String productName;

	public PurchaseRequest(double cost, String name) {
		this.cost = cost;
		this.productName = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}

abstract class PurchaseApprovalAuthority {
	protected PurchaseApprovalAuthority approvalAuthority;

	public void setNextApprovalAuthority(
			PurchaseApprovalAuthority approvalAuthority) {
		this.approvalAuthority = approvalAuthority;
	}

	public void checkApproval(PurchaseRequest request) {
		if (!approvePurchase(request)) {
			if (approvalAuthority != null) {
				approvalAuthority.checkApproval(request);
			} else {
				System.out.println(request.getProductName() + " @ "
						+ request.getCost() + " CAN NOT BE APPROVED");
			}
		}
	}

	public abstract boolean approvePurchase(PurchaseRequest request);
}

class ManagerApproval extends PurchaseApprovalAuthority {
	@Override
	public boolean approvePurchase(PurchaseRequest request) {
		if (request.getCost() <= 1000) {
			System.out.println("MANAGER APPROVES THE REQUEST TO PURCHASE "
					+ request.getProductName() + " @ " + request.getCost());
			return true;
		}
		return false;
	}
}

class DepartmentHeadApproval extends PurchaseApprovalAuthority {
	@Override
	public boolean approvePurchase(PurchaseRequest request) {
		if (request.getCost() <= 30000) {
			System.out
					.println("DEPARTMENT HEAD APPROVES THE REQUEST TO PURCHASE "
							+ request.getProductName()
							+ " @ "
							+ request.getCost());
			return true;
		}
		return false;
	}
}

class CompanytHeadApproval extends PurchaseApprovalAuthority {
	@Override
	public boolean approvePurchase(PurchaseRequest request) {
		if (request.getCost() <= 100000) {
			System.out.println("COMPANY HEAD APPROVES THE REQUEST TO PURCHASE "
					+ request.getProductName() + " @ " + request.getCost());
			return true;
		}
		return false;
	}
}

public class ChainOfResponsibility {
	public static void main(String[] args) {
		PurchaseApprovalAuthority manager = new ManagerApproval();
		PurchaseApprovalAuthority deapartmentHead = new DepartmentHeadApproval();
		PurchaseApprovalAuthority companyHead = new CompanytHeadApproval();

		manager.setNextApprovalAuthority(deapartmentHead);
		deapartmentHead.setNextApprovalAuthority(companyHead);

		PurchaseRequest request1 = new PurchaseRequest(400, "Pendrive");
		PurchaseRequest request2 = new PurchaseRequest(8000, "Hard Disk");
		PurchaseRequest request3 = new PurchaseRequest(45000, "HP Laptop");
		PurchaseRequest request4 = new PurchaseRequest(145000, "Macbook");

		manager.checkApproval(request1);
		manager.checkApproval(request2);
		manager.checkApproval(request3);
		manager.checkApproval(request4);

	}
}
