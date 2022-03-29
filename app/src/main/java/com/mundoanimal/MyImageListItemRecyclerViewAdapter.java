package com.mundoanimal;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mundoanimal.ImageListContent.ImageListItem;
import com.mundoanimal.ImageListFragment.OnImageListFragmentInteractionListener;

import java.util.List;

public class MyImageListItemRecyclerViewAdapter extends RecyclerView.Adapter<MyImageListItemRecyclerViewAdapter.ViewHolder> {
    private final List<ImageListContent.ImageListItem> mValues;
    private final OnImageListFragmentInteractionListener mListener;

    public MyImageListItemRecyclerViewAdapter(List<ImageListItem> items, OnImageListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setImageResource(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnImageListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final View mView;
        public final ImageView mContentView;
        public ImageListItem mItem;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (ImageView) view.findViewById(R.id.imageAnimal);
            cardView = view.findViewById(R.id.cardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.add(mItem.content, 121, 0 , "Guardar");
            menu.add(mItem.content, 122, 1 , "Compartir");
        }
    }
}
