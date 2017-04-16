package com.dalgim.example.sb.cxf.wsssignature.endpoint;

import com.dalgim.example.sb.cxf.wsssignature.model.Fruit;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by dalgim on 08.04.2017.
 */
@WebService
public interface FruitService {

    @WebMethod
    Collection<Fruit> getAllFruit();
}
