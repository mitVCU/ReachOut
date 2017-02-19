package com.jzheadley.reachout.models;

import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.Collection;
import java.util.Set;

/**
 * Created by pjhud on 2/18/2017.
 */

public class ModelUtilities {

    public static int creditScore(Collection<Proposal> proposals) {
        int rawScore = 0;
        for (Proposal prop : proposals) {
            switch (prop.getState()) {
                case Proposal.STATE_FUNDED:
                    rawScore -= prop.getAmountToBeRepayed() / 20;
                case Proposal.STATE_CASH_WITHDRAWN:
                    rawScore -= prop.getAmountToBeRepayed() / 20;
                case Proposal.STATE_CASH_REPAID:
                    if (dueDate(prop) > prop.getDateFunded()) {
                        rawScore += prop.getAmountToBeRepayed() / 10;
                    } else {
                        rawScore -= prop.getAmountToBeRepayed() / 5;
                    }
                    break;
                default:
            }
        }
        double subtractionTerm = Math.max(25, rawScore + 50);
        double flattened = Math.max(0, 1000.0 - (25000.0 / subtractionTerm));
        return (int) flattened;
    }

    public static long dueDate(Proposal proposal) {
        return proposal.getDateFunded() + ((long)proposal.getMonthsOfLoan() * 2592000000L);
    }

}
