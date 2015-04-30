package main.java.server.response.content;

public class QueryStringDecoder {
    public String decode(String queryString) {
        String content = "";

        for (String parameter : paramsFrom(queryString)) {
            content += paramDecode(parameter) + "\r\n";
        }

        return content;
    }

    private String[] paramsFrom(String queryString) {
        return queryString.split("&");
    }

    private String paramDecode(String parameter) {
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
