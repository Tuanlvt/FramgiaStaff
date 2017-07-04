package com.framgia.framgiastaff.screen.main;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.framgia.framgiastaff.R;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.data.source.remote.EmployeeRepository;
import com.framgia.framgiastaff.databinding.ActivityMainBinding;
import com.framgia.framgiastaff.screen.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity {

    private MainContract.ViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private Paint mPaint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Employee> employeeList = new ArrayList<>();
        mAdapter = new MainAdapter(getApplicationContext(), employeeList);
        mViewModel = new MainViewModel(getApplicationContext(), mAdapter);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        MainContract.Presenter presenter = new MainPresenter(mViewModel, employeeRepository);
        mViewModel.setPresenter(presenter);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
        initViews();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        initSwipe();
    }

    private void initSwipe() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();

                        if (direction == ItemTouchHelper.LEFT) {
                            mAdapter.removeItem(position);
                        }
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {

                        Bitmap icon = null;
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                            View itemView = viewHolder.itemView;
                            float height = (float) itemView.getBottom() - (float) itemView.getTop();
                            float width = height / 3;

                            if (dX <= 0) {
                                mPaint.setColor(Color.TRANSPARENT);
                                RectF background = new RectF((float) itemView.getRight() + dX,
                                        (float) itemView.getTop(), (float) itemView.getRight(),
                                        (float) itemView.getBottom());
                                c.drawRect(background, mPaint);
                                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width,
                                        (float) itemView.getTop() + width,
                                        (float) itemView.getRight() - width,
                                        (float) itemView.getBottom() - width);
                            }
                        }
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                                isCurrentlyActive);
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
