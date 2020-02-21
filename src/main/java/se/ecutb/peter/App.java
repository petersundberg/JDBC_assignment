package se.ecutb.peter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws SQLException {
        CityDaoJDBC dao = new CityDaoJDBC();

        //Find by id -----------------------------------------------
//      City city = dao.findById(1);
//      System.out.println(city);


        //Find by code -----------------------------------------------
//        String myCode = "NLD";
//        dao.findByCode(myCode).forEach(System.out::println);


        //Find by name -----------------------------------------------
//      String myName = "London";
//      dao.findByName(myName).forEach(System.out::println);


        //Find all -----------------------------------------------
//      dao.findAll().forEach(System.out::println);


        //Add City -----------------------------------------------
//        String addedCity = "Karlskrona";
//        dao.add(new City(addedCity,"SWE","Blekinge",65000));
//        dao.findAll().forEach(System.out::println); //dao.findByName(addedCity).forEach(System.out::println);


        //Update City by Id -----------------------------------------------
//      City city = dao.findById(1);
//      city.setName("Karlskrona");   //id 1 = Kabul
//      dao.update(city);
//      System.out.println(city);


        //Delete city -----------------------------------------------
//        City deleteCity = dao.findById(4080);
//        System.out.println(deleteCity);
//        System.out.println("Affected rows: " + dao.delete(deleteCity));



    }

}
