package com.example.dynamiclistshapeanimations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dynamiclistshapeanimations.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//on create method that invokes when app start
        List<ShapeItem> shapeItems = generateShapeItems(); //gernating shapes and convert this into the form of array
        RecyclerView recyclerView = findViewById(R.id.recyclerView);// picking the recycler view
        ShapeAdapter adapter = new ShapeAdapter(shapeItems); // putting my shape item into my custom adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //setting out the Linear layout for recycler view
        recyclerView.setAdapter(adapter);//setting adapter
    }



    //class shape that is used to store that means store animation type it's default colour height width
    //and animaion type etc
    public class ShapeItem {
        private int shapeColor;
        private int animationType;
        private String shapeType;  // Shape type, e.g., "circle," "rectangle," "triangle."
        private float width;       // Width of the shape.
        private float height;      // Height of the shape.

        public ShapeItem(int shapeColor, int animationType, String shapeType, float width, float height) {
            this.shapeColor = shapeColor;
            this.animationType = animationType;
            this.shapeType = shapeType;
            this.width = width;
            this.height = height;
        }

        public int getShapeColor() {
            return shapeColor;
        }

        public void setShapeColor(int shapeColor) {
            this.shapeColor = shapeColor;
        }

        public int getAnimationType() {
            return animationType;
        }

        public void setAnimationType(int animationType) {
            this.animationType = animationType;
        }

        public String getShapeType() {
            return shapeType;
        }

        public void setShapeType(String shapeType) {
            this.shapeType = shapeType;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }


    //gernating shape items by creating the objects of shape class and then converting  into the arrya
    private List<ShapeItem> generateShapeItems() {
        // Replace this with your logic to generate ShapeItem objects.
        // For example:
        List<ShapeItem> items = new ArrayList<>();
        items.add(new ShapeItem(Color.RED, 0, "circle", 100, 100));
        items.add(new ShapeItem(Color.BLUE, 1, "rectangle", 10, 80));
        items.add(new ShapeItem(Color.GREEN, 2, "triangle", 120, 120));



        items.add(new ShapeItem(Color.RED, 0, "circle", 100, 100));
        items.add(new ShapeItem(Color.BLUE, 1, "rectangle", 10, 80));
        items.add(new ShapeItem(Color.GREEN, 2, "triangle", 120, 120));


        items.add(new ShapeItem(Color.RED, 0, "circle", 100, 100));
        items.add(new ShapeItem(Color.BLUE, 1, "rectangle", 10, 80));
        items.add(new ShapeItem(Color.GREEN, 2, "triangle", 120, 120));



        // Add more items as needed.
        return items;
    }
    //over custom adapter that is doing main task for us binding view applying animation and applying

    //on click listener on each individual item of recycler view
    public static class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder> {

        private List<ShapeItem> shapeItems;
        private Context context;

        public ShapeAdapter(List<ShapeItem> shapeItems) {
            this.context = context;
            this.shapeItems = shapeItems;
        }

        @NonNull
        @Override
        public ShapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xml, parent, false);


            return new ShapeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ShapeViewHolder holder, int position) {
            ShapeItem item = shapeItems.get(position);

            holder.shapeView.setBackgroundColor(item.getShapeColor());

            holder.animationButton.setOnClickListener(view -> {
                int randomAnimationType = (int) (Math.random() * 3);

                switch (randomAnimationType) {
                    case 0:
                        applyFadeAnimation(holder.shapeView);
                        break;
                    case 1:
                        applyTranslateAnimation(holder.shapeView);
                        break;
                    case 2:
                        applyScaleAnimation(holder.shapeView);
                        break;
                }

                int randomColor = Color.argb(255, (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                holder.shapeView.setBackgroundColor(randomColor);
            });
        }

        private void applyFadeAnimation(View view) {
            ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
            fadeAnimator.setDuration(1000);
            fadeAnimator.start();
        }

        private void applyTranslateAnimation(View view) {
            ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, 200f);
            translateXAnimator.setDuration(1000);
            translateXAnimator.start();
        }

        private void applyScaleAnimation(View view) {
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 2f);
            scaleXAnimator.setDuration(1000);
            scaleXAnimator.start();
        }

        @Override
        public int getItemCount() {
            return shapeItems.size();
        }

        static class ShapeViewHolder extends RecyclerView.ViewHolder {
            View shapeView;
            Button animationButton;

            public ShapeViewHolder(View itemView) {
                super(itemView);
                shapeView = itemView.findViewById(R.id.shapeView);

                animationButton = itemView.findViewById(R.id.animationButton);
            }
        }
    }
}