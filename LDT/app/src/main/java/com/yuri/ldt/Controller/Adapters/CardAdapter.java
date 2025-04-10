package com.yuri.ldt.Controller.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri.ldt.Controller.Helpers.AndroidHelper;
import com.yuri.ldt.Model.CardModel;
import com.yuri.ldt.R;
import com.yuri.ldt.View.CardEdit;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context context;
    private List<CardModel> cardModelList;

    public CardAdapter(Context context, List<CardModel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CardModel card = cardModelList.get(position);
        holder.nomeCard.setText(card.getTitulo());
        holder.dataCriacao.setText(card.getData());
        Log.d("TAG", "dados card: " + card.getTitulo() + " " + card.getData() + " " + card.getIdCard() + " " + card.getIdUsuario() + " " + card.getDescricao());

        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(context, CardEdit.class);
            intent.putExtra("idCard", card.getIdCard());
            intent.putExtra("idUsuario", card.getIdUsuario());
            intent.putExtra("titulo", card.getTitulo());
            intent.putExtra("data", card.getData());
            intent.putExtra("descricao", card.getDescricao());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeCard;
        private TextView dataCriacao;
        private ImageView delete;
        private RelativeLayout card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nomeCard = itemView.findViewById(R.id.nomeCard);
            this.dataCriacao = itemView.findViewById(R.id.dataCriacao);
            this.delete = itemView.findViewById(R.id.delete);
            this.card = itemView.findViewById(R.id.card);
        }
    }
}
