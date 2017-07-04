package com.framgia.framgiastaff.data.source.remote;

import com.framgia.framgiastaff.data.model.Type;
import java.util.List;
import rx.Observable;

/**
 * Created by levutantuan on 7/2/17.
 */

public interface TypeDataSource {

    interface RemoteDataSource {

        Observable<List<Type>> getListType();
    }
}
