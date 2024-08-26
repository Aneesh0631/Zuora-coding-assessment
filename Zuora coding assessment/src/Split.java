import java.util.ArrayList;
import java.util.List;

class Split {
    public static List<List<Transaction>> splitTransactions(List<Transaction> transactions, int N) {
        List<List<Transaction>> result = new ArrayList<>();
        List<Transaction> credits = new ArrayList<>();
        List<Transaction> debits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.type.equals("CR")) {
                credits.add(t);
            } else {
                debits.add(t);
            }
        }
        int creditIndex = 0;
        int debitIndex = 0;
        int totalDebits = debits.size();
        boolean isOddDebits = totalDebits % 2 != 0;
        while (debitIndex < debits.size()) {
            List<Transaction> currentSplit = new ArrayList<>();
            int debitSum = 0;
            if (isOddDebits && result.size() == 1) {
                Transaction currentDebit = debits.get(debitIndex);
                currentSplit.add(currentDebit);
                debitSum += currentDebit.amount;
                debitIndex++;
            } else {
                while (currentSplit.size() < N - 1 && debitIndex < debits.size()) {
                    Transaction currentDebit = debits.get(debitIndex);
                    currentSplit.add(currentDebit);
                    debitSum += currentDebit.amount;
                    debitIndex++;
                }
            }
            if (debitSum > 0 && creditIndex < credits.size()) {
                Transaction currentCredit = credits.get(creditIndex);
                if (currentCredit.amount >= debitSum) {
                    currentSplit.add(new Transaction("CR", debitSum));
                    currentCredit.amount -= debitSum;
                    if (currentCredit.amount == 0) {
                        creditIndex++;
                    }
                } else {
                    currentSplit.add(new Transaction("CR", currentCredit.amount));
                    debitSum -= currentCredit.amount;
                    creditIndex++;
                }
            }

            result.add(currentSplit);
        }
        while (creditIndex < credits.size()) {
            List<Transaction> currentSplit = new ArrayList<>();
            Transaction currentCredit = credits.get(creditIndex);
            currentSplit.add(new Transaction("CR", currentCredit.amount));
            creditIndex++;
            result.add(currentSplit);
        }
        return result;
    }
}
