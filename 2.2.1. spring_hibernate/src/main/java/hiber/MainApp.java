package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User us1 = new User("User1", "Lastname1", "user1@mail.ru");
      User us2 = new User("User2", "Lastname2", "user2@mail.ru");
      User us3 = new User("User3", "Lastname3", "user3@mail.ru");
      User us4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car1 = new Car("Lada", 11);
      Car car2 = new Car("Murassia", 9);
      Car car3 = new Car("Kavasaki", 1);
      Car car4 = new Car("Ford", 8);
      car1.setUser(us1);
      us1.setCar(car1);
      car2.setUser(us2);
      us2.setCar(car2);
      car3.setUser(us3);
      us3.setCar(car3);
      car4.setUser(us4);
      us4.setCar(car4);

      userService.add(us1);
      userService.add(us2);
      userService.add(us3);
      userService.add(us4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      User usWithCar = userService.returnCarOwner("Ford", 8);
      System.out.println("Id = "+usWithCar.getId());
      System.out.println("First Name = "+usWithCar.getFirstName());
      System.out.println("Last Name = "+usWithCar.getLastName());
      System.out.println("Email = "+usWithCar.getEmail());
      System.out.println();

      context.close();
   }
}
