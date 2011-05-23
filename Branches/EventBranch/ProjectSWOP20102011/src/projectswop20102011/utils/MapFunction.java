package projectswop20102011.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class representing a function that maps objects to other objects.
 * @param <TSource> The class from where the mapper maps.
 * @param <TDestination>  The class to where the mapper maps.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class MapFunction<TSource,TDestination> {
    
    /**
     * Maps a collection of sources to an ArrayList of destinations.
     * @param <TSource> The type of objects to map from.
     * @param <TDestination> The type of object to map to.
     * @param sources A collection of sources to map from.
     * @param function The function that describes how to map a source to its destination.
     * @return An ArrayList that contains the products of the mapping of the sources.
     * @throws Throwable If one of the mappings throws a Throwable.
     */
    public static<TSource,TDestination> ArrayList<TDestination> mapCollection (Collection<TSource> sources, MapFunction<? super TSource,TDestination> function) throws Throwable {
        ArrayList<TDestination> result = new ArrayList<TDestination>(sources.size());
        for(TSource x : sources) {
            result.add(function.getFunctionResult(x));
        }
        return result;
    }

    /**
     * An abstract method to represent the mapping function.
     * @param source The source to calculate the destination from.
     * @return The destination object from the source.
     * @throws Throwable If something goes wrong when mapping.
     */
    public abstract TDestination getFunctionResult (TSource source) throws Throwable;

}
