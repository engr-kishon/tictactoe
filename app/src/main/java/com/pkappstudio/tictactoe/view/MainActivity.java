package com.pkappstudio.tictactoe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pkappstudio.tictactoe.R;
import com.pkappstudio.tictactoe.adapter.GameBoardAdapter;
import com.pkappstudio.tictactoe.databinding.ActivityMainBinding;
import com.pkappstudio.tictactoe.model.Game;
import com.pkappstudio.tictactoe.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainActivityViewModel mViewModel;
    private GameBoardAdapter mGameBoardAdapter;
    private static final int ROW_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creating View Binding
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        // Creating View Model
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // Setting ViewModel
        mBinding.setViewmodel(mViewModel);
        // Setting Content
        setContentView(mBinding.getRoot());

        initGame();


        mGameBoardAdapter.setOnGameFinishListener(new GameBoardAdapter.OnGameFinishListener() {
            @Override
            public void onFinish(int winner) {
                if (winner == Game.PLAYER_SMILE.getValue()) {
                    String text = getEmojiByUnicode(Game.PLAYER_SMILE_UNICODE.getValue()) + "  Rocks";
                    mBinding.textStatus.setText(text);
                } else if (winner == Game.PLAYER_WINK.getValue()) {
                    String text = getEmojiByUnicode(Game.PLAYER_WINK_UNICODE.getValue()) + "  Rocks";
                    mBinding.textStatus.setText(text);
                } else {
                    String text = getEmojiByUnicode(Game.PLAYER_SMILE_UNICODE.getValue()) + "  Tie  "
                            + getEmojiByUnicode(Game.PLAYER_WINK_UNICODE.getValue());
                    mBinding.textStatus.setText(text);
                }

                mBinding.textStatus.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initGame() {
        mGameBoardAdapter = new GameBoardAdapter(ROW_COUNT);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setAdapter(mGameBoardAdapter);
        mBinding.textStatus.setText(getString(R.string.game_status));
    }

    public void onExitClick(View view) {
        finish();
    }

    public void onResetClick(View view) {
        initGame();
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}