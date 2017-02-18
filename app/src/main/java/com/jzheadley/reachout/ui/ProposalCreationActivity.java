package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.views.ThreeButtonView;

public class ProposalCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_creation);

        ((ThreeButtonView) findViewById(R.id.three_button_loan_amount)).setPromptText(getString(R.string.prompt_loan_amount));
        ((ThreeButtonView) findViewById(R.id.three_button_repayment_amount)).setPromptText(getString(R.string.prompt_loan_repayment_amount));
        ((ThreeButtonView) findViewById(R.id.three_button_repayment_date)).setPromptText(getString(R.string.prompt_loan_repayment_date));
        ((ThreeButtonView) findViewById(R.id.three_button_money_making)).setPromptText(getString(R.string.prompt_money_making));
        ((ThreeButtonView) findViewById(R.id.three_button_loan_purchase)).setPromptText(getString(R.string.prompt_loan_purchase));
        ((ThreeButtonView) findViewById(R.id.three_button_how_money_help)).setPromptText(getString(R.string.prompt_loan_how_help));
    }
}
