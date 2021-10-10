package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  private static final String TAG = "WalletViewModel";
  private int mWinValue, mIncrement, mBalance;
  private int mDieValue = 0; // This is a locally declared member variable to maintain die value across orientation changes and process death scenario.
  private Die mDie;

  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    mBalance = 0;
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    return mBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    mBalance = amount;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    if(mDie == null)    // Checking if die exists
      throw new IllegalStateException("No die provided");

    mDie.roll();    // Rolling the die

    if(mDie.value() == mWinValue)
      mBalance += mIncrement;
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    if(mDie.value() != 0) {     // If die value is not 0, we update our local mDieValue, however, in case it is 0, we return our saved die value.
      mDieValue = mDie.value();
    }

    return mDieValue;
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    mIncrement = increment;
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    mWinValue = winValue;
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    mDie = d;
  }
}
