package projectswop20102011.userinterface;

import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A static utility class providing several static methods for parsing several structures.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UserInterfaceParsers {

    /**
     * No instances can be created
     */
    private UserInterfaceParsers() {
    }

    public static String parseOptionsString(UserInterface channel, String parameterName, String... options) throws ParsingAbortedException {
        String list = "";
        if (options != null && options.length > 0) {
            list = options[0];
            for (int i = 1; i < options.length; i++) {
                list += "/" + options[i];
            }
        }
        while (true) {
            channel.writeOutput(String.format("%s=? (%s)", parameterName, list));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing options aborted.");
            }
            for (String option : options) {
                if (option.equals(input)) {
                    return option;
                }
            }
            channel.writeOutput("Unknown option, please retry.");
        }
    }
}
