package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.DispatchUnitsToDisasterController;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.BooleanParser;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

public class DispatchUnitsToDisasterUserInterface extends CommandUserInterface {

	private final DispatchUnitsToDisasterController dispatchUnitsToDisasterController;
	private final DisasterMapper disasterController;

	public DispatchUnitsToDisasterUserInterface(DispatchUnitsToDisasterController dispatchUnitsToDisasterController, DisasterMapper disasterController) throws InvalidCommandNameException, InvalidControllerException {
		super("dispatch units to disaster");

		if (dispatchUnitsToDisasterController == null || disasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.dispatchUnitsToDisasterController = dispatchUnitsToDisasterController;
		this.disasterController = disasterController;
	}

	@Override
	public DispatchUnitsToDisasterController getController() {
		return this.dispatchUnitsToDisasterController;
	}

	private DisasterMapper getDisasterController() {
		return disasterController;
	}

	@Override
	public void handleUserInterface() {
		try {
			long disasterID = this.parseInputToType(new LongParser(), "The id of the disaster");
			Disaster selectedDisaster = getDisasterController().getDisasterFromId(disasterID);
			if (selectedDisaster == null) {
				this.writeOutput("Disaster not found.");
			} else {
				Hashtable<String, String> information = selectedDisaster.getLongInformation();
				this.writeOutput("DISASTER DETAILS:");
				this.writeOutput(String.format("\ttype: %s", selectedDisaster.getClass().getSimpleName()));
				for (Entry<String, String> entry : information.entrySet()) {
					this.writeOutput(String.format("\t%s: %s", entry.getKey(), entry.getValue()));
				}
				this.writeOutput("SUGGESTED UNITS:");
				try {
					Set<Unit> suggestedUnits = this.getController().getUnitsByPolicy(selectedDisaster);
					ArrayList<Unit> suggestedUnitsL = new ArrayList<Unit>(suggestedUnits);
					//print information for the suggested units
					for (int i = 0; i < suggestedUnitsL.size(); i++) {
						Unit u = suggestedUnitsL.get(i);
						double distance = u.getCurrentLocation().getDistanceTo(selectedDisaster.getLocation());
						long eta = u.getETA(selectedDisaster.getLocation());
						this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
					}
					boolean acceptSuggestion = this.parseInputToType(new BooleanParser(), "Do you accept the suggestion");
					if (!acceptSuggestion) {
						HashSet<Unit> assignedUnits;
						boolean retry = true;
						while (retry) {
							assignedUnits = new HashSet<Unit>();
							retry = false;
							this.writeOutput("REQUIRED UNITS:");
							//TODO: Hier moet enkel het aantal units komen die vereist is, niet welke soorten
							Set<Unit> requiredUnits = this.getController().getRequiredUnits(selectedDisaster);
							ArrayList<Unit> requiredUnisL = new ArrayList<Unit>(requiredUnits);
							//print information for the suggested units
							for (int i = 0; i < requiredUnisL.size(); i++) {
								Unit u = requiredUnisL.get(i);
								double distance = u.getCurrentLocation().getDistanceTo(selectedDisaster.getLocation());
								long eta = u.getETA(selectedDisaster.getLocation());
								this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
							}

							this.writeOutput("AVAILABLE UNITS:");
							ArrayList<Unit> availableUnits = (ArrayList<Unit>) this.getController().getAvailableUnitsSorted(selectedDisaster);
							for (int i = 0; i < availableUnits.size(); i++) {
								Unit u = availableUnits.get(i);
								double distance = u.getCurrentLocation().getDistanceTo(selectedDisaster.getLocation());
								long eta = u.getETA(selectedDisaster.getLocation());
								this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
							}
							String expression;
							do {
								this.writeOutput("Type in a unit id, or type \"stop\" to finish the list.");
								expression = this.parseInputToType(new StringParser(), "id");
								if (!expression.equals("stop")) {
									try {
										int id = Integer.parseInt(expression);
										assignedUnits.add(availableUnits.get(id));
										this.writeOutput("Unit added.");
									} catch (Exception ex) {
										this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
									}
								}
							} while (!expression.equals("stop"));
							try {
								this.getController().dispatchToDisaster(selectedDisaster, assignedUnits);
								this.writeOutput("The chosen units are assigned");
							} catch (Exception ex) {
								this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
								this.writeOutput("Please give a new selection of units.");
								retry = true;
							}
						}
					} else {
						this.getController().dispatchToDisaster(selectedDisaster, suggestedUnits);
						this.writeOutput("The suggested units are assigned");
					}
				} catch (Exception ex) {
					this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
				}
			}
		} catch (ParsingAbortedException ex) {
			this.writeOutput("Command aborted.");
		}

	}
}
