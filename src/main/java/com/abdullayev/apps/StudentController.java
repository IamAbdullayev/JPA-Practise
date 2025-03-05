package com.abdullayev.apps;


import com.abdullayev.apps.dao.CrudJPA;
import com.abdullayev.apps.dao.HouseDAO;
import com.abdullayev.apps.entity.House;
import com.abdullayev.apps.entity.Student;

public class StudentController {
    public static void main(String[] args) {
        HouseDAO houseDAO = new HouseDAO();

        // New object house
//        House house = new House("Kopro-house", 6, 280.2);
        // Insert new object house into the table (Database)
//        houseDAO.insert(house);

        // Edit the object house
//        houseDAO.update(null, 3, null, 2);

        // Remove the object house
//        houseDAO.remove(1);

        // Show the object house
        House house = houseDAO.show(2);

        // Close resources
        houseDAO.closeResources();

        // Show new object house
        System.out.println(house);
    }
}
