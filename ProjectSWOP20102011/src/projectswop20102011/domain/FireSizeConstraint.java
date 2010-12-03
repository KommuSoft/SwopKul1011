package projectswop20102011.domain;

/**
 * A class that represents a constraint that checks if the firetruck is capable to extinguish the fire.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireSizeConstraint implements Constraint{
        
	private Firetruck firetruck;
	private FireSize fireSize;

	/**
	 * Creates a FireSizeConstraint with the given firetruck and firesize.
	 * @param firetruck
	 *		The firetruck of this FireSizeConstraint.
	 * @param fireSize
	 *		The fireSize of this FireSizeConstraint.
	 */
	public FireSizeConstraint(Firetruck firetruck, FireSize fireSize){
		setFiretruck(firetruck);
		setFireSize(fireSize);
	}

	/**
	 * Returns the firetruck of this FireSizeConstraint.
	 * @return The firetruck of this FireSizeConstraint.
	 */
	public Firetruck getFiretruck() {
		return firetruck;
	}

	/**
	 *
	 * @param firetruck
	 */
	private void setFiretruck(Firetruck firetruck) {
		this.firetruck = firetruck;
	}

	/**
	 *
	 * @return
	 */
	public FireSize getFireSize() {
		return fireSize;
	}

	/**
	 *
	 * @param fireSize
	 */
	private void setFireSize(FireSize fireSize) {
		this.fireSize = fireSize;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public boolean isValid() {
		return getFiretruck().canHandleFireSize(this.getFireSize());
	}
}
