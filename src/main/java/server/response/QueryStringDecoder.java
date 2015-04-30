package main.java.server.response;

public class QueryStringDecoder {
    public String decode(String parameter) {
        String decodedString = "";
        String[] parameterCharacterList = parameter.split("");

        for (int i = 0; i < parameterCharacterList.length; i++) {
            if (parameterCharacterList[i].equals("=")) {
                decodedString += " " + parameterCharacterList[i] + " " ;
            }
            else if (parameterCharacterList[i].equals("%")) {
                String hexSet = parameterCharacterList[i + 1] + parameterCharacterList[i + 2];
                int decimal = Integer.parseInt(hexSet, 16);
                decodedString += (char)decimal;

                i += 2;
            } else {
                decodedString += parameterCharacterList[i];
            }
        }

        return decodedString;
    }
}
