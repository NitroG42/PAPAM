package fr.openium.pam;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ChuckNorrisAdapter extends RecyclerView.Adapter<ChuckNorrisAdapter.ChuckNorrisViewHolder> {
    private List<ChuckNorrisFact> facts;

    public ChuckNorrisAdapter(List<ChuckNorrisFact> facts) {
        this.facts = facts;
    }

    @NonNull
    @Override
    public ChuckNorrisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TextView textView = (TextView) layoutInflater.inflate(R.layout.item, parent, false);
//        TextView textView = new TextView(parent.getContext());
        textView.setBackgroundColor(new Random().nextInt());
//        textView.setPadding(32, 32, 32, 32);
        ChuckNorrisViewHolder chuckNorrisViewHolder = new ChuckNorrisViewHolder(textView);
        return chuckNorrisViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChuckNorrisViewHolder holder, int position) {
        //Required to display correctly the text as the api is not sending the content as json but as text/html
        String text = Html.fromHtml(facts.get(position).fact).toString();
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public static class ChuckNorrisViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ChuckNorrisViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
