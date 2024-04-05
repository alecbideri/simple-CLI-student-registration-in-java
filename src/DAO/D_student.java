package DAO;
import java.sql.* ;
import model.Student;
public class D_student {
        
        String url = "jdbc:mysql://localhost:3306/data1";
        String username = "root";
        String password = "Alecb200@.";
        
  public String insert(Student obj){
               
       try {
           
           // creating connection
            Connection con = DriverManager.getConnection(url , username , password);
            
            String sql_insert = "insert into students(id , name , age)values(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql_insert);
            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.setInt(3, obj.getAge());
           int check = st.executeUpdate();
            
           if (check > 0){
               return "Data inserted successfully";
           }else{
              return "Data not inserted";
           }
            
       }catch (Exception e){
           e.printStackTrace();
           return "Server error";
    }
      
  }
  
  public String update(Student obj){
      try {
          
           // creating connection
          Connection con = DriverManager.getConnection(url , username , password);
          
          //update the database 
          
          String sql_update = "update students set name = ? , age = ? where id = ?" ;
          PreparedStatement st = con.prepareStatement(sql_update);
          
          // updating the fields from the students class respctively
          
          
          st.setInt(3, obj.getId());
          st.setString(1, obj.getName());
          st.setInt(2, obj.getAge());
          
          int check  = st.executeUpdate();
          
          // checking and verifying the successfullness execution of the code 
          
          if (check > 0){
              return "Data update successfully";
          }else{
              return "Data not updated";
          }
          
      }catch(Exception e){               
       
          e.printStackTrace();
          return "Server error";
      }
  }
  
  public String delete(Student obj){
      try{
          // creating connection
          Connection con = DriverManager.getConnection(url , username , password);
          
          //deleting the databse 
          
          String sql_delete = "delete from students where id = ? " ;
          PreparedStatement st = con.prepareStatement(sql_delete);
          
          // deleting the fields from the students class respctively
                    
          st.setInt(1, obj.getId());

           int check  = st.executeUpdate();
          
          // checking and verifying the successfullness execution of the code 
          
          if (check > 0){
              return "Data deleted successfully.";
          }else{
              return "Data not deleted.";
          }
          
      }catch(Exception e){               
       
          e.printStackTrace();
          return "Server error";
      } 
  }
  
  public Student get_by_id(int student_id){
      try {
          // creating connection 
          Connection con = DriverManager.getConnection(url, username, password);
          
          // selecting all data from the database 
          
          String sql_select = "select * from students where id = ?";
          PreparedStatement st = con.prepareStatement(sql_select);
          st.setInt(1, student_id);
          ResultSet rs = st.executeQuery();
          
          if(rs.next()){
              Student student = new Student();
              student.setId(rs.getInt("id"));
              student.setName(rs.getString("name"));
              student.setAge(rs.getInt("age"));
              return student ;
          }else{
              return null ;
          }
          
      }catch (Exception e){
       e.printStackTrace();
       return null ;
      }
  }
  
  public void select_all(){
      try{
          
          //connecting to the database
          Connection con = DriverManager.getConnection(url , username , password);
          
          // selecting all students from the database 
          
          String sql_select_all = "select * from students";
          PreparedStatement st = con.prepareStatement(sql_select_all);
          ResultSet rs = st.executeQuery();
          
          while(rs.next()){
              int id = rs.getInt("id");
              String name = rs.getString("name");
              int age = rs.getInt("age");
              
              // print the info to the console 
              
              System.out.println("Student ID :" + id);
              System.out.println("Student Name:" + name);
              System.out.println("Student Age:" + age);
              System.out.println("___________________");
              
          }
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  public void select_entity(int student_id){
        try {
          // creating connection 
          Connection con = DriverManager.getConnection(url, username, password);
          
          // selecting all data from the database 
          
          String sql_select = "select * from students where id = ?";
          PreparedStatement st = con.prepareStatement(sql_select);
          st.setInt(1, student_id);
          ResultSet rs = st.executeQuery();
          
          if(rs.next()){
               int id = rs.getInt("id");
              String name = rs.getString("name");
              int age = rs.getInt("age");
              
              // print the info to the console 
              
              System.out.println("Student ID :" + id);
              System.out.println("Student Name:" + name);
              System.out.println("Student Age:" + age);
              System.out.println("___________________");
                  
          }  
  }catch(Exception e){
          e.printStackTrace();
      }
  
    }
  
}

