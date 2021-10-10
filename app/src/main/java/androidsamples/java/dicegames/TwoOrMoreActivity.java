package androidsamples.java.dicegames;

import static androidsamples.java.dicegames.WalletActivity.KEY_BALANCE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class TwoOrMoreActivity extends AppCompatActivity {
  private static final String TAG = "TwoOrMoreActivity";
  static final String KEY_BALANCE_RETURN = "KEY_BALANCE_RETURN";

  private TextView mTxtBalance, mTxtDie1, mTxtDie2, mTxtDie3, mTxtDie4;
  private EditText mTxtWager;
  private RadioGroup mRadioGroup;
  private TwoOrMoreViewModel mTwoOrMoreVM;

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    // Buttons
    Button mButtonGo = findViewById(R.id.btn_go);
    Button mButtonBack = findViewById(R.id.btn_back);
    Button mButtonInfo = findViewById(R.id.btn_info);

    // Texts
    mTxtBalance = findViewById(R.id.txt_balance_twoormore);
    mTxtWager = findViewById(R.id.edit_wager);

    // Radio Groups
    mRadioGroup = findViewById(R.id.radioGroup);

    // Dice
    mTxtDie1 = findViewById(R.id.txt_die1);
    mTxtDie2 = findViewById(R.id.txt_die2);
    mTxtDie3 = findViewById(R.id.txt_die3);
    mTxtDie4 = findViewById(R.id.txt_die4);

    // View Model
    mTwoOrMoreVM = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    // Fetching values from intent
    int balance;
    if(savedInstanceState == null)
      balance = getIntent().getIntExtra(KEY_BALANCE, 0);

    // Fetching values from bundle in case of process death scenario
    else
      balance = savedInstanceState.getInt(KEY_BALANCE, 0);

    mTwoOrMoreVM.setBalance(balance);
    mTxtBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));

    // Checking if Dice Values exist
    if(mTwoOrMoreVM.diceValues().size() != 0)
      updateDice();

    mButtonGo.setOnClickListener(v -> {
      Context context = getApplicationContext();
      CharSequence text = "Please enter a valid wager amount.";
      int duration = Toast.LENGTH_SHORT;

      Toast toast = Toast.makeText(context, text, duration);

      try {
        mTwoOrMoreVM.setWager(Integer.parseInt(mTxtWager.getText().toString()));
      } catch (NumberFormatException e) {
        toast.show();
        return;
      }

      // Fetching game type value based on selection of user
      int checkedRadioId = mRadioGroup.getCheckedRadioButtonId();
      RadioButton checkedRadioButton = mRadioGroup.findViewById(checkedRadioId);
      String checkedRadioText = checkedRadioButton.getText().toString();

      checkGameType(checkedRadioText);
      addAllDie();

      GameResult gameResult = mTwoOrMoreVM.play();

      if (gameResult == GameResult.UNDECIDED)
        toast.show();

      mTxtBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));
      updateDice();
    });

    mButtonBack.setOnClickListener(this::returnToWallet);

    mButtonInfo.setOnClickListener(this::launchInfoActivity);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState)
  {
    super.onSaveInstanceState(outState);
    outState.putInt(KEY_BALANCE, mTwoOrMoreVM.balance());
  }

  /**
   * Updates UI by setting all dice with updated value
   */
  @SuppressLint("SetTextI18n")
  private void updateDice() {
    mTxtDie1.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(0)));
    mTxtDie2.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(1)));
    mTxtDie3.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(2)));
    mTxtDie4.setText(Integer.toString(mTwoOrMoreVM.diceValues().get(3)));
  }

  /**
   * Add required number of die to continue with the game
   */
  private void addAllDie()
  {
    for (int i = 0; i < 4; i++) {
      mTwoOrMoreVM.addDie(new Die6());
    }
  }

  /**
   *
   * Checks and assigns game type
   *
   * @param text Radio Button text in Two Or More Activity
   */
  private void checkGameType(String text)
  {
    switch (text) {
      case "2 Alike":
        mTwoOrMoreVM.setGameType(GameType.TWO_ALIKE);
        break;
      case "3 alike":
        mTwoOrMoreVM.setGameType(GameType.THREE_ALIKE);
        break;
      case "4 alike":
        mTwoOrMoreVM.setGameType(GameType.FOUR_ALIKE);
        break;
      default:
        mTwoOrMoreVM.setGameType(GameType.NONE);
        throw new IllegalStateException("Unexpected Radio Value: " + text);
    }
  }

  /**
   * Function to return an intent back to WalletActivity
   *
   * @param view the View for the activity
   */
  public void returnToWallet(View view)
  {
    Intent resultIntent = new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN, mTwoOrMoreVM.balance());
    setResult(RESULT_OK, resultIntent);
    finish();
  }

  /**
   *
   * Launches InfoActivity on clicking the Info button which displays the rules of the game
   *
   * @param view View for the activity
   */
  public void launchInfoActivity(View view)
  {
    Intent resultIntent = new Intent(this, InfoActivity.class);
    startActivity(resultIntent);
  }

  /**
   * This overridden function helps maintain UI state even when the device's physical back button is pressed
   */
  @Override
  public void onBackPressed() {
    Intent resultIntent = new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN, mTwoOrMoreVM.balance());
    setResult(RESULT_CANCELED, resultIntent);
    super.onBackPressed();
  }
}



