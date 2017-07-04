package com.framgia.framgiastaff.data.source.remote;

import com.framgia.framgiastaff.data.model.Employee;
import java.util.List;
import rx.Observable;

/**
 * Created by levutantuan on 7/2/17.
 */

public interface EmployeeDataSource {

    interface RemoteDataSource {
        Observable<Employee> register(Employee employee);

        Observable<List<Employee>> getListEmployee();
    }
}
