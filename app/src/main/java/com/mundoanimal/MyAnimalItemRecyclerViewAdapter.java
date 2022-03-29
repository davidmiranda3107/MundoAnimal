package com.mundoanimal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mundoanimal.AnimalContent.AnimalItem;
import com.mundoanimal.AnimalFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyAnimalItemRecyclerViewAdapter extends RecyclerView.Adapter<MyAnimalItemRecyclerViewAdapter.ViewHolder> {
    private final List<AnimalItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private String mContinente;

    public MyAnimalItemRecyclerViewAdapter(List<AnimalItem> items, OnListFragmentInteractionListener listener, String continente) {
        mValues = items;
        mListener = listener;
        mContinente = continente;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_animal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setImageResource(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Bundle bundle = new Bundle();
                    bundle.putString("continente", mContinente);
                    bundle.putString("animal", String.valueOf(holder.mIdView.getText()));
                    Navigation.findNavController(holder.mView).navigate(R.id.action_AnimalFragment_to_ImageListFragment, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public AnimalItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.txtAnimal);
            mContentView = (ImageView) view.findViewById(R.id.imageAnimal);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
