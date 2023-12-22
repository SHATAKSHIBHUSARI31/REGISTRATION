import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course
{
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) 
    {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student 
{
    String studentID;
    String name;
    List<Course> registeredCourses;

    public Student(String studentID, String name) 
    {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem 
{
    public static void main(String[] args) 
    {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        courses.add(new Course("CSCI101", "Introduction to Programming", "Basic programming concepts", 30, "MWF 9:00 AM"));
        courses.add(new Course("MATH201", "Calculus I", "Differential and integral calculus", 25, "TTH 10:30 AM"));
        courses.add(new Course("PHYS101", "Physics Fundamentals", "Basic principles of physics", 20, "MWF 1:00 PM"));

        Scanner sc = new Scanner(System.in);

        while (true) 
	    {
            System.out.println("Student Course Registration System");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) 
	        {
                case 1:
                    displayAvailableCourses(courses);
                    break;
                case 2:
                    registerForCourse(students, courses, sc);
                    break;
                case 3:
                    dropCourse(students, sc);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayAvailableCourses(List<Course> courses) 
    {
        System.out.println("Available Courses:");
        for (Course course : courses) 
	    {
            System.out.println(course.code + " - " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity + " Schedule: " + course.schedule);
            System.out.println("------------------------------------------------------");
        }
    }

    public static void registerForCourse(List<Student> students, List<Course> courses, Scanner sc) 
    {
        System.out.print("Enter your student ID: ");
        String studentID = sc.next();

        Student student = findStudentByID(students, studentID);
        if (student == null) 
	    {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Available Courses:");
        displayAvailableCourses(courses);

        System.out.print("Enter the course code you want to register for: ");
        String courseCode = sc.nextLine();

        Course course = findCourseByCode(courses, courseCode);
        if (course == null) 
	    {
            System.out.println("Course not found.");
            return;
        }

        if (student.registeredCourses.contains(course)) 
	    {
            System.out.println("You are already registered for this course.");
        } 
 	    else if (course.capacity <= 0) 
	    {
            System.out.println("Course is full. You cannot register.");
        } 
	    else 
	    {
            student.registeredCourses.add(course);
            course.capacity--;
            System.out.println("Registration successful.");
        }
    }

    public static void dropCourse(List<Student> students, Scanner sc) 
    {
        System.out.print("Enter your student ID: ");
        String studentID = sc.nextLine();

        Student student = findStudentByID(students, studentID);
        if (student == null) 
	    {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Your Registered Courses:");
        if (student.registeredCourses.isEmpty()) 
	    {
            System.out.println("You are not registered for any courses.");
        } 
	    else 
 	    {
            for (Course course : student.registeredCourses) 
	        {
                System.out.println(course.code + " - " + course.title);
            }
            System.out.print("Enter the course code you want to drop: ");
            String courseCode = sc.nextLine();

            Course course = findCourseByCode(student.registeredCourses, courseCode);
            if (course == null) 
	        {
                System.out.println("You are not registered for this course.");
            } 
	        else 
	        {
                student.registeredCourses.remove(course);
                course.capacity++;
                System.out.println("Course dropped successfully.");
            }
        }
    }

    public static Student findStudentByID(List<Student> students, String studentID) 
    {
        for (Student student : students) 
	    {
            if (student.studentID.equals(studentID)) 
	        {
                return student;
            }
        }
        return null;
    }

    public static Course findCourseByCode(List<Course> courses, String courseCode) 
    {
        for (Course course : courses) 
	    {
            if (course.code.equals(courseCode)) 
	        {
                return course;
            }
        }
        return null;
    }
}