package com.mundoanimal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mundoanimal.AnimalContent.AnimalItem;

public class AnimalFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;

    public AnimalFragment() {
    }

   /* public static AnimalFragment newInstance(int columnCount) {
        AnimalFragment fragment = new AnimalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }*/

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigate(R.id.action_AnimalFragment_to_MainFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);
        // Set the adapter
        Bundle bundle = this.getArguments();
        String continente = bundle.getString("continente");
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
           // if (mColumnCount <= 1) {
            //    recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //} else {
                GridLayoutManager glm = new GridLayoutManager(context, mColumnCount);
                recyclerView.setLayoutManager(glm);
                recyclerView.addItemDecoration(new SpaceGrid(2, 20, true));
            //}
            AnimalContent animalContent = new AnimalContent();
            animalContent.fillListToShow(continente);
            recyclerView.setAdapter(new MyAnimalItemRecyclerViewAdapter(animalContent.ITEMS, mListener, continente));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(AnimalItem item);
    }

    private class SpaceGrid extends RecyclerView.ItemDecoration{
        private int mSpanCount;
        private int mSpacing;
        private boolean mIncludeEdge;
        private SpaceGrid(int spanCount, int spacing, boolean includeEdge){
            mSpanCount = spanCount;
            mSpacing = spacing;
            mIncludeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            int position = parent.getChildAdapterPosition(view);
            int column = position % mSpanCount;
            if(mIncludeEdge){
                outRect.left = mSpacing - column * mSpacing / mSpanCount;
                outRect.right =(column + 1) * mSpacing / mSpanCount;
                if(position < mSpanCount){
                    outRect.top = mSpacing;
                }
                outRect.bottom = mSpacing;
            }else{
                outRect.left = column * mSpacing / mSpanCount;
                outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
                if(position < mSpanCount){
                    outRect.top = mSpacing;
                }
            }
        }
    }
}
