package com.dps924.assignment_1;

import java.util.ArrayList;

public class Calculator {

    public ArrayList<String> numberOperations;

    public Calculator() {
        this.numberOperations = new ArrayList<>();
    }

    public int calculate() throws Exception {
        int result;
        if (this.numberOperations.size() == 0) {
            throw new Exception("No values to calculate.");
        }
        else if (this.numberOperations.size() >= 3){

            try {

                ArrayList<String> operationsList = new ArrayList<>(this.numberOperations.subList(0, 3));

                // Validate first three elements as number, operator, number
                // if it's not in correct format it will throw an error
                result = Integer.parseInt(operationsList.get(0));
                String operator = operationsList.get(1);
                int num = Integer.parseInt(operationsList.get(2));

                // remove the first 3 elements from the original list
                if (operationsList.size() > 0) {
                    this.numberOperations.subList(0, operationsList.size()).clear();
                }

                int idx = 0;

                do {

                    switch (operator) {
                        case "+":
                            result += num;
                            break;
                        case "-":
                            result -= num;
                            break;
                        case "÷":
                            result /= num;
                            break;
                        case "×":
                            result *= num;
                            break;
                        case "Max":
                            result = Math.max(result, num);
                            break;
                        case "Min":
                            result = Math.min(result, num);
                            break;
                        case "^":
                            result = (int) Math.pow(result, num);
                            break;
                        case "%":
                            result %= num;
                            break;
                        default:
                            // in case the position occupied by the operator is a number like 222
                            throw new Exception("Not an operator.");
                    }

                    if (idx < this.numberOperations.size()) {
                        // if the operator, number sequence is broken an error will be thrown
                        operator = this.numberOperations.get(idx);
                        num = Integer.parseInt(this.numberOperations.get(idx + 1));
                    }

                    // +2 to get to the next operator position
                    idx += 2;

                // the idx goes beyond the size of the array to calculate the last operation
                // correctly
                } while (idx != this.numberOperations.size() + 2);



            }catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception("Not an operator.");
            }

        }
        else {
            throw new Exception("Unable to calculate.");
        }

        this.numberOperations.clear();
        // So that the calculation can continue with the current result
        // unless the clear button is pressed
        this.numberOperations.add(Integer.toString(result));

        return result;
    }

    public void push(String value) {
        this.numberOperations.add(value);
    }


}
