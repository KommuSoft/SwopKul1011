package projectswop20102011;

import projectswop20102011.reflection.EmergencyParameter;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParameterTextualEqualityEmergencyEvaluationCriterium extends EmergencyEvaluationCriterium {

    private final EmergencyParameter parameter;
    private final String value;

    public ParameterTextualEqualityEmergencyEvaluationCriterium (EmergencyParameter parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    @Override
    public boolean isValidEmergency(Emergency emergency) {
        try {
            return this.parameter.getParameterValueFromEmergency(emergency).toString().equals(value);
        } catch (Throwable ex) {
            return false;//minimal world assumption
        }
    }

}
