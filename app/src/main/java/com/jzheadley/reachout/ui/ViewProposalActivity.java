package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.ModelUtilities;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;

import java.sql.Date;

public class ViewProposalActivity extends BaseActivity {
    private static final String TAG = "ViewProposalActivity";
    ThreeButtonView loanAmount;
    ThreeButtonView loanRepayAmount;
    ThreeButtonView moneyMaking;
    ThreeButtonView loanLength;
    ThreeButtonView loanPurchase;
    ThreeButtonView reason;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_proposal);
        final Proposal proposal = getIntent().getExtras().getParcelable("singleProposal");

        loanAmount = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_amount));
        loanRepayAmount = ((ThreeButtonView) findViewById(R.id.three_button_get_repayment_amount));
        loanLength = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_length));
        moneyMaking = ((ThreeButtonView) findViewById(R.id.three_button_get_money_making));
        loanPurchase = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_purchase));
        reason = ((ThreeButtonView) findViewById(R.id.three_button_get_how_money_help));
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.endorse);

        if(ModelUtilities.getCurrentUser().isLeader())
        {
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.VISIBLE);
        }
        else
        {
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.INVISIBLE);
        }

        loanAmount.setPlayResponseVisibility(View.INVISIBLE);
        loanAmount.setAddResponseVisibility(View.INVISIBLE);
        // loanAmount.setEditTextVisibility(View.INVISIBLE);
        loanRepayAmount.setPlayResponseVisibility(View.INVISIBLE);
        loanRepayAmount.setAddResponseVisibility(View.INVISIBLE);
        // loanRepayAmount.setEditTextVisibility(View.INVISIBLE);
        loanLength.setPlayResponseVisibility(View.INVISIBLE);
        loanLength.setAddResponseVisibility(View.INVISIBLE);
        // loanLength.setEditTextVisibility(View.INVISIBLE);
        moneyMaking.setPlayResponseVisibility(View.INVISIBLE);
        moneyMaking.setAddResponseVisibility(View.INVISIBLE);
        // moneyMaking.setEditTextVisibility(View.INVISIBLE);
        loanPurchase.setPlayResponseVisibility(View.INVISIBLE);
        loanPurchase.setAddResponseVisibility(View.INVISIBLE);
        // loanPurchase.setEditTextVisibility(View.INVISIBLE);
        reason.setPlayResponseVisibility(View.INVISIBLE);
        reason.setAddResponseVisibility(View.INVISIBLE);
        // reason.setEditTextVisibility(View.INVISIBLE);

        loanAmount.setPromptText(getString(R.string.response_loan_amount) + " " + Integer.toString(proposal.getAmountBorrowed()) + " dollars");
        loanAmount.setEditTextText(getString(R.string.response_loan_amount) + " " + Integer.toString(proposal.getAmountBorrowed()) + " dollars");

        loanRepayAmount.setPromptText(Integer.toString(proposal.getAmountToBeRepayed()) +" "+ getString(R.string.response_loan_repayment_amount));
        loanRepayAmount.setEditTextText(Integer.toString(proposal.getAmountToBeRepayed()) +" "+ getString(R.string.response_loan_repayment_amount));

        moneyMaking.setPromptText(getString(R.string.response_money_making) + " " + proposal.getBusinessDescription());
        moneyMaking.setEditTextText(getString(R.string.response_money_making) + " " + proposal.getBusinessDescription());

        loanLength.setPromptText(getString(R.string.response_loan_repayment_date) + " " + new Date(ModelUtilities.dueDate(proposal)));
        loanLength.setEditTextText(getString(R.string.response_loan_repayment_date) + " " + new Date(ModelUtilities.dueDate(proposal)));

        loanPurchase.setPromptText(getString(R.string.response_loan_purchase) + " " + proposal.getPurchaseDescription());
        loanPurchase.setEditTextText(getString(R.string.response_loan_purchase) + " " + proposal.getPurchaseDescription());

        reason.setPromptText(getString(R.string.response_loan_how_help) + " " + proposal.getPlanDescription());
        reason.setEditTextText(getString(R.string.response_loan_how_help) + " " + proposal.getPlanDescription());
        ModelSingleton.getInstance().synchWithDB();


        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "onClick: state "+proposal.getState());
                Intent cash, repay;
                cash = new Intent(ViewProposalActivity.this, CashActivity.class);
                repay = new Intent(ViewProposalActivity.this, RepayActivity.class);
                if (proposal.getState() <= Proposal.STATE_FUNDED) {
                    cash.putExtra("proposal", proposal);
                    startActivity(cash);
                } else if (proposal.getState() == Proposal.STATE_CASH_WITHDRAWN) {
                    repay.putExtra("proposal", proposal);
                    startActivity(repay);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ModelUtilities.endorse(ModelUtilities.getCurrentUser(),proposal);
            }
        });
    }
}
