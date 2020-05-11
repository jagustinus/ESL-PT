package com.example.progtech.easierstudentlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.holder.MatkulHolder;
import com.example.progtech.easierstudentlife.model.EventData;
import com.example.progtech.easierstudentlife.model.MatkulData;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.eventViewHolder>{

    Context c;
    List<EventData> eventData;

    public EventAdapter(List<EventData> eventData) {
        this.eventData = eventData;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mata_kuliah_cardview,parent, false);

        return new eventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventViewHolder holder, int position) {

//        holder.namaMatkul.setText(matkulData.get(position).getName());
//        holder.dayTime.setText(matkulData.get(position).getDay()+" | "+" "+matkulData.get(position).getStart()+" - "+matkulData
//                .get(position).getEnd());
//        holder.semester.setText("at "+matkulData.get(position).getNamaRuang()+"("+matkulData.get(position).getRuang()+")");

    }

    public class eventViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook;
        TextView title, author, shordDesc, longDesc, pages, published;


        public eventViewHolder(@NonNull View itemView) {
            super(itemView);

//            imgBook = itemView.findViewById(R.id.book_img_book);
//            title = itemView.findViewById(R.id.book_item_title);
//            author = itemView.findViewById(R.id.book_item_author);
////            shordDesc = itemView.findViewById(R.id.book_item_p);
////            longDesc = itemView.findViewById(R.id.book_item_title);
//            published = itemView.findViewById(R.id.book_item_page_published);
//            pages = itemView.findViewById(R.id.book_item_page_count);

        }
    }

    @Override
    public int getItemCount() {
        return eventData.size();
    }
}
