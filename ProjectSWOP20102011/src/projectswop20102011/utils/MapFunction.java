package projectswop20102011.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class representing a function that maps objects to other objects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class MapFunction<TSource,TDestination> {
    
    public static<TSource,TDestination> ArrayList<TDestination> mapCollection (Collection<TSource> sources, MapFunction<? super TSource,TDestination> function) {
        ArrayList<TDestination> result = new ArrayList<TDestination>(sources.size());
        //TDestination[] result = new TDestination[sources.size()];
        for(TSource x : sources) {
            result.add(function.getFunctionResult(x));
        }
        return result;
    }

    /**
     * An abstract method to represent the mapping function.
     * @param source The source to calculate the destination from.
     * @return The destination object from the source.
     */
    public abstract TDestination getFunctionResult (TSource source);

}
