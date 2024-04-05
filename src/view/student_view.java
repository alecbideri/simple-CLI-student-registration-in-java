package view;
import model.Student ;
import DAO.D_student;
import java.util.Scanner ;
public class student_view {
    public static void main(String[] args) {
       
        Student student = new Student();
        D_student student_dao = new D_student ();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do{
                    
        System.out.println("Welcome to student management system");
        System.out.println("____________________________________");
        System.out.println("1.Record data.");
        System.out.println("2.update Data.");
        System.out.println("3.Retrieve all students.");
        System.out.println("4.Retrieve one student.");
        System.out.println("5.Delete data");
        System.out.println("6.Exit the program.");
        System.out.print("Make a choice(1-6):");
        choice = sc.nextInt();
        
            switch(choice){
                case 1: 
                    record_data(sc  , student , student_dao);
                     break ;
                case 2:
                    update_data(sc , student , student_dao);
                    break ;
                case 3 :
                     select_all(student_dao);   
                 break ;
                case 4:
                    select_entity(sc , student_dao);                   
                    break ;                   
                case 5: 
                    delete_data(sc, student , student_dao);
                    break ;
                case 6 :
                    System.out.println("Thanks for using our program");
                    break ;
                default :
                    System.out.println("Invalid choice, Please choose from 1-6");
                    break;
            }
        }while(choice!=6);           
    }
    
    
    // Section of methods used to manipulate the data respectively in database from(record to delete).
    
    private static void record_data(Scanner sc , Student student , D_student student_dao){
           
     System.out.print("Enter ID:");
     int id = sc.nextInt();
     System.out.println("Enter a name:");
     String name = sc.next();
     System.out.println("Ente age:");
     int age = sc.nextInt();
     student.setId(id);
     student.setName(name);
     student.setAge(age);
     String feedback = student_dao.insert(student);
     System.out.println(feedback);     
     
    }
    
  private static void update_data(Scanner sc , Student student , D_student student_dao){
    System.out.print("Enter Id for a student to update:");
   int update_id = sc.nextInt();
   Student existing_student = student_dao.get_by_id(update_id);

   if(existing_student != null){
       System.out.print("Enter 'Y' if you want to update the name:");
       String update_name = sc.next();
       System.out.println("");
       System.out.print("Enter 'Y' if you want to also update the age:");
       String update_age = sc.next();
       System.out.println("");

       if(update_name.equals("Y")){
           System.out.print("Enter a new name:");
           String new_name = sc.next();
           existing_student.setName(new_name);
       }

       if(update_age.equals("Y")){
           System.out.print("Enter a new age:");
           int new_age = sc.nextInt();
           existing_student.setAge(new_age);
       }
       
       String feedback = student_dao.update(existing_student);
       System.out.println(feedback);
   }else{
       System.out.println("No student found with the provided ID.");
   }
  }
  
  private static void select_all(D_student student_dao){
       
    System.out.println("All data for students");
    System.out.println("=====================");
    student_dao.select_all();

   }
    
    private static void select_entity(Scanner sc, D_student student_dao) {
        System.out.print("Enter ID of the student to retrieve: ");
        int studentId = sc.nextInt();
        Student student = student_dao.get_by_id(studentId);
        if (student != null) {
            System.out.println("______________________________");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Age: " + student.getAge());
            System.out.println("_______________________________");
        } else {
            System.out.println("No student found with the provided ID.");
     }
        
    }
    
   private static void delete_data(Scanner sc , Student student , D_student student_dao){
    
    System.out.println("Enter a Id for a student to delete:");
    int delete_id  = sc.nextInt();
    if(delete_id > 0){
       student.setId(delete_id);
       String feedback_delete = student_dao.delete(student);
        System.out.println(feedback_delete);

    }else{
       System.out.println("That Id doesn't exist!");
    }
    
 }
    
}
