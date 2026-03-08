import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class gradesystem {

	static Scanner scan = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static String[] studentNames;
	static double[] studentGrades;
	static int numStudents;
	static int passed;

	public static void main(String[] args){
		print("Enter number of students: ");
		numStudents = scan.nextInt(); scan.nextLine();

		studentNames = new String[numStudents];
		studentGrades = new double[numStudents];

		InputStudentGrades();

		WriteToFile(studentNames, studentGrades);
	}

	static void InputStudentGrades(){
		System.out.println();
		for(int i = 0; i < numStudents; i++){
			print("Enter name of student " + (i + 1) + ": ");
			studentNames[i] = scan.nextLine();
			print("Enter grade of " + studentNames[i] + ": ");
			studentGrades[i] = scan.nextDouble(); scan.nextLine();
		}
	}

	static void WriteToFile(String[] studentNames, double[] studentGrades){
		sb.append("Student Grades:\n");
		for(int i = 0; i < numStudents; i++){
			sb.append(studentNames[i]).append(": ").append(studentGrades[i]).append("\n");
		}
		passed = checkPassed(studentGrades);
		int failed = numStudents - passed;
		sb.append("\nPassed: ").append(passed);
		sb.append("\nFailed: ").append(failed);


		try(FileWriter writer = new FileWriter("GradesReport.txt")){
			writer.write(sb.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static int checkPassed(double[] studentGrades){
		int counter = 0;
		for(double grade : studentGrades){
			if(grade >= 75) counter++;
		}
		return counter;
	}

	static void print(Object txt){
		System.out.print(txt);
	}
}