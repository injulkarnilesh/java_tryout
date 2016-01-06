package in.algorithm.course.part.one.week.one;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PerculationProblemTest {
	
	@Test
	public void testPerculation() throws Exception {
		PerculationProblem problem = new PerculationProblem(5);
		Assert.assertFalse(problem.doesPerculate());
		problem.open(0, 0);
		problem.open(0, 1);
		problem.open(1, 1);
		problem.open(1, 2);
		Assert.assertFalse(problem.doesPerculate());
		problem.open(1, 3);
		problem.open(2, 3);
		problem.open(3, 3);
		problem.open(3, 3);
		problem.open(3, 2);
		problem.open(4, 2);
		Assert.assertTrue(problem.doesPerculate());
		System.out.println(problem.toString());		
	}
}
