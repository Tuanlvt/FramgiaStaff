package com.framgia.framgiastaff.data.source.remote;

import com.framgia.framgiastaff.data.model.Employee;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by levutantuan on 7/2/17.
 */

public class EmployeeRepository {

    public EmployeeRepository() {
    }

    public Observable<Employee> register(Employee employee) {
        Employee employees = new Employee(employee.getId(), employee.getEmail(), employee.getSex(),
                employee.getDateWorking(), employee.getType(), employee.getLocation());
        return Observable.just(employees);
    }

    public Observable<List<Employee>> getListEmployee() {
        List<Employee> employee = new ArrayList<>();
        employee.add(new Employee("A1", "nguyen.van.a@framgia.com", "Male", "20/10/2016", "NewDev",
                "Edu"));
        employee.add(new Employee("A2", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A3", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A4", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A5", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A6", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A7", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A8", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A9", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A10", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A11", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A12", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A13", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A14", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A15", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A16", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        employee.add(new Employee("A17", "tran.van.c@framgia.com", "Male", "01/02/2017", "Intern",
                "Edu"));
        return Observable.just(employee);
    }
}
