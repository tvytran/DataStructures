import java.util.Arrays;
import java.util.HashMap;

/**
 * Command line calculator that works with only integers.
 * The command line argument must be put in quotes, as in:
 * java IntCalculator "(2 x 3) ^ 2"
 * @author Thuy Vy Tran tt2964
 * @version 1.0 October 5, 2022
 */
public class IntCalculator {
    public static final HashMap<Character, Integer>
        binaryOperatorPrecedenceMap = new HashMap<>(),
        unaryOperatorPrecedenceMap = new HashMap<>();
    /*
     * userExpression is the infix expression entered by the user.
     * infixExpression is similar to the user expression, except that each
     * negation minus sign has been replaced with a ~ to avoid ambiguity im
     * the conversion and evaluation algorithms.
     */
    private String infixExpression, userExpression, postfixExpression;
    private Error error;

    /*
     * Operators and their precedences. Operators with higher precedences are
     * evaluated first.
     */
    static {
        unaryOperatorPrecedenceMap.put('~', 4);
        binaryOperatorPrecedenceMap.put('^', 3);
        binaryOperatorPrecedenceMap.put('x', 2);
        binaryOperatorPrecedenceMap.put('/', 2);
        binaryOperatorPrecedenceMap.put('%', 2);
        binaryOperatorPrecedenceMap.put('+', 1);
        binaryOperatorPrecedenceMap.put('-', 1);
    }

    /**
     * Creates an instance of an integer calculator.
     * @param expression the infix expression supplied by the user
     */
    public IntCalculator(String expression) {
        setExpression(expression);
    }

    /**
     * Sets the expression instance variables after parsing the characters in
     * input expression. If an invalid symbol is discovered, the internal
     * Error object is set accordingly.
     * @param expression the infix expression supplied by the user
     */
    public void setExpression(String expression) {
        error = null;
        boolean leading = true;
        StringBuilder internalBuilder = new StringBuilder(),
                      externalBuilder = new StringBuilder();
        for (int i = 0, len = expression.length(); i < len; i++) {
            char symbol = expression.charAt(i);
            if (isWhiteSpace(symbol)) {
                internalBuilder.append(symbol);
                externalBuilder.append(symbol);
                continue;
            }
            if (!isValid(symbol)) {
                if (error == null) {
                    error = new Error(getErrorHeader(i) + "Unexpected symbol '"
                            + symbol + "' found at position " + (i + 1) + ".",
                            0, i);
                }
            }
            char newSymbol = symbol;
            if (leading && symbol == '-') {
                newSymbol = '~';
            }
            leading =  (symbol == '(' || isUnencodedUnaryOperator(symbol)
                                      || isBinaryOperator(symbol));
            internalBuilder.append(newSymbol);
            externalBuilder.append(symbol);
        }
        this.infixExpression = internalBuilder.toString();
        this.userExpression = externalBuilder.toString();
    }

    /**
     * Returns the precedence of the given operator.
     * @param operator the operator of which to find the precedence
     * @return the precedence of the given operator
     */
    public static int precedence(char operator) {
        Integer val = binaryOperatorPrecedenceMap.get(operator);
        if (val == null) {
            val = unaryOperatorPrecedenceMap.get(operator);
        }
        return val != null ? val : -1;
    }

    /**
     * Returns true if the symbol is valid; false otherwise.
     * @param symbol the symbol to check
     * @return true if the symbol is valid
     */
    public static boolean isValid(char symbol) {
        return isBinaryOperator(symbol) ||
               isUnaryOperator(symbol) ||
               isDigit(symbol) ||
               isParenthesis(symbol);
    }

    /**
     * Returns true if the symbol is an operator; false otherwise.
     * @param symbol the symbol to check
     * @return true if the symbol is an operator
     */
    public static boolean isOperator(char symbol) {
        return binaryOperatorPrecedenceMap.containsKey(symbol)
                || unaryOperatorPrecedenceMap.containsKey(symbol);
    }

    /**
     * Returns true if the symbol is a binary operator; false otherwise.
     * @param symbol the symbol to check
     * @return true if the symbol is a binary operator
     */
    public static boolean isBinaryOperator(char symbol) {
        return binaryOperatorPrecedenceMap.containsKey(symbol);
    }

    /**
     * Returns true if the symbol is an unencoded unary operator -; false
     * otherwise.
     * @param symbol the symbol to check
     * @return true if the symbol is an unencoded unary operator
     */
    public static boolean isUnencodedUnaryOperator(char symbol) {
        return symbol == '-';
    }

    /**
     * Returns true if the symbol is a unary operator; false otherwise. ~ is
     * the only unary operator.
     * @param symbol the character to evaluate
     * @return true if the symbol is a unary operator
     */
    public static boolean isUnaryOperator(char symbol) {
        return unaryOperatorPrecedenceMap.containsKey(symbol);
    }

    /**
     * Returns true if the symbol is a digit 0 through 9; false otherwise.
     * @param symbol the character to evaluate
     * @return true if the symbol is a digit
     */
    public static boolean isDigit(char symbol) {
        return symbol >= 48 && symbol <= 57;
    }

    /**
     * Returns true if the symbol is whitespace; false otherwise. Whitespace
     * characters include space, tab, and new line characters.
     * @param symbol the character to evaluate
     * @return true if the symbol is a whitespace character
     */
    public static boolean isWhiteSpace(char symbol) {
        return symbol == ' ' || symbol == '\t' || symbol == '\n';
    }

    /**
     * Returns true if the symbol is an opening or closing parenthesis; false
     * otherwise.
     * @param symbol the character to evaluate
     * @return true if the symbol is an opening or closing parenthesis
     */
    public static boolean isParenthesis(char symbol) {
        return symbol == '(' || symbol == ')';
    }

    /**
     * Returns true if the symbol is left associative; false otherwise.
     * @param symbol the character to evaluate
     * @return true if the symbol is left associative
     */
    public static boolean isLeftAssociative(char symbol) {
        // Only ^ is right associative. All other symbols are left associative.
        return symbol != '^';
    }

    /**
     * Returns a String of spaces followed by a caret and a space, so that the
     * caret points to the first erroneous character in the
     * expression.
     * @param numSpaces the number of spaces before the caret
     * @return a String of spaces followed by a caret and a space
     */
    public static String getErrorHeader(int numSpaces) {
        char[] charArray = new char[numSpaces];
        Arrays.fill(charArray, ' ');
        return new String(charArray) + "^ ";
    }

    /**
     * Returns true if instance variable 'infixExpression' is valid; false
     * otherwise. At this point, all characters in the expression are known to
     * be valid, but the expression itself may not be well-formed.
     * @return true if the infix expression is valid
     * @throws StackException if an error occurs when calling a method on the
     * stack. This should not happen. The throws clause is there so that you
     * don't need any try-catch blocks in the body of this method.
     */
    public boolean containsValidExpression() throws StackException {
        if (error != null) {
            return false;
        }
        MyStack<Symbol> stack = new MyArrayList<>();
        StringBuilder numBuilder = new StringBuilder();
        boolean leading = true,
                operandFound = false,
                binaryOperatorFound = false;
        int len = infixExpression.length();
        for (int i = 0; i < len; i++) {
            char symbol = infixExpression.charAt(i);
            if (isWhiteSpace(symbol)) {
                if (numBuilder.length() > 0) {
                    operandFound = true;
                    numBuilder = new StringBuilder();
                }
                continue;
            }
            boolean isBinaryOperator = isBinaryOperator(symbol),
                    isOperand = isDigit(symbol);
            if (isOperand) {
                if (operandFound && !binaryOperatorFound) {
                    error = new Error(getErrorHeader(i - 1)
                            + "Expected operator at position " + i + ".",
                            0, i - 1);
                    return false;
                }
                numBuilder.append(symbol);
                binaryOperatorFound = false;
            } else if (isBinaryOperator) {
                binaryOperatorFound = true;
                operandFound = false;
            }
            if (symbol == ')' || isBinaryOperator) {
                if (leading) {
                    error = new Error(getErrorHeader(i)
                            + "Expected operand, but found '" + symbol
                            + "' at position " + (i + 1) + ".", 0, i);
                    return false;
                }
            } else if (!isOperand && !leading) {
                error = new Error(getErrorHeader(i)
                        + "Expected operator, but found '" + symbol
                        + "' found at position " + (i + 1) + ".", 0, i);
                return false;
            }
            if (symbol == '(') {
                stack.push(new Symbol(symbol, 0, i));
            } else if (symbol == ')') {
                if (stack.isEmpty()) {
                    error = new Error(getErrorHeader(i)
                            + "Unmatched ')' found at position "
                            + (i + 1) + ".", 0, i);
                    return false;
                }
                stack.pop();
                binaryOperatorFound = false;
                operandFound = true;
            }
            leading = isBinaryOperator || isUnaryOperator(symbol) ||
                      symbol == '(';
        }
        if (leading) {
            error = new Error(getErrorHeader(len)
                    + "Missing operand at position " + (len + 1) + ".", 0,
                    len);
            return false;
        }
        if (!stack.isEmpty()) {
            Symbol stackTop = stack.pop();
            error = new Error(getErrorHeader(stackTop.position)
                    + "Unmatched '(' found at position "
                    + (stackTop.position + 1) + ".", 0,stackTop.position);
            return false;
        }
        return true;
    }

    /**
     * Converts the infix expression stored in the instance variable
     * 'infixExpression' into postfix, storing the result in instance variable
     * 'postfixExpression'. Each symbol in the postfix expression is
     * separated by a space.
     * @return a string containing the postfix expression
     * @throws StackException if an error occurs when calling a method on the
     * stack. This should not happen. The throws clause is there so that you
     * don't need any try-catch blocks in the body of this method.
     */
    public String infixToPostfix() throws StackException {
        // TODO
        int length = infixExpression.length();
        MyStack<Character> stack = new MyArrayList<>();
        postfixExpression = "";
        if(containsValidExpression() == false)
        {
            throw new StackException("Does not contain valid expression.");
        }

        int i = 0;
        while(i < length)
        {
            char ch = infixExpression.charAt(i);
            if(i == length-1)
            {
                if(isDigit(ch)) {
                    postfixExpression = postfixExpression + ch;
                }
                else if(isParenthesis(ch))
                {
                    if(ch == '(')
                    {
                        stack.push(ch);
                    }
                    else 
                    {
                        while (!stack.isEmpty() && !stack.peek().equals('('))
                        {
                            char c = stack.pop();
                            postfixExpression = postfixExpression + c;
                        }
                        if(!stack.isEmpty())
                            stack.pop();
                    }
                }
                else if(isBinaryOperator(ch))
                {
                    while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                        char c = stack.pop();
                        postfixExpression = postfixExpression + c;
                    }


                }
            }
            else if(isDigit(ch))
            {
                StringBuilder builder = new StringBuilder();
                builder.append(ch);
                boolean j = true;
                while(i < length && j)
                {
                    if(i+1 == length)
                        j = false;
                    else {
                        i++;
                        ch = infixExpression.charAt(i);
                        if(isDigit(ch))
                            builder.append(ch);
                        else{
                            j = false;
                            i--;
                        }
                    }
                }
                if(i == length-1)
                    postfixExpression = postfixExpression + builder.toString();
                else
                    postfixExpression  = postfixExpression + builder.toString() + " ";
            }
            else if(isParenthesis(ch))
            {
                if(ch == '(')
                {
                    stack.push(ch);
                }
                else
                {
                    while(!stack.isEmpty() && !(stack.peek().equals('(')))
                    {
                        char c = stack.pop();
                        postfixExpression = postfixExpression + c + " ";
                    }
                    stack.pop();
                }
            }
            else if(isUnaryOperator(ch))
            {
                stack.push(ch);
            }
            else if(isBinaryOperator(ch))
            {
                /*
                if(!stack.isEmpty() && stack.peek() == '^' && ch == '^')
                {
                    stack.push(ch);
                }
                */
                while(!stack.isEmpty() && precedence(ch) <= precedence(stack.peek()))
                {
                    if(stack.peek() == '^' && ch == '^')
                    {
                        break;
                    }
                    char c = stack.pop();
                    postfixExpression = postfixExpression + c + " ";
                }
                stack.push(ch);
            }
            else if(isWhiteSpace(ch))
            {

            }
            i++;
        }
        while(!stack.isEmpty())
        {
            char c = stack.pop();
            postfixExpression = postfixExpression + " " + c;
        }

        // This is the last line of the method.
        return this.postfixExpression;
    }

    /**
     * Evaluates the postfix expression and returns the integer value of the
     * expression. All operations are performed with integers.
     * @return the integer value that results after evaluating the postfix
     * expression
     * @throws StackException if an error occurs when calling a method on the
     * stack. This should not happen. The throws clause is there so that you
     * don't need any try-catch blocks in the body of this method.
     * @throws IllegalArgumentException if an attempt to divide or mod by zero
     * is encountered. The message of the exception reads:
     * "Cannot evaluate expression, division by zero."
     * An IllegalArgumentException si also thrown if the user attempts to
     * compute 0^0. The message of the exception reads:
     * "Cannot evaluate expression, 0^0 is undefined."
     */
    public int evaluatePostfix() throws StackException, IllegalArgumentException
    {
        // TODO
        MyStack<Integer> stack = new MyArrayList<>();
        int length = postfixExpression.length();
        for(int i = 0; i < postfixExpression.length(); i++)
        {
            char ch = postfixExpression.charAt(i);
            if(isDigit(ch))
            {
                StringBuilder builder = new StringBuilder();
                builder.append(ch);
                int j = 0;
                while(i < length && j == 0)
                {
                    if(i+1 == length)
                        j = 1;
                    else
                    {
                        i++;
                        ch = postfixExpression.charAt(i);
                        if (!isWhiteSpace(ch))
                            builder.append(ch);
                        else
                        {
                            j = 1;
                            i--;
                        }
                    }
                }
                stack.push(Integer.parseInt(String.valueOf(builder)));
            }
            else if(ch == '~')
            {
                Integer value = stack.pop()*-1;
                stack.push(value);
            }
            else if(ch == 'x'|| ch == '+' || ch == '-' || ch == '%' || ch == '/' || ch == '^')
            {
                Integer first = stack.pop();
                Integer second = stack.pop();
                if(ch == 'x')
                {
                    Integer value = first*second;
                    stack.push(value);
                }
                else if(ch == '/')
                {
                    if(first == 0)
                    {
                        throw new IllegalArgumentException("Cannot evaluate expression, division by zero.");
                    }
                    Integer value = second/first;
                    stack.push(value);
                }
                else if(ch == '+')
                {
                    Integer value = first + second;
                    stack.push(value);
                }
                else if(ch == '%')
                {
                    if(first == 0)
                    {
                        throw new IllegalArgumentException("Cannot evaluate expression, division by zero.");
                    }
                    Integer value = second%first;
                    stack.push(value);
                }
                else if(ch == '-')
                {
                    Integer value = second - first;
                    stack.push(value);
                }
                else if(ch == '^')
                {
                    Integer value = second;
                    if(second == 0 && first == 0)
                    {
                        throw new IllegalArgumentException("Cannot evaluate expression, 0^0 is undefined.");
                    }
                    for(int j = 1; j < first; j++)
                    {
                        value = value*second;
                    }
                    stack.push(value);
                }
                else if(isWhiteSpace(ch))
                {

                }

            }
        }

        return stack.pop();
    }

    /**
     * Returns the internal error message, if one exists.
     * @return the internal error message
     */
    public String getErrorMessage() {
        return error == null ? "No errors found." : error.message;
    }

    /**
     * Returns the infix expression supplied by the user.
     * @return the infix expression supplied by the user
     */
    public String getExpression() {
        return userExpression;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }
        String input = builder.toString().trim();
        if (input.length() == 0) {
            System.err.println("Usage: java IntCalculator <expression>");
            System.exit(1);
        }
        IntCalculator calc = new IntCalculator(input);

        try {
            if (calc.containsValidExpression()) {
                String postfix = calc.infixToPostfix();
                System.out.println("Postfix expression: " + postfix);
                System.out.println("Evaluation:         "
                        + calc.evaluatePostfix());
            } else {
                System.err.println(calc.getExpression());
                System.err.println(calc.getErrorMessage());
            }
        } catch (Exception e) {
            System.err.println("Error:              " + e.getMessage());
        }
    }
}

class Symbol {
    char character;
    int lineNumber, position;

    Symbol(char character, int lineNumber, int position) {
        this.character = character;
        this.lineNumber = lineNumber;
        this.position = position;
    }
}

class Error {
    int lineNumber, position;
    String message;

    Error(String message, int lineNumber, int position) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.position = position;
    }
}
