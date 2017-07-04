package com.framgia.framgiastaff.data.source.remote;

import com.framgia.framgiastaff.data.model.Type;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by levutantuan on 7/2/17.
 */

public class TypeRepository {

    public TypeRepository() {
    }

    public Observable<List<Type>> getListType() {
        List<Type> types = new ArrayList<>();
        types.add(new Type("Open Education"));
        types.add(new Type("Intern"));
        types.add(new Type("NewDew"));
        types.add(new Type("Part Time"));
        return Observable.just(types);
    }
}
