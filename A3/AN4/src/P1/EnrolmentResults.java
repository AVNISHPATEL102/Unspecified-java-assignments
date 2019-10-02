package P1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class EnrolmentResults {

	private static void pause(Scanner userIn)
	{
		System.out.println("\nAdvance code? Hit enter");
		userIn.nextLine();
	}

	private static void request(Syllabus List, Scanner userIn)
	{
		System.out.println("Please enter the name of the request file you'd like to submit");
		Scanner sc = new Scanner(System.in);
		String thisLine;
		StringBuilder sb = null;
		String answer = sc.nextLine();
		try
		{
			sc = new Scanner(new FileInputStream(answer));
			sb = new StringBuilder();
			while (sc.hasNextLine()) 
			{
				thisLine = sc.nextLine();
				sb.append(thisLine);
				sb.append("\n");
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Did not find requested Request file. Remember *.txt !");
			System.exit(0);
		} 
		finally 
		{
			sc.close();
		}
		String Request = sb.toString();
		StringTokenizer st = new StringTokenizer(Request, "\n");
		ArrayList<String> Finished = new ArrayList<String>();
		ArrayList<String> Requested = new ArrayList<String>();
		boolean flip = false;
		while (st.hasMoreTokens()) 
		{
			String temp = st.nextToken();
			if (temp.equals("Requested")) 
			{
				flip = true;
			}
			if (flip == false && temp.equals("Finished") == false)
			{
				Finished.add(temp);
			} else if (temp.equals("Requested") == false && temp.equals("Finished") == false)
				Requested.add(temp);
		}
		if (Requested.size() == 0)
			System.out.println("This request form has no requested courses...");
		else 
		{
			for (int i = 0; i < Requested.size(); i++) 
			{
				CourseList tempList = List.getList();
				Course tempCourse = tempList.find(Requested.get(i)).getCourse();
				System.out.println(tempCourse);
				boolean coreqs = false;
				boolean prereqs = false;
				if (Finished.contains(tempCourse.getPreReqID()) || tempCourse.getPreReqID() == "")
				{
					prereqs = true;
				}
				if (Finished.contains(tempCourse.getCoReqID()) || Requested.contains(tempCourse.getCoReqID())
						|| tempCourse.getCoReqID() == "")
				{
					coreqs = true;
				}
				if (coreqs == true && prereqs == true)
				{
					if (tempCourse.getCoReqID() == "" && tempCourse.getPreReqID() == "")
						System.out.println("Sucessful Enrollment into " + tempCourse.getCourseID());
					else if (tempCourse.getCoReqID() == "" && tempCourse.getPreReqID() != "") 
					{
						System.out.println("Sucessful Enrollment into " + tempCourse.getCourseID()
								+ " due to enrollment in coreq: " + tempCourse.getCoReqID());
					}
					if (tempCourse.getCoReqID() != "" && tempCourse.getPreReqID() == "")
					{
						System.out.println("Sucessful Enrollment into " + tempCourse.getCourseID()
								+ " due to previous completion of prequisite: " + tempCourse.getPreReqID());
					}
					if (tempCourse.getCoReqID() != "" && tempCourse.getPreReqID() != "")
					{
						System.out.println("Sucessful Enrollment into " + tempCourse.getCourseID()
								+ " due to previous completion of prequisite: " + tempCourse.getPreReqID()
								+ " and current enrollment in the coreq " + tempCourse.getCoReqID());
					}
				} 
				else
					System.out.println("Student can't enroll in " + tempCourse.getCourseID()
							+ " as he/she doesn't have sufficient background needed");
			}
		}

		
		System.out.println("================================================");
		pause(userIn);
		System.out.println("PART D : ");
		System.out.println("================================================");
		boolean flag = true;
		int iterations = 0;
		while (flag) {

			System.out.println(
					"Please enter the ID (not the name) of the course you'd like to search the request file for"
							+ "\n   (Enter \"stop\" to terminate this section of searching)"
							+ "\n   ~You have searched for courses a running total of " + iterations + " times.");

			String search = userIn.nextLine();
			if (search.equalsIgnoreCase("stop"))
				flag = false;
			else 
			{
				int countindex = 0;
				int iterationcount = 0;
				;
				boolean flag2 = false;
				countindex = Finished.size() + Requested.size();
				for (int i = 0; i < countindex; i++)
				{
					if (flag2 == false)
						iterationcount++;
					if (i < Finished.size()) 
					{
						if (Finished.get(i).equals(search))
							flag2 = true;
					} else {
						if (Requested.get(i - Finished.size()).equals(search))
							flag2 = true;
					}
				}
				if (flag2 == true) 
				{
					System.out.println("The request file contains the course you're looking for");
					System.out.println("It took " + iterationcount + " iterations to get this result");
				} 
				else 
				{
					System.out.println("The request file doesn't contains the course you're looking for");
					System.out.println("It took " + iterationcount + " iterations to get this result");
				}
				System.out.println("================================================");
			}
			iterations++;
		}
	}

	public static void main(String[] args)
	{

		Scanner userIn = new Scanner(System.in);

		
		
		System.out.println("Creating two course lists and displaying contents : ");
		CourseList List1 = new CourseList();
		CourseList List2 = new CourseList();

		List1.display();
		List2.display();
		

		pause(userIn);

		
		
		System.out.println("Opening the Syllabus.txt file, and reading its contents line by line");
		System.out.println("   Note that Syllabus has a CourseList attribute and initializes that particular list");

		Syllabus syllabus = new Syllabus("Syllabus.txt");
		System.out.println("Syllabus's list equals the syllabus is " + syllabus.getList().equals(syllabus));

		pause(userIn);

		
		System.out.println();
		System.out.println();

		request(syllabus, userIn);

		
		System.out.println();
		System.out.println();

		System.out.println("Making a new course list as follows, into List1:");
		Course c1 = new Course("ARTS446", "It's the arts", 4, "ARTS244", "ORGN412");
		Course c2 = new Course("TECH336", "Bits and Bites", 3, "TECH222", "");
		Course c3 = new Course("ARTS412", "Art of origami", 4, "ARTS244", "ARTS446");
		Course c4 = new Course("ARTS512", "Advanced art", 6, "ARTS446", "");
		Course c5 = new Course("ARTS532", "Advanced art", 6, "ARTS446", "");

		System.out.println("List1 equals the syllabus is " + List1.equals(syllabus));
		List1.addToStart(c1);
		System.out.println("List1 equals the syllabus is " + List1.equals(syllabus));
		List1.addToStart(c2);
		List1.addToStart(c3);

		List1.display();

		System.out.println("\n Replacing c3 at index 2");
		List1.replaceAtIndex(c3, 2);
		List1.display();

		pause(userIn);

		System.out.println("Copying this courselist into List2");
		List2 = new CourseList(List1);
		List2.display();
		System.out.println("Contents of List2 (compare this to the previous)");

		pause(userIn);

		System.out.println("\nAdding c4 to list at index 2....");
		List1.insertAtIndex(c4, 2);
		List1.display();

		pause(userIn);

		System.out.println("ARTS412 is found within this course list List1 is : " + List1.contains("ARTS412"));
		System.out.println("PIE345 is found within this course list List1 is : " + List1.contains("PIE345"));

		

		pause(userIn);

		System.out.println("Deleting element at index 3");
		List1.deleteFromIndex(3);
		List1.display();

		pause(userIn);

		System.out.println("Deleting element from start");
		List1.deleteFromStart();
		List1.display();

		pause(userIn);

		System.out.println("Deleting element from start, AGAIN");
		List1.deleteFromStart();
		List1.display();

		pause(userIn);

		System.out.println("Deleting element from start, AGAIN");
		List1.deleteFromStart();
		List1.display();

		pause(userIn);

		System.out.println("c1.equals(c2) is " + c1.equals(c2));
		System.out.println("c4.equals(c5) is " + c4.equals(c5));
		System.out.println("Cloning c1 to ec6");
		Course c6 = c1.clone();
		System.out.println("c1 is : " + c1);
		System.out.println("ec6 (the clone of c1) is : " + c6);

		System.out.println("================================================");

		System.out.println("================================================");
		System.out.println("~=================~END OF A4~==================~");
		System.out.println("================================================");

		userIn.close();
	}
}