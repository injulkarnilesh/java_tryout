package in.nilesh.designpattern.behavioral;

/*
 * Receiver
 */
class StockTrade{
	public void buyStock(){
		System.out.println("Buying the stock");
	}	
	public void sellStock(){
		System.out.println("Selling the stock");
	}
}
/*
 * Command Interface
 */
interface Command{
	public void execute();
}

/*
 * Concrete Command
 */
class BuyStockCommand implements Command{
	private StockTrade stock;
	
	public BuyStockCommand(StockTrade stock) {
		this.stock = stock;
	}
	
	@Override
	public void execute() {
		stock.buyStock();
	}	
}

/*
 * Concrete Command
 */
class SellStockCommand implements Command{
	private StockTrade stock;
	
	public SellStockCommand(StockTrade stock) {
		this.stock = stock;
	}
	
	@Override
	public void execute() {
		stock.sellStock();
	}	
}

/*
 * Invoker
 */
class Agent{
		
	public void placeOrder(Command stockCommand){
		stockCommand.execute();
	}	
} 
/*
 * Client
 */
public class CommandDesignPattern {
	public static void main(String[] args) {
		
		Agent agent = new Agent();
		StockTrade stockTrade = new StockTrade();
		
		Command buyCommand = new BuyStockCommand(stockTrade);
		Command sellCommand = new SellStockCommand(stockTrade);
		Command buyAgainCommand = new BuyStockCommand(stockTrade);
		
		agent.placeOrder(buyCommand);
		agent.placeOrder(sellCommand);
		agent.placeOrder(buyAgainCommand);
	}
}
