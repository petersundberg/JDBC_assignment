package se.ecutb.peter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CityDao {

    public City findById(int id);

    public List<City> findByCode(String code);

    public List<City> findByName(String name);

    List<City> findAll();

    public City add (City city);

    public City update(City city);

    public int delete(City city);


}
