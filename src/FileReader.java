
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Scanner;

public class FileReader {
	
	String filename;
	Comparator comparator;
	DefaultMap<Character, Student> hashMap = new MyHashMap<Character,Student>();
	
	public FileReader(String name) {
		// Constructor for the filereader
		this.filename = name;

		// defining a comparator
		Comparator<Student> comparator = new Comparator<Student>() {
		@Override
		public int compare(Student student1, Student student2) {
			if (student1.marks > student2.marks) {
				return 1;
			}

			else if (student1.marks < student2.marks) {
				return -1;
			}

			return 0;
			}
		};

		this.comparator = comparator;

		// mb change this? character seems weird
		this.hashMap = new MyHashMap<Character,Student>(10, comparator);
	}
	
	public void createHeap() {
        //Method to read input.txt using FileInputStream and putting the student entries to the hashMap
		try {
			String currentLine = null;

			// if this doesn't work, add absolute path. also change br to fileinputstream
			// "F:\\Documents\\GitHub\\Exam3-starter\\src\\input.txt"
			InputStream f = new FileInputStream(this.filename);
			Scanner sc = new Scanner(f);

			while (sc.hasNextLine()) {
				currentLine = sc.nextLine();

				// student's specs
				String[] studentSpecs = currentLine.split(",");
				String studentName = studentSpecs[0];
				char studentSection = studentSpecs[1].charAt(0);
				double studentScore = Double.parseDouble(studentSpecs[2]);

				// adding the student
				Student newStudent = new Student(studentName, studentSection, studentScore);
				this.hashMap.put(studentSection, newStudent);

			 }

			// System.out.println(hashMap.size());

			sc.close(); 

		}
		
		catch (Exception e) {
            e.printStackTrace();
		}


	}
	
	public Student getMaxOfSection(char section) {
		//Method that returns a maximum scoring student's record given the section
		try {
			hashMap.get(section);
		} 
		
		catch (Exception e) {
			return null;
		}
		
		return this.hashMap.get(section);

	}
	


}
