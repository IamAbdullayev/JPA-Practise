package com.abdullayev.apps;


import com.abdullayev.apps.dao.CrudJPA;
import com.abdullayev.apps.entity.Student;

public class StudentController {
    public static void main(String[] args) {
        CrudJPA crudJPA = new CrudJPA();

        Student student = crudJPA.findByID(1);



        // Закрываем ресурсы только после выполнения всех операций
        crudJPA.closeResources();

        // Выводим информацию о студенте
        System.out.println("================================");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Surname: " + student.getSurname());
        System.out.println("Average Grade: " + student.getAvgGrade());
        System.out.println("================================");
    }
}
