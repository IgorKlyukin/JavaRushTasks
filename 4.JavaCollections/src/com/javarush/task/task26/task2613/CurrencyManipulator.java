package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int n = 0;
        for (Map.Entry<Integer, Integer> entry :
                denominations.entrySet()) {
            n += entry.getKey() * entry.getValue();
        }
        return n;
    }

    public boolean hasMoney(){
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() - expectedAmount >= 0;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        List<Integer> nominations = new ArrayList<>(denominations.keySet());
        Collections.sort(nominations);
        Collections.reverse(nominations);

        for (int i = 0, n = nominations.size(); i < n; i++) {
            if (expectedAmount == 0)
                break;
            int count = expectedAmount / nominations.get(i);
            if (count == 0)
                continue;
            if (count > denominations.get(nominations.get(i))) {
                count = denominations.get(nominations.get(i));
            }
            expectedAmount -= count * nominations.get(i);
            result.put(nominations.get(i), count);
        }

        if (expectedAmount != 0)
            throw new NotEnoughMoneyException();
        else {
            for (Integer nominal : result.keySet()) {
                denominations.put(nominal, denominations.get(nominal) - result.get(nominal));
            }
        }

        return result;
    }
}
