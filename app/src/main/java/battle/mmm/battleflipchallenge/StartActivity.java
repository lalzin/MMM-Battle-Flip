package battle.mmm.battleflipchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onClickBtnPlay(View v)
    {
        Intent i = new Intent(StartActivity.this, MainBattleFlip.class);
        startActivity(i);
    }
}
