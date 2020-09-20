package xyz.cafe.factory;

import io.github.mcroteau.Parakeet;
import io.github.mcroteau.resources.access.Accessor;
import xyz.cafe.access.JdbcAccessor;

public class ParakeetFactory {

    Parakeet parakeet;
    Accessor accessor;

    public ParakeetFactory(){
        accessor = new JdbcAccessor();
        parakeet = new Parakeet(accessor);
    }

    public Parakeet getParakeet(){
        return this.parakeet;
    }

}