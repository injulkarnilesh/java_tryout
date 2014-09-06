package in.nilesh.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

class StockOffer {
	private String colleagueCode;
	private int stockShares;
	private String stockSymbol;

	public StockOffer(String colleagueCode, int stockShares, String stockSymbol) {
		this.colleagueCode = colleagueCode;
		this.stockShares = stockShares;
		this.stockSymbol = stockSymbol;
	}

	public String getColleagueCode() {
		return colleagueCode;
	}

	public int getStockShares() {
		return stockShares;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public boolean matchesOffer(StockOffer newOffer){
		if(this.stockSymbol.equals(newOffer.getStockSymbol()) && this.stockShares == newOffer.stockShares){
			return true;
		}else{
			return false;
		}
		
	}

}

/*
 * Abstract Colleague
 */
abstract class BrokerColleague {
	private String brokerColleagueCode;
	private Mediator mediator;

	public BrokerColleague(String colleagueCode, Mediator mediator) {
		this.brokerColleagueCode = colleagueCode;
		this.mediator = mediator;
	}

	public String getBrokerColleagueCode() {
		return this.brokerColleagueCode;
	}

	public void sellOffer(String stock, int shares) {
		StockOffer sellOffer = new StockOffer(this.brokerColleagueCode, shares, stock);
		mediator.sellOffer(sellOffer);
	}

	public void buyOffer(String stock, int shares) {
		StockOffer buyOffer = new StockOffer(this.brokerColleagueCode, shares, stock);
		mediator.buyOffer(buyOffer);
	}
}

/*
 * Mediator Interface
 */
interface Mediator {
	public void sellOffer(StockOffer sellOffer);

	public void buyOffer(StockOffer buyOffer);

	public void addColleague(BrokerColleague colleague);
}

/*
 * Concrete Mediator
 */
class StockMediator implements Mediator {
	private List<BrokerColleague> colleagues = new ArrayList<>();
	private List<StockOffer> sellOffers = new ArrayList<>();
	private List<StockOffer> buyOffers = new ArrayList<>();

	public void addColleague(BrokerColleague colleague) {
		colleagues.add(colleague);
		System.out.println("The STOCK-MEDIATOR welcomes "
				+ colleague.getBrokerColleagueCode());
	}

	public void buyOffer(StockOffer buyOffer) {
		boolean offerBought = false;
			
		java.util.Iterator<StockOffer> sellOfferIterator = sellOffers.iterator();
		while (sellOfferIterator.hasNext()) {
			StockOffer sellOffer = sellOfferIterator.next();
			
			if (sellOffer.matchesOffer(buyOffer)) {
				offerBought = true;
				sellOfferIterator.remove();
				System.out.println("Buy offer " + buyOffer.getStockShares() + " of " + buyOffer.getStockSymbol()
						+ " sold to " + sellOffer.getColleagueCode());
				break;
			}
		}

		if (!offerBought) {
			buyOffers.add(buyOffer);
			System.out.println("Buy offer " + buyOffer.getStockShares() + " of " + buyOffer.getStockSymbol()
					+ " added to inventory");
		}

	}

	public void sellOffer(StockOffer sellOffer) {

		boolean offerSold = false;

		java.util.Iterator<StockOffer> buylOfferIterator = buyOffers.iterator();
		while (buylOfferIterator.hasNext()) {
			StockOffer buyOffer = buylOfferIterator.next();
			if (buyOffer.matchesOffer(sellOffer)) {
				offerSold = true;
				buylOfferIterator.remove();
				System.out.println("Sell offer " + sellOffer.getStockShares() + " of " + sellOffer.getStockSymbol()
						+ " bought by " + buyOffer.getColleagueCode());
				break;
			}
		}

		if (!offerSold) {
			sellOffers.add(sellOffer);
			System.out.println("Sell offer " + sellOffer.getStockShares() + " of " + sellOffer.getStockSymbol()
					+ " added to inventory");
		}
	}
}

/*
 * Concrete Colleague
 */
class ShareKhanStockBroker extends BrokerColleague {
	public ShareKhanStockBroker(String brokerCode, Mediator mediator) {
		super(brokerCode, mediator);
	}
}

/*
 * Concrete Colleague
 */
class MotilalOswalStockBroker extends BrokerColleague {
	public MotilalOswalStockBroker(String brokerCode, Mediator mediator) {
		super(brokerCode, mediator);
	}
}

/*
 * Concrete Colleague
 */
class KotakSecurityStockBroker extends BrokerColleague {
	public KotakSecurityStockBroker(String brokerCode, Mediator mediator) {
		super(brokerCode, mediator);
	}
}

public class MediatorDesignPattern {

	public static void main(String[] args) {
		final String ABC_STOCKS = "ABC_STOCKS";
		final String XYZ_STOCKS = "XYZ_STOCKS";
		final String QW_STOCKS = "QW_STOCKS";

		Mediator stockMediator = new StockMediator();

		BrokerColleague shareKhanStockBroker = new ShareKhanStockBroker("SHARE",
				stockMediator);
		BrokerColleague motilalOswalStockBroker = new MotilalOswalStockBroker(
				"MOTILAL", stockMediator);
		BrokerColleague kotakSecurityStockBroker = new KotakSecurityStockBroker(
				"KOTAK", stockMediator);

		stockMediator.addColleague(shareKhanStockBroker);
		stockMediator.addColleague(motilalOswalStockBroker);
		stockMediator.addColleague(kotakSecurityStockBroker);

		shareKhanStockBroker.buyOffer(ABC_STOCKS, 100);
		kotakSecurityStockBroker.buyOffer(QW_STOCKS, 200);
		motilalOswalStockBroker.sellOffer(ABC_STOCKS, 100);
		kotakSecurityStockBroker.sellOffer(ABC_STOCKS, 100);
		kotakSecurityStockBroker.sellOffer(QW_STOCKS, 500);
		kotakSecurityStockBroker.sellOffer(XYZ_STOCKS, 200);
		motilalOswalStockBroker.buyOffer(QW_STOCKS, 500);

	}
}
