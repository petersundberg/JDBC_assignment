package se.ecutb.peter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static se.ecutb.peter.Database.getConnection;

public class CityDaoJDBC implements CityDao{

    private static final String INSERT = "INSERT INTO city(name, countrycode, district, population) VALUES(?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM city WHERE id = ?";
    private static final String FIND_BY_CODE = "SELECT * FROM city WHERE countrycode = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM city WHERE name = ?";
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String UPDATE_CITY = "UPDATE city SET name = ?, countrycode = ?, district = ?, population = ? WHERE id = ?";
    private static final String DELETE_CITY = "DELETE FROM city WHERE id = ?";


    public City add(City city){
        Connection connection= null;
        PreparedStatement statement = null;
        ResultSet keySet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,city.getName());
            statement.setString(2,city.getCountryCode());
            statement.setString(3,city.getDistrict());
            statement.setObject(4,city.getPopulation());
            statement.execute();
            keySet = statement.getGeneratedKeys();
            while (keySet.next()){
                city = new City(
                        keySet.getInt(1),
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (keySet != null){
                    keySet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return city;
    }


    @Override
    public City findById(int id) {
        City city = null;
        try(
                Connection connection = getConnection();
                PreparedStatement statement = create_findById(connection, id);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countrycode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    private PreparedStatement create_findById(Connection connection, int id) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            return statement;
        }


    public List<City> findByCode(String code){
        List<City> cities = new ArrayList<>();
        try(
                Connection connection = getConnection();
                PreparedStatement statement = create_findByCode(connection, code);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                cities.add(new City(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getLong("population")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    private PreparedStatement create_findByCode(Connection connection, String code) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(FIND_BY_CODE);
        statement.setString(1, code);
        return statement;
    }

    public List<City> findByName(String name){
        List<City> cities = new ArrayList<>();
        try(
                Connection connection = getConnection();
                PreparedStatement statement = create_findByName(connection, name);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                cities.add(new City(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getLong("population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    private PreparedStatement create_findByName(Connection connection, String name) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(FIND_BY_NAME);
        statement.setString(1, name);
        return statement;
    }


    public List<City> findAll(){
        List<City> cities = new ArrayList<>();
        try(
                Connection connection = getConnection();
                PreparedStatement statement = create_findAll(connection);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                cities.add(new City(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countryCode"),
                        resultSet.getString("district"),
                        resultSet.getLong("population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    private PreparedStatement create_findAll(Connection connection) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(FIND_ALL);
        return statement;
    }


    public City update(City city){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CITY)
        ) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setObject(4, city.getPopulation());
            statement.setInt(5, city.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    private City createCityFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("countrycode"),
                resultSet.getString("district"),
                resultSet.getLong("population")
        );
    }


    public int delete(City city) {
        int deletedRows = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CITY);
        ){
            statement.setInt(1,city.getId());
            deletedRows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return deletedRows;
    }


}
