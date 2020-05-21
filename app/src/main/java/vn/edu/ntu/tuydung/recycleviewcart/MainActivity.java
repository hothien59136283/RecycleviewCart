package vn.edu.ntu.tuydung.recycleviewcart;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.tuydung.controller.ICartController;
import vn.edu.ntu.tuydung.model.Product;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();

    }
    private void addView()
    {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));
        ICartController controller =(ICartController) getApplication();
        listProduct = controller.getListProduct();
        adapter =new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);

    }
    //lớp cài đặt cho việc hiển thị của một Product
    private  class ProductViewHolder extends RecyclerView.ViewHolder
    {   TextView txtname, txtprice,txtdesc;
        ImageView imgBtn;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=this.itemView.findViewById(R.id.txtname);
            txtprice=this.itemView.findViewById(R.id.txtprice);
            txtdesc=this.itemView.findViewById(R.id.txtdesc);
            imgBtn=this.itemView.findViewById(R.id.imgBtn);

        }
        public void bind(Product p)
        {
            txtname.setText(p.getName());
            txtprice.setText(new Integer(p.getPrice()).toString());
            txtdesc.setText(p.getDesc());
            imgBtn.setImageResource(R.drawable.ic_action_add_to_cart);
        }
    }
    //lớp Adapter kết nối recycleview và dữ liệu

    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }
        //Tạo một ViewHolder để hiển thị dữ liệu
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater =getLayoutInflater();
            //Chuyển layout đẫ thiết kế bằng xml thành đối tượng View
            View view=layoutInflater.inflate(R.layout.product_item,parent,
            false);
            return new ProductViewHolder(view);
        }
        //Kết nối một mục dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));

        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }



}
