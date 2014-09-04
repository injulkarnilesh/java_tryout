package in.nilesh.designpattern.behavioral;

import java.util.Stack;

/*
 * Context holding current status of input and output
 */
class Context {
	private double currentOutput;
	private String inputTokens[];
	private int tokenIndex;
	private Stack<Double> stack;
	
	public Context(String inputString) {
		this.inputTokens = inputString.split("\\s");
		this.tokenIndex = 0;
		this.stack = new Stack<Double>();
	}
			
	public String getNextTokenAndAdvanceIndex(){		
		return inputTokens[tokenIndex++];
	}
	
	public void pushToStack(double item){
		stack.push(item);
	}
	
	public double popFromStack(){
		return stack.pop();
	}
	
	public String getCurrentToken(){
		return inputTokens[tokenIndex];
	}
	
	public String[] getTokens(){
		return inputTokens;
	}
	
	public void advanceIndex(){
		tokenIndex++;
	}
	
	public double getCurrentOutput() {
		return currentOutput;
	}

	public void setCurrentOutput(double currentOutput) {
		this.currentOutput = currentOutput;
	}
	
}
/*
 * Abstract Expression
 */
interface Expression {
	public void interprete(Context context);
}

/*
 * Concrete Expression
 */
class Operand implements Expression{
	@Override
	public void interprete(Context context) {
		String operand = context.getNextTokenAndAdvanceIndex();
		context.pushToStack(Double.parseDouble(operand));
	}
}

/*
 * Concrete Expression 
 * Abstract class operator could be extracted from all the Operators + Template
 */
class PlusOperator implements Expression {
	@Override
	public void interprete(Context context) {
		double rightOperand = context.popFromStack();
		double leftOperand = context.popFromStack();
		double output = leftOperand + rightOperand;
		context.advanceIndex();
		context.pushToStack(output);
		context.setCurrentOutput(output);
	}
}

/*
 * Concrete Expression
 */
class MinusOperator implements Expression {
	@Override
	public void interprete(Context context) {
		double rightOperand = context.popFromStack();
		double leftOperand = context.popFromStack();
		double output = leftOperand - rightOperand;
		context.advanceIndex();
		context.pushToStack(output);
		context.setCurrentOutput(output);
	}
}

/*
 * Concrete Expression
 */
class MultiplyOperator implements Expression {
	@Override
	public void interprete(Context context) {
		double rightOperand = context.popFromStack();
		double leftOperand = context.popFromStack();
		double output = leftOperand * rightOperand;
		context.advanceIndex();
		context.pushToStack(output); 
		context.setCurrentOutput(output);
	}
}

class DivideOperator implements Expression {
	@Override
	public void interprete(Context context) {
		double rightOperand = context.popFromStack();
		double leftOperand = context.popFromStack();
		double output = leftOperand / rightOperand;
		context.advanceIndex();
		context.pushToStack(output); 
		context.setCurrentOutput(output);
	}
}

class ExpressionFactory {
	public static Expression getOperandOrOperator(String token){
		Expression exp;
		
		switch (token) {
			case "+":
				exp = new PlusOperator();
				break;
			case "-":
				exp = new MinusOperator();
				break;
			case "*":
				exp = new MultiplyOperator();
				break;	
			case "/":
				exp = new DivideOperator();
				break;	
			default:
				exp = new Operand();
				break;
		}
		
		return exp;
	}
}

public class Interpreter {
	public static void main(String[] args) {
		String inputString = "4 6 2 / 1 + * 6 -";
		Context context = new Context(inputString);
		for(String token : context.getTokens()){
			Expression exp = ExpressionFactory.getOperandOrOperator(token);
			exp.interprete(context);
		}
		System.out.println(inputString + " = " + context.getCurrentOutput());
	}
}
