package com.pkappstudio.tictactoe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pkappstudio.tictactoe.R;
import com.pkappstudio.tictactoe.databinding.BoardItemBinding;
import com.pkappstudio.tictactoe.model.Game;
import com.pkappstudio.tictactoe.model.TicTacToe;

/**
 * Created by kishon on 09,March,2022
 */
public class GameBoardAdapter extends RecyclerView.Adapter<GameBoardAdapter.GameBoardViewHolder> {

    private boolean flag = true;
    private OnGameFinishListener finishListener;

    private TicTacToe ticTacToe;

    private int x_index = -1;
    private int y_index = -1;

    public GameBoardAdapter(int cell) {
        this.ticTacToe = new TicTacToe(cell);
        this.ticTacToe.initGame();
    }

    @NonNull
    @Override
    public GameBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BoardItemBinding mBinding = BoardItemBinding.inflate(layoutInflater, parent, false);
        return new GameBoardViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameBoardViewHolder holder, int position) {
        if (position % ticTacToe.getCell() == 0) {
            x_index++;
            y_index = 0;
        } else {
            y_index++;
        }

        holder.mBinding.cellImage.setTag(x_index + "-" + y_index);

        holder.mBinding.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ticTacToe.isFinished()) {
                    return;
                }

                String tag = (String) holder.mBinding.cellImage.getTag();

                x_index = Integer.valueOf(tag.split("-")[0]);
                y_index = Integer.valueOf(tag.split("-")[1]);

                if (flag && ticTacToe.put(x_index, y_index, Game.PLAYER_WINK.getValue())) {
                    holder.mBinding.cellImage.setBackgroundResource(R.drawable.ic_o);
                    flag = !flag;

                    ticTacToe.match(x_index, y_index, Game.PLAYER_WINK.getValue());
                } else if (ticTacToe.put(x_index, y_index, Game.PLAYER_SMILE.getValue())) {
                    holder.mBinding.cellImage.setBackgroundResource(R.drawable.ic_x);
                    flag = !flag;

                    ticTacToe.match(x_index, y_index, Game.PLAYER_SMILE.getValue());
                }

                ticTacToe.isEmpty();

                if (ticTacToe.isFinished()) {
                    finishListener.onFinish(ticTacToe.getWinner());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (ticTacToe.getCell() * ticTacToe.getCell());
    }

    public class GameBoardViewHolder extends RecyclerView.ViewHolder {

        private BoardItemBinding mBinding;

        public GameBoardViewHolder(BoardItemBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnGameFinishListener {
        void onFinish(int winner);
    }

    public void setOnGameFinishListener(final OnGameFinishListener finishListener) {
        this.finishListener = finishListener;
    }
}
