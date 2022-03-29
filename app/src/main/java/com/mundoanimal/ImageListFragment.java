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
import com.mundoanimal.ImageListContent.ImageListItem;


public class ImageListFragment extends Fragment {

    private int mColumnCount = 2;
    private OnImageListFragmentInteractionListener mListener;
    private String continente;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        continente = bundle.getString("continente");
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Bundle bundle = new Bundle();
                bundle.putString("continente",continente);
                Navigation.findNavController(getView()).navigate(R.id.action_ImageListFragment_to_AnimalFragment, getArguments());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        // Set the adapter
        Bundle bundle = this.getArguments();
        String animal = bundle.getString("animal");
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            GridLayoutManager glm = new GridLayoutManager(context, mColumnCount);
            recyclerView.setLayoutManager(glm);
            recyclerView.addItemDecoration(new SpaceGrid(2, 20, true));
            ImageListContent imageListContent = new ImageListContent();
            imageListContent.fillListToShow(animal);
            recyclerView.setAdapter(new MyImageListItemRecyclerViewAdapter(imageListContent.ITEMS, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnImageListFragmentInteractionListener) {
            mListener = (OnImageListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnImageListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnImageListFragmentInteractionListener {
        void OnImageListFragmentInteraction(ImageListItem item);
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
