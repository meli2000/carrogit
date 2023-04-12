package com.mlc.carrogit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Producto> listaProductos;
    List<Producto> carroCompra;

    public AdaptadorProductos(Context context, TextView tvCantProductos, Button btnVerCarro, List<Producto> listaProductos, List<Producto> carroCompra) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;

        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_productos, null, false);
        return new AdaptadorProductos.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.imagen.setBackgroundResource(listaProductos.get(i).getImagen());
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNomProducto());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+listaProductos.get(i).getPrecio());




        productosViewHolder.cbCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                carroCompra.add(listaProductos.get(i));

            }
        });
        productosViewHolder.cbquitarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));


                // Eliminar el producto del carrito
                carroCompra.remove(i);

                // Actualizar la vista del carrito
                notifyDataSetChanged();

            }
        });

        btnVerCarro.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarroCompra.class);
            intent.putExtra("CarroCompras", (Serializable) carroCompra);
            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        ImageView imagen;
        Button cbCarro, cbquitarCarro;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            imagen=itemView.findViewById(R.id.imagen);
            cbCarro = itemView.findViewById(R.id.cbCarro);
            cbquitarCarro = itemView.findViewById(R.id.cbquitarCarro);
        }
    }
}
