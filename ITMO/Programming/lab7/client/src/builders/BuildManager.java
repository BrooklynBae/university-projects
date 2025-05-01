package builders;

import exceptions.BuildObjectException;

public interface BuildManager<T>{

    T buildObject() throws BuildObjectException;

}
