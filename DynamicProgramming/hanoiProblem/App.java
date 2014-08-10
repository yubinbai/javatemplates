package hanoi.hanoiProblem;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

//		RecursiveHanoi.RecursiveSteps(3, "A", "B", "C");
		IterativeTwo i2 = new IterativeTwo();
		System.out.println(i2.solutionSteps(3));

	}
}
