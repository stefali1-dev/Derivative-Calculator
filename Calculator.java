public class Calculator {

    private int leftIndex, rightIndex;

    /**
     * Recursive function that obtains the derivative of the mathematical expression from the input string
     * It calls other functions that can call the original function, thus creating a recursion
     *
     * @param str the string that contains the mathematical expression
     * @return the final returned string will be the derived expression
     */
    public String derivative(String str) {

        // eliminating brackets
        while (!str.equals(elimBrackets(str)))
            str = elimBrackets(str);

        // treating sum case
        if (isSum(str))
            return addition(str);

        // treating subtraction case
        if (isDif(str))
            return subtraction(str);

        // treating product case
        if (isMultiplication(str))
            return multiplyRule(str);

        // treating division case
        if (isDivision(str))
            return divideRule(str);

        // --------------------------- //

        if (isConst(str))
            return "0";

        // variable case
        if (isX(str))
            return "1";

        if (isPower(str))
            return powerRule(str);

        if (isSqrt(str))
            return sqrtRule(str);

        if (isLog(str))
            return logRule(str);

        if (isConstPowX(str))
            return constPowXRule(str);

        // -- trigonometric functions -- //
        if (isTrigon(str))
            return trigonDerivate(str);

        return "Error";
    }

    /**
     * Checks if a given string represents a constant
     *
     * @param s string to be checked
     * @return true or false
     */
    boolean isConst(String s) {
        if (s.indexOf('x') != -1)
            return false;
        if (isSingleExpression(s)) {
            return true;
        }
        if (isConstPowConst(s))
            return true;
        if (s.equals("e"))
            return true;
        boolean ok = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)) && !isOperationSign(s.charAt(i)) && s.charAt(i) != 'e') {
                ok = false;
                break;
            }
        }
        return ok;
    }

    /**
     * Checks if a given string represents a variable
     */
    boolean isX(String s) {
        return s.equals("x");
    }

    /**
     * Checks if a given string represents a square root
     */
    boolean isSqrt(String s) {
        String s2 = s.substring(0, s.indexOf('(') + 1);
        return s2.equals("sqrt(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a logarithmic function
     */
    boolean isLog(String s) {
        String s2 = s.substring(0, s.indexOf('(') + 1);
        return s2.equals("ln(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a sinus function
     */
    boolean isSin(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("sin(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a cosinus function
     */
    boolean isCos(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("cos(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a tangent function
     */
    boolean isTg(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("tg(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a cotangent function
     */
    boolean isCtg(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("ctg(") && isSolo(s);
    }

    /**
     * Checks if a given string represents an arcsine function
     */
    boolean isArcSin(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("arcsin(") && isSolo(s);
    }

    /**
     * Checks if a given string represents an arccos function
     */
    boolean isArcCos(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("arccos(") && isSolo(s);
    }

    /**
     * Checks if a given string represents an arctg function
     */
    boolean isArcTg(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("arctg(") && isSolo(s);
    }

    /**
     * Checks if a given string represents an arcctg function
     */
    boolean isArcCtg(String s) {
        String s1 = s.substring(0, s.indexOf('(') + 1);
        return s1.equals("arcctg(") && isSolo(s);
    }

    /**
     * Checks if a given string represents a trigonometric function
     */
    boolean isTrigon(String s) {
        return isSin(s) || isCos(s) || isTg(s) || isCtg(s) || isArcSin(s) ||
                isArcCos(s) || isArcTg(s) || isArcCtg(s);
    }

    /**
     * Checks if a given string contains a power raising
     */
    boolean isPower(String s) {
        if (s.indexOf('^') == -1)
            return false;
        else {
            String s1 = s.substring(0, s.lastIndexOf('^'));
            String s2 = s.substring(s.lastIndexOf('^') + 1);
            s1 = elimBrackets(s1);
            s2 = elimBrackets(s2);
            return !isConst(s1) && isConst(s2);
        }
    }

    /**
     * Checks if a given string contains a power raising of a constant
     */
    boolean isConstPowX(String s) {
        if (s.indexOf('^') == -1)
            return false;
        else {
            String s1 = s.substring(0, s.indexOf('^'));
            String s2 = s.substring(s.indexOf('^') + 1);
            s1 = elimBrackets(s1);
            s2 = elimBrackets(s2);
            return isConst(s1) && !isConst(s2);
        }
    }

    /**
     * Checks if a given string contains a constant raised to the power of another constant
     */
    boolean isConstPowConst(String s) {
        if (s.indexOf('^') == -1)
            return false;
        else {
            String s1 = s.substring(0, s.indexOf('^'));
            String s2 = s.substring(s.indexOf('^') + 1);
            while (!s1.equals(elimBrackets(s1)))
                s1 = elimBrackets(s1);
            while (!s2.equals(elimBrackets(s2)))
                s2 = elimBrackets(s2);
            return isConst(s1) && isConst(s2);
        }
    }

    /**
     * Checks if a given string doesn't contain multiple expressions
     */
    boolean isSolo(String s) {
        int countOpen = 0;
        int countClose = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                countOpen++;
            if (s.charAt(i) == ')')
                countClose++;
            if (countOpen != 0)
                if (countOpen == countClose && i != s.length() - 1)
                    return false;
        }
        return true;
    }

    boolean isSingleExpression(String s) {
        return isX(s) || isSqrt(s) || isLog(s) || isTrigon(s) || isPower(s);
    }

    /**
     * Checks if a character is outside the bound of parentheses in a given string
     *
     * @param s the string
     * @param c the character
     * @return the position of the character if it's inside parentheses, -1 if it's outside
     */
    int isOutOfParan(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                count++;
            if (s.charAt(i) == ')')
                count--;
            if (s.charAt(i) == c && count == 0)
                return i;
        }
        return -1;
    }

    /**
     * Eliminates the outer brackets of a given string
     *
     * @return the string without outside brackets
     */
    String elimBrackets(String s) {
        if (s.indexOf('(') == 0 && s.lastIndexOf(')') == s.length() - 1) {
            int countOpen = 0;
            int countClose = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(')
                    countOpen++;
                if (s.charAt(i) == ')')
                    countClose++;
                if (countOpen == countClose && i != s.length() - 1)
                    return s;
            }
            return s.substring(1, s.length() - 1);
        } else
            return s;
    }

    boolean hasBrackets(String s) {
        return (s.indexOf('(') == 0) && (s.lastIndexOf(')') == s.length() - 1);
    }

    boolean isOperationSign(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Gets the expression inside the brackets with the biggest scope from a given string
     *
     * @return the expression
     */
    String Chain(String s) {
        if (s.indexOf('(') != -1) {
            int first = s.indexOf('(');
            int last = s.lastIndexOf(')');
            return s.substring(first + 1, last);
        } else
            return s;
    }

    /**
     * Counts the apparitions of a string in another string
     *
     * @param s       the big string
     * @param findStr the string to be counted
     * @return the apparitions
     */
    int stringCount(String s, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = s.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    /**
     * Inserts a string in another string at a given position
     *
     * @param s   the string where the insertion takes place
     * @param sub the string to be inserted
     * @param poz the position of insertion
     * @return
     */
    String addSubString(String s, String sub, int poz) {
        for (int i = 0; i < sub.length(); i++) {
            s = addChar(s, sub.charAt(i), poz);
            poz++;
        }
        return s;
    }

    /**
     * Inserts a character at a given position in a string
     *
     * @param str      the string where the insertion takes place
     * @param ch       the character to be inserted
     * @param position the position of insertion
     * @return
     */
    String addChar(String str, char ch, int position) {
        int len = str.length();
        char[] updatedArr = new char[len + 1];
        str.getChars(0, position, updatedArr, 0);
        updatedArr[position] = ch;
        str.getChars(position, len, updatedArr, position + 1);
        return new String(updatedArr);
    }

    String leftPart(String s, int index) {
        leftIndex = index - 1;
        int i, countOpen = 0, countClose = 0;

        for (i = leftIndex; i >= 0; i--) {
            if (s.charAt(i) == ')')
                countClose++;
            if (s.charAt(i) == '(')
                countOpen++;
            if ((countOpen == countClose && isOperationSign(s.charAt(i))) || countOpen > countClose) {
                leftIndex = i + 1;
                break;
            }

        }

        if (i == -1)
            leftIndex = 0;
        s = s.substring(leftIndex, index);
        return s;
    }

    String rightPart(String s, int index) {
        rightIndex = index + 1;
        int i, countOpen = 0, countClose = 0;
        for (i = rightIndex; i < s.length(); i++) {
            if (s.charAt(i) == ')')
                countClose++;
            if (s.charAt(i) == '(')
                countOpen++;
            if ((countOpen == countClose && isOperationSign(s.charAt(i))) || countClose > countOpen) {
                rightIndex = i - 1;
                break;
            }
        }
        if (i == s.length())
            rightIndex = s.length() - 1;
        s = s.substring(index + 1, rightIndex + 1);
        return s;
    }

    /**
     * Formats a given string to correspond with the latex format and to be displayed
     *
     * @return the formatted string
     */
    String formatLatexStyle(String s) {

        // fraction format
        String c = "/";
        for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {
            // partea stanga)
            String s1 = leftPart(s, index);
            // partea dreapta
            String s2 = rightPart(s, index);
            int lenFrac = rightIndex - leftIndex + 1;


            // elim brackets
            while (!s1.equals(elimBrackets(s1)))
                s1 = elimBrackets(s1);
            while (!s2.equals(elimBrackets(s2)))
                s2 = elimBrackets(s2);

            String frac = "\\frac {" + s1 + "}{" + s2 + "}";

            s = s.substring(0, leftIndex) + s.substring(leftIndex + lenFrac);

            s = addSubString(s, frac, leftIndex);
        }
        // multiplication format
        c = "*";
        for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {
            s = s.substring(0, index) + s.substring(index + 1);
            s = addSubString(s, "\\cdot ", index);
        }

        // power format

        c = "^";
        for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {

            if (s.charAt(index + 1) == 'x' || s.charAt(index + 1) == 'e') {
                //
            } else if (s.charAt(index + 1) == '(') {
                int start_poz = index + 1;
                int end_poz = getParanEndPos(s, start_poz);

                s = s.substring(0, start_poz) + s.substring(start_poz + 1);
                s = addChar(s, '{', start_poz);

                s = s.substring(0, end_poz) + s.substring(end_poz + 1);
                s = addChar(s, '}', end_poz);
            } else {
                s = addChar(s, '{', index + 1);
                index += 2;
                try {
                    while (Character.isDigit(s.charAt(index))) {
                        index++;
                    }
                    s = addChar(s, '}', index);
                } catch (Exception e) {
                    s = addChar(s, '}', s.length());
                }
            }

        }

        // sqrt format
        String c2 = "sqrt(";
        for (int index = s.indexOf(c2); index >= 0; index = s.indexOf(c2, index + 1)) {
            // insert backslash
            s = addChar(s, '\\', index);
            index++;
            // first paran
            index += 4;
            s = addChar(s, '{', index);
            index++;
            s = s.substring(0, index) + s.substring(index + 1);

            //second paran
            while (s.charAt(index) != ')')
                index++;
            s = addChar(s, '}', index);
            index++;
            s = s.substring(0, index) + s.substring(index + 1);

            //
        }

        return s;
    }

    /**
     * Small adjustments after the latex formatting of a given string, before the string is displayed
     */
    String finalStringFormat(String s) {
        // remove useless
        String[] elem = {"\\cdot 1", "\\cdot (1)", "1\\cdot ", "(1)\\cdot ", "\\cdot ((1)), ", "((1))\\cdot ", "^{1}"};
        for (String c : elem) {
            for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {
                s = s.substring(0, index) + s.substring(index + c.length());
            }
        }
        // replace --
        String[] elem2 = {"--", "++"};
        for (String c : elem2) {
            for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {
                s = s.substring(0, index) + s.substring(index + c.length());
                s = addChar(s, '+', index);

            }
        }
        String[] elem3 = {"-+", "+-"};
        for (String c : elem3) {
            for (int index = s.indexOf(c); index >= 0; index = s.indexOf(c, index + 1)) {
                s = s.substring(0, index) + s.substring(index + c.length());
                s = addChar(s, '-', index);

            }
        }
        if (s.charAt(0) == '+')
            s = s.substring(1);
        return s;
    }

    /**
     * @return the position of the first closing bracket
     */
    int getParanEndPos(String s, int start_pos) {
        int count = 0;
        for (int i = start_pos; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                count++;
            if (s.charAt(i) == ')')
                count--;
            if (count == 0)
                return i;
        }
        System.out.println("Bad input");
        return -1;
    }

    //  --------- Rules ----------//

    int plusPosition(String s) {
        return isOutOfParan(s, '+');
    }

    int minusPosition(String s) {
        return isOutOfParan(s, '-');
    }

    int multiplicPosition(String s) {
        return isOutOfParan(s, '*');
    }

    int divisionPosition(String s) {
        return isOutOfParan(s, '/');
    }

    boolean isSum(String s) {
        return plusPosition(s) != -1;
    }

    boolean isDif(String s) {
        return minusPosition(s) != -1;
    }

    boolean isMultiplication(String s) {
        return multiplicPosition(s) != -1;
    }

    boolean isDivision(String s) {
        return divisionPosition(s) != -1;
    }

    /**
     * Brakes apart the two operands of an addition in a given string and derives them
     *
     * @return the derived given string, with the 2 operands derived
     */
    String addition(String s) {
        int poz = plusPosition(s);
        String s1 = s.substring(0, poz);
        String s2 = s.substring(poz + 1);

        String s1_derivate = derivative(s1);
        String s2_derivate = derivative(s2);

        if (hasBrackets(s1))
            s1_derivate = "(" + s1_derivate + ")";

        if (hasBrackets(s2))
            s2_derivate = "(" + s2_derivate + ")";

        if (isConst(s1) && !isConst(s2))
            return s2_derivate;

        if (isConst(s2) && !isConst(s1))
            return s1_derivate;

        if (isConst(s2) && isConst(s1))
            return "0";

        return s1_derivate + "+" + s2_derivate;
    }

    /**
     * Brakes apart the two operands of a subtraction in a given string and derives them
     *
     * @return the derived given string, with the 2 operands derived
     */
    String subtraction(String s) {
        int poz = minusPosition(s);
        String s1 = s.substring(0, poz);
        String s2 = s.substring(poz + 1);

        String s1_derivate = derivative(s1);
        String s2_derivate = derivative(s2);

        if (hasBrackets(s1))
            s1_derivate = "(" + s1_derivate + ")";

        if (hasBrackets(s2))
            s2_derivate = "(" + s2_derivate + ")";

        if (isConst(s1) && !isConst(s2))
            return "-" + s2_derivate;

        if (isConst(s2) && !isConst(s1))
            return s1_derivate;

        if (isConst(s2) && isConst(s1))
            return "0";

        return s1_derivate + "-" + s2_derivate;
    }

    /**
     * Brakes apart the two operands of a multiplication in a given string and derives them
     *
     * @return the derived given string, with the 2 operands derived, formatted in latex style
     */
    String multiplyRule(String s) {
        int poz = multiplicPosition(s);
        String s1 = s.substring(0, poz);
        String s2 = s.substring(poz + 1);

        if (isMultiplication(s1) || isDivision(s1))
            s1 = "(" + s1 + ")";
        if (isMultiplication(s2) || isDivision(s2))
            s2 = "(" + s2 + ")";

        String s1_derivate = derivative(s1);
        String s2_derivate = derivative(s2);

        s1 = formatLatexStyle(s1);
        s2 = formatLatexStyle(s2);


        if (hasBrackets(s1))
            s1_derivate = "(" + s1_derivate + ")";

        if (hasBrackets(s2))
            s2_derivate = "(" + s2_derivate + ")";

        if (s1.equals("0") || s2.equals("0"))
            return "0";
        // ----------------------------------//
        if (isConst(s1) && !isConst(s2))
            return s1 + "\\cdot " + s2_derivate;

        if (isConst(s2) && !isConst(s1))
            return s1_derivate + "\\cdot " + s2;

        if (isConst(s2) && isConst(s1))
            return "0";

        return s1_derivate + "\\cdot " + s2 + "+" + s1 + "\\cdot " + s2_derivate;

    }

    /**
     * Brakes apart the two operands of a division in a given string and derives them
     *
     * @return the derived given string, with the 2 operands derived, formatted in latex style
     */
    String divideRule(String s) {
        int poz = divisionPosition(s);
        String s1 = s.substring(0, poz);
        String s2 = s.substring(poz + 1);

        if (isMultiplication(s1) || isDivision(s1))
            s1 = "(" + s1 + ")";
        if (isMultiplication(s2) || isDivision(s2))
            s2 = "(" + s2 + ")";

        String s1_derivate = derivative(s1);
        String s2_derivate = derivative(s2);

        s1 = formatLatexStyle(s1);
        s2 = formatLatexStyle(s2);

        if (s1.equals("0"))
            return "0";

        if (hasBrackets(s1))
            s1_derivate = "(" + s1_derivate + ")";

        if (hasBrackets(s2))
            s2_derivate = "(" + s2_derivate + ")";
        // ----------------------------------//
        if (isConst(s1) && !isConst(s2))
            return "-\\frac {" + s1 + "\\cdot " + s2_derivate + "} {" + s2 + "^2}";

        if (isConst(s2) && !isConst(s1))
            return "\\frac {" + s1_derivate + "\\cdot " + s2 + "} {" + s2 + "^2}";

        if (isConst(s2) && isConst(s1))
            return "0";

        return "\\frac {" + s1_derivate + "\\cdot " + s2 + "-" + s1 + "\\cdot " + s2_derivate + "} {" + s2 + "^2}";
    }

    /**
     * Brakes apart the two operands of a power raising in a given string and derives them
     *
     * @return the derived given string formatted in latex style
     */
    String powerRule(String s) {
        String s1 = s.substring(0, s.lastIndexOf('^'));
        String s1_derivate = derivative(s1);
        if (hasBrackets(s1))
            s1_derivate = "(" + s1_derivate + ")";

        String Const_1 = s.substring(s.lastIndexOf('^') + 1);
        Const_1 = elimBrackets(Const_1);

        // -------
        String Const_2 = Const_1;
        char last_char = Const_2.charAt(Const_2.length() - 1);
        Const_2 = Const_2.substring(0, Const_2.length() - 1);
        String st = String.valueOf((char) (last_char - 1));
        Const_2 = Const_2.concat(st);
        // -------

        if (Const_1.equals("e"))
            Const_2 = "e-1";


        s = Const_1 + "\\cdot " + formatLatexStyle(s1) + "^{" + Const_2 + "}";
        if (s1.equals("x"))
            return s;
        else
            return s + "\\cdot " + s1_derivate;
    }

    /**
     * Applies the derivation square root rule in a given string
     *
     * @return the derived given string formatted in latex style
     */
    String sqrtRule(String s) {
        String s1 = Chain(s);

        String s1_derivate = derivative(s1);

        s1_derivate = "(" + s1_derivate + ")";


        s = "\\frac {1}{2\\cdot \\sqrt{" + formatLatexStyle(s1) + "}" + "}";
        if (s1.equals("x"))
            return s;
        else
            return s + "\\cdot " + s1_derivate;
    }

    /**
     * Applies the derivation log rule in a given string
     *
     * @return the derived given string formatted in latex style
     */
    String logRule(String s) {
        String s1 = Chain(s);

        String s1_derivate = derivative(s1);

        s1_derivate = "(" + s1_derivate + ")";
        if (s1.equals("x"))
            return "\\frac {1}{x}";
        else
            return "\\frac {1}{" + formatLatexStyle(s1) + "}\\cdot " + s1_derivate;
    }

    /**
     * Applies the derivation constant raised to a power rule in a given string
     *
     * @return the derived given string formatted in latex style
     */
    String constPowXRule(String s) {
        String s1 = s.substring(0, s.indexOf('^'));
        String s2 = s.substring(s.indexOf('^') + 1);
        s1 = elimBrackets(s1);
        s2 = elimBrackets(s2);

        if (!isSum(s2) && !isDif(s2))
            s2 = elimBrackets(s2);

        String s2_derivate = "(" + derivative(s2) + ")";

        if (s.equals("e^x"))
            return s;

        else if (s1.equals("e"))
            return "e^{" + formatLatexStyle(s2) + "}" + "\\cdot " + s2_derivate;
        else
            return s1 + "^{" + formatLatexStyle(s2) + "}" + "\\cdot " + "ln(" + s1 + ")" + s2_derivate;
    }

    /**
     * Applies the rule of trigonometric function derivation in a given string
     *
     * @return the derived given string formatted in latex style
     */
    String trigonDerivate(String s) {
        String s1 = Chain(s);
        String s1_derivate = "(" + derivative(s1) + ")";
        s1 = formatLatexStyle(s1);

        if (isSin(s)) {
            if (s1.equals("x"))
                return "cos(x)";
            else
                return "cos(" + s1 + ")\\cdot " + s1_derivate;
        }
        if (isCos(s)) {
            if (s1.equals("x"))
                return "-sin(x)";
            else
                return "-sin(" + s1 + ")\\cdot " + s1_derivate;
        }
        if (isTg(s)) {
            if (s1.equals("x"))
                return "\\frac {1}{cos^{2}(x)}";
            else
                return "\\frac {1}{cos^{2}(" + s1 + ")}\\cdot " + s1_derivate;
        }
        if (isCtg(s)) {
            if (s1.equals("x"))
                return "-\\frac {1}{sin^{2}(x)}";
            else
                return "-\\frac {1}{sin^{2}(" + s1 + ")}\\cdot " + s1_derivate;
        }
        if (isArcSin(s)) {
            if (s1.equals("x"))
                return "\\frac {1}{\\sqrt{1-x^{2}}}";
            else
                return "\\frac {1}{\\sqrt{1-(" + s1 + ")^{2}}}\\cdot " + s1_derivate;
        }
        if (isArcCos(s)) {
            if (s1.equals("x"))
                return "-\\frac {1}{\\sqrt{1-x^{2}}}";
            else
                return "-\\frac {1}{\\sqrt{1-(" + s1 + ")^{2}}}\\cdot " + s1_derivate;
        }
        if (isArcTg(s)) {
            if (s1.equals("x"))
                return "\\frac {1}{1+x^{2}}";
            else
                return "\\frac {1}{1+(" + s1 + ")^{2}}\\cdot " + s1_derivate;
        }
        if (isArcCtg(s)) {
            if (s1.equals("x"))
                return "-\\frac {1}{1+x^{2}}";
            else
                return "-\\frac {1}{1+(" + s1 + ")^{2}}\\cdot " + s1_derivate;
        }
        return s1_derivate;

    }
}