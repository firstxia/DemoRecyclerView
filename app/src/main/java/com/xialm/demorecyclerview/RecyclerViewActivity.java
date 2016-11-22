package com.xialm.demorecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xialm.demorecyclerview.view.ToastUtils;

import static com.xialm.demorecyclerview.data.Cheeses.NAMES;

/**
 * RecyclerView的使用只需三步:
 * 1.初始化RecyclerView
 * 2.设置LayoutManager
 * 3.设置适配器
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        // 1.查找RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        // 2.设置LayoutManager
        // 为了替换ListView
        /*LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);  //设置,否则会报RecyclerView测量的空指针异常*/

        // 为了替换GridView
        /*GridLayoutManager glm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(glm);*/

        // 为了替换瀑布流
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        // 3. 设置适配器
        recyclerView.setAdapter(new MyAdapter());
//        recyclerView.setOnClickListener();  没有条目点击事件,只能在每个条目上添加点击事件
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public int getItemCount() {  //条目的数量
//            return Cheeses.NAMES.length;
            return NAMES.length;  //导包的时候,静态导入一个数组
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // a.创建一个view
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            // b.创建一个viewHolder,并返回
            return new MyViewHolder(view);
        }

        /**
         * 将内容设置到holder中的控件上
         */
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(NAMES[position]);
            // 因为RecyclerView没有条目点击事件,所以只能在这里为每个条目独自设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(RecyclerViewActivity.this, NAMES[position], Toast.LENGTH_SHORT).show();
                    ToastUtils.ShowToast(RecyclerViewActivity.this, NAMES[position]);
                }
            });
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

    }

}
