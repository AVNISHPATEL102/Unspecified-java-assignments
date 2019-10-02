package P1;
import java.util.NoSuchElementException;

public class CourseList implements Cloneable
{

	
	public class CourseNode 
	{
		
		
		
		private Course course;
		private CourseNode next;

		public CourseNode() 
		{
			course = null;
			next = null;
		}
		public CourseNode(Course c, CourseNode node) 
		{
			course = c;
			next = node;
		}
		public CourseNode(CourseNode otherNode) 
		{
			this.course = otherNode.course.clone();
			this.next = otherNode.next; 
			
		}
		
		public CourseNode clone() 
		{
			return new CourseNode(this);
		}
		
		public Course getCourse() 
		{
			return new Course(course, course.getCourseID());
		}

		

		

	}

	
	
	
	
	
	
	
	
	private CourseNode head;
	private int size;

	
	public CourseList()
	{
		head = null;
		size = 0;
	}

	
	public CourseList(CourseList otherList)
	{
		if (otherList == null)
			throw new NullPointerException();
		
		if (otherList.head == null)
			head = null;
		
		else
		{
			CourseNode temp = otherList.head; 
			CourseNode newHead = new CourseNode(new Course((temp.course), temp.course.getCourseID()), null);
			
			CourseNode end = newHead; 
			temp = temp.next; 
			while (temp != null) 
			{
				end.next = new CourseNode(new Course((temp.course), temp.course.getCourseID()), null);
				
				end = end.next; 
				temp = temp.next; 
				
			}
			head = newHead; 
			size = otherList.size;
		}
	}

	public int getSize()
	{
		return size;
	}

	public boolean addToStart(Course c) 
	{
		if (c == null)
			return false;
		head = new CourseNode(c, head);
		size++;
		return true;
	}

	public boolean insertAtIndex(Course c, int index) throws NoSuchElementException
	{
		if (c == null)
		{
			System.out.println("Null");
			return false;
		}
		
		if (index < 0 || index > (size - 1))
			throw new NoSuchElementException();
		
		if (index == 0)
			addToStart(c);
		
		else 
		{
			CourseNode indexedNode = head;
			for (int i = 0; i < (index - 1); i++) 
			{
				indexedNode = indexedNode.next;
			}
			indexedNode.next = new CourseNode(c, indexedNode.next); 
		}
		size++;
		return true;
	}

	
	
	public boolean deleteFromIndex(int index) throws NoSuchElementException 
	{
		if (index < 0 || index > (size - 1))
			throw new NoSuchElementException();
		
		if (index == 0) 
		{
			head = head.next;
		} 
		else 
		{
			CourseNode indexedNode = head;
			for (int i = 0; i < (index - 1); i++)
			{
				indexedNode = indexedNode.next;
			}
			indexedNode.next = indexedNode.next.next;
		}
		size--;
		return true;
	}

	
	
	public boolean deleteFromStart() 
	{
		if (size == 0)
			return false;
		
		else 
		{
			head = head.next;
			size--;
			return true;
		}
	}

	
	public CourseNode find(String c) 
	{
		CourseNode t = head;
		for (int i = 0; i < size; i++) 
		{
			if (t.course.getCourseID().equals(c))
			
				return t;
			t = t.next;
		}
		return null;
	}

	public boolean findSimilarCourse(Course c) 
	{
		CourseNode w = head;
		
		
		for (int i = 0; i < size; i++) 
		{
			if (w.course.equals(c))
				return true;
			w = w.next;
		}
		return false;
	}

	
	
	public void replaceAtIndex(Course c, int index) 
	{
		if (index < 0 || index > (size - 1))
			return;
		
		if (index == 0) 
		{
			head = new CourseNode(c.clone(), head.next);
		} 
		else 
		{
			CourseNode indexedNode = head;
			
			for (int i = 0; i < (index - 1); i++)
			{
				indexedNode = indexedNode.next;
			}
			indexedNode.next = new CourseNode(new Course(c, c.getCourseID()), indexedNode.next.next);
		}
	}

	public boolean contains(String c)
	{
		CourseNode t = head;

		for (int i = 0; i < size; i++) 
		{
			if (t.course.getCourseID().equals(c))
				return true;
			t = t.next;
		}
		return false;
	}

	public boolean equals(Syllabus s)
	{
		CourseList sList = new CourseList(s.getList());
		CourseNode test = head;
		
		
		for (int i = 0; i < size; i++) 
		{
			if (!sList.findSimilarCourse(test.getCourse()))
			{
				return false;
			}
			test = test.next;
		}
		return true;
	}

	public void display()
	{
		System.out.print("This course list has");
		if (size == 0) 
		{
			System.out.println(" no contents");
		} 
		else 
		{
			System.out.print(" " + (int) size + " courses. They are : \n");
			CourseNode m = head;
			
			
			for (int i = 0; i < size; i++)
			{
				System.out.println("\n " + (i + 1) + ":\n" + m.course.toString());
				m = m.next;
			}
		}
	}


}
